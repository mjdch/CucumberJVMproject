package com.wh.utils;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebdriverSetup {

    private static void setUpWebDriverVariablesBasedOnOS() {
        if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) {
            File f = new File("WebDriver/Linux/chromedriver");
            System.setProperty("webdriver.chromne.driver", f.getAbsolutePath());
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            File f = new File("WebDriver/Windows/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());

        }
    }

    public static WebDriver setUpWebdriver(){
        setUpWebDriverVariablesBasedOnOS();

        WebDriver driver;

        String device = System.getProperty("device");

        if(device != null && !device.equals("")) {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", device);

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return new ChromeDriver(capabilities);

        } else {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver;
        }
    }

}
