package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class HomePageSelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Builds")
    private WebElement menuBuilds;

    @FindBy(linkText = "Configure")
    private WebElement menuConfigure;

    @FindBy(linkText = "My Views")
    private WebElement menuMyView;

    public HomePageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserBuildsPage selectMenuBuildsAndGoToBuildsPage() {
        menuBuilds.click();

        return new UserBuildsPage(getDriver());
    }

    public UserConfigurePage selectMenuConfigureAndGoToConfigurePage() {
        menuConfigure.click();

        return new UserConfigurePage(getDriver());
    }

    public MyViewPage selectMenuMyViewAndGoToMyViewPage() {
        menuMyView.click();

        return new MyViewPage(getDriver());
    }
}
