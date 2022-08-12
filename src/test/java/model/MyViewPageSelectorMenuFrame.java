package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MyViewPageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Configure")
    private WebElement menuConfigure;

    public MyViewPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public PipelineConfigPage selectMenuConfigureAndGoToPipelineConfigPage() {
        menuConfigure.click();

        return new PipelineConfigPage(getDriver());
    }

    public MultibranchPipelineConfigPage selectMenuConfigureAndGoToMultibranchPipelineConfigPage() {
        menuConfigure.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }
}
