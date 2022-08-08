package model;

import model.base.BaseHeaderFooterPage;
import model.base.BaseSideMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class GlobalCredentialsPage extends BaseSideMenuPage<GlobalCredentialsPage,GlobalCredentialsPageSideMenuFrame> {

    @FindBy(xpath = "//div[@id='main-panel']/table")
    private WebElement sortablePaneBigtable;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GlobalCredentialsPageSideMenuFrame getSideMenu() {
        return new GlobalCredentialsPageSideMenuFrame(getDriver());
    }

    public String getTableText(){
        return sortablePaneBigtable.getText();
    }
}