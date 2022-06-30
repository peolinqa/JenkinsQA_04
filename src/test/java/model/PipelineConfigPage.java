package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PipelineConfigPage extends BasePage {

    public PipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".samples select")
    private WebElement script;

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    public PipelineConfigPage selectScriptByValue(String name) {
        new Select(script).selectByValue(name);
        return this;
    }

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new ProjectPage(getDriver());
    }
}
