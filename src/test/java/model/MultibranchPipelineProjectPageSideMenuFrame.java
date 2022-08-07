package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class MultibranchPipelineProjectPageSideMenuFrame extends BaseModel<MultibranchPipelineProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    public MultibranchPipelineProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<MultibranchPipelineProjectPage, MultibranchPipelineProjectPageSideMenuFrame> clickRenameAndGoToRenamePage() {
        renameButton.click();

        return new RenamePage<>(getDriver(), new MultibranchPipelineProjectPage(getDriver()));
    }
}
