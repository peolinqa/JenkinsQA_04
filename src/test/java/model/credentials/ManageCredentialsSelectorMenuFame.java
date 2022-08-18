package model.credentials;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageCredentialsSelectorMenuFame extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Add domain')]")
    private WebElement menuSelectorAddDomain;

    public ManageCredentialsSelectorMenuFame(WebDriver driver) {
        super(driver);
    }

    public NewDomainPage clickMenuSelectorAddDomain() {
        menuSelectorAddDomain.click();

        return new NewDomainPage(getDriver());
    }
}
