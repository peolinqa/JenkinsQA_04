package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;

public final class BuildChangesPage extends BaseBuildPage<BuildChangesPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    public BuildChangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
    }
}