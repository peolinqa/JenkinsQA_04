package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class MultibranchPipelinePage extends BasePage {

    @FindBy(linkText = "Scan Repository Log")
    private WebElement scanRepositoryLog;

    @FindBy(linkText = "View as plain text")
    private WebElement viewAsPlainText;

    @FindBy(linkText = "Delete Multibranch Pipeline")
    private WebElement deleteMultibranchPipeline;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineConfigPage clickConfigureProject() {
        ProjectUtils.Dashboard.Project.Configure.click(getDriver());

        return new MultibranchPipelineConfigPage(getDriver());
    }

    public MultibranchPipelinePage clickScanRepositoryLog() {
        scanRepositoryLog.click();

        return this;
    }

    public MultibranchPipelineConsolePage clickViewAsPlainText() {
        viewAsPlainText.click();

        return new MultibranchPipelineConsolePage(getDriver());
    }

    public MultibranchPipelinePage clickDeleteMultibranchPipeline() {
        deleteMultibranchPipeline.click();

        return this;
    }

    public HomePage clickYesButton() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}