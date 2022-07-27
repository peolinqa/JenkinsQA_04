package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultiConfigurationConfigPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[contains(@tooltip, 'Discard old builds')]")
    private WebElement helpButtonDiscardOldBuilds;

    @FindBy(xpath = "//div[@class='help']/div")
    private WebElement discardOldBuildsHiddenTextArea;

    @FindBy (xpath = "//button[@type='submit']")
    private  WebElement saveButton;

    public MultiConfigurationConfigPage(WebDriver driver) {
        super(driver);
    }

    public boolean helpButtonDiscardOldBuildsIsVisible() {
        return helpButtonDiscardOldBuilds.isDisplayed();
    }

    public String getAttributeHelpButtonDiscardOldBuilds(String attribute) {
        return helpButtonDiscardOldBuilds.getAttribute(attribute);
    }

    public MultiConfigurationConfigPage clickHelpButtonDiscardOldBuilds() {
        helpButtonDiscardOldBuilds.click();

        return this;
    }

    public String getTextDiscardOldBuildsHiddenTextArea() {
        return discardOldBuildsHiddenTextArea.getText();
    }

    public MultiConfigurationProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new MultiConfigurationProjectPage(getDriver());
    }
}
