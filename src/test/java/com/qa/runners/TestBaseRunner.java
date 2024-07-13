package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

public class TestBaseRunner {

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getTestNGCucumberRunner() {
        return testNGCucumberRunner.get();
    }

    private static void setTestNGCucumberRunner(TestNGCucumberRunner testNGCucumberRunner) {
        TestBaseRunner.testNGCucumberRunner.set(testNGCucumberRunner);
    }

    @Parameters({"platformName", "udid", "deviceName", "systemPort",
            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context,
                           String platformName, String udid, String deviceName,
                           @Optional("androidOnly") String systemPort,
                           @Optional("androidOnly") String chromeDriverPort,
                           @Optional("iOSOnly") String wdaLocalPort,
                           @Optional("iOSOnly") String webkitDebugProxyPort) {

        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        GlobalParams params = new GlobalParams();
        params.setPlatformName(platformName);
        params.setUdid(udid);
        params.setDeviceName(deviceName);

        switch (platformName) {
            case "Android" -> {
                params.setSystemPort(systemPort);
                params.setChromeDriverPort(chromeDriverPort);
            }
            case "iOS" -> {
                params.setWdaLocalPort(wdaLocalPort);
                params.setWebkitDebugProxyPort(webkitDebugProxyPort);
            }
        }

        new ServerManager().startServer();
        new DriverManager().initializeDriver();

        XmlTest currentXmlTest = context.getCurrentXmlTest();
        CucumberPropertiesProvider properties = currentXmlTest::getParameter;

        setTestNGCucumberRunner(new TestNGCucumberRunner(this.getClass(), properties));

    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        getTestNGCucumberRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getTestNGCucumberRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {

        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager = new ServerManager();
        if (serverManager.getLocalService() != null)
            serverManager.getLocalService().stop();

        if (getTestNGCucumberRunner() != null)
            getTestNGCucumberRunner().finish();

    }

}
