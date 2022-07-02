package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderConfigPage extends BasePage {

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage saveConfigAndGoToFolderPage() {
        saveButton.click();

        return new FolderPage(getDriver());
    }
}
