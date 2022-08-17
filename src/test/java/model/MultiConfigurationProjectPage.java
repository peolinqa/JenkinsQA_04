package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public final class MultiConfigurationProjectPage extends BaseProjectPage<MultiConfigurationProjectPage, MultiConfigurationProjectPageSideMenuFrame> {

    @FindBy(className = "build-status-link")
    private WebElement iconBuildStatus;

    @FindBy(id = "description-link")
    private WebElement btnAddDescription;

    @FindBy(name = "description")
    private WebElement textareaDescription;

    @FindBy(id = "yui-gen2-button")
    private WebElement btnSaveDescription;

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement textDescription;

    @FindBy(css = "#disable-project button")
    private WebElement btnDisableProject;

    @FindBy(xpath = "//a[@href='default/']")
    private WebElement linkDefault;

    @FindBy(xpath = "//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")
    private WebElement iconTooltipStatusSuccess;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigurationProjectPageSideMenuFrame getSideMenu() {
        return new MultiConfigurationProjectPageSideMenuFrame(getDriver());
    }

    @Override
    public String getProjectNameText() {
        return super.getProjectNameText().substring("Project ".length());
    }

    public MultiConfigurationProjectPage clickIconBuildStatus() {
        getWait5().until(ExpectedConditions.visibilityOf(iconBuildStatus)).click();

        return this;
    }

    public MultiConfigurationProjectPage clickAddDescriptionButton() {
        btnAddDescription.click();

        return this;
    }

    public MultiConfigurationProjectPage setTextareaDescription(String description) {
        textareaDescription.sendKeys(description);

        return this;
    }

    public MultiConfigurationProjectPage clickSaveButton() {
        btnSaveDescription.click();

        return this;
    }

    public String getDescriptionText() {
        return textDescription.getText();
    }

    public MultiConfigurationProjectPage clickDisableProjectButton() {
        btnDisableProject.click();

        return this;
    }

    public boolean isMenuBuildNowDisplayed() {
        boolean isBuildNowDisplayed = false;

        List<WebElement> jobMenu = getDriver().findElements(By.xpath("//div[@id='tasks']//span[2]"));
        for (WebElement menu : jobMenu) {
            if (menu.getText().contains("Build Now")) {
                isBuildNowDisplayed = true;
            }
        }

        return isBuildNowDisplayed;
    }

    public MultiConfigurationProjectDefaultPage clickLinkDefault() {
        linkDefault.click();

        return new MultiConfigurationProjectDefaultPage(getDriver());
    }

    public boolean tooltipStatusSuccessIsDisplayed() {
        return iconTooltipStatusSuccess.isDisplayed();
    }
}
