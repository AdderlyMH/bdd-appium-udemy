package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BasePage {

    private AppiumDriver driver;
    TestUtils testUtils = new TestUtils();

    public BasePage() {
        driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.TIME_OUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.TIME_OUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitAndClick(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element, String attribute, String logMsg) {
        waitForVisibility(element);
        String textAtt = attribute;
        if (new GlobalParams().getPlatformName().equals("iOS") && attribute.equals("text"))
            textAtt = "label";

        testUtils.logger().info("Get {} attribute: {}", textAtt, logMsg);
//        ExtentReport.getTest().log(Status.INFO, "Get " + textAtt + " attribute: " + logMsg);

        return element.getAttribute(textAtt);
    }

    public WebElement scrollToElement() {
        return driver.findElement(AppiumBy.
                androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))." +
                        "scrollIntoView(new UiSelector().text(\"Terms of Service | Privacy Policy\"));"));
    }

    public void iOSScrollToElement() {
        RemoteWebElement element = (RemoteWebElement) driver.findElement(By.className("XCUIElementTypeTable"));
        String elementId = element.getId();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("elementId", elementId);
//        scrollObject.put("direction", "down");
//        scrollObject.put("predicateString", "label == 'ADD TO CART'");
        scrollObject.put("name", "test-ADD TO CART");
        driver.executeScript("mobile:scroll", scrollObject);
    }

    public void activateApp() {
        switch (new GlobalParams().getPlatformName()) {
            case "Android" -> ((InteractsWithApps) driver).activateApp(new Properties().getProperty("androidAppPackage"));
            case "iOS" -> ((InteractsWithApps) driver).activateApp(new Properties().getProperty("iOSBundleId"));
        }
    }

    public void terminateApp() {
        switch (new GlobalParams().getPlatformName()) {
            case "Android" -> ((InteractsWithApps) driver).terminateApp(new Properties().getProperty("androidAppPackage"));
            case "iOS" -> ((InteractsWithApps) driver).terminateApp(new Properties().getProperty("iOSBundleId"));
        }
    }

}
