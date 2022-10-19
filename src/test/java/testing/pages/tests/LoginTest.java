package testing.pages.tests;

import org.junit.jupiter.api.*;
import testing.pages.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    LoginPage lp;
    //String CSV_PATH= "src/loginsData.csv";
    //private CSVReader csvReader;
    //String[] csvCell;

    private final String ADMINpASSWORD = System.getenv("adminPassword");
    private final String USERpASSWORD = System.getenv("userPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
    }
    @BeforeEach
    public void openLoginPage() {
        lp.getUrl("https://localhost:7241/Account/Login");
    }

    @AfterAll
    public void tearDown(){
        quit();
    }

    @Test
    public void loginUser(){
        lp.logIn(lp.USEReMAIL, USERpASSWORD);

        assertTrue(lp.validateLoginUserName(lp.USERnAME));
        assertFalse(lp.validateIsAdmin());

        lp.logout();
    }
    @Test
    public void loginAdminSuccessful(){
        lp.logIn(lp.ADMINeMAIL, ADMINpASSWORD);

        assertTrue(lp.validateLoginUserName(lp.ADMINnAME));
        assertTrue(lp.validateIsAdmin());

        lp.logout();
    }
    @Test
    public void loginUnregisteredWithValidEmail(){
        lp.logIn("IdontCare@IdontCare", "IdontCare");

        assertTrue(lp.validateErrorMessages("Invalid Information."));
        assertTrue(lp.isLoginButton());
    }
    @Test
    public void loginWithInvalidEmail(){
        lp.logIn("IdontCare", "IdontCare");
        assertTrue(lp.isLoginButton());
    }
    @Test
    public void loginWithWrongPassword(){
        lp.logIn(lp.ADMINeMAIL, "IdontCare");

        assertTrue(lp.validateErrorMessages("Invalid Information."));
        assertTrue(lp.isLoginButton());
        assertFalse(lp.validateIsAdmin());
    }


}