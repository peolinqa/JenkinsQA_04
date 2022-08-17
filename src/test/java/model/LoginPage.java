package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class LoginPage extends BasePage {

    @FindBy(name = "j_username")
    private WebElement inputUsername;

    @FindBy(name = "j_password")
    private WebElement inputPassword;

    @FindBy(name = "Submit")
    private WebElement btnSignIn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setUsername(String text) {
        inputUsername.sendKeys(text);

        return this;
    }

    public LoginPage setPassword(String text) {
        inputPassword.sendKeys(text);

        return this;
    }

    public void clickSignInButton() {
        btnSignIn.click();
    }
}
