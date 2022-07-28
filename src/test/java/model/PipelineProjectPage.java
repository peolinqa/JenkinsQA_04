package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.WebDriver;

public class PipelineProjectPage extends BaseProjectDeleteWithAlertPage {

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelineProjectPage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new PipelineProjectPage(getDriver()));
    }
}
