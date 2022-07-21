package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

public class OrganizationFolderConfigPage extends BaseDashboardPage {

    @FindBy(css = "#yui-gen17")
    private WebElement saveButton;

    @FindBy(name = "_.disabled")
    private WebElement disableCheckBox;

    @FindBy(xpath = "//select[@class='setting-input dropdownList']/option[text()='Default Icon']/ancestor::select")
    private WebElement appearanceDropDownList;

    @FindBy(xpath = "//select[@class='setting-input dropdownList']/option[text()='Metadata Folder Icon']")
    private WebElement metadataFolderIcon;

    @FindBy(xpath = "//textarea[@name='_.description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//div[text() = 'Child Health metrics']")
    private WebElement childHealthMetricsTab;

    @FindBy(id = "yui-gen13-button")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@class='form-config tabBarFrame']//div[text()='Properties']")
    private WebElement propertiesTab;

    @FindBy(xpath = "//div[@descriptorid='com.cloudbees.hudson.plugins.folder.health.WorstChildHealthMetric']")
    private WebElement childHealthMetric;

    @FindBy(id = "yui-gen12-button")
    private WebElement metricsButton;

    @FindBy(xpath = "//div[@id='notification-bar']")
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

    public OrganizationFolderConfigPage clickDisableCheckBox() {
        disableCheckBox.click();

        return this;
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

    public OrganizationFolderConfigPage clickPropertiesTab() {
        propertiesTab.click();

        return this;
    }

    public OrganizationFolderConfigPage clickMetricsButton() {
        getActions().pause(1000).moveToElement(metricsButton).click().build().perform();

        return this;
    }

    public OrganizationFolderConfigPage clickApplyButton() {
        applyButton.click();

        return this;
    }

    public boolean checkChildMetricsIsDisplayed() {

        return childHealthMetric.isDisplayed();
    }

    public String getTextFromNotification(){
        return notificationIsSaved.getText();
    }

    public String getClassAttributeFromNotification(){
        return notificationIsSaved.getAttribute("class");
    }

    public String getColorValueAttributeFromNotification(){
        return notificationIsSaved.getCssValue("color");
    }

    public OrganizationFolderConfigPage inputDisplayNameField(String newName){
        displayNameField.sendKeys(newName);

        return this;
    }

    public OrganizationFolderConfigPage clickApply() {
        TestUtils.scrollToElement(getDriver(), applyButton);
        applyButton.click();

        return this;
    }

    public OrganizationFolderConfigPage clickProjectTab(){
        projectTab.click();

        return this;
    }

    public boolean ckeckChildProjectIsDisplayed(){
        return childProject.isDisplayed();
    }

    public OrganizationFolderConfigPage clickHealthMetricsTab(){
        healthMetricsTab.click();

        return this;
    }

    public boolean ckeckhealthMetricsIsDisplayed(){
        return healthMetrics.isDisplayed();
    }

    public OrganizationFolderConfigPage clickAutomaticBranchProjectTriggeringTab(){
        automaticBranchProjectTriggeringTab.click();

        return this;
    }

    public boolean ckeckAutomaticBranchProjectTriggeringIsDisplayed(){
        return automaticBranchProjectTriggering.isDisplayed();
    }
}