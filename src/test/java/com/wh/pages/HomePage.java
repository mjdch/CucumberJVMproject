package com.wh.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class HomePage extends BasicPage {
    /**
     * Page Object class for HomePage of William Hill bettings.
     */
    private final String WilliamHill_Title = "Online Betting from William Hill - The Home of Betting";
    private final String WilliamHill_Homepage = "http://sports.williamhill.com/betting/en-gb";
    private final By login_button_on_hp = By.xpath("//*[@class='account-tab__text -login']");

    //Football selector from list, first for expanding dropdown, second for clicking on competitions
    private final By football_list = By.id("nav-football");
    private final By footbal_competitions = By.id("nav-football-competitions");
    private final By premier_league_events = By.xpath("//li[@data-content-type='competition' and contains(a,'English Premier League')]/following-sibling::li//div[@class='event']//a[@class='btmarket__name btmarket__name--featured']");


    private final By mobile_football_competiions = By.xpath("//span[@class='contextual-nav__title' and contains(text(),'Competitions')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open William Hill homepage. Assertions by homepage title from {@link #WilliamHill_Title }
     * @return HomePage
     */
    public HomePage openHomePage(){
        driver.get(WilliamHill_Homepage);
        System.out.println(isMobile());
        Assert.assertEquals(WilliamHill_Title,driver.getTitle());
        return this;
    }

    /**
     * Method for clicking on Login button on HomePage, after clicking Login Menu appears
     * @return LoginPage
     */
    public LoginPage clickLoginOnHP(){
        driver.findElement(login_button_on_hp).click();
        return new LoginPage(driver);
    }

    /**
     * Method for clicking on "football" in menu and then clicking on "Competitions", Mobile differs from desktop
     * sothere is IF
     * @return FootballCompetitionsPage
     */
    public HomePage clickOnFootballCompetitions(){
        driver.findElement(football_list).click();

        if(isMobile()){
            getClickableElement(mobile_football_competiions).click();
        } else{
            getClickableElement(footbal_competitions).click();
        }

        return this;
    }

    /**
     * Method for clicking Random Premier League Event from 10 Most popular one, in fact It could be moved to other class
     * reflecting Football Competition League page but I Put it in this class for simplicity.
     * Assertions If correct event is opened by checking event name on link from list and on Event Page
     */

    public FootballEventPage clickOnRandomPremierLeagueEvent(){
        List<WebElement> events = getClickableElements(premier_league_events);
        Random r = new Random();
        WebElement event = events.get(r.nextInt(10));

        //Regex for two representation of Event Name, Desktop & Mobile
        String[] event_teams = event.getText().split(" v |\\n");

        //Resolves issues with overlaying bottom menu on element which should be clickable
//        scrollToElementWithJS(event);
        scrollElementToCenter(event);

        event.click();

        FootballEventPage fep =  new FootballEventPage(driver);

        Assert.assertTrue(Arrays.equals(event_teams,fep.getEventName().split(" vs ")));

        return fep;
    }


}
