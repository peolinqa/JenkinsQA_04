package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultiConfigurationProjectConsolePage extends BaseBuildPage {

    @FindBy(xpath = "//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")
    private WebElement tooltipStatusSuccess;

    public MultiConfigurationProjectConsolePage(WebDriver driver) {
        super(driver);
    }

    public boolean tooltipStatusSuccessIsDisplayed() {
        return tooltipStatusSuccess.isDisplayed();
    }
}