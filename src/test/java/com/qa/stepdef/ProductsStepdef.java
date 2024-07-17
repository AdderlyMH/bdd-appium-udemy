package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class ProductsStepdef {
    @Given("I'm logged in")
    public void iMLoggedIn() {
        new LoginPage().enterUsername("standard_user");
        new LoginPage().enterPassword("secret_sauce");
        new LoginPage().pressLoginBtn();
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {
        boolean checkTitle = new ProductsPage().getProductTitle().equalsIgnoreCase(title);
        boolean checkPrice = new ProductsPage().getProductPrice().equalsIgnoreCase(price);
        assertTrue(checkTitle && checkPrice, "No match. checkTitle: " + checkTitle +
                ", checkPrice: " + checkPrice);
    }

    @When("I click on product title {string}")
    public void iClickOnProductTitle(String title) {
        new ProductsPage().clickProductTitle();
    }

    @Then("the product details are: title {string}, price {string} and description {string}")
    public void theProductDetailsAreTitlePriceAndDescription(String title, String price, String description) {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        boolean pdpTitleCheck = productDetailsPage.getPdpTitle().equalsIgnoreCase(title);
        boolean pdpDescriptionCheck = productDetailsPage.getPdpDescription().equalsIgnoreCase(description);
        productDetailsPage.scrollToFooterLogo();
        assertTrue(pdpTitleCheck && pdpDescriptionCheck);
    }
}
