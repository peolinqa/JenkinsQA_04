package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.function.Function;

public class BaseModel<Self extends BaseModel<?>> {

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

    protected WebDriverWait getWait20() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), 20);
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

    public <Value> Self assertEquals(Function<Self, Value> actual, Value expected) {
        Assert.assertEquals(actual.apply((Self)this), expected);

        return (Self)this;
    }

    public <Value> Self assertTrue(Function<Self, Value> value) {
        Assert.assertEquals(value.apply((Self)this), true);

        return (Self)this;
    }
}
