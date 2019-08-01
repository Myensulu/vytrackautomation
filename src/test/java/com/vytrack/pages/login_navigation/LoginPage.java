package com.vytrack.pages.login_navigation;

// For WebElements and methods

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    //we are
    private WebDriver driver = Driver.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));

    @FindBy(id = "prependedInput")
    public WebElement userNameElement;

    @FindBy(name = "_password")
    public WebElement passwordElement;

    @FindBy(id="_submit")
    public WebElement loginButtonElement;

    @FindBy(css = ".custom-checkbox__icon")
    public WebElement rememberMeElement;

    @FindBy(partialLinkText = "Forgot your password?")
    public WebElement forgotPasswordElement;

    @FindBy(tagName = "h2")
    public WebElement titleElement;

    @FindBy(css = "[class='alert alert-error'] > div")
    public WebElement errorMessageElement;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
    public void login(String username, String password){
        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
    }
    public String getErrorMessage(){
        return errorMessageElement.getText();
    }
    public void clickRememberMe(){
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeElement));
        if(!rememberMeElement.isSelected()){
            rememberMeElement.click();
        }
    }
}