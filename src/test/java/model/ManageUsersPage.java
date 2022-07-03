package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage extends BasePage {

    @FindBy(linkText = "Create User")
    private WebElement createUser;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        createUser.click();

        return new CreateUserPage(getDriver());
    }
}
