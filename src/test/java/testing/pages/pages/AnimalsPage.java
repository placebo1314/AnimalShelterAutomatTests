package testing.pages.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AnimalsPage extends BasePage {
    @FindBy(id = "sortBynclusionDate")
    public WebElement inclusionDate;
    @FindBy(id = "sortByName")
    public WebElement name;
    @FindBy(id = "species")
    public WebElement speciesField;


    public boolean hasFirstRow(String titleName, String value) {
        String path = "//tbody/tr[1]/td[" + findThByName(titleName) + "]";
        return driver.findElement(By.xpath(path)).getText().contains(value);
    }
    public void selectSpecies(String species) {
        Select select = new Select(speciesField);
        select.selectByValue(species);
    }
    public boolean hasOnlySpecies(String species) {
        for (WebElement e : driver.findElements(By.xpath("//tbody/tr/td[" + findThByName("Fajta") + "]")))
        {
            if (!e.getText().contains(species))
                return false;
        }
        return true;
    }
}
