package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectorMenuFrame extends BasePage {

    @FindBy(linkText = "Builds")
    private WebElement builds;

    @FindBy(linkText = "Configure")
    private WebElement configure;

    public SelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserBuildPage clickBuildsAndGoToBuildsPage() {
        builds.click();

        return new UserBuildPage(getDriver());
    }

    public UserConfigurePage clickConfigureAndGoToConfigurePage() {
        configure.click();

        return new UserConfigurePage(getDriver());
    }
}
