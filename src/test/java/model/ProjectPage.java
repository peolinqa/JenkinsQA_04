package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProjectPage extends BasePage {

    @FindBy(css = "h1.page-headline")
    private WebElement projectName;

    @FindBy(css = "h1")
    private WebElement folderName;

    @FindBy(linkText = "Configure")
    private WebElement configureButton;

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//div//button[@type='submit'][text()='Disable Project']")
    private WebElement disableButton;

    @FindBy(xpath = "//div//button[@type='submit'][text()='Enable']")
    private WebElement enableButton;

    @FindBy(id = "enable-project")
    private WebElement text;

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }

    public String getFolderName() {
        return folderName.getText();
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();
        return new HomePage(getDriver());
    }

    public ItemConfigPage clickConfigure() {
        configureButton.click();

        return new ItemConfigPage(getDriver());
    }

    public Boolean _disableButton() {
        return disableButton.isDisplayed();

    }

    public ProjectPage clickDisable() {
        disableButton.click();

        return this;
    }

    public ProjectPage clickEnable() {
        enableButton.click();

        return this;
    }

    public String getDescriptionName() {
        return textDescription.getText();
    }

    public String[] getDisableName() {
        return text.getText().split("\n");
    }

    public void assertProjectStatus(String value) {
        Assert.assertTrue(getWait20().until(ExpectedConditions.attributeToBe(
                By.cssSelector(".tobsTable-body .job"), "class", value)));
    }
}
