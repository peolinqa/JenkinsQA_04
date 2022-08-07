package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;

public final class UserPage extends BaseProjectPage<UserPage, UserPageSideMenuFrame> {

    public UserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public UserPageSideMenuFrame getSideMenu() {
        return new UserPageSideMenuFrame(getDriver());
    }
}