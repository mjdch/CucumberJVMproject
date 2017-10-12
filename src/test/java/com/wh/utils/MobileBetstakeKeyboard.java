package com.wh.utils;


import com.wh.pages.BasicPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobileBetstakeKeyboard extends BasicPage {

    //Return list of buttons
    private final By buttons_selector = By.xpath("//div[@class='betslip-numberpad__row']/button");
    private final By close_button = By.xpath("//button[contains(text(),'CLOSE')]");

    public MobileBetstakeKeyboard(WebDriver driver) {
        super(driver);
    }

    public void inputValueByKeyboard(String value) {
        List<WebElement> buttons = getClickableElements(buttons_selector);

        for (int i = 0; i < value.length(); i++) {

            for (WebElement e : buttons) {
                try{
                    if (value.charAt(i) == e.getText().charAt(0)) {
                        e.click();
                        break;
                    }
                }   catch (IndexOutOfBoundsException ex){
                    System.out.println("Button don't have any text inside");
                }

            }


        }
    getElement(close_button).click();
    }
}
