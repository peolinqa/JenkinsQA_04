package model.base;

import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseHeaderFooterPage<Self extends BaseHeaderFooterPage<?>> extends BasePage<Self> {

    @FindBy(xpath = "//h1[text() = 'Error']")
    private List<WebElement> errorText;

    @FindBy(id = "jenkins-head-icon")
    private WebElement headerIcon;

    @FindBy(id = "header")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[@href='api/']")
    private WebElement apiFooter;

    @FindBy(css = ".login")
    private WebElement userPage;

    @FindBy(id = "menuSelector")
    private WebElement menuSelector;

    @FindBy(xpath = "//a[@id='jenkins-home-link']/img[2]")
    private WebElement headerJenkinsLink;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//ul[@class='first-of-type']/li")
    private List<WebElement> dashboardDropdownMenuElements;

    @FindBy(xpath = "//header[@id='header']/div")
    private List<WebElement> headerMainElement;

    public BaseHeaderFooterPage(WebDriver driver) {
        super(driver);
    }

    public ErrorPage getErrorPageIfPresent() {
        if (errorText.size() > 0) {
            return new ErrorPage(getDriver());
        }

        return null;
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

    public String getPageHeaderCssValueBackgroundColor() {
        return pageHeader.getCssValue("background-color");
    }

    public String getPageHeaderCssValueDisplay() {
        return pageHeader.getCssValue("display");
    }

    public String getPageHeaderCssValueHeight() {
        return pageHeader.getCssValue("height");
    }

    public String getPageHeaderCssValueAlignItems() {
        return pageHeader.getCssValue("align-items");
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

    public SelectorMenuFrame clickDropDownMenu() {
        getActions().moveToElement(userPage).pause(200).perform();
        menuSelector.click();

        return new SelectorMenuFrame(getDriver());
    }

    public HomePage clickJenkins() {
        headerJenkinsLink.click();
        return new HomePage(getDriver());
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();

        return new HomePage(getDriver());
    }

    public Self clickDashboardDropdownMenu() {
        getActions().moveToElement(dashboardButton).build().perform();
        getActions().moveToElement(menuSelector).click().build().perform();

        return (Self) this;
    }

    public List<String> getListOfDashboardDropdownMenuElements() {
        return dashboardDropdownMenuElements
                .stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isRightPositionOfJenkinsHeadIcon() {
        return getDriver().findElement(By.xpath(String.format("//div[@class='logo']/a/img[%s]", 1)))
                .getAttribute("id")
                .equals("jenkins-head-icon")
                &&
                getDriver().findElement(By.xpath(String.format("//div[@class='logo']/a/img[%s]", 2)))
                        .getAttribute("id")
                        .equals("jenkins-name-icon");
    }

    public boolean isRightPositionOfHeaderElementsUI() {
        for (int i = 0; i < headerMainElement.size(); i++) {
            return headerMainElement.get(i).isDisplayed();
        }

        return false;
    }
}