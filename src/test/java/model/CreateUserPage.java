package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

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

    @FindBy(className = "error")
    private WebElement errorMessage;

    @FindBy(className = "error")
    private List<WebElement> errorMessagesList;

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

    public <T> T clickCreateUserButton(T page) {
        createUserButton.click();

        return page;
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

    public String getErrorMessage() {

        return errorMessage.getText();
    }

    public CreateUserPage getErrorMessagesList(Set<String> errorMessagesTextList) {
        errorMessagesList.stream().map(WebElement::getText).forEach(errorMessagesTextList::add);

        return new CreateUserPage(getDriver());
    }

    public String getCssValue(String cssProperty) {

        return errorMessage.getCssValue(cssProperty);
    }

    public String getAttributeFullName() {

        return fullNameField.getAttribute("value");
    }
}