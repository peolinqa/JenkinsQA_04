package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class OrganizationFolderProjectPageSideMenuFrame extends BaseModel<OrganizationFolderProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    public OrganizationFolderProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<OrganizationFolderProjectPage, OrganizationFolderProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new OrganizationFolderProjectPage(getDriver()));
    }
}
