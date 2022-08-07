package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseProjectDeleteWithAlertPage<Self extends BaseProjectDeleteWithAlertPage<?, SideMenu>, SideMenu> extends BaseProjectPage<Self, SideMenu> {

    public BaseProjectDeleteWithAlertPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickDeleteProjectAndConfirm() {
        deleteButton.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }
}
