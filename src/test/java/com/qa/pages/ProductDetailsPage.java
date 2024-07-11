package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

    private AppiumDriver driver;
    TestUtils testUtils = new TestUtils();

    @AndroidFindBys({
            @AndroidBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    })
    private WebElement pdpTitle;

    @AndroidFindBys({
            @AndroidBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    })
    private WebElement pdpDescription;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement pdpBackToProductsBtn;

    public ProductDetailsPage() {
        driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public ProductDetailsPage scrollToFooterLogo() {
        scrollToElement();
        return this;
    }

    public String getPdpTitle() {
        return getAttribute(pdpTitle, "text", "Returning PDP Title from " + pdpTitle);
    }

    public String getPdpDescription() {
        return getAttribute(pdpDescription, "text", "Returning PDP Description from " + pdpDescription);
    }

    public ProductsPage clickPdpBackToProductsBtn() {
        click(pdpBackToProductsBtn);
        return new ProductsPage();
    }

}
