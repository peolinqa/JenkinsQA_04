package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ErrorPage extends BasePage{

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement headerWithError;

    public void checkHeaderWithErrorAndAssert() {
        Assert.assertEquals(headerWithError.getText(),"Error");
    }
}
