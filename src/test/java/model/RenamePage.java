package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RenamePage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement renameButton;

    @FindBy(xpath = "//h1[text() = 'Error']")
    private List<WebElement> errorText;

    public RenamePage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickRenameAndGoToFolder() {
        renameButton.click();

        return new FolderPage(getDriver());
    }

    public RenamePage setNewProjectName(String name) {
        renameInput.clear();
        renameInput.sendKeys(name);

        return this;
    }

    public FreestylePage clickRenameAndGoToFreestyle() {
        renameButton.click();

        return new FreestylePage(getDriver());
    }

    public OrganizationFolderProjectPage clickRenameAndGoToOrganizationFolder() {
        renameButton.click();

        return new OrganizationFolderProjectPage(getDriver());
    }
}