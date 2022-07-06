package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Page404Page extends BasePage {

    public Page404Page(WebDriver driver) {
        super(driver);
    }

    public void assertTitleOfPage404() {
        Assert.assertEquals(getDriver().getTitle(), "Error 404 Not Found");
    }
}
