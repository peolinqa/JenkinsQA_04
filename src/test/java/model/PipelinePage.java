package model;

import model.base.BaseProjectDeleteWithAlertPage;
import org.openqa.selenium.WebDriver;

public class PipelinePage extends BaseProjectDeleteWithAlertPage {

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelinePage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new PipelinePage(getDriver()));
    }
}
