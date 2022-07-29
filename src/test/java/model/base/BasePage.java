package model.base;

import model.HomePage;
import org.openqa.selenium.WebDriver;
import runner.BaseModel;
import runner.ProjectUtils;


public abstract class BasePage<Self extends BasePage<?>> extends BaseModel<Self> {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goHome() {
        ProjectUtils.get(getDriver());

        return new HomePage(getDriver());
    }
}