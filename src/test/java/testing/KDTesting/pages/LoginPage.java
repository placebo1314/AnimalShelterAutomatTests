package testing.KDTesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

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
        fillEmail(email);
        fillPassword(password);
    }
    public void fillEmail(String email){
        emailField.sendKeys(email);
    }
    public void fillPassword(String password){
        passwordField.sendKeys(password);
    }
    public void logIn(String email, String password){
        getUrl(LOGINuRL);
        fillEmailAndPassword(email, password);
        SubmitLoginButton();
    }
    public void SubmitLoginButton(){
        submitLogin.click();
    }
    public boolean validateLoginUserName(String user){
        return logoutButton.getText().contains(user);
    }
    public boolean validateIsAdmin(){
        getUrl("https://localhost:7241/Admin/Admin");
        return driver.findElements(By.id("addAnimal")).size() == 1 ;
    }
    public boolean validateErrorMessages(String message) {
        return emailDiv.getText().contains(message) && passwordDiv.getText().contains(message);
    }
    public boolean isLoginButton(){
        return driver.findElements(By.id("login")).size() == 1;
    }
}