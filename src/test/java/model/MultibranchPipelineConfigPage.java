package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultibranchPipelineConfigPage extends BasePage {

    public MultibranchPipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "_.disabled")
    private WebElement checkboxDisable;

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    public MultibranchPipelineConfigPage clickCheckboxDisable() {
        checkboxDisable.click();

        return this;
    }

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new ProjectPage(getDriver());
    }

}
