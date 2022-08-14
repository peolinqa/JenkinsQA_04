package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class HomePageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Builds")
    private WebElement menuSelectorBuilds;

    @FindBy(linkText = "Configure")
    private WebElement menuSelectorConfigure;

    @FindBy(linkText = "My Views")
    private WebElement menuSelectorMyView;

    @FindBy(xpath = "//a/span[contains(text(), 'Delete')]")
    private WebElement menuSelectorDelete;

    public HomePageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserBuildsPage clickMenuSelectorBuilds() {
        menuSelectorBuilds.click();

        return new UserBuildsPage(getDriver());
    }

    public UserConfigurePage clickMenuSelectorUserConfigure() {
        menuSelectorConfigure.click();

        return new UserConfigurePage(getDriver());
    }

    public MyViewPage clickMenuSelectorMyView() {
        menuSelectorMyView.click();

        return new MyViewPage(getDriver());
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
