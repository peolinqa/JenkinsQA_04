package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreestyleProjectPage extends BaseProjectDeleteWithAlertPage {

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(xpath = "//a[text() = 'Edit description']")
    private WebElement editDescription;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement multiButton;

    @FindBy(id = "enable-project")
    private WebElement text;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }

    public RenamePage<FreestyleProjectPage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new FreestyleProjectPage(getDriver()));
    }

    public FreestyleConfigPage clickFreestyleConfigure() {
        clickConfigureButton();

        return new FreestyleConfigPage(getDriver());
    }

    public String _disableButton() {
        return multiButton.getText();
    }

    public FreestyleProjectPage clickMultiButton() {
        multiButton.click();

        return this;
    }

    public FreestyleProjectPage clickEditDescription() {
        editDescription.click();

        return this;
    }

    public FreestyleProjectPage editDescription(String text) {
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(text);

        return this;
    }

    public String getDescriptionName() {
        return textDescription.getText();
    }

    public String[] getDisableName() {
        return text.getText().split("\n");
    }
}
