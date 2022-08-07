package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class PipelineProjectPageSideMenuFrame extends BaseModel<PipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(linkText = "Build Now")
    private WebElement menuBuild;

    @FindBy(linkText = "Build with Parameters")
    private WebElement menuBuildWithParameters;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    public PipelineProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelineProjectPage, PipelineProjectPageSideMenuFrame> clickMenuRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new PipelineProjectPage(getDriver()));
    }

    public PipelineProjectPage clickMenuBuildPipelineButton() {
        menuBuild.click();

        return new PipelineProjectPage(getDriver());
    }

    public BuildWithParametersPage clickMenuBuildWithParameters() {
        menuBuildWithParameters.click();

        return new BuildWithParametersPage(getDriver());
    }

    public PipelineProjectPage clickMenuBuildMultipleTimes(int number) {
        for (int i = 0; i < number; ++i) {
            menuBuild.click();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return new PipelineProjectPage(getDriver());
    }

    public HomePage clickMenuDeleteProjectAndConfirm() {
        menuDelete.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }
}
