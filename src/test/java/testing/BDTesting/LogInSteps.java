package testing.BDTesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import testing.KDTesting.pages.LoginPage;
import testing.KDTesting.pages.BasePage;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInSteps extends BasePage {
    LoginPage lp = new LoginPage();

    @Given("Login page is opened")
    public void openLoginPage(){
        lp.getUrl(lp.LOGINuRL);
    }

    @When("User enter email: {string} and password: {string}")
    public void fillLoginFieldsAsUser(String email, String password){
        if (Objects.equals(email, "valid"))
            lp.fillEmail(lp.USEReMAIL);
        else if(Objects.equals(email, "invalid"))
            lp.fillEmail("invalid@invalid");

        if(Objects.equals(password, "valid"))
            lp.fillPassword(System.getenv("userPassword"));
        else if(Objects.equals(password, "invalid"))
            lp.fillPassword("invalid");
    }
    @And("Press login")
    public void submitLoginButton(){
            lp.SubmitLoginButton();
        }

    @Then("If the {string} and the {string} are valid, user logged in, else not logged in")
    public void CheckUserLogin(String userEmail, String password){
        if (Objects.equals(userEmail, "valid") && Objects.equals(password, "valid")){
            assertTrue(lp.validateLoginUserName(lp.USERnAME));
            assertFalse(lp.isLoginButton());
            lp.logout();
        }
        else {
            if (!Objects.equals(userEmail, "empty") && !Objects.equals(password, "empty"))
                assertTrue(lp.validateErrorMessages("Invalid Information."));
            assertTrue(lp.isLoginButton());
        }
        assertFalse(lp.validateIsAdmin());
    }

    @When("An Admin enter email: {string} and password: {string}")
    public void enterPassword(String email, String password){
        if (Objects.equals(email, "valid"))
            lp.fillEmail(lp.ADMINeMAIL);
        else if(Objects.equals(email, "invalid"))
            lp.fillEmail("invalid@invalid");

        if(Objects.equals(password, "valid"))
            lp.fillPassword(System.getenv("adminPassword"));
        else if(Objects.equals(password, "invalid"))
            lp.fillPassword("invalid");
    }
    @Then("If the {string} and the {string} are valid, Admin logged in, else not logged in")
    public void checkAdminLogin(String adminEmail, String password){
        if (Objects.equals(adminEmail, "valid") && Objects.equals(password, "valid")){
            assertTrue(lp.validateLoginUserName(lp.ADMINnAME));
            assertFalse(lp.isLoginButton());
            assertTrue(lp.validateIsAdmin());
            lp.logout();
        }
        else{
            if (!Objects.equals(adminEmail, "empty") && !Objects.equals(password, "empty"))
                assertTrue(lp.validateErrorMessages("Invalid Information."));
            assertTrue(lp.isLoginButton());
        }
    }
    }