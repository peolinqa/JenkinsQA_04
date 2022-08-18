package model.buildsHistory;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class EditBuildInformationPage extends BaseHeaderFooterPage {

    @FindBy(id = "yui-gen2-button")
    private WebElement btnSave;

    @FindBy(name = "displayName")
    private WebElement inputDisplayName;

    @FindBy(name = "description")
    private WebElement textareaDescription;

    public EditBuildInformationPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleBuildPage clickSaveButton() {
        btnSave.click();

        return new FreestyleBuildPage(getDriver());
    }

    public EditBuildInformationPage editBuildName(String name) {
        inputDisplayName.sendKeys(name);

        return this;
    }

    public EditBuildInformationPage editBuildDescription(String description) {
        textareaDescription.sendKeys(description);

        return this;
    }
}
