package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class CreateUserPage extends BaseHeaderFooterPage {

    @FindBy(id = "username")
    private WebElement fieldUserName;

    @FindBy(name = "password1")
    private WebElement fieldPassword;

    @FindBy(name = "password2")
    private WebElement fieldConfirmPassword;

    @FindBy(name = "fullname")
    private WebElement fieldFullName;

    @FindBy(name = "email")
    private WebElement fieldEmailAddress;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnCreateUser;

    @FindBy(className = "error")
    private WebElement errorMessage;

    @FindBy(className = "error")
    private List<WebElement> listErrorMessages;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUserName(String userName) {
        fieldUserName.sendKeys(userName);

        return this;
    }

    public CreateUserPage setPassword(String password) {
        fieldPassword.sendKeys(password);

        return this;
    }

    public CreateUserPage setConfirmPassword(String confirmPassword) {
        fieldConfirmPassword.sendKeys(confirmPassword);

        return this;
    }

    public CreateUserPage setFullName(String fullName) {
        fieldFullName.sendKeys(fullName);

        return this;
    }

    public CreateUserPage setEmailAddress(String emailAddress) {
        fieldEmailAddress.sendKeys(emailAddress);

        return this;
    }

    public <T> T clickCreateUserButton(T page) {
        btnCreateUser.click();

        return page;
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public Set<String> getErrorMessagesList() {
        Set<String> errorMessagesTextList = new TreeSet<>();
        listErrorMessages.stream().map(WebElement::getText).forEach(errorMessagesTextList::add);

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

    public String getFullNameAttributeValue() {
        return fieldFullName.getAttribute("value");
    }
}
