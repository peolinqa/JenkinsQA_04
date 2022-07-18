package model;

import model.base.BaseHeaderFooterPage;
import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserConfigurePage extends BaseHeaderFooterPage {

    @FindBy(name = "_.fullName")
    private WebElement fullNameField;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveButton;

    public UserConfigurePage(WebDriver driver) {
        super(driver);
    }

    public UserConfigurePage setFullName(String fullName) {
        fullNameField.sendKeys(fullName);

        return this;
    }

    public UserConfigurePage clearFullName() {
        fullNameField.clear();

        return this;
    }

    public UserStatusPage clickSaveButton() {
        saveButton.click();

        return new UserStatusPage(getDriver());
    }

    public String getGen2ButtonText() {
        return saveButton.getText();
    }
}