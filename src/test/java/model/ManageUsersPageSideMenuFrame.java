package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class ManageUsersPageSideMenuFrame extends BaseModel<ManageUsersPageSideMenuFrame> {

    @FindBy(linkText = "Create User")
    private WebElement createUser;

    public ManageUsersPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        createUser.click();

        return new CreateUserPage(getDriver());
    }
}