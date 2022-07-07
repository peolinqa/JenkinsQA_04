package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage extends BasePage {

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy(name = "password1")
    private WebElement passwordField;

    @FindBy(name = "password2")
    private WebElement confirmPasswordField;

    @FindBy(name = "fullname")
    private WebElement fullNameField;

    @FindBy(name = "email")
    private WebElement emailAddressField;

    @FindBy(id = "yui-gen1-button")
    private WebElement createUserButton;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUserName(String userName) {
        userNameField.sendKeys(userName);

        return this;
    }

    public CreateUserPage setPassword(String password) {
        passwordField.sendKeys(password);

        return this;
    }

    public CreateUserPage setConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);

        return this;
    }

    public CreateUserPage setFullName(String fullName) {
        fullNameField.sendKeys(fullName);

        return this;
    }

    public CreateUserPage setEmailAddress(String emailAddress) {
        emailAddressField.sendKeys(emailAddress);

        return this;
    }


    public ManageUsersPage clickCreateUserButton() {
        createUserButton.click();

        return new ManageUsersPage(getDriver());
    }

    public CreateUserPage clearUserName() {
        userNameField.clear();

        return this;
    }

    public CreateUserPage clearPassword() {
        passwordField.clear();

        return this;
    }

    public CreateUserPage clearConfirmPassword() {
        confirmPasswordField.clear();

        return this;
    }

    public CreateUserPage clearFullName() {
        fullNameField.clear();

        return this;
    }

    public CreateUserPage clearEmailAddress() {
        emailAddressField.clear();

        return this;
    }
}
