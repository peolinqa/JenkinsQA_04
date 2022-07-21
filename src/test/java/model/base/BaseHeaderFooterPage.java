package model.base;

import model.*;
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

    @FindBy(id = "menuSelector")
    protected WebElement menuSelector;

    @FindBy(linkText = "Builds")
    private WebElement builds;

    @FindBy(linkText = "Configure")
    private WebElement configure;

    @FindBy(xpath = "//a[@id='jenkins-home-link']/img[2]")
    private WebElement headerJenkinsLink;

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

    public SelectorMenuFrame navigateAndClickDropDownUserMenu() {
        getActions().moveToElement(userPage).pause(200).perform();
        menuSelector.click();

        return new SelectorMenuFrame(getDriver());
    }

    public HomePage clickJenkinsIconAndGoToHomePage() {
        headerIcon.click();

        return new HomePage(getDriver());
    }

    public HomePage clickJenkins(){
        headerJenkinsLink.click();
        return new HomePage(getDriver());
    }
}
