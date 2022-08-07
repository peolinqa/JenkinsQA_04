package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class LoginPage extends BasePage {

    @FindBy(name = "j_username")
    private WebElement usernameText;

    @FindBy(name = "j_password")
    private WebElement passwordText;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage sendUser(String text) {
        usernameText.sendKeys(text);

        return this;
    }

    public LoginPage sendPassword(String text) {
        passwordText.sendKeys(text);

        return this;
    }

    public void login() {
        submitButton.click();
    }
}