package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApiPage extends BasePage {

    public ApiPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//div[@id='main-panel']/h1")
    private WebElement footerApi;

    public String getFooterApiText(){
        return footerApi.getText();
    }
}
