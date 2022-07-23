package model;

import model.base.BaseProjectDeleteWithoutConfirmPage;
import org.openqa.selenium.WebDriver;

public class PipelinePage extends BaseProjectDeleteWithoutConfirmPage {

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelinePage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new PipelinePage(getDriver()));
    }
}
