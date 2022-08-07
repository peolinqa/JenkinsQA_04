package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class ManageUsersPageSideMenuFrame extends BaseModel<ManageUsersPageSideMenuFrame> {

    @FindBy(linkText = "Create User")
    private WebElement menuCreateUser;

    public ManageUsersPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickMenuCreateUser() {
        menuCreateUser.click();

        return new CreateUserPage(getDriver());
    }
}