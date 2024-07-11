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

public class ProductsPage extends BasePage {

    private AppiumDriver driver;
    TestUtils testUtils = new TestUtils();

    @AndroidFindBys({
            @AndroidBy(accessibility = "test-Cart drop zone"),
            @AndroidBy (className = "android.widget.TextView")
    })
    private WebElement titleTxtView;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private WebElement productTitle;

    @AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private WebElement productPrice;

    public ProductsPage() {
        driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTitle() {
        return getAttribute(titleTxtView, "text", "Returning Title from " + titleTxtView);
    }

    public String getProductTitle() {
        return getAttribute(productTitle, "text", "Returning Product Title from " + productTitle);
    }

    public String getProductPrice() {
        return getAttribute(productPrice, "text", "Returning Product Price from " + productPrice);
    }

    public ProductDetailsPage clickProductTitle() {
        click(productTitle);
        return new ProductDetailsPage();
    }

}
