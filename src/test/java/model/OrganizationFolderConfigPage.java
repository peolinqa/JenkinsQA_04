package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganizationFolderConfigPage extends BasePage {

    @FindBy(css = "#yui-gen17")
    private WebElement saveButton;

    @FindBy(name = "_.disabled")
    private WebElement disableCheckBox;

    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new OrganizationFolderProjectPage(getDriver());
    }

    public OrganizationFolderConfigPage clickDisableCheckBox() {
        disableCheckBox.click();

        return this;
    }
}