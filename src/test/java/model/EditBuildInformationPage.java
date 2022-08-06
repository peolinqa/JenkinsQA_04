package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditBuildInformationPage extends BaseHeaderFooterPage {

    @FindBy(id = "yui-gen2-button")
    private WebElement saveButton;

    @FindBy(name = "displayName")
    private WebElement inputDisplayName;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(xpath = "//span[text()='Back to Project']")
    private WebElement backToProjectButton;

    public EditBuildInformationPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleBuildPage clickSaveButton() {
        saveButton.click();

        return new FreestyleBuildPage(getDriver());
    }

    public EditBuildInformationPage editBuildName(String name) {
        inputDisplayName.sendKeys(name);

        return this;
    }

    public EditBuildInformationPage editBuildDescription(String description) {
        descriptionTextarea.sendKeys(description);

        return this;
    }
}
