package com.wh.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginPage and in fact the part on HomePage after expanding menua after clicking "Login" on top bar.
 */
public class LoginPage extends BasicPage {

    /**
     * Selectors for Login Box
     */
    private final By username_selector = By.xpath("//input[@name='username']");
    private final By password_selector = By.xpath("//input[@name='password']");
    private final By login_button = By.id("loginButton");

    /**
     * Selector after sucessfully login
     */

    private final By deposit_selector = By.id("depositHeaderButtonLink");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method for inputing username & password on Login menu. Assertion of successfully login by checking of WebElement with "Deposit" text.
     *
     * @param username username for login panel
     * @param password password for login panel
     */
    public void logIn(String username, String password) {
        driver.findElement(username_selector).sendKeys(username);
        driver.findElement(password_selector).sendKeys(password);
        driver.findElement(login_button).click();

        /**
         *  Waiting to show "Deposit" text in given selector {@link #deposit_selector}, after login there is
         *  some reloading before showing text "Deposit", implicit waits couldn't resolve this issue. In fact
         *  if there is more such a situation this could be standalone method in {@link com.wh.pages.BasicPage}
         */
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.textToBePresentInElementLocated(deposit_selector, "Deposit"));

        WebElement deposit = driver.findElement(deposit_selector);
        Assert.assertEquals("Deposit", deposit.getText());
    }

}
