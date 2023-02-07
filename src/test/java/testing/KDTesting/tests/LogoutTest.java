package testing.KDTesting.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testing.KDTesting.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.KDTesting.utils.DriverSingleton.quit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogoutTest {
    LoginPage lp;

    private final String USERpASSWORD = System.getenv("userPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        lp.getUrl(lp.LOGINuRL);
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
        assertFalse(lp.validateIsAdmin());
    }
}
