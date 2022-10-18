package testing.pages.tests;

import org.junit.jupiter.api.*;
import testing.pages.pages.AdminPage;
import testing.pages.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminTest {
    LoginPage lp;
    AdminPage ap;
    private String adminEmail = System.getenv("adminEmail");
    private String adminPassword = System.getenv("adminPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        ap = new AdminPage();

        lp.getUrl("https://localhost:7241/Account/Login");
        lp.logIn(adminEmail, adminPassword);
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
    public void addAnimalWithFutureDate(){
        ap.addAnimal("TestName314", "Macska", "2027-04-20");
        lp.getUrl("https://localhost:7241/Admin/Admin");
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
}
