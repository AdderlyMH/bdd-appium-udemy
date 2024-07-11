package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

// mvn test -D"platformName"="Android" -D"udid"="emulator-5554" -D"deviceName"="pixel_8_pro" -D"systemPort"="10002" -D"chromeDriverPort"="1102"
// mvn test -D"cucumber.plugin"="html:target/android_pixel_8_pro/report.html" -D"platformName"="Android" -D"deviceName"="pixel_8_pro" -D"udid"="emulator-5554" -D"systemPort"="10002" -D"chromeDriverPort"="11002"
// mvn test -D"cucumber.plugin"="html:target/android_2201117TL/report.html" -D"platformName"="Android" -D"deviceName"="2201117TL" -D"udid"="2dcaa2ce" -D"systemPort"="10003" -D"chromeDriverPort"="11003"

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber/cucumber.html"},
//        tags = "@Login",
        snippets = CAMELCASE,
        monochrome = true,
        features = "src/test/resources",
        glue = "com/qa")
public class MyRunnerTest {

    @BeforeClass
    public static void initialize() {
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());

        new ServerManager().startServer();
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void quit() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager = new ServerManager();
        if (serverManager.getLocalService() != null)
            serverManager.getLocalService().stop();
    }

}
