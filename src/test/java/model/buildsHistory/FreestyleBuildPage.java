package model.buildsHistory;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;

public final class FreestyleBuildPage extends BaseBuildPage<FreestyleBuildPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    public FreestyleBuildPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
    }
}
