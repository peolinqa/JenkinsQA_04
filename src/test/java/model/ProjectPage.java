package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProjectPage extends BaseProjectDeleteWithAlertPage {

    private static final By DISPLAY_NAME = By.cssSelector(".display-name");

    @FindBy(linkText = "Build Now")
    private WebElement buildButton;

    @FindBy(xpath = "//a[@href ='lastBuild/']")
    private WebElement lastBuildButton;

    @FindBy(xpath = "//a[@class='tip model-link inside build-link display-name']")
    private WebElement buildName;

    @FindBy(xpath = "//div[@class='pane desc indent-multiline']")
    private WebElement buildDescription;

    @FindBy(xpath = "//span[text()='Back to Dashboard']")
    private WebElement backToDashboard;

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public ProjectPage clickBuildButton() {
        buildButton.click();

        return this;
    }

    public ProjectPage waitForBuildToComplete() {
            getWait20().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".display-name")));

        return this;
    }

    public String getBuildNumber() {
        WebElement displayName = getWait20().until(ExpectedConditions.presenceOfElementLocated(DISPLAY_NAME));

        return displayName.getText().substring("#".length());
    }

    public boolean buildNumberIsDisplayed() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(DISPLAY_NAME)).isDisplayed();
    }

    public LastBuildPage selectLastBuild() {
        getWait5().until(ExpectedConditions.elementToBeClickable(lastBuildButton)).click();

        return new LastBuildPage(getDriver());
    }

    public HomePage clickBackToDashboard() {
        backToDashboard.click();

        return new HomePage(getDriver());
    }

    public String getBuildName() {
        return buildName.getText();
    }

    public String getBuildDescription() {
        return  buildDescription.getText();
    }
}
