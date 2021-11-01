package com.miro.whiteboard.runner;

import com.miro.whiteboard.utils.ReportUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.miro.whiteboard.steps",
        tags = "@allTests",
        plugin = {"pretty", "json:build/funding.json", "html:target/cucumber-html-report.html"}
)

public class RunMiroTest {
    @AfterClass
    public static void publishReport() {
        new ReportUtil().generteReport();
    }
}
