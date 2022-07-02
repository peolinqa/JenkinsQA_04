package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderPage extends BasePage {

    @FindBy(linkText = "Rename")
    private WebElement renameFolder;

    @FindBy(css = "h1")
    private WebElement folderName;

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public String getFolderName() {
        return folderName.getText();
    }

    public RenameFolderPage clickRenameFolder() {
        renameFolder.click();

        return new RenameFolderPage(getDriver());
    }
}