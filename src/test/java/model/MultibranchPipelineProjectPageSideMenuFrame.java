package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class MultibranchPipelineProjectPageSideMenuFrame extends BaseModel<MultibranchPipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    @FindBy(linkText = "Configure")
    private WebElement menuConfigure;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    public MultibranchPipelineProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<MultibranchPipelineProjectPage, MultibranchPipelineProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new MultibranchPipelineProjectPage(getDriver()));
    }

    public MultibranchPipelineProjectPageSideMenuFrame clickMenuDelete() {
        menuDelete.click();

        return this;
    }

    public HomePage clickBtnYesConfirmDelete() {
        btnYes.click();

        return new HomePage(getDriver());
    }

    public MultibranchPipelineConfigPage clickMenuConfigure() {
        menuConfigure.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }
}
