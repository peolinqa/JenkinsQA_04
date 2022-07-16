package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseDashboardPage extends BaseHeaderFooterPage{

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    public BaseDashboardPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();

        return new HomePage(getDriver());
    }
}