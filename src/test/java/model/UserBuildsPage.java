package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class UserBuildsPage extends BaseHeaderFooterPage {

    @FindBy(tagName = "h1")
    private WebElement userName;

    public UserBuildsPage(WebDriver driver) {
        super(driver);
    }

    public String getUserNameText() {
        return userName.getText();
    }
}
