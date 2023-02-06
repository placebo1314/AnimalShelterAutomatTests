package testing.BDTesting;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testing.KDTesting.pages.BasePage;
import testing.KDTesting.pages.LoginPage;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutSteps extends BasePage {
    LoginPage lp = new LoginPage();

    @Given("Logged in as user")
    public void LoginAsUser(){
        getUrl(lp.LOGINuRL);
        lp.logIn(lp.USEReMAIL, System.getenv("userPassword"));
    }
    @Given("Logged in as Admin")
    public void LoginAsAdmin(){
        getUrl(lp.LOGINuRL);
        lp.logIn(lp.ADMINeMAIL, System.getenv("adminPassword"));
    }
    @When("Click on the LogOut button")
    public void LogOut(){
        lp.logout();
    }
    @Then("Logged out")
    public void checkAdminLogin(){
        assertTrue(lp.isLoginButton());
        assertFalse(lp.validateIsAdmin());
    }
}
