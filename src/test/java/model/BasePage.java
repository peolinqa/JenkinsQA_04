package model;

import org.openqa.selenium.WebDriver;
import runner.BaseModel;
import runner.ProjectUtils;

public class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public void goHome() {
        ProjectUtils.get(getDriver());
    }
}
