package model.manageJenkins;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public final class ConfigureGlobalSecurityPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[@title='Help for feature: SSHD Port']")
    private WebElement btnHelpForFeature;

    @FindBy(className = "jenkins-section__header")
    private List<WebElement> listOfHeaders;

    @FindBy(className = "jenkins-help-button")
    private List<WebElement> listOfHelpButtons;

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    public int getSizeOfHeadersList() {
        return listOfHeaders.size();
    }

    public String getTooltipButtonHelpSSHServerText() {
        TestUtils.scrollToElement(getDriver(), btnHelpForFeature);
        getActions().pause(500).moveToElement(btnHelpForFeature).perform();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();
    }

    public List<String> getActualHeaderNamesList() {
        List<String> actualHeaders = new ArrayList<>();
        for (WebElement e : listOfHeaders) {
            actualHeaders.add(e.getText());
        }

        return actualHeaders;
    }

    private List<WebElement> getDisplayedHelpIconsList() {
        List<WebElement> helpIconList = new ArrayList<>();

        for (WebElement list : listOfHelpButtons) {
            TestUtils.scrollToElement(getDriver(), list);
            if (list.isDisplayed()) {
                helpIconList.add(list);
            }
        }

        return helpIconList;
    }

    public int countHelpIcons() {
        return getDisplayedHelpIconsList().size();
    }

    public List<String> getTooltipTextList() {

        List<WebElement> helpIconsList = getDisplayedHelpIconsList();
        List<String> tooltipText = new ArrayList<>();

        for (int i = 0; i < getDisplayedHelpIconsList().size(); i++) {

            getActions().pause(500).moveToElement(helpIconsList.get(i)).build().perform();
            tooltipText.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText());
        }

        return tooltipText;
    }
}
