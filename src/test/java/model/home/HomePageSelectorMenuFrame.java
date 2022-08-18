package model.home;

import model.base.BasePage;
import model.projects.multiconfig.MultiConfigurationConfigPage;
import model.projects.pipeline.PipelineConfigPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class HomePageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Configure")
    private WebElement menuSelectorConfigure;

    @FindBy(xpath = "//a/span[contains(text(), 'Delete')]")
    private WebElement menuSelectorDelete;

    public HomePageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public HomePage clickMenuSelectorDelete() {
        menuSelectorDelete.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }

    public MultiConfigurationConfigPage clickMenuSelectorMultiConfProjectConfigure() {
        menuSelectorConfigure.click();

        return new MultiConfigurationConfigPage(getDriver());
    }

    public PipelineConfigPage clickMenuSelectorPipelineConfigure() {
        menuSelectorConfigure.click();

        return new PipelineConfigPage(getDriver());
    }

}
