package testing.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class LoginPage extends BasePage {

    @FindBy(id = "login")
    public WebElement loginButton;
    @FindBy(id = "submit-login")
    public WebElement submitLogin;
    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(id = "email-div")
    public WebElement emailDiv;
    @FindBy(id = "password-div")
    public WebElement passwordDiv;

    public void fillEmailAndPassword(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }
    public void logIn(String email, String password){
        fillEmailAndPassword(email, password);
        submitLogin.click();
    }
    public boolean validateLoginUserName(String user){
        if(logoutButton.getText().contains(user))
            return true;
        return false;
    }
    public boolean validateIsAdmin(){
        getUrl("https://localhost:7241/Admin/Admin");
        return driver.findElements(By.id("addAnimal")).size() == 1 ;
    }

    public boolean validateErrorMessages(String message) {
        return emailDiv.getText().contains(message) && passwordDiv.getText().contains(message);
    }

    public boolean isLoginButton(){
        return driver.findElements(By.id("login")).size() == 1 ;
    }
}