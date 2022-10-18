package testing.pages.tests;

import org.junit.jupiter.api.*;
import testing.pages.pages.AdminPage;
import testing.pages.pages.LoginPage;
import testing.pages.pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;
import static testing.pages.utils.Utility.getAlphaNumericString;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {
    LoginPage lp;
    RegisterPage rp;

    private String userName = "Test" + getAlphaNumericString(5);
    private String userEmail = userName + "@" + userName;
    private String userPassword = System.getenv("userPassword");//"aa";
    private String adminName = System.getenv("adminName");//"Admin";
    private String adminEmail = System.getenv("adminEmail");//"Admin@Admin";
    private String adminPassword = System.getenv("adminPassword");//"Admin";

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        rp = new RegisterPage();
    }
    @BeforeEach
    public void openRegisterPage() {
        lp.getUrl("https://localhost:7241/Account/Register");
    }
    @AfterAll
    public void tearDown(){
        quit();
    }

    @Test
    public void registerSuccessful(){
        rp.RegistNewAccount(userName, userEmail, userPassword);
        lp.logIn(userEmail, userPassword);

        assertTrue(lp.validateLoginUserName(userName));
        assertFalse(lp.validateIsAdmin());

        lp.logout();
    }
    @Test
    public void registerInvalidEmail(){
        rp.RegistNewAccount("IdontCare", "IdontCare", userPassword);

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWhenUserAlreadyExists(){
        rp.RegistNewAccount(adminName, adminEmail, adminPassword);

        assertTrue(rp.validateErrorMessage("Already taken."));
        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutPassword(){
        rp.fillUserNameField(userName+"2");
        rp.fillEmailField(userEmail+"2");
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutName(){
        rp.fillPasswordField(userPassword);
        rp.fillEmailField(userEmail+"2");
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutEmail(){
        rp.fillUserNameField(userName+"2");
        rp.fillPasswordField(userPassword);
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }

}
