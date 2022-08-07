package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class GlobalCredentialsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[@title='Add Credentials']")
    private WebElement addCredentials;

    @FindBy(xpath = "//div[@id='main-panel']/table")
    private WebElement sortablePaneBigtable;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public NewCredentialsPage clickAddCredentials(){
        addCredentials.click();

        return new NewCredentialsPage(getDriver());
    }

    public String getTableText(){
        return sortablePaneBigtable.getText();
    }
}