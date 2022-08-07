package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class MultiConfigurationProjectPage extends BaseProjectDeleteWithAlertPage<MultiConfigurationProjectPage, MultiConfigurationProjectPageSideMenuFrame> {

    @FindBy(linkText = "Build Now")
    private WebElement buildNowButton;

    @FindBy(className = "build-status-link")
    private WebElement tooltipStatus;

    @FindBy(id = "description-link")
    private WebElement addOrEditDescriptionButton;

    @FindBy(xpath = "//div/textarea[@name='description']")
    private WebElement descriptionTextArea;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(id = "yui-gen1-button")
    private WebElement disableProjectButton;

    @FindBy(xpath = "//a[@href='default/']")
    private WebElement defaultButton;

    @FindBy(xpath = "//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")
    private WebElement tooltipStatusSuccess;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigurationProjectPageSideMenuFrame getSideMenu() {
        return new MultiConfigurationProjectPageSideMenuFrame(getDriver());
    }

    public MultiConfigurationProjectPage clickBuildNow() {
        buildNowButton.click();

        return this;
    }

    public WebElement getTooltipStatus() {
        return getWait5().until(ExpectedConditions.visibilityOf(tooltipStatus));
    }

    public MultiConfigurationProjectPage clickTooltipStatus() {
        getTooltipStatus().click();

        return this;
    }

    public MultiConfigurationProjectPage clickAddDescription() {
        addOrEditDescriptionButton.click();

        return this;
    }

    public MultiConfigurationProjectPage setDescription(String description) {
        descriptionTextArea.sendKeys(description);

        return this;
    }

    public MultiConfigurationProjectPage saveConfigAndGoToMultiConfigurationProject() {
        saveButton.click();

        return this;
    }

    public String getDescription() {
        return textDescription.getText();
    }

    public MultiConfigurationProjectPage clickDisableProjectButton() {
        disableProjectButton.click();

        return this;
    }

    public boolean isDisplayedBuildNowButton() {
        boolean isBuildNowDisplayed = false;

        List<WebElement> jobMenu = getDriver().findElements(By.xpath("//div[@id='tasks']//span[2]"));
        for (WebElement menu : jobMenu) {
            if (menu.getText().contains("Build Now")) {
                isBuildNowDisplayed = true;
            }
        }

        return isBuildNowDisplayed;
    }

    @Override
    public String getProjectName() {
        return super.getProjectName().substring("Project ".length());
    }

    public MultiConfigurationProjectDefaultPage clickDefaultButton() {
        defaultButton.click();

        return new MultiConfigurationProjectDefaultPage(getDriver());
    }

    public boolean tooltipStatusSuccessIsDisplayed() {
        return tooltipStatusSuccess.isDisplayed();
    }
}