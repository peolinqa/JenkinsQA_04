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
    private WebElement dropDownMenuAddDomainTab;

    @FindBy(xpath = "//a[@href='/credentials/store/system/domain/_/']")
    private WebElement jenkinsGlobalDomainsCell;

    @FindBy(xpath = "//a[@href='/iconSize?16x16']")
    private WebElement iconSmallSize;

    @FindBy(xpath = "//a[@href='/iconSize?24x24']")
    private WebElement iconMediumSize;

    @FindBy(xpath = "//a[@href='/iconSize?32x32']")
    private WebElement iconLargeSize;

    @FindBy(xpath = ".//td[@data='Jenkins Credentials Provider']//img")
    private WebElement iconCredentialsProvider;

    @FindBy(xpath = "//span[text()='mall']/..")
    private WebElement iconSmallChangeButton;

    @FindBy(xpath = "//span[text()='edium']/..")
    private WebElement iconMediumChangeButton;

    @FindBy(xpath = "//span[text()='arge']/..")
    private WebElement iconLargeChangeButton;

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

    public NewDomainPage clickDropDownMenuAddDomain() {
        dropDownMenuAddDomainTab.click();

        return new NewDomainPage(getDriver());
    }

    public GlobalCredentialsPage clickGlobalCredentials() {
        jenkinsGlobalDomainsCell.click();

        return new GlobalCredentialsPage(getDriver());
    }

    public ManageCredentialsPage clickSmallSizeIcon() {
        iconSmallSize.click();

        return this;
    }

    public ManageCredentialsPage clickMediumSizeIcon() {
        iconMediumSize.click();

        return this;
    }

    public ManageCredentialsPage clickLargeSizeIcon() {
        iconLargeSize.click();

        return this;
    }

    public String getIconCredentialsProvider() {
        return iconCredentialsProvider.getAttribute("class");
    }

    public String[] getChangeIconButtonsBGColors() {
        return new String[]{
                iconSmallChangeButton.getCssValue("background-color"),
                iconMediumChangeButton.getCssValue("background-color"),
                iconLargeChangeButton.getCssValue("background-color")
        };
    }

    public String getIconSize() {
        return (iconCredentialsProvider.getCssValue("height"));
    }

    public boolean clickIconButtonAndGetSize() {
        String str = getIconSize();
        clickSmallSizeIcon();
        String str1 = getIconSize();

        return str.equals(str1);
    }
}