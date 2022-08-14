package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ErrorPage extends BaseHeaderFooterPage {

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    private WebElement headerWithError;

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private WebElement errorMessage;

    public String getErrorHeaderText() {
        return headerWithError.getText();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public boolean isDisplayedErrorHeader() {
        return headerWithError.isDisplayed();
    }
}
