package com.example.pages;

import com.example.drivers.WebDriverContainer;
import org.openqa.selenium.By;

public final class MainGooglePage {

    private static final String PAGE_URL = "http://www.google.com";

    private final By inputSelector = By.id("lst-ib");
    private final By searchButtonSelector = By.name("btnK");
    private final By luckyButtonSelector = By.xpath("//div/center/*[contains(@aria-label, 'Мне повезёт!')]");

    public MainGooglePage executeSearch(final String input)
    {
        WebDriverContainer.INSTANCE.getDriver().findElement(inputSelector).sendKeys(input);
        WebDriverContainer.INSTANCE.getDriver().findElement(searchButtonSelector).click();

        return this;
    }

    public MainGooglePage open()
    {
        WebDriverContainer.INSTANCE.getDriver().get(PAGE_URL);
        return this;
    }

}
