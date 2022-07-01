package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfigureGlobalSecurityPage extends BasePage {

    @FindBy(xpath = "//a[@title='Help for feature: SSHD Port']")
    private WebElement helpButton;

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public String getTextTooltipButtonHelpSSHServerPOM(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", helpButton);

        getActions().pause(500).moveToElement(helpButton).perform();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();
    }

}
