package model;

import model.base.BaseDashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultibranchPipelinePage extends BaseDashboardPage {

    @FindBy(linkText = "Scan Repository Log")
    private WebElement scanRepositoryLog;

    @FindBy(linkText = "View as plain text")
    private WebElement viewAsPlainText;

    @FindBy(linkText = "Delete Multibranch Pipeline")
    private WebElement deleteMultibranchPipeline;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    @FindBy(className = "icon-folder-disabled")
    private WebElement iconFolderDisabled;

    @FindBy(xpath = "//h1//img[@class='icon-pipeline-multibranch-project icon-xlg']")
    private WebElement iconFolderEnabled;

    @FindBy(xpath = "//form[contains(text(),'This Multibranch Pipeline is currently disabled')]")
    private WebElement messageDisabled;

    @FindBy(linkText = "Configure")
    private WebElement configure;

    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineConfigPage clickConfigureProject() {
        configure.click();

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

    public WebElement getIconFolderDisabled() {
        return iconFolderDisabled;
    }

    public WebElement getIconFolderEnabled() {
        return iconFolderEnabled;
    }

    public WebElement getMessageDisabled() {
        return messageDisabled;
    }
}