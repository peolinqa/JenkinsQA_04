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

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private WebElement errorMessage;

    public void checkHeaderWithErrorAndAssert(String textError) {
        Assert.assertEquals(headerWithError.getText(),textError);
    }

    public String getErrorHeader() {
        return headerWithError.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
