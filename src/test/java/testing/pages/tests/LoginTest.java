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

    private String adminPassword = System.getenv("adminPassword");
    private String userName = System.getenv("userName");
    private String userEmail = System.getenv("userEmail");
    private String userPassword = System.getenv("userPassword");
    private String adminName = System.getenv("adminName");
    private String adminEmail = System.getenv("adminEmail");

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
        lp.logIn(userEmail, userPassword);

        assertTrue(lp.validateLoginUserName(userName));
        assertFalse(lp.validateIsAdmin());

        lp.logout();
    }
    @Test
    public void loginAdminSuccessful(){
        lp.logIn(adminEmail, adminPassword);

        assertTrue(lp.validateLoginUserName(adminName));
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
        lp.logIn(adminEmail, "IdontCare");

        assertTrue(lp.validateErrorMessages("Invalid Information."));
        assertTrue(lp.isLoginButton());
        assertFalse(lp.validateIsAdmin());
    }


}