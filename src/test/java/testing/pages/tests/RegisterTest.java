package testing.pages.tests;

import org.junit.jupiter.api.*;
import testing.pages.pages.LoginPage;
import testing.pages.pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {
    LoginPage lp;
    RegisterPage rp;

    private final String USERpASSWORD = System.getenv("userPassword");//"aa";
    private final String ADMINpASSWORD = System.getenv("adminPassword");//"Admin";

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
    public void registerUser(){
        rp.RegistNewAccount(lp.USERnAME, lp.USEReMAIL, USERpASSWORD);
        lp.logIn(lp.USEReMAIL, USERpASSWORD);

        assertTrue(lp.validateLoginUserName(lp.USERnAME));
        assertFalse(lp.validateIsAdmin());

        lp.logout();
    }
    @Test
    public void registerInvalidEmail(){
        rp.RegistNewAccount("IdontCare", "IdontCare", USERpASSWORD);

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWhenUserAlreadyExists(){
        rp.RegistNewAccount(lp.ADMINnAME, lp.ADMINeMAIL, ADMINpASSWORD);

        assertTrue(rp.validateErrorMessage("Already taken."));
        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutPassword(){
        rp.fillUserNameField(rp.USERnAME +"2");
        rp.fillEmailField(rp.USEReMAIL +"2");
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutName(){
        rp.fillPasswordField(USERpASSWORD);
        rp.fillEmailField(rp.USEReMAIL +"2");
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }
    @Test
    public void registerWithoutEmail(){
        rp.fillUserNameField(rp.USERnAME +"2");
        rp.fillPasswordField(USERpASSWORD);
        rp.submitRegister.click();

        assertTrue(lp.isLoginButton());
    }

}
