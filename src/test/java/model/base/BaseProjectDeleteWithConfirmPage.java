package model.base;

import model.DeleteProjectPage;
import org.openqa.selenium.WebDriver;

public class BaseProjectDeleteWithConfirmPage extends BaseProjectPage{

    public BaseProjectDeleteWithConfirmPage(WebDriver driver) {
        super(driver);
    }

    public DeleteProjectPage clickDeleteProject() {
        deleteButton.click();

        return new DeleteProjectPage(getDriver());
    }
}
