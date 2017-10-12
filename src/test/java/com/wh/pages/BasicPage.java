package com.wh.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasicPage {

    /**
     * BasicPage from which all of pages will inherit. It could contain besides WebDriver commonly used methods
     * for interactions with WebElements (i.e getClickableElement() with explicit wait )
     */

    protected WebDriver driver;

    public BasicPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method for waiting untill overlay element while loding dissapear, I need it to use after clickin match event
     * in Football Competitions Page sometimes it take a long time to load causing Selenium throwing
     * expecptions.
     */
    protected void waitUntilLoadingEnd(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("wh-global-overlay")));
    }


    /**
     * Method for returning WebElement after it is clickable, in my case resolving issue in {@link HomePage#clickOnFootballCompetitions()}
     * All caused by overlay loading element. I've decied to add this as WebElement returning method instead of using
     * {@link #waitUntilLoadingEnd()} everywhere
     */


    protected WebElement getClickableElement(By by){

            WebElement element = null;
            WebDriverWait wait = new WebDriverWait(driver, 10);

            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(by));
                return element;
            } catch (WebDriverException e) {
                System.out.printf("Can't find element by: %s", by.toString());
            }
            return element;

    }

    /**
     * The same purpose as above, but returning List of WebElements
     */
    protected List<WebElement> getClickableElements(By by) {
        List<WebElement> elements = null;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            elements = driver.findElements(by);
        } catch (Exception e) {
            System.out.println("Couldn't found elements");
        }
        return elements;
    }

    /**
     * Returning WebElement upon waiting to locate it, in fact in every case fetching up WebElement
     * this should be use to prevent NPE
     * @param by By locator of WebElement
     * @return WebElement upon waiting.
     */

    protected WebElement getElement(By by){

        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;

    }

    /**
     * Execute JavaScript on website
     * @param script javascript expression
     */
    protected void executeJavaScript(String script){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);

    }

    /**
        Scroll view to element be in center of window. Stolen from: https://stackoverflow.com/questions/20167544/selenium-scroll-element-into-center-of-view
     */
    protected void scrollElementToCenter(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }


    /**
     * Check if isMobile System variable is set, we will pass it by Maven CLI
     * @return
     */
    protected boolean isMobile(){
        String value = System.getProperty("device");
        if(value != null && !value.equals("")) {
            return true;
        }
        return false;
    }

}

