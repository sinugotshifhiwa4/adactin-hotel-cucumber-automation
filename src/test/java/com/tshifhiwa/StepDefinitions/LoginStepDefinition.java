package com.tshifhiwa.StepDefinitions;

import com.tshifhiwa.configuration.environment.EnvironmentResolver;
import com.tshifhiwa.pages.context.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

    private  final TestContext context;

    public  LoginStepDefinition(TestContext context){
        this.context = context;
    }

    @Given("the user navigates to the Login page")
    public void theUserNavigatesToTheLoginPage() {
        context.loginPage.navigateToPortal(EnvironmentResolver.getBaseUrl());
    }

    @Then("the page title should display {string}")
    public void thePageTitleShouldDisplay(String expectedTitle) {
        context.loginPage.verifyPageTitleIsDisplayedCorrectly(expectedTitle);
    }

    @When("the user enters a valid username and password")
    public void theUserEntersAValidUsernameAndPassword() {
        context.loginPage.fillUsername(EnvironmentResolver.getUsername());
        context.loginPage.fillPassword(EnvironmentResolver.getPassword());
    }

    @And("the user clicks the Login button")
    public void theUserClicksTheLoginButton() {
        context.loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the search hotel page")
    public void theUserShouldBeRedirectedToTheSearchHotelPage() {
        context.loginPage.verifyLoginErrorMessageIsNotVisible();
    }
}
