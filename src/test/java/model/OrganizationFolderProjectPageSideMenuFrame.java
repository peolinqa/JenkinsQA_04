package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class OrganizationFolderProjectPageSideMenuFrame extends BaseModel<OrganizationFolderProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public OrganizationFolderProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<OrganizationFolderProjectPage, OrganizationFolderProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new OrganizationFolderProjectPage(getDriver()));
    }

    public OrganizationFolderProjectPageSideMenuFrame clickMenuDelete() {
        menuDelete.click();

        return this;
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
