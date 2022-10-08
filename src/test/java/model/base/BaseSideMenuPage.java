package model.base;

import org.openqa.selenium.WebDriver;

public abstract class BaseSideMenuPage<Self extends BaseSideMenuPage<?, ?>, SideMenu> extends BaseHeaderFooterPage<Self> {

    public BaseSideMenuPage(WebDriver driver) {
        super(driver);
    }

    public abstract SideMenu getSideMenu();
}
