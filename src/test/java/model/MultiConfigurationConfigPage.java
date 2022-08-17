package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MultiConfigurationConfigPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//a[contains(@tooltip, 'Discard old builds')]")
    private WebElement btnHelpDiscardOldBuilds;

    @FindBy(xpath = "//div[@class='help']/div")
    private WebElement textareaDiscardOldBuildsHidden;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;

    public MultiConfigurationConfigPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHelpButtonDiscardOldBuildsVisible() {
        return btnHelpDiscardOldBuilds.isDisplayed();
    }

    public String getHelpDiscardOldBuildsAttribute(String attribute) {
        return btnHelpDiscardOldBuilds.getAttribute(attribute);
    }

    public MultiConfigurationConfigPage clickBtnHelpDiscardOldBuilds() {
        btnHelpDiscardOldBuilds.click();

        return this;
    }

    public String getDiscardOldBuildsHiddenText() {
        return textareaDiscardOldBuildsHidden.getText();
    }

    public MultiConfigurationProjectPage saveProjectConfiguration() {
        btnSave.click();

        return new MultiConfigurationProjectPage(getDriver());
    }
}
