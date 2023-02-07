package testing.KDTesting.tests;

import org.junit.jupiter.api.*;
import testing.KDTesting.pages.AdminPage;
import testing.KDTesting.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.KDTesting.utils.DriverSingleton.quit;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminTest {
    LoginPage lp;
    AdminPage ap;

    private final String ADMINpASSWORD = System.getenv("adminPassword");
    private final String USERpASSWORD = System.getenv("userPassword");
    private final String BASEURL = "https://localhost:7241/Admin/Admin";

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        ap = new AdminPage();

        lp.getUrl(lp.LOGINuRL);
        lp.logIn(lp.ADMINeMAIL, ADMINpASSWORD);
    }
    @AfterAll
    public void tearDown(){
        quit();
    }

    @Test
    public void addAnimalSuccesful(){
        ap.addAnimal("TestName314", "Macska", "2022-10-18");

        assertTrue(ap.isTableContainsAnimal("TestName314"));
        assertTrue(ap.validateField("TestName314", "Species", "Macska"));

        ap.deleteAnimal("TestName314");
    }
    @Test
    public void addAnimalEmpty(){
        ap.addAnimalButton.click();
        ap.submitAnimalButton.click();

        lp.getUrl(BASEURL);
        assertFalse(ap.isTableContainsAnimal("TestName314"));
    }
    @Test
    public void addAnimalWithFutureDate(){
        ap.addAnimal("TestName314", "Macska", "2027-04-20");
        lp.getUrl(BASEURL);
        assertFalse(ap.isTableContainsAnimal("TestName314"));
    }
    @Test
    public void deleteAnimal(){
        ap.addAnimal("TestName314", "Macska", "2022-10-18");
        ap.deleteAnimal("TestName314");

        assertFalse(ap.isTableContainsAnimal("TestName314"));
    }
    @Test
    public void editAnimal(){
        ap.addAnimal("TestAnimal123", "Macska", "2022-10-18");
        ap.editAnimal("TestAnimal123", "Name", "EditedAnimal123");

        assertTrue(ap.isTableContainsAnimal("EditedAnimal123"));
        assertFalse(ap.isTableContainsAnimal("TestAnimal123"));

        ap.deleteAnimal("EditedAnimal123");
    }
    @Test
    public void addAdmin(){
        ap.changeAdminStatus(lp.USERnAME);

        assertTrue(ap.checkAdminIcon(lp.USERnAME));
        lp.logout();
        lp.logIn(lp.USEReMAIL, USERpASSWORD);
        assertTrue(lp.validateIsAdmin());

        lp.logout();
        lp.logIn(lp.ADMINeMAIL, ADMINpASSWORD);
        ap.changeAdminStatus(lp.USERnAME);
        lp.getUrl(BASEURL);
    }
}
