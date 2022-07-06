package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenameOrganizationFolderPage extends BasePage {

    @FindBy(name = "newName")
    private WebElement nameInput;

    @FindBy(css = "#yui-gen1-button")
    private WebElement renameButton;


    public RenameOrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderProjectPage setNewProjectNameAndGoToProject(String newProjectName) {
        nameInput.clear();
        nameInput.sendKeys(newProjectName);
        renameButton.click();

        return new OrganizationFolderProjectPage(getDriver());
    }
}
