package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class PipelineProjectPageSideMenuFrame extends BaseModel<PipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    @FindBy(linkText = "Build Now")
    private WebElement buildButton;

    @FindBy(linkText = "Build with Parameters")
    private WebElement buildWithParameters;

    @FindBy(css = ".icon-edit-delete")
    protected WebElement deleteButton;

    public PipelineProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelineProjectPage> clickRenameAndGoToRenamePage() {
        renameButton.click();

        return new RenamePage<>(getDriver(), new PipelineProjectPage(getDriver()));
    }

    public PipelineProjectPage clickBuildPipelineButton() {
        buildButton.click();

        return new PipelineProjectPage(getDriver());
    }

    public BuildWithParametersPage clickBuildWithParameters() {
        buildWithParameters.click();

        return new BuildWithParametersPage(getDriver());
    }

    public HomePage clickDeleteProjectAndConfirm() {
        deleteButton.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }

    public PipelineProjectPage clickMultipleTimesBuildButton(int number) {
        for (int i = 0; i < number; ++i) {
            buildButton.click();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return new PipelineProjectPage(getDriver());
    }
}
