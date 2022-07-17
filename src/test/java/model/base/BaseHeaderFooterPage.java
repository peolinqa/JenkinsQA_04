package model.base;

import model.ApiPage;
import model.HomePage;
import model.UserBuildPage;
import model.UserStatusPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseHeaderFooterPage extends BasePage {

    @FindBy(id = "jenkins-head-icon")
    private WebElement headerIcon;

    @FindBy(id = "header")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[@href='api/']")
    private WebElement apiFooter;

    @FindBy(css = ".login")
    private WebElement userPage;

    @FindBy(css = "div[id='menuSelector']")
    private WebElement menuSelector;

    @FindBy(linkText = "Builds")
    private WebElement builds;

    public BaseHeaderFooterPage(WebDriver driver) {
        super(driver);
    }

    public String getLogoIconTagName() {
        return headerIcon.getTagName();
    }

    public String getLogoIconAttribute(String attribute) {
        return headerIcon.getAttribute(attribute);
    }

    public String getPageHeaderLocation() {
        return pageHeader.getLocation().toString();
    }

    public String getPageHeaderCssValue(String value) {
        return pageHeader.getCssValue(value);
    }

    public ApiPage goToApiPage() {
        apiFooter.click();

        return new ApiPage(getDriver());
    }

    public boolean headerIconIsVisible() {
        return headerIcon.isDisplayed();
    }

    public boolean topPageHeaderIsVisible() {
        return pageHeader.isDisplayed();
    }

    public UserStatusPage clickUserAndGoToUserPage() {
        userPage.click();

        return new UserStatusPage(getDriver());
    }

    public HomePage navigateToUserMenu() {
        getActions().moveToElement(userPage).pause(200).perform();

        return new HomePage(getDriver());
    }

    public HomePage navigateToUserMenuAndClick() {
        menuSelector.click();

        return new HomePage(getDriver());
    }

    public UserBuildPage clickBuildsAndGoToBuildsPage() {
        builds.click();

        return new UserBuildPage(getDriver());
    }
}
