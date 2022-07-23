package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.WebDriver;

public class PipelinePage extends BaseProjectPage {

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    public RenamePage<PipelinePage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new PipelinePage(getDriver()));
    }
}
