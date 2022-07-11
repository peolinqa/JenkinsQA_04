package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageCredentialsPage extends BasePage {

    @FindBy(xpath = "//a[@href='/credentials/store/system']")
    private WebElement credentialsStoreSystem;

    @FindBy(id = "menuSelector")
    private WebElement menuSelector;

    @FindBy(xpath = "//span[contains(text(), 'Add domain')]")
    private WebElement addDomain;

    @FindBy(xpath = "//a[@href='/credentials/store/system/domain/_/']")
    WebElement global;

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
}
