package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ManageCredentialsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[@href='/credentials/store/system']")
    private WebElement credentialsStoreSystem;

    @FindBy(id = "menuSelector")
    private WebElement menuSelector;

    @FindBy(xpath = "//span[contains(text(), 'Add domain')]")
    private WebElement addDomain;

    @FindBy(xpath = "//a[@href='/credentials/store/system/domain/_/']")
    private WebElement global;

    @FindBy(xpath = "//a[@href='/iconSize?16x16']")
    private WebElement smallSizeIcon;

    @FindBy(xpath = "//a[@href='/iconSize?24x24']")
    private WebElement mediumSizeIcon;

    @FindBy(xpath = "//a[@href='/iconSize?32x32']")
    private WebElement largeSizeIcon;

    @FindBy(xpath = ".//td[@data='Jenkins Credentials Provider']//img")
    private WebElement attributeClass;

    @FindBy(xpath = "//span[text()='mall']/..")
    private WebElement smallChangeIconButtonBGColor;

    @FindBy(xpath = "//span[text()='edium']/..")
    private WebElement mediumChangeIconButtonBGColor;

    @FindBy(xpath = "//span[text()='arge']/..")
    private WebElement largeChangeIconButtonBGColor;

    @FindBy(xpath = "//li[@class='jenkins-icon-size__items-item']")
    private WebElement iconButtonName;

    public ManageCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public ManageCredentialsPage clickCredentialsStoreSystemMenu() {
        getActions().moveToElement(credentialsStoreSystem).perform();

        return new ManageCredentialsPage(getDriver());
    }

    public ManageCredentialsPage clickMenuSelector() {
        menuSelector.click();

        return new ManageCredentialsPage(getDriver());
    }

    public NewDomainPage clickAddDomain() {
        addDomain.click();

        return new NewDomainPage(getDriver());
    }

    public GlobalCredentialsPage clickGlobalCredentials() {
        global.click();

        return new GlobalCredentialsPage(getDriver());
    }

    public ManageCredentialsPage clickSmallSizeIcon() {
        smallSizeIcon.click();

        return this;
    }

    public ManageCredentialsPage clickMediumSizeIcon() {
        mediumSizeIcon.click();

        return this;
    }

    public ManageCredentialsPage clickLargeSizeIcon() {
        largeSizeIcon.click();

        return this;
    }

    public String getAttributeClass() {
        return attributeClass.getAttribute("class");
    }

    public String[] getChangeIconButtonsBGColors() {
        return new String[]{
                smallChangeIconButtonBGColor.getCssValue("background-color"),
                mediumChangeIconButtonBGColor.getCssValue("background-color"),
                largeChangeIconButtonBGColor.getCssValue("background-color")
        };
    }

    public String getIconSize() {
        return (attributeClass.getCssValue("height"));
    }

    public boolean clickIconButtonAndGetSize() {
        String str = getIconSize();
        clickSmallSizeIcon();
        String str1 = getIconSize();

        return str.equals(str1);
    }
}