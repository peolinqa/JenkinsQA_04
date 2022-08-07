package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class FolderProjectPageSideMenuFrame extends BaseModel<FolderProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    public FolderProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<FolderProjectPage, FolderProjectPageSideMenuFrame> clickRenameAndGoToRenamePage() {
        renameButton.click();

        return new RenamePage<>(getDriver(), new FolderProjectPage(getDriver()));
    }
}
