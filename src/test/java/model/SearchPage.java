package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends HomePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "error")
    private WebElement searchMessage;

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private WebElement searchMainPanel;

    public String getSearchMessageText() {
        return searchMessage.getText();
    }

    public String getSearchMainPanelText(){
        return searchMainPanel.getText();
    }
}