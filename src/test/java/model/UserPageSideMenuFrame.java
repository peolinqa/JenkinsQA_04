package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class UserPageSideMenuFrame extends BaseModel<UserPageSideMenuFrame> {

    @FindBy(linkText = "Delete")
    private WebElement menuDelete;

    public UserPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public void clickMenuDelete() {
        menuDelete.click();
    }
}
