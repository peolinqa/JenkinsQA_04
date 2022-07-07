package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MultiConfigurationProjectPage extends BaseBuildPage {

    @FindBy(linkText = "Build Now")
    private WebElement buildNowButton;

    @FindBy(className = "build-status-link")
    private WebElement tooltipStatus;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationProjectPage clickBuildNow() {
        buildNowButton.click();

        return this;
    }

    public WebElement getTooltipStatus() {

        return getWait5().until(ExpectedConditions.visibilityOf(tooltipStatus));
    }

    public MultiConfigurationProjectConsolePage clickTooltipStatus() {
        getTooltipStatus().click();

        return new MultiConfigurationProjectConsolePage(getDriver());
    }
}
