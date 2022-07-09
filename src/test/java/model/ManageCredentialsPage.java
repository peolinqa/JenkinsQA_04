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

    public ManageCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public ManageCredentialsPage clickCredentialsStoreSystem(){
        getActions().moveToElement(credentialsStoreSystem).perform();

       return new ManageCredentialsPage(getDriver());
    }

    public ManageCredentialsPage clickMenuSelector(){
        menuSelector.click();

        return new ManageCredentialsPage(getDriver());
    }

    public NewDomainPage clickAddDomain(){
        addDomain.click();

        return new NewDomainPage(getDriver());
    }
}
