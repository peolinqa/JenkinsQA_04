package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderPage extends BasePage {

    @FindBy(linkText = "Rename")
    private WebElement renameFolder;

    @FindBy(css = "h1")
    private WebElement folderName;

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement folderDescription;

    @FindBy(xpath = "//span[text()='Delete Folder']")
    private WebElement deleteFolder;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public FolderPage(WebDriver driver) {

        super(driver);
    }

    public String getFolderName() {

        return folderName.getText();
    }

    public String getFolderDescription() {

        return folderDescription.getText();
    }

    public RenameFolderPage clickRenameFolder() {
        renameFolder.click();

        return new RenameFolderPage(getDriver());
    }

    public FolderPage clickDeleteFolder() {
        deleteFolder.click();

        return this;
    }

    public HomePage clickYesButton() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}