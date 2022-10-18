package testing.pages.pages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testing.pages.utils.DriverSingleton.quit;

public class AdminPage extends BasePage{
    @FindBy(id = "addAdmin")
    public WebElement addAdminButton;
    @FindBy(id = "addAnimal")
    public WebElement addAnimalButton;


}
