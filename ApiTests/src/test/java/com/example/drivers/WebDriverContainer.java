package com.example.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public enum WebDriverContainer {

    INSTANCE;

    private WebDriver driver;

    WebDriverContainer() {
        System.setProperty("webdriver.chrome.driver", "/Users/alex/Tools/web_drivers/chromedriver");

        this.driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
