package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public final class ManageScriptConsolePage extends BaseHeaderFooterPage {
    private final String DELETE_SCRIPT = "for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}";

    @FindBy(xpath = "//div[@class='CodeMirror-scroll cm-s-default']")
    private WebElement console;

    @FindBy(css = "[type='submit']")
    private WebElement runButton;

    @FindBy(xpath = "//a[text()='Groovy script']")
    private WebElement groovyScript;

    @FindBy(css = ".CodeMirror-scroll.cm-s-default")
    private WebElement textArea;

    @FindBy(xpath = "//h2/following-sibling::pre")
    private WebElement result;

    public ManageScriptConsolePage(WebDriver driver) {
        super(driver);
    }

    public ManageScriptConsolePage clickRunButton() {
        runButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel > h2")));

        return this;
    }

    public ManageScriptConsolePage useDeleteAllProjectScript() {

        getActions().moveToElement(console).click().sendKeys(DELETE_SCRIPT).build().perform();

        return this;
    }

    public ManageScriptConsolePage clickGroovyScript() {
        groovyScript.click();

        return this;
    }

    public String getTitleGroovy() {
        return getDriver().getTitle();
    }

    public ManageScriptConsolePage setTextArea(String str) {
        getActions()
                .moveToElement(textArea)
                .click()
                .sendKeys(String.format("'%s'",str))
                .build()
                .perform();

        return this;
    }

    public String getResult() {
        return result.getText();
    }
}
