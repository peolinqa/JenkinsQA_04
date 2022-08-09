package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MultibranchPipelineProjectPage extends BaseProjectPage<MultibranchPipelineProjectPage, MultibranchPipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Scan Repository Log")
    private WebElement scanRepositoryLog;

    @FindBy(linkText = "View as plain text")
    private WebElement viewAsPlainText;

    @FindBy(className = "icon-folder-disabled")
    private WebElement iconFolderDisabled;

    @FindBy(xpath = "//h1//img[@class='icon-pipeline-multibranch-project icon-xlg']")
    private WebElement iconFolderEnabled;

    @FindBy(xpath = "//form[contains(text(),'This Multibranch Pipeline is currently disabled')]")
    private WebElement messageDisabled;

    public MultibranchPipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchPipelineProjectPageSideMenuFrame getSideMenu() {
        return new MultibranchPipelineProjectPageSideMenuFrame(getDriver());
    }

    public MultibranchPipelineProjectPage clickScanRepositoryLog() {
        scanRepositoryLog.click();

        return this;
    }

    public MultibranchPipelineConsolePage clickViewAsPlainText() {
        viewAsPlainText.click();

        return new MultibranchPipelineConsolePage(getDriver());
    }

    public boolean isIconFolderDisabledDisplayed() {
        return iconFolderDisabled.isDisplayed();
    }

    public boolean istIconFolderEnabled() {
        return iconFolderEnabled.isDisplayed();
    }

    public String textMessageDisabled() {
        return messageDisabled.getText();
    }
}