package model.projects.folder;

import model.home.HomePage;
import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderConfigPageSelectorMenuFrame extends BasePage {

    @FindBy(xpath = "//span[text()='Delete Folder']")
    private WebElement menuSelectorDeleteFolder;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    public FolderConfigPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPageSelectorMenuFrame clickMenuSelectorDeleteFolder() {
        menuSelectorDeleteFolder.click();

        return this;
    }

    public HomePage confirmDeleteAndGoHomePage() {
        btnYes.click();

        return new HomePage(getDriver());
    }
}
