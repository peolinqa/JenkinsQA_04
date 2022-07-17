package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserBuildPage extends BaseHeaderFooterPage {

    @FindBy(tagName = "h1")
    private WebElement userName;

    public String getTextName() {
        return userName.getText();
    }

    public UserBuildPage(WebDriver driver) {
        super(driver);
    }
}
