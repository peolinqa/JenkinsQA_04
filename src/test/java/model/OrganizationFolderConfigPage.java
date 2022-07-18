package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrganizationFolderConfigPage extends BasePage {

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
}