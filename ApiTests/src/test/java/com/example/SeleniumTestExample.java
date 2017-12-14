package com.example;

import com.example.pages.MainGooglePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestExample {

    @Test
    public void test() throws Exception {
        MainGooglePage page = new MainGooglePage();
        page.open().executeSearch("Hello world");

        Thread.sleep(5_000);
    }

}
