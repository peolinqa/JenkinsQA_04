package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderConfigPageSelectorMenuFrame extends BasePage {

    @FindBy(xpath = "//span[text()='Delete Folder']")
    private WebElement deleteFolder;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public FolderConfigPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPageSelectorMenuFrame selectMenuDeleteFolderAndGoToHomePage() {
        deleteFolder.click();

        return this;
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
