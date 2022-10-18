package testing.pages.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testing.pages.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogoutTest {
    LoginPage lp;

    String userEmail = System.getenv("userEmail");
    String userPassword = System.getenv("userPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        lp.getUrl("https://localhost:7241/Account/Login");
    }
    @AfterAll
    public void tearDown(){
        quit();
    }

    @Test
    public void logoutSuccessful(){
        lp.logIn(userEmail, userPassword);
        lp.logout();

        assertTrue(lp.isLoginButton());
    }
}
