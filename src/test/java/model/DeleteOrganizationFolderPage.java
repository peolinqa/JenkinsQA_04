package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteOrganizationFolderPage extends BasePage {

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public DeleteOrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public HomePage deleteOrganizationFolderAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}