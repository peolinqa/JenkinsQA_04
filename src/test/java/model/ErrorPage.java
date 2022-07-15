package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends BasePage {

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement headerWithError;

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private WebElement errorMessage;

    public String getErrorHeader() {
        return headerWithError.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isDisplayedErrorHeader() {

        return headerWithError.isDisplayed();
    }
}
