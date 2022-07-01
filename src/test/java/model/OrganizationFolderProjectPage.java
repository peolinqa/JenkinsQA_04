package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganizationFolderProjectPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement projectName;

    @FindBy(xpath = "//span[contains(text(),'Rename')]")
    private WebElement renameButton;

    @FindBy(xpath = "//span[contains(text(),'Delete Organization Folder')]")
    private WebElement deleteButton;

    public OrganizationFolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {

        return projectName.getText().trim();
    }

    public RenameOrganizationFolderPage renameOrganizationFolder() {
        renameButton.click();

        return new RenameOrganizationFolderPage(getDriver());
    }

    public DeleteOrganizationFolderPage deleteOrganizationFolder() {
        deleteButton.click();

        return new DeleteOrganizationFolderPage(getDriver());
    }
}
