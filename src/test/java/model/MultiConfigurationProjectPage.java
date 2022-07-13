package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

import java.util.List;

public class MultiConfigurationProjectPage extends BaseBuildPage {

    @FindBy(linkText = "Build Now")
    private WebElement buildNowButton;

    @FindBy(className = "build-status-link")
    private WebElement tooltipStatus;

    @FindBy(id = "description-link")
    private  WebElement addOrEditDescriptionButton;

    @FindBy (xpath = "//div/textarea[@name='description']")
    private WebElement descriptionTextArea;

    @FindBy (id = "yui-gen2-button")
    private  WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(css = "h1.page-headline")
    private WebElement projectName;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationProjectPage clickBuildNow() {
        buildNowButton.click();

        return this;
    }

    public WebElement getTooltipStatus() {

        return getWait5().until(ExpectedConditions.visibilityOf(tooltipStatus));
    }

    public MultiConfigurationProjectConsolePage clickTooltipStatus() {
        getTooltipStatus().click();

        return new MultiConfigurationProjectConsolePage(getDriver());
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
        ProjectUtils.clickDisableProject(getDriver());

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

    public RenamePage clickAdnGoToRenamePage() {
        ProjectUtils.Dashboard.Project.Rename.click(getDriver());

        return new RenamePage(getDriver());
    }

    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }


}
