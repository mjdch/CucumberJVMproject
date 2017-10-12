package com.wh.pages;


import com.wh.utils.MobileBetstakeKeyboard;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BetSlipPage extends BasicPage {

    /**
     * Page for BetSlip Currently only for one event single bet.
     */

    private final By betTeam_selector = By.xpath("//span[@class='betslip-selection__name betslip-selection__name--single']/label");
    private final By odds_selector = By.xpath("//span[@class='betslip-selection__name betslip-selection__name--single']/selection-price");
    private final By stake_selector = By.xpath("//input[starts-with(@id, 'stake-input')]");
    private final By return_selector = By.xpath("//span[starts-with(@id,'estimated-returns')]/span");
    private final By mobile_betslip_button = By.id("betslip-btn-toolbar");

    public BetSlipPage(WebDriver driver) {
        super(driver);

        if(isMobile()){
            getElement(mobile_betslip_button).click();
        }

    }


    /**
     * Placing bet method then assertion if inputed stake value is properly inputed in BetSlip event stake.
     */

    public void placeBetStake(double stake) {


        if(isMobile()){
            getElement(stake_selector).click();
            MobileBetstakeKeyboard btk = new MobileBetstakeKeyboard(driver);
            btk.inputValueByKeyboard(String.valueOf(stake));

        } else {
            getElement(stake_selector).sendKeys(String.valueOf(stake));
        }


        String stake_in_betslip = driver.findElement(stake_selector).getAttribute("data-ng-init");
        //Nasty sleep for mobile devices, need to recalculate
        sleep(1, TimeUnit.SECONDS);
        //Need to compare double values instead of String equalsTo, because on mobile device. This stake field automaticaly add 0 on end If it is only one digit after dot.
        Assert.assertEquals(stake, Double.parseDouble(stake_in_betslip),0);

    }

    /**
     * Calculate return according to odd and stake provided by user with proposed return as
     * string which will be used in assertion. Need to add "Locale.ENGLISH" for formatter to use
     * dot instead of comma.
     */

    public void calculateReturn() {
        String odd_string = driver.findElement(odds_selector).getText();
        String stake_string = driver.findElement(stake_selector).getAttribute("data-ng-init");


        double odd = parseAndCalculateOdds(odd_string);
        double stake = Float.parseFloat(stake_string);

        double calculate = stake * odd;

        String calculated_return = String.format(Locale.ENGLISH, "%.2f", calculate);

        String proposed_return = driver.findElement(return_selector).getText();

        Assert.assertEquals(calculated_return, proposed_return);
    }

    /**
     * Parse fraction odd representation and return decimal odd. If odd is "EVS" then return 2.
     *
     * @param odd String from WebElement
     * @return odd represented in decimal format
     */
    private double parseAndCalculateOdds(String odd) {

        if (odd.equals("EVS")) {
            return 2;
        }

        String[] splitted = odd.split("/");
        double x = Double.parseDouble(splitted[0]);
        double y = Double.parseDouble(splitted[1]);

        double calc_value = (x / y) + 1;

        return calc_value;
    }

}
