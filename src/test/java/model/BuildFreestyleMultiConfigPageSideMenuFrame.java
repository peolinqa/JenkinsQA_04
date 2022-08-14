package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class BuildFreestyleMultiConfigPageSideMenuFrame extends BaseModel<BuildFreestyleMultiConfigPageSideMenuFrame> {

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

    public BuildFreestyleMultiConfigPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public BuildChangesPage clickMenuChanges() {
        menuChanges.click();

        return new BuildChangesPage(getDriver());
    }
}
