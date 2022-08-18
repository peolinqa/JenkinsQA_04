package model.projects.multibranch;

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
    private WebElement btnSave;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnAddSource;

    @FindBy(id = "yui-gen10")
    private WebElement gitHubSelectorMenu;

    @FindBy(name = "_.repositoryUrl")
    private WebElement inputRepositoryUrl;

    @FindBy(id = "yui-gen20-button")
    private WebElement btnValidate;

    public MultibranchPipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelineConfigPage clickCheckboxDisable() {
        checkboxDisable.click();

        return this;
    }

    public MultibranchPipelineProjectPage saveProjectConfiguration() {
        btnSave.click();

        return new MultibranchPipelineProjectPage(getDriver());
    }

    public MultibranchPipelineConfigPage clickAddSourceButton() {
        btnAddSource.click();

        return this;
    }

    public MultibranchPipelineConfigPage clickGitHubSelectorMenu() {
        gitHubSelectorMenu.click();

        return this;
    }

    public MultibranchPipelineConfigPage setInputRepositoryUrl(String text) {
        inputRepositoryUrl.sendKeys(text);

        return this;
    }

    public MultibranchPipelineConfigPage clickValidateButton() {
        btnValidate.click();

        return this;
    }

    public String getValidateText() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(By.className("ok"))).getText();
    }

    public String getRepositoryUrlText() {
        return inputRepositoryUrl.getAttribute("value");
    }
}
