package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageCredentialsSelectorMenuFame extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Add domain')]")
    private WebElement menuAddDomain;

    public ManageCredentialsSelectorMenuFame(WebDriver driver) {
        super(driver);
    }

    public NewDomainPage selectMenuAddDomainAndGoToNewDomainPage() {
        menuAddDomain.click();

        return new NewDomainPage(getDriver());
    }
}
