package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NoSuchJobErrorPage extends BasePage {

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement error;

    public NoSuchJobErrorPage(WebDriver driver) {
        super(driver);
    }

    public String errorMessage(){
       return error.getText();
    }
}
