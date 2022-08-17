package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class GlobalCredentialsPageSideMenuFrame extends BaseModel<GlobalCredentialsPageSideMenuFrame> {

    @FindBy(linkText = "Add Credentials")
    private WebElement menuAddCredentials;

    public GlobalCredentialsPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public NewCredentialsPage clickMenuAddCredentials(){
        menuAddCredentials.click();

        return new NewCredentialsPage(getDriver());
    }
}
