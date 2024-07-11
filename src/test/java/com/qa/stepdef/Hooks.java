package com.qa.stepdef;

import com.qa.utils.*;
import io.appium.java_client.InteractsWithApps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;

public class Hooks {

    GlobalParams params = new GlobalParams();
    DriverManager driverManager = new DriverManager();
    PropertyManager propertyManager = new PropertyManager();
    VideoManager videoManager = new VideoManager();

    @Before
    public void initialize() {

        videoManager.startRecording();

        switch (params.getPlatformName()) {
            case "Android" -> ((InteractsWithApps) driverManager.getDriver()).
                    activateApp(propertyManager.getProperties().
                            getProperty("androidAppPackage"));
            case "iOS" -> ((InteractsWithApps) driverManager.getDriver()).
                    activateApp(propertyManager.getProperties().
                            getProperty("iOSBundleId"));
        }

    }

    @After
    public void quit(Scenario scenario) {

        // Attach screenshot to default cucumber report
        if (scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        switch (params.getPlatformName()) {
            case "Android" -> ((InteractsWithApps) driverManager.getDriver()).
                    terminateApp(propertyManager.getProperties().
                            getProperty("androidAppPackage"));
            case "iOS" -> ((InteractsWithApps) driverManager.getDriver()).
                    terminateApp(propertyManager.getProperties().
                            getProperty("iOSBundleId"));
        }

        videoManager.stopRecording(scenario.getName().replaceAll("\\s","_"));

    }

}
