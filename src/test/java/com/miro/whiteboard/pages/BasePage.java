package com.miro.whiteboard.pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public static Scenario scenario;

    public static WebDriver driver;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }
}
