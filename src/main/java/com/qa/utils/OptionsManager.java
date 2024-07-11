package com.qa.utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import java.io.File;
import java.util.Properties;

public class OptionsManager {

    TestUtils testUtils = new TestUtils();

    public BaseOptions<?> getOptions() {
        String appUrl = System.getProperty("user.dir") + File.separator +
                "src" + File.separator +
                "test" + File.separator +
                "resources" + File.separator +
                "app" + File.separator +
                "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";

        BaseOptions<?> options = null;

        GlobalParams params = new GlobalParams();
        Properties properties = new PropertyManager().getProperties();

        switch (params.getPlatformName()) {
            case "Android" -> {
                UiAutomator2Options androidOptions = new UiAutomator2Options();
                androidOptions.setAutomationName(properties.getProperty("androidAutomationName"));
                androidOptions.setAppPackage(properties.getProperty("androidAppPackage"));
                androidOptions.setAppActivity(properties.getProperty("androidAppActivity"));
                androidOptions.setSystemPort(Integer.parseInt(params.getSystemPort()));
                androidOptions.setChromedriverPort(Integer.parseInt(params.getChromeDriverPort()));
                androidOptions.setApp(appUrl);
                options = androidOptions;
            }
            case "iOS" -> {
                XCUITestOptions iOSOptions = new XCUITestOptions();
                iOSOptions.setAutomationName(properties.getProperty("iOSAutomationName"));
                iOSOptions.setBundleId(properties.getProperty("iOSBundleId"));
                iOSOptions.setWdaLocalPort(Integer.parseInt(params.getWdaLocalPort()));
                iOSOptions.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                iOSOptions.setApp(appUrl);
                options = iOSOptions;
            }
        }

        if (options != null) {
            options.setPlatformName(params.getPlatformName());
            options.setCapability("udid", params.getUdid());
            options.setCapability("deviceName", params.getDeviceName());
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + params.getPlatformName());
        }
        return options;
    }

}
