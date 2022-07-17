package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ManageScriptConsolePage extends BasePage {

    public ManageScriptConsolePage(WebDriver driver) {
        super(driver);
    }

    private final String DELETE_SCRIPT = "for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}";

    @FindBy(xpath = "//div[@class='CodeMirror-scroll cm-s-default']")
    private WebElement console;

    @FindBy(css = "[type='submit']")
    private WebElement runButton;


    public ManageScriptConsolePage clickRunButton() {
        runButton.click();

        return this;
    }

    public ManageScriptConsolePage useDeleteAllProjectScript() {

        getActions().moveToElement(console).click().sendKeys(DELETE_SCRIPT).build().perform();

        return this;
    }

}
