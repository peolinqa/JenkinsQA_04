package model;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Page404 extends BasePage {

    public Page404(WebDriver driver) {
        super(driver);
    }

    public void assertTitleOfPage404() {
        Assert.assertEquals(getDriver().getTitle(), "Error 404 Not Found");
    }
}
