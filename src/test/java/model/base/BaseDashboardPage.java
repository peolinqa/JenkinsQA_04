package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDashboardPage<Self extends BaseDashboardPage<?>> extends BaseHeaderFooterPage<Self> {

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//ul[@class='first-of-type']/li")
    private List<WebElement> dashboardDropdownMenuElements;

    public BaseDashboardPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();

        return new HomePage(getDriver());
    }

    public BaseDashboardPage clickDashboardDropdownMenu() {
        getActions().moveToElement(dashboardButton).build().perform();
        getActions().moveToElement(menuSelector).click().build().perform();

        return this;
    }

    public List<String> getListOfDashboardDropdownMenuElements() {
       return dashboardDropdownMenuElements
                .stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }
}
