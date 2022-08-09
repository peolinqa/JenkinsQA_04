package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageCredentialsDropDownMenuFame extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Add domain')]")
    private WebElement dropDownMenuAddDomainTab;

    public ManageCredentialsDropDownMenuFame(WebDriver driver) {
        super(driver);
    }

    public NewDomainPage clickDropDownMenuAddDomain() {
        dropDownMenuAddDomainTab.click();

        return new NewDomainPage(getDriver());
    }
}
