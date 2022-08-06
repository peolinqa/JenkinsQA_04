package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreestyleProjectPage extends BaseProjectDeleteWithAlertPage {

    private static final By BUILD_NAME = By.cssSelector("tr:nth-child(2)  a.display-name");

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(xpath = "//a[text() = 'Edit description']")
    private WebElement editDescription;

    @FindBy(name = "description")
    private WebElement descriptionField;

    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement multiButton;

    @FindBy(id = "enable-project")
    private WebElement text;

    @FindBy(linkText = "Build Now")
    private WebElement buildButton;

    @FindBy(xpath = "//a[@href ='lastBuild/']")
    private WebElement lastBuildButton;

    @FindBy(xpath = "//span[text()='Back to Dashboard']")
    private WebElement backToDashboard;

    @FindBy(css = "tr:nth-child(2)  a.display-name")
    private WebElement buildName;

    @FindBy(xpath = "//div[@class='pane desc indent-multiline']")
    private WebElement buildDescription;

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
        descriptionField.clear();
        descriptionField.sendKeys(text);

        return this;
    }

    public String getDescriptionName() {
        return textDescription.getText();
    }

    public String[] getDisableName() {
        return text.getText().split("\n");
    }

    public FreestyleProjectPage clickBuildButton() {
        buildButton.click();

        return this;
    }

    public boolean buildNumberIsDisplayed() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(BUILD_NAME)).isDisplayed();
    }

    public String getBuildName() {
        return buildName.getText();
    }

    public String getBuildDescription() {
        return buildDescription.getText();
    }
}
