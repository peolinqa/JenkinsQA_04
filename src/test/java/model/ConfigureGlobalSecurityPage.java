package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BasePage {

    @FindBy(xpath = "//a[@title='Help for feature: SSHD Port']")
    private WebElement helpButton;

    @FindBy(className = "jenkins-section__header")
    private List<WebElement> securityChapters;

    @FindBy(className = "jenkins-help-button")
    private List<WebElement> locatorHelp;

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSecurityChapters() {
        return securityChapters;
    }

    public String getTextTooltipButtonHelpSSHServerPOM() {
        TestUtils.scrollToElement(getDriver(), helpButton);

        getActions().pause(500).moveToElement(helpButton).perform();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();
    }

    public List<String> getActualSecurityChaptersNames() {
        List<String> actualChapters = new ArrayList<>();
        for (WebElement e : getSecurityChapters()) {
            actualChapters.add(e.getText());
        }
        return actualChapters;
    }

    public int countHelpIcons() {
        int actualAmountTooltip = 0;

        for (WebElement list : locatorHelp) {
            TestUtils.scrollToElement(getDriver(), list);
            if (list.isDisplayed()) {
                ++actualAmountTooltip;
            }
        }

        return actualAmountTooltip;
    }
}