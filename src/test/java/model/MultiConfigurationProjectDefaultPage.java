package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MultiConfigurationProjectDefaultPage extends BaseHeaderFooterPage {

    @FindBy(linkText = "Workspace")
    private WebElement linkWorkspace;

    public MultiConfigurationProjectDefaultPage(WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationProjectWorkspacePage clickLinkWorkspace() {
        linkWorkspace.click();

        return new MultiConfigurationProjectWorkspacePage(getDriver());
    }
}
