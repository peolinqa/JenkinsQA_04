package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class UserPageSideMenuFrame extends BaseModel<UserPageSideMenuFrame> {

    @FindBy(linkText = "Delete")
    private WebElement menuDelete;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    public UserPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public UserPageSideMenuFrame clickMenuDelete() {
        menuDelete.click();

        return this;
    }

    public ManageUsersPage confirmDeleteUser() {
        btnYes.click();

        return new ManageUsersPage(getDriver());
    }
}
