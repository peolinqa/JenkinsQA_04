package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserStatusPage extends BasePage {

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement userName;

    public UserStatusPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {

        return userName.getText();
    }
}
