package model;

import model.base.BasePage;
import org.openqa.selenium.By;
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

    public UserConfigurePage clickUserConfigure(String userName) {
        getDriver().findElement(By.xpath(String.format("//a[@href='user/%s/configure']", userName.toLowerCase()))).click();

        return new UserConfigurePage(getDriver());
    }
}
