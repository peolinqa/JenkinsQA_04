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

    @FindBy(linkText = "My Views")
    private WebElement myView;

    public SelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserBuildsPage clickBuildsAndGoToBuildsPage() {
        builds.click();

        return new UserBuildsPage(getDriver());
    }

    public UserConfigurePage clickConfigureAndGoToConfigurePage() {
        configure.click();

        return new UserConfigurePage(getDriver());
    }

    public MyViewPage clickMyViewAndGoToMyViewPage() {
        myView.click();

        return new MyViewPage(getDriver());
    }
}
