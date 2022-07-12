package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApiPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement restApiMainTitle;

    public ApiPage(WebDriver driver) {
        super(driver);
    }

    public String getMainTitleFromApiPage() {
        return restApiMainTitle.getText();
    }
}
