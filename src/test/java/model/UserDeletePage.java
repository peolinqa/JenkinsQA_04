package model;

import model.base.BaseProjectDeleteWithConfirmPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserDeletePage extends BaseProjectDeleteWithConfirmPage {

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public UserDeletePage(WebDriver driver) {
        super(driver);
    }

    public ManageUsersPage clickYesButton() {
        yesButton.click();

        return new ManageUsersPage(getDriver());
    }
}