package com.wh;

import com.wh.pages.BetSlipPage;
import com.wh.pages.FootballEventPage;
import com.wh.pages.HomePage;
import com.wh.pages.LoginPage;
import com.wh.utils.WebdriverSetup;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class PlaceBetSteps {

    /**
     * Test Step definitions class for given scenario, from file placeBet.feature.
     */

    private WebDriver driver;
    private HomePage hp;
    private LoginPage lp;
    private FootballEventPage fep;
    private BetSlipPage bsp;

    @Given("^The William Hill homepage$")
    public void the_William_Hill_homepage(){
        hp = new HomePage(driver);
        hp.openHomePage();

    }

    @And("^User login sucessfully$")
    public void user_login_successfully(){
        //AccountReader from file, need to fillup with valid username & password for WH Website, I don't want to share my test account in public repository
        AccountReader ar = new AccountReader();

        lp = hp.clickLoginOnHP();
        lp.logIn(ar.getUsername(),ar.getPassword());
    }

    @And("^User navigate to Premiership football event$")
    public void user_navigate_to_premiership_football_event(){
        fep = hp.clickOnFootballCompetitions().clickOnRandomPremierLeagueEvent();
    }

    //Simple Regex for positive float number
    @When("^User place (\\d+.\\d+) GBP bet on Home team$")
    public void user_place_bet_on_home_team(double bet){
        fep.betOnHomeTeam();
        bsp = new BetSlipPage(driver);
        bsp.placeBetStake(bet);
    }

    @Then("^Odds and returns should be offered$")
    public void odds_and_returns_should_be_offered(){
        bsp.calculateReturn();
    }


    /**
     * Initialization of WebDriver before executing test steps. If passed variable "device" by maven
     * ChromeDriver will emulate this device. It must be on the emulation list in Chrome
     */
    @Before
    public void setUpDriver(){
        driver = WebdriverSetup.setUpWebdriver();

    }

    /**
     * Closing WebDriver after tests.
     */

    @After
    public void tearUpBrowser(){
        driver.quit();
    }

}
