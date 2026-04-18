package com.tshifhiwa.pages.adactinHotel;

import com.tshifhiwa.pages.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By companyLogo = By.cssSelector("img[alt='AdactIn Group']");
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login");
    private  final By loginErrorMessage = By.className("auth_error");

    public void fillUsername(String username) {
        fillElement(usernameInput, username, "Username Input");
    }

    public void fillPassword(String password) {
        fillElement(passwordInput, password, "Password Input");
    }

    public void clickLoginButton() {
        clickElement(loginButton, "Login Button");
    }

    public boolean verifyPageTitleIsDisplayedCorrectly(String expectedTitle){
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    public  void verifyLoginErrorMessageIsVisible(){
        waitForElementVisible(loginErrorMessage, "Login Error Message");
    }

    public  void verifyLoginErrorMessageIsNotVisible(){
        waitForElementNotVisible(loginErrorMessage, "Login Error Message");
    }
}
