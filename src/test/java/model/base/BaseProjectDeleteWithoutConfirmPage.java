package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;

public class BaseProjectDeleteWithoutConfirmPage extends BaseProjectPage{

    public BaseProjectDeleteWithoutConfirmPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickDeleteProjectAndConfirm() {
        deleteButton.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }
}
