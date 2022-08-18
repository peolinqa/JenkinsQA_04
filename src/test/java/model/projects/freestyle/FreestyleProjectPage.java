package model.projects.freestyle;

import model.base.BaseProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class FreestyleProjectPage extends BaseProjectPage<FreestyleProjectPage, FreestyleProjectPageSideMenuFrame> {

    private static final By BUILD_NAME = By.cssSelector("tr:nth-child(2)  a.display-name");

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement textDescription;

    @FindBy(xpath = "//a[text() = 'Edit description']")
    private WebElement btnEditDescription;

    @FindBy(name = "description")
    private WebElement textareaDescription;

    @FindBy(css = "#disable-project button")
    private WebElement btnDisableProject;

    @FindBy(css = "#enable-project button[type='submit']")
    private WebElement btnEnableProject;

    @FindBy(id = "yui-gen2-button")
    private WebElement btnSave;

    @FindBy(id = "enable-project")
    private WebElement textWarningDisable;

    @FindBy(css = "tr:nth-child(2) a.display-name")
    private WebElement buildNameOnPaneBuildHistory;

    @FindBy(xpath = "//div[@class='pane desc indent-multiline']")
    private WebElement buildDescrOnPaneBuildHistory;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectPageSideMenuFrame getSideMenu() {
        return new FreestyleProjectPageSideMenuFrame(getDriver());
    }

    @Override
    public String getProjectNameText() {
        return super.getProjectNameText().substring("Project ".length());
    }

    public String getDisableButtonText() {
        return btnDisableProject.getText();
    }

    public FreestyleProjectPage clickDisableProjectButton() {
        btnDisableProject.click();

        return this;
    }

    public FreestyleProjectPage clickEnableProjectButton() {
        btnEnableProject.click();

        return this;
    }

    public FreestyleProjectPage clickSaveButton() {
        btnSave.click();

        return this;
    }

    public FreestyleProjectPage clickEditDescription() {
        btnEditDescription.click();

        return this;
    }

    public FreestyleProjectPage editProjectDescription(String text) {
        textareaDescription.clear();
        textareaDescription.sendKeys(text);

        return this;
    }

    public String getDescriptionText() {
        return textDescription.getText();
    }

    public String[] getWarningDisableText() {
        return textWarningDisable.getText().split("\n");
    }

    public boolean isBuildNumberDisplayed() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(BUILD_NAME)).isDisplayed();
    }

    public String getBuildNameText() {
        return buildNameOnPaneBuildHistory.getText();
    }

    public String getBuildDescriptionText() {
        return buildDescrOnPaneBuildHistory.getText();
    }
}
