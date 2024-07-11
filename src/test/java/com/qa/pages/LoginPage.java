package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private AppiumDriver driver;
    TestUtils testUtils = new TestUtils();

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(id = "test-Username")
    private WebElement usernameTxtFld;

    @AndroidFindBy (accessibility = "test-Password")
    @iOSXCUITFindBy (id = "test-Password")
    private WebElement passwordTxtFld;

    @AndroidFindBy (accessibility = "test-LOGIN")
    @iOSXCUITFindBy (id = "test-LOGIN")
    private WebElement loginBtn;

    @AndroidFindBys({
            @AndroidBy(accessibility = "test-Error message"),
            @AndroidBy (className = "android.widget.TextView")
    })
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name='test-Error message']/child::XCUIElementTypeStaticText")
    private WebElement errorTxtView;

    public LoginPage() {
        driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameTxtFld, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordTxtFld, password);
        return this;
    }

    public String getErrorMessage() {
        return getAttribute(errorTxtView, "text", "Returning error message from " + errorTxtView);
    }

    public ProductsPage pressLoginBtn() {
        click(loginBtn);
        return new ProductsPage();
    }

    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return pressLoginBtn();
    }

}
