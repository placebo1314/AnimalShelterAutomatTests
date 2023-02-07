package testing.BDTesting;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterSteps {
    private final WebDriver driver;

    private final String LOGINuRL = "https://localhost:7241/Account/Login";
    private final String REGISTERuRL = "https://localhost:7241/Account/Register";
    private final String ADMINpAGEuRL = "https://localhost:7241/Admin/Admin";
    private final String testEmail = "aa@aa";
    private final String testName = "aa";
    private String name;
    private String validEmail;
    private final String password = "Guest";

    public RegisterSteps() {
        this.driver = testing.KDTesting.utils.DriverSingleton.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    private String generateRandomTestName(){
        return "TestUser" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }

    @Given("Open the main page")
    public void openMainPage() { driver.get(LOGINuRL); }
    @And("Go to the register page")
    public void openRegisterPage(){
        driver.get(REGISTERuRL);
    }

    @When("Enter valid email, username and password")
    public void fillRegisterFieldsWithValidData(){

        name = generateRandomTestName();
        validEmail = name + "@test.com";
        driver.findElement(By.id("Username")).sendKeys(name);
        driver.findElement(By.id("Email")).sendKeys(validEmail);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @And("Press register button")
    public void submitRegisterButton(){
        driver.findElement(By.id("submit-register")).click();
    }
    @Then("Register is successful")
    public void validateRegisterOk(){
        //Login
        driver.get(LOGINuRL);
        driver.findElement(By.id("email")).sendKeys(validEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();

        assertTrue(driver.findElement(By.id("logout")).getText().contains(name));
        //isAdmin
        driver.get(ADMINpAGEuRL);
        assertFalse(driver.findElements(By.id("addAnimal")).size() == 1);
        //logout
        driver.findElement(By.id("logout")).click();
    }
    @When("Enter invalid email and valid username, password")
    public void fillRegisterFieldsWithInvalidEmail(){
        name = generateRandomTestName();
        driver.findElement(By.id("Username")).sendKeys(name);
        driver.findElement(By.id("Email")).sendKeys(name);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @Then("Register is failed")
    public void invalidRegistration(){
        //Login
        driver.get(LOGINuRL);
        if(validEmail != null)
            driver.findElement(By.id("email")).sendKeys(validEmail);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();

        assertTrue(driver.findElements(By.id("logout")).size() == 0 );
    }
    @When("Enter an existing email, username and a password")
    public void registerWhenUserAlreadyExists(){
        name = testName;
        validEmail = testEmail;
        driver.findElement(By.id("Username")).sendKeys(testName);
        driver.findElement(By.id("Email")).sendKeys(testEmail);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @When("Enter email and username")
    public void registerWithoutPassword(){
        name = generateRandomTestName();
        validEmail = name + "@test.com";
        driver.findElement(By.id("Username")).sendKeys(name);
        driver.findElement(By.id("Email")).sendKeys(validEmail);
    }
    @When("Enter email and password")
    public void registerWithoutName(){
        name = null;
        validEmail = generateRandomTestName() + "@test.com";
        driver.findElement(By.id("Email")).sendKeys(validEmail);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @When("Enter username and password")
    public void registerWithoutEmail(){
        validEmail = null;
        driver.findElement(By.id("Username")).sendKeys(generateRandomTestName());
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @When("Enter an existing email, and valid username, password pair")
    public void registerWithExistEmail(){
        name = generateRandomTestName();
        driver.findElement(By.id("Username")).sendKeys(name);
        driver.findElement(By.id("Email")).sendKeys(testEmail);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
    @When("Enter an existing username, and valid email, password pair")
    public void registerWithExistUsername(){
        name = testName;
        validEmail = generateRandomTestName() + "@test.com";
        driver.findElement(By.id("Username")).sendKeys(testName);
        driver.findElement(By.id("Email")).sendKeys(validEmail);
        driver.findElement(By.id("Password")).sendKeys(password);
    }
}
