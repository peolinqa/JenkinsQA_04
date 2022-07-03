package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenameFolderPage extends FolderPage {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameFolderInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement renameButton;

    public RenameFolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage renameFolder(String text) {
        renameFolderInput.clear();
        renameFolderInput.sendKeys(text);
        renameButton.click();

        return new FolderPage(getDriver());
    }

    public ErrorPage renameAndGoToErrorPage(String text) {
        renameFolderInput.clear();
        renameFolderInput.sendKeys(text);
        renameButton.click();

        return new ErrorPage(getDriver());
    }
}