package com.qa.utils;

import com.google.common.io.ByteStreams;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.time.Duration;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> localService = new ThreadLocal<>();
    TestUtils testUtils = new TestUtils();

    public AppiumDriverLocalService getLocalService() {
        return localService.get();
    }

    public void startServer() {
        try {
            AppiumDriverLocalService localService = getBuiltLocalService();
            localService.start();

            if (!localService.isRunning()) {
                testUtils.logger().fatal("Appium Service not started");
                throw new AppiumServerHasNotBeenStartedLocallyException("Appium Service not started. ABORT!");
            }

            localService.clearOutPutStreams();
            ServerManager.localService.set(localService);
            testUtils.logger().info("Appium Service started");
        } catch (Exception e) {
            testUtils.logger().fatal("Appium Service failed");
            throw e;
        }
    }

    private AppiumDriverLocalService getBuiltLocalService() {
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
                usingAnyFreePort().
                withArgument(GeneralServerFlag.SESSION_OVERRIDE).
                withArgument(GeneralServerFlag.LOCAL_TIMEZONE).
                withTimeout(Duration.ofSeconds(90)).
                withLogOutput(ByteStreams.nullOutputStream()).
                withLogFile(new File(params.getPlatformName() + "_" +
                        params.getDeviceName() + File.separator +
                        "service.log")));
    }

}
