package testing.KDTesting.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegisterPage extends BasePage {

    @FindBy(id = "Email")
    public WebElement emailField;
    @FindBy(id = "Password")
    public WebElement passwordField;
    @FindBy(id = "Username")
    public WebElement userNameField;
    @FindBy(id = "submit-register")
    public WebElement submitRegister;
    @FindBy(id = "email-div")
    public WebElement emailDiv;

    public void RegistNewAccount(String name, String email, String password) {
        fillRegisterFields(name, email, password);
        submitRegister.click();

    }
    public void fillRegisterFields(String name, String email, String password) {
        fillUserNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
    }
    public void fillUserNameField(String name) {
        userNameField.sendKeys(name);
    }
    public void fillEmailField(String email) {
        emailField.sendKeys(email);
    }
    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public boolean validateErrorMessage(String message) {
        return emailDiv.getText().contains(message);
    }
}
