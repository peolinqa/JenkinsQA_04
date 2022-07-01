package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemConfigPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Save')]")
    private WebElement saveButton;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;


    public ItemConfigPage(WebDriver driver) {
        super(driver);
    }

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new ProjectPage(getDriver());
    }

    public ItemConfigPage setDescription(String text) {
        descriptionTextarea.sendKeys(text);

        return this;
    }
}
