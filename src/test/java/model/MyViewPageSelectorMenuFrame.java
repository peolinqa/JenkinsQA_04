package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MyViewPageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Configure")
    private WebElement menuSelectorConfigure;

    public MyViewPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public PipelineConfigPage clickMenuSelectorPipelineConfigure() {
        menuSelectorConfigure.click();

        return new PipelineConfigPage(getDriver());
    }

    public MultibranchPipelineConfigPage clickMenuSelectorMultibranchConfigure() {
        menuSelectorConfigure.click();

        return new MultibranchPipelineConfigPage(getDriver());
    }
}
