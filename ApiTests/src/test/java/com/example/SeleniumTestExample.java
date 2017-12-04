package com.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestExample {

    @Test
    public void test() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/alex/Tools/web_drivers/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("http://google.com");

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium automation");

        WebElement searchButton = driver.findElement(By.cssSelector("center > input"));
        searchButton.click();

        Thread.sleep(5_000);

        driver.quit();
    }

}
