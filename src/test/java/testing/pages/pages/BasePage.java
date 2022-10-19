package testing.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import testing.pages.utils.DriverSingleton;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    @FindBy(id = "logout")
    protected WebElement logoutButton;

    public String USERnAME = System.getenv("userName");//"aa";
    public String USEReMAIL = System.getenv("userEmail");// "aa@aa";
    public String ADMINnAME = System.getenv("adminName");//"Admin";
    public String ADMINeMAIL = System.getenv("adminEmail");//"Admin@Admin";

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
    public int findThByName(String name) {
        List<WebElement> matrix = driver.findElements(By.xpath("//thead/tr/th"));
        int result = 0;
        for (WebElement e : matrix)
        {
            result++;
            if (e.getText().contains(name))
                return result;
        }
        return 0;
    }
}