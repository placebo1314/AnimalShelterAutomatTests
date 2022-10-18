package testing.pages.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.pages.pages.AdminPage;
import testing.pages.pages.LoginPage;
import testing.pages.pages.RegisterPage;

import static testing.pages.utils.DriverSingleton.quit;

public class AdminTest {
    LoginPage lp;
    AdminPage ap;
    private String adminEmail = System.getenv("adminEmail");
    private String adminPassword = System.getenv("adminPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        ap = new AdminPage();

        lp.logIn(adminEmail, adminPassword);
    }
    @AfterAll
    public void tearDown(){
        quit();
    }

    @Test
    public void addAnimal(){
        

        lp.logout();
    }
}
