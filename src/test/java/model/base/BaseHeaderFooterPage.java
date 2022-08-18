package model.base;

import model.ApiPage;
import model.helpPages.ErrorPage;
import model.home.HomePage;
import model.Users.HeaderFooterPageSelectorMenuFrame;
import model.Users.UserStatusPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BaseHeaderFooterPage<Self extends BaseHeaderFooterPage<?>> extends BasePage<Self> {

    @FindBy(xpath = "//h1[text() = 'Error']")
    private List<WebElement> headerErrorPage;

    @FindBy(id = "jenkins-head-icon")
    private WebElement imgJenkinsIcon;

    @FindBy(id = "header")
    private WebElement pageHeaderPanel;

    @FindBy(xpath = "//a[@href='api/']")
    private WebElement linkRestApiFooter;

    @FindBy(css = ".login > a.model-link span")
    private WebElement linkUser;

    @FindBy(id = "menuSelector")
    private WebElement menuSelector;

    @FindBy(id = "jenkins-name-icon")
    private WebElement imgJenkinsNameIcon;

    @FindBy(linkText = "Dashboard")
    private WebElement linkDashboard;

    @FindBy(xpath = "//header[@id='header']/div")
    private List<WebElement> listHeaderMainElements;

    public BaseHeaderFooterPage(WebDriver driver) {
        super(driver);
    }

    public ErrorPage getErrorPageIfPresent() {
        if (headerErrorPage.size() > 0) {
            return new ErrorPage(getDriver());
        }

        return null;
    }

    public String getLogoIconTagName() {
        return imgJenkinsIcon.getTagName();
    }

    public String getLogoIconAttribute(String attribute) {
        return imgJenkinsIcon.getAttribute(attribute);
    }

    public String getPageHeaderLocation() {
        return pageHeaderPanel.getLocation().toString();
    }

    public String getPageHeaderCssValueBackgroundColor() {
        return pageHeaderPanel.getCssValue("background-color");
    }

    public String getPageHeaderCssValueDisplay() {
        return pageHeaderPanel.getCssValue("display");
    }

    public String getPageHeaderCssValueHeight() {
        return pageHeaderPanel.getCssValue("height");
    }

    public String getPageHeaderCssValueAlignItems() {
        return pageHeaderPanel.getCssValue("align-items");
    }

    public ApiPage goToApiPage() {
        linkRestApiFooter.click();

        return new ApiPage(getDriver());
    }

    public boolean isHeaderIconVisible() {
        return imgJenkinsIcon.isDisplayed();
    }

    public boolean isTopPageHeaderVisible() {
        return pageHeaderPanel.isDisplayed();
    }

    public UserStatusPage clickUserAndGoToUserPage() {
        linkUser.click();

        return new UserStatusPage(getDriver());
    }

    public HeaderFooterPageSelectorMenuFrame clickUserDropDownMenu() {
        getActions().moveToElement(linkUser).perform();
        menuSelector.click();

        return new HeaderFooterPageSelectorMenuFrame(getDriver());
    }

    public HomePage clickJenkinsName() {
        imgJenkinsNameIcon.click();

        return new HomePage(getDriver());
    }

    public HomePage clickLinkDashboard() {
        linkDashboard.click();

        return new HomePage(getDriver());
    }

    public HeaderFooterPageSelectorMenuFrame clickDashboardDropdownMenu() {
        getActions().moveToElement(linkDashboard).build().perform();
        getActions().moveToElement(menuSelector).click().build().perform();

        return new HeaderFooterPageSelectorMenuFrame(getDriver());
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
        for (int i = 0; i < listHeaderMainElements.size(); i++) {
            return listHeaderMainElements.get(i).isDisplayed();
        }

        return false;
    }

}
