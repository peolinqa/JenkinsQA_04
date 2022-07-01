package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderConfigPage extends BasePage {

    @FindBy(linkText = "Rename")
    private WebElement renameFolder;

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameFolderInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement renameButton;

    @FindBy(css = "h1")
    private WebElement folderName;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage clickRenameFolder() {
        renameFolder.click();

        return new FolderConfigPage(getDriver());
    }

    public FolderConfigPage renameFolder(String text) {
        renameFolderInput.clear();
        renameFolderInput.sendKeys(text);
        renameButton.click();

        return this;
    }

    public String getFolderName() {
        return folderName.getText();
    }
}
