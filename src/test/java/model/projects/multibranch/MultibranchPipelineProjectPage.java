package model.projects.multibranch;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MultibranchPipelineProjectPage extends BaseProjectPage<MultibranchPipelineProjectPage, MultibranchPipelineProjectPageSideMenuFrame> {

    @FindBy(css = "h1 img.icon-folder-disabled")
    private WebElement iconProjectDisabled;

    @FindBy(css = "h1 img.icon-pipeline-multibranch-project")
    private WebElement iconProjectEnabled;

    @FindBy(id = "enable-project")
    private WebElement textWarningDisable;

    public MultibranchPipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchPipelineProjectPageSideMenuFrame getSideMenu() {
        return new MultibranchPipelineProjectPageSideMenuFrame(getDriver());
    }

    public boolean isIconProjectDisabledDisplayed() {
        return iconProjectDisabled.isDisplayed();
    }

    public boolean istIconProjectEnabled() {
        return iconProjectEnabled.isDisplayed();
    }

    public String getWarningDisableText() {
        return textWarningDisable.getText();
    }
}
