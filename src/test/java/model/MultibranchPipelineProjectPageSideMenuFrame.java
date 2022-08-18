package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class MultibranchPipelineProjectPageSideMenuFrame extends BaseModel<MultibranchPipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Configure")
    private WebElement menuConfigure;

    @FindBy(linkText = "Scan Repository Log")
    private WebElement menuScanRepositoryLog;

    @FindBy(linkText = "View as plain text")
    private WebElement menuViewAsPlainText;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    public MultibranchPipelineProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineConfigPage clickMenuConfigure() {
        menuConfigure.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }

    public MultibranchPipelineProjectPageSideMenuFrame clickLinkScanRepositoryLog() {
        menuScanRepositoryLog.click();

        return this;
    }

    public MultibranchPipelineConsolePage clickLinkViewAsPlainText() {
        menuViewAsPlainText.click();

        return new MultibranchPipelineConsolePage(getDriver());
    }

    public MultibranchPipelineProjectPageSideMenuFrame clickMenuDelete() {
        menuDelete.click();

        return this;
    }

    public RenamePage<MultibranchPipelineProjectPage, MultibranchPipelineProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new MultibranchPipelineProjectPage(getDriver()));
    }

    public HomePage clickBtnYesConfirmDelete() {
        btnYes.click();

        return new HomePage(getDriver());
    }
}
