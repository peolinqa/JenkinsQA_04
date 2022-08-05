package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

public class OrganizationFolderConfigPage extends BaseHeaderFooterPage {

    @FindBy(css = "#yui-gen17")
    private WebElement saveButton;

    @FindBy(xpath = "//select[@class='setting-input dropdownList']/option[text()='Default Icon']/ancestor::select")
    private WebElement appearanceDropDownList;

    @FindBy(xpath = "//select[@class='setting-input dropdownList']/option[text()='Metadata Folder Icon']")
    private WebElement metadataFolderIcon;

    @FindBy(xpath = "//textarea[@name='_.description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//div[@class = 'form-config tabBarFrame']//div[text() = 'Child Health metrics']")
    private WebElement childHealthMetricsTab;

    @FindBy(id = "yui-gen13-button")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@descriptorid='com.cloudbees.hudson.plugins.folder.health.WorstChildHealthMetric']")
    private WebElement childHealthMetric;

    @FindBy(id = "yui-gen12-button")
    private WebElement metricsButton;

    @FindBy(css = ".first-child #yui-gen9-button")
    private WebElement addMetricButton;

    @FindBy(css = "#notification-bar")
    private WebElement notificationIsSaved;

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement displayNameField;

    @FindBy(xpath = "//div[text()='Projects']")
    private WebElement projectTab;

    @FindBy(xpath = "//div[@colspan='4']/div[text()='Projects']")
    private WebElement childProject;

    @FindBy(xpath = "//div[text()='Health metrics']")
    private WebElement healthMetricsTab;

    @FindBy(xpath = "//div[@colspan='4']/div[text()='Health metrics']")
    private WebElement healthMetrics;

    @FindBy(xpath = "//div[@class='tabBar config-section-activators']/div[text()='Automatic branch project triggering']")
    private WebElement automaticBranchProjectTriggeringTab;

    @FindBy(xpath = "//div[@colspan='4']/div[text()='Automatic branch project triggering']")
    private WebElement automaticBranchProjectTriggering;

    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new OrganizationFolderProjectPage(getDriver());
    }

    public OrganizationFolderConfigPage clickAppearanceDropDownList() {
        appearanceDropDownList.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public OrganizationFolderConfigPage selectOptionMetadataFolderIcon() {
        metadataFolderIcon.click();

        return new OrganizationFolderConfigPage(getDriver());
    }

    public OrganizationFolderConfigPage enterDescription(String text) {
        descriptionField.sendKeys(text);

        return this;
    }

    public OrganizationFolderConfigPage clickChildHealthMetricsTab() {
        childHealthMetricsTab.click();

        return this;
    }

    public OrganizationFolderConfigPage clickMetricsButton() {
        getActions().pause(1000).moveToElement(metricsButton).click().build().perform();

        return this;
    }

    public boolean checkChildMetricsIsDisplayed() {
        getWait5().until(ExpectedConditions.visibilityOf(addMetricButton));
        return childHealthMetric.isDisplayed();
    }

    public String getTextFromNotification() {
        return getWait5().until(ExpectedConditions.visibilityOf(notificationIsSaved)).getText();
    }

    public String getClassAttributeFromNotification() {
        return notificationIsSaved.getAttribute("class");
    }

    public String getColorValueAttributeFromNotification() {
        return notificationIsSaved.getCssValue("color");
    }

    public OrganizationFolderConfigPage inputDisplayNameField(String newName) {
        displayNameField.sendKeys(newName);

        return this;
    }

    public OrganizationFolderConfigPage clickApply() {
        TestUtils.scrollToElement(getDriver(), applyButton);
        applyButton.click();

        return this;
    }

    public OrganizationFolderConfigPage clickProjectTab() {
        projectTab.click();

        return this;
    }

    public boolean checkChildProjectIsDisplayed() {
        return childProject.isDisplayed();
    }

    public OrganizationFolderConfigPage clickHealthMetricsTab() {
        healthMetricsTab.click();

        return this;
    }

    public boolean checkHealthMetricsIsDisplayed() {
        return healthMetrics.isDisplayed();
    }

    public OrganizationFolderConfigPage clickAutomaticBranchProjectTriggeringTab() {
        automaticBranchProjectTriggeringTab.click();

        return this;
    }

    public boolean checkAutomaticBranchProjectTriggeringIsDisplayed() {
        return automaticBranchProjectTriggering.isDisplayed();
    }
}