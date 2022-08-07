package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class FolderProjectPageSideMenuFrame extends BaseModel<FolderProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    @FindBy(id = "yui-gen1-button")
    protected WebElement yesButton;

    public FolderProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<FolderProjectPage, FolderProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new FolderProjectPage(getDriver()));
    }

    public FolderProjectPageSideMenuFrame clickMenuDelete() {
        menuDelete.click();

        return this;
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
