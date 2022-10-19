package testing.pages.tests;

import org.junit.jupiter.api.*;
import testing.pages.pages.AdminPage;
import testing.pages.pages.AnimalsPage;
import testing.pages.pages.LoginPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnimalsPageTest {
    LoginPage lp;
    AnimalsPage anp;
    AdminPage adp;
    private final String ADMINpASSWORD = System.getenv("adminPassword");

    @BeforeAll
    public void setUp() {
        lp = new LoginPage();
        anp = new AnimalsPage();
        adp = new AdminPage();

        lp.logIn(lp.ADMINeMAIL, ADMINpASSWORD);
        adp.addAnimal("ZZZTestAnimal", "Macska", "0001-01-01");
        adp.addAnimal("000TestAnimal", "Kutya", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lp.logout();
        lp.getUrl("https://localhost:7241/Home/Animals");
    }
    @AfterAll
    public void tearDown(){
        lp.logIn(lp.ADMINeMAIL, ADMINpASSWORD);
        adp.deleteAnimal("000TestAnimal");
        adp.deleteAnimal("ZZZTestAnimal");
        quit();
    }
    @Test
    @Order(1)
    public void orderByDateAsc(){
        assertTrue(anp.hasFirstRow("Gazdikeresés kezdete", "0001-01-01" ));
        assertTrue(anp.hasFirstRow("Név", "ZZZTestAnimal" ));
    }
    @Test
    @Order(2)
    public void orderByDateDesc(){
        anp.inclusionDate.click();

        assertFalse(anp.hasFirstRow("Név", "ZZZTestAnimal " ));
    }
    @Test
    @Order(3)
    public void orderByNameAsc(){
        anp.name.click();

        assertTrue(anp.hasFirstRow("Név", "000TestAnimal" ));
    }
    @Test
    @Order(4)
    public void orderByNameDesc(){
        anp.name.click();

        assertTrue(anp.hasFirstRow("Név", "ZZZTestAnimal" ));
    }
    @Test
    @Order(5)
    public void filterBySpecies(){
        anp.selectSpecies("Kutya");

        assertTrue(anp.hasOnlySpecies("Kutya"));
    }
}
