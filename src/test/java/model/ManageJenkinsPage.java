package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage extends BasePage {
    @FindBy(xpath = "//dt[text()='Configure Global Security']")
    private WebElement configureGlobalSecurity;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public ConfigureGlobalSecurityPage clickConfigureGlobalSecurity() {
        configureGlobalSecurity.click();

        return new ConfigureGlobalSecurityPage(getDriver());
    }

}
