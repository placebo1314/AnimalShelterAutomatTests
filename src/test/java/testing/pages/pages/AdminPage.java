package testing.pages.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminPage extends BasePage{
    @FindBy(id = "addAnimal")
    public WebElement addAnimalButton;
    @FindBy(id = "editUsers")
    public WebElement editUsersButton;
    //AddAnimals:
    @FindBy(id = "Name")
    public WebElement nameField;
    @FindBy(id = "Species")
    public WebElement speciesField;
    @FindBy(id = "Inclusion")
    public WebElement InclusionField;
    @FindBy(id = "submit-animal")
    public WebElement submitAnimalButton;


    public void addAnimal(String name, String species, String date) {
        addAnimalButton.click();
        fillDate(date);
        fillName(name);
        selectSpecies(species);
        submitAnimalButton.click();
    }
    public void fillDate(String date) {
        InclusionField.sendKeys(date);
    }
    public void fillName(String name) {
        nameField.sendKeys(name);
    }
    public void selectSpecies(String species) {
        Select select = new Select(speciesField);
        select.selectByValue(species);
    }
    public boolean isTableContainsAnimal(String name) {
        return findTrByName(name) > 0;
    }
    private int findTrByName(String name) {
        List<WebElement> matrix = driver.findElements(By.xpath("//tbody/tr"));
        int result = 0;
        for (WebElement e : matrix)
        {
            result++;
            if (e.getText().contains(name))
                return result;
        }
        return 0;
    }

    public boolean validateField(String name, String header, String value) {
        int tr = findTrByName(name);
        int th = findThByName(header);
        if(tr>0 && th>0) {
            String path = "//tbody/tr[" + tr + "]/td[" + th + "]";
            return driver.findElement(By.xpath(path)).getText().contains(value);
        }
        return false;
    }
    public void deleteAnimal(String name) {
        String path = "//tbody/tr[" + findTrByName(name) + "]/td/a/i[@class='fa fa-trash']";
        driver.findElement(By.xpath(path)).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void editAnimal(String name, String editFieldId, String newValue) {
        String path = "//tbody/tr[" + findTrByName(name) + "]/td/a/i[@class='fi-page-edit']";
        driver.findElement(By.xpath(path)).click();
        WebElement target = driver.findElement(By.id(editFieldId));
        target.clear();
        target.sendKeys(newValue);
        submitAnimalButton.click();
    }
    public void changeAdminStatus(String userName) {
        editUsersButton.click();
        String path =  "//tbody/tr[" + findTrByName(userName) + "]/td[" + findThByName("Edit") + "]/a";
        driver.findElement(By.xpath(path)).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public boolean checkAdminIcon(String userName) {
        getUrl("https://localhost:7241/Admin/Administrators");
        String path =  "//tbody/tr[" + findTrByName(userName) + "]/td[" + findThByName("Edit") + "]/a/i";
        return driver.findElement(By.xpath(path)).getAttribute("class").contains("fa fa-trash");
    }
}
