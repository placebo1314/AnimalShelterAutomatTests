package testing.pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import testing.pages.utils.DriverSingleton;

import java.time.Duration;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    @FindBy(id = "logout")
    protected WebElement logoutButton;

    protected static String adminPassword = System.getenv("adminPassword");//"Admin";
    private String userName = System.getenv("userName");//"aa";
    private String userEmail = System.getenv("userEmail");// "aa@aa";
    private String userPassword = System.getenv("userPassword");//"aa";
    private String adminName = System.getenv("adminName");//"Admin";
    private String adminEmail = System.getenv("adminEmail");//"Admin@Admin";

    public BasePage(){
        this.driver = DriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();
    }

    public void getUrl(String url)
    {
        driver.get(url);
    }
    public void logout(){
        logoutButton.click();
    }
}