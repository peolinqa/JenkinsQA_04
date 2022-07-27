package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CreateUserPage extends BaseHeaderFooterPage {

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

    public String getErrorMessage() {

        return errorMessage.getText();
    }

    public Set<String> getErrorMessagesList() {
        Set<String> errorMessagesTextList = new TreeSet<>();
        errorMessagesList.stream().map(WebElement::getText).forEach(errorMessagesTextList::add);

        return errorMessagesTextList;
    }

    public List<String> getCssValuesList() {
        List<String> cssProperties = List.of(
                "color", "font-weight", "padding-left", "min-height", "line-height",
                "background-image", "background-position", "background-repeat", "background-size");
        List<String> cssValuesList = new ArrayList<>();

        for (String cssProperty : cssProperties) {
            if (!cssProperty.contains("image")) {
                cssValuesList.add(errorMessage.getCssValue(cssProperty));
            }
        }

        return cssValuesList;
    }

    public String getAttributeFullName() {
        return fullNameField.getAttribute("value");
    }
}