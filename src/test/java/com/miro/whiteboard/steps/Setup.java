package com.miro.whiteboard.steps;

import com.miro.whiteboard.DriverManager;
import com.miro.whiteboard.pages.BasePage;
import com.miro.whiteboard.pages.HomePage;
import com.miro.whiteboard.utils.CommonUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Properties;

public class Setup {

    private String propertiesFileName;
    private Properties props = null;
    public static String baseUrl;

    @Before
    public void setup(Scenario scenario) {
        BasePage.scenario = scenario;
        BasePage.driver = DriverManager.getDriver();
        String profile = System.getProperty("profile") != null ? System.getProperty("profile") : "e1";
        propertiesFileName = "test-config-" + profile + ".properties";
        if (props == null) {
            props = new CommonUtil().readPropertiesFile(propertiesFileName);
        }
        baseUrl = props.getProperty("testscript.baseurl");
        System.out.println("baseUrl = " + baseUrl);
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            CommonUtil.takeScreenShot(scenario);
        }
        HomePage.driver.quit();
    }
}
