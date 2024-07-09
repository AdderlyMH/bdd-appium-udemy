package com.qa.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsStepdef {
    @Given("I'm logged in")
    public void iMLoggedIn() {
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {
    }

    @When("I click on product title {string}")
    public void iClickOnProductTitle(String title) {
    }

    @Then("the product details are: title {string}, price {string} and description {string}")
    public void theProductDetailsAreTitlePriceAndDescription(String title, String price, String description) {
    }
}
