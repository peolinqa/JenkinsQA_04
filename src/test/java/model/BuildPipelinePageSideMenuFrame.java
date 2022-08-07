package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class BuildPipelinePageSideMenuFrame extends BaseModel<BuildPipelinePageSideMenuFrame> {

    @FindBy(linkText = "Back to Project")
    private WebElement menuBackToProject;

    @FindBy(linkText = "Status")
    private WebElement menuStatus;

    @FindBy(linkText = "Changes")
    private WebElement menuChanges;

    @FindBy(linkText = "Console Output")
    private WebElement menuConsoleOutput;

    @FindBy(linkText = "Edit Build Information")
    private WebElement menuEditBuildInformation;

    @FindBy(css = "a[title^='Delete build']")
    private WebElement menuDeleteBuild;

    public BuildPipelinePageSideMenuFrame(WebDriver driver) {
        super(driver);
    }
}
