package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class MultibranchPipelineConfigPage extends BaseHeaderFooterPage {

    @FindBy(name = "_.disabled")
    private WebElement checkboxDisable;

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(id = "yui-gen1-button")
    private WebElement addSourceButton;

    @FindBy(id = "yui-gen10")
    private WebElement gitHubField;

    @FindBy(name = "_.repositoryUrl")
    private WebElement repositoryUrl;

    @FindBy(id = "yui-gen20-button")
    private WebElement validateButton;

    public MultibranchPipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineConfigPage clickCheckboxDisable() {
        checkboxDisable.click();

        return this;
    }

    public MultibranchPipelineProjectPage saveConfigAndGoToMultibranchPipelineProject() {
        saveButton.click();

        return new MultibranchPipelineProjectPage(getDriver());
    }

    public MultibranchPipelineProjectPage saveConfigAndGoToMultibranchPipelinePage() {
        saveButton.click();

        return new MultibranchPipelineProjectPage(getDriver());
    }

    public MultibranchPipelineConfigPage clickAddSourceButton() {
        addSourceButton.click();

        return this;
    }

    public MultibranchPipelineConfigPage clickGitHubField() {
        gitHubField.click();

        return this;
    }

    public MultibranchPipelineConfigPage setRepositoryUrl(String text) {
        repositoryUrl.sendKeys(text);

        return this;
    }

    public MultibranchPipelineConfigPage clickValidateButton() {
        validateButton.click();

        return this;
    }

    public String getValidateText() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(By.className("ok"))).getText();
    }

    public String getTextRepositoryUrl() {
        return repositoryUrl.getAttribute("value");
    }
}