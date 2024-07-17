package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class LoginStepdef {
    @Given("I enter user name {string}")
    public void iEnterUserName(String username) {
        new LoginPage().enterUsername(username);
    }

    @And("I enter the password {string}")
    public void iEnterThePassword(String password) {
        new LoginPage().enterPassword(password);
    }

    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
    }

    @Then("I get the error message {string}")
    public void iGetTheErrorMessage(String errorMsg) {
        assertEquals(new LoginPage().getErrorMessage(), errorMsg, "Error message is not correct");
    }

    @Then("I get the page title {string}")
    public void iGetThePageTitle(String title) {
        assertEquals(new ProductsPage().getTitle(), title, "Title is not correct");
    }
}
