package model.base;

import model.ApiPage;
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

    public WebElement getTopPageHeader() {
        return pageHeader;
    }

    public String getPageHeaderCssValue(String value) {
        return pageHeader.getCssValue(value);
    }

    public ApiPage goToApiPage() {
       apiFooter.click();

       return new ApiPage(getDriver());
    }
}
