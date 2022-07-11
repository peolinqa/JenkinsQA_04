package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCredentialsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//input[@name='_.username']")
    WebElement userName;

    @FindBy(xpath = "//input[@name='_.password']")
    WebElement newPassword;

    @FindBy(id = "yui-gen1-button")
    private WebElement okButton;

    public NewCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public GlobalCredentialsPage createUserCredentials(String name, String password){
        userName.sendKeys(name);
        newPassword.sendKeys(password);
        okButton.click();

        return new GlobalCredentialsPage(getDriver());
    }
}
