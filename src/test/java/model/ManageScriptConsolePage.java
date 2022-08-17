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
    private WebElement textareaScriptConsole;

    @FindBy(css = "[type='submit']")
    private WebElement btnRun;

    @FindBy(xpath = "//a[text()='Groovy script']")
    private WebElement linkGroovyScript;

    @FindBy(xpath = "//h2/following-sibling::pre")
    private WebElement textResult;

    public ManageScriptConsolePage(WebDriver driver) {
        super(driver);
    }

    public ManageScriptConsolePage clickRunButton() {
        btnRun.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel > h2")));

        return this;
    }

    public ManageScriptConsolePage useDeleteAllProjectScript() {

        getActions().moveToElement(textareaScriptConsole).click().sendKeys(DELETE_SCRIPT).build().perform();

        return this;
    }

    public ManageScriptConsolePage clickLinkGroovyScript() {
        linkGroovyScript.click();

        return this;
    }

    public String getGroovyTitle() {
        return getDriver().getTitle();
    }

    public ManageScriptConsolePage setTextArea(String str) {
        getActions()
                .moveToElement(textareaScriptConsole)
                .click()
                .sendKeys(String.format("'%s'", str))
                .build()
                .perform();

        return this;
    }

    public String getResultText() {
        return textResult.getText();
    }
}
