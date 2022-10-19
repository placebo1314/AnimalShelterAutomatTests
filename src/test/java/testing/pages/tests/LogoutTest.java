package testing.pages.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testing.pages.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogoutTest {
    LoginPage lp;

    private final String USERpASSWORD = System.getenv("userPassword");

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
        lp.logIn(lp.USEReMAIL, USERpASSWORD);
        lp.logout();

        assertTrue(lp.isLoginButton());
    }
}
