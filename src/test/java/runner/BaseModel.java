package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseModel {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait5() {
        if (wait == null) {
            wait = new WebDriverWait(driver, 5);
        }
        return wait;
    }

    protected Actions getActions() {
        if(actions == null) {
            actions = new Actions(driver);
        }
        return actions;
    }

    public BaseModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }
}
