package model.base;

import model.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseHeaderFooterPage<Self extends BaseHeaderFooterPage<?>> extends BasePage<Self> {

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

    @FindBy(xpath = "//a[@id='jenkins-home-link']/img[2]")
    private WebElement headerJenkinsLink;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//ul[@class='first-of-type']/li")
    private List<WebElement> dashboardDropdownMenuElements;


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

    public HomePage clickDashboardButton() {
        dashboardButton.click();

        return new HomePage(getDriver());
    }

    public BaseHeaderFooterPage<Self> clickDashboardDropdownMenu() {
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
