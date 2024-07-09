package com.qa.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepdef {
    @Given("I enter user name {string}")
    public void iEnterUserName(String username) {
    }

    @And("I enter the password {string}")
    public void iEnterThePassword(String password) {
    }

    @When("I login")
    public void iLogin() {
    }

    @Then("I get the error message {string}")
    public void iGetTheErrorMessage(String errorMsg) {
    }

    @Then("I get the page title {string}")
    public void iGetThePageTitle(String title) {
    }
}
