package com.qa.utils;

public class GlobalParams {

    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> udid = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<>();
    private static ThreadLocal<String> wdaLocalPort = new ThreadLocal<>();
    private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<>();

    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName) {
        GlobalParams.platformName.set(platformName);
    }

    public String getUdid() {
        return udid.get();
    }

    public void setUdid(String udid) {
        GlobalParams.udid.set(udid);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName) {
        GlobalParams.deviceName.set(deviceName);
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort) {
        GlobalParams.systemPort.set(systemPort);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort) {
        GlobalParams.chromeDriverPort.set(chromeDriverPort);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort) {
        GlobalParams.wdaLocalPort.set(wdaLocalPort);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort) {
        GlobalParams.webkitDebugProxyPort.set(webkitDebugProxyPort);
    }


    public void initializeGlobalParams() {
        GlobalParams params = new GlobalParams();
        params.setPlatformName(System.getProperty("platformName", "Android"));
        params.setUdid(System.getProperty("udid", "emulator-5554"));
        params.setDeviceName(System.getProperty("deviceName", "pixel_8_pro"));

        switch (params.getPlatformName()) {
            case "Android" -> {
                params.setSystemPort(System.getProperty("systemPort", "10000"));
                params.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
            }
            case "iOS" -> {
                params.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                params.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
            }
            default -> throw new RuntimeException("Invalid platform name");
        }
    }

}
