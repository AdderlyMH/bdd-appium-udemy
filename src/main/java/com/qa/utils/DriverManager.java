package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private ServerManager serverManager = new ServerManager();
    private OptionsManager optionsManager = new OptionsManager();
    TestUtils testUtils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        DriverManager.driver.set(driver);
    }

    public void initializeDriver() {
        if (driver.get() == null) {
            try {
                testUtils.logger().info("Initializing Appium driver...");
                AppiumDriver driverInstance = createDriverInstance();
                DriverManager.driver.set(driverInstance);
                testUtils.logger().info("Driver initialized.");
            } catch (Exception e) {
                testUtils.logger().fatal("Driver initialization failed. ABORT! : {}", e.getMessage());
                throw e;
            }
        }
    }

    private AppiumDriver createDriverInstance() {
        GlobalParams params = new GlobalParams();
        testUtils.logger().info("params.getPlatformName() = {}", params.getPlatformName());
        return switch (params.getPlatformName()) {
            case "Android" -> new AndroidDriver(serverManager.getLocalService().getUrl(), optionsManager.getOptions());
            case "iOS" -> new IOSDriver(serverManager.getLocalService().getUrl(), optionsManager.getOptions());
            default -> null;
        };
    }

}
