package com.miro.whiteboard.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The report util configuration required for generating the customized report
 */
public class ReportUtil {
    public void generteReport() {
        List<String> jsonPath = new ArrayList<>();
        jsonPath.add("build/funding.json");
        Configuration config = new Configuration(new File("build"), "funding");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPath, config);
        reportBuilder.generateReports();
    }
}
