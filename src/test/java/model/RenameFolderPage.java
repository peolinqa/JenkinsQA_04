package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RenameFolderPage extends FolderPage {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameFolderInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement renameButton;

    @FindBy(xpath = "//h1[text() = 'Error']")
    private List<WebElement> errorText;

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