package com.wh.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FootballEventPage extends BasicPage {

    // Fulltime (90 minutes) results buttons selectors
    private final By fulltime_result_home = By.xpath("//header[contains(h2,'90 Minutes')]/following-sibling::div//div[@class='btmarket__selection'][1]/button");
    private final By fulltime_result_draw = By.xpath("//header[contains(h2,'90 Minutes')]/following-sibling::div//div[@class='btmarket__selection'][2]/button");
    private final By fulltime_result_away = By.xpath("//header[contains(h2,'90 Minutes')]/following-sibling::div//div[@class='btmarket__selection'][3]/button");

    private final By event_name = By.xpath("//h1[@class='header-panel__title']");


    private String home_team_name;
    private String away_team_name;

    public FootballEventPage(WebDriver driver) {
        super(driver);
        home_team_name = extractHomeTeamName();
        away_team_name = extractAwayTeamName();
    }


    public void betOnHomeTeam() {
        getClickableElement(fulltime_result_home).click();
    }

    public void betOnDraw() {
        getClickableElement(fulltime_result_draw).click();
    }

    public void betOnAwayTeam() {
        getClickableElement(fulltime_result_away).click();
    }


    public String getEventName() {
        return getElement(event_name).getText();
    }

    public String extractHomeTeamName() {
        String[] splitted = getEventName().split(" vs ");
        return splitted[0];
    }

    public String extractAwayTeamName() {
        String[] splitted = getEventName().split(" vs ");
        return splitted[1];
    }

}
