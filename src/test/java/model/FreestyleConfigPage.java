package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class FreestyleConfigPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Save')]")
    private WebElement btnSave;

    @FindBy(name = "description")
    private WebElement textareaDescription;

    @FindBy(name = "githubProject")
    private WebElement checkBoxGithubProject;

    @FindBy(name = "_.projectUrlStr")
    private WebElement inputProjectUrl;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    private WebElement btnApply;

    @FindBy(xpath = "//div[@class = 'tab config-section-activator config_build_triggers']")
    private WebElement tabBuildTriggers;

    public FreestyleConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage saveProjectConfiguration() {
        btnSave.click();

        return new FreestyleProjectPage(getDriver());
    }

    public FreestyleConfigPage setDescription(String text) {
        textareaDescription.sendKeys(text);

        return this;
    }

    public String getDescriptionText() {
        return textareaDescription.getText();
    }

    public FreestyleConfigPage clickCheckBoxGithubProject() {
        checkBoxGithubProject.click();

        return this;
    }

    public FreestyleConfigPage setInputProjectUrl(String text) {
        inputProjectUrl.sendKeys(text);

        return this;
    }

    public String getInputProjectUrlAttrValue() {
        return inputProjectUrl.getAttribute("value");
    }

    public String getHelpNamesGeneral() {
        getActions().moveToElement(getDriver()
                .findElement(By.xpath("//label[text()='Discard old builds']/../a"))).pause(500).build().perform();
        getActions().moveToElement(getDriver()
                .findElement(By.xpath("//a[@tooltip='Help for feature: Discard old builds']"))).build().perform();

        return getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'tt']"))).getText();
    }

    public String getHelpNamesBuildTriggers() {
        getActions().moveToElement(getDriver()
                .findElement(By.xpath("//label[text()='Build periodically']/../a"))).pause(500).build().perform();
        getActions().moveToElement(getDriver()
                .findElement(By.xpath("//a[@tooltip='Help for feature: Build periodically']"))).build().perform();

        return getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'tt']"))).getText();
    }

    public FreestyleConfigPage clickTabBuildTriggers() {
        tabBuildTriggers.click();

        return this;
    }

    public boolean clickApplyAndGetAlert() {
        btnApply.click();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).isDisplayed();
    }
}
