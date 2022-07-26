package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MultiConfigurationProjectDefaultPage extends BasePage {

    @FindBy(linkText = "Workspace")
    private WebElement workspaceButton;

    public MultiConfigurationProjectDefaultPage(WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationProjectWorkspacePage clickWorkspaceButton() {
        workspaceButton.click();

        return new MultiConfigurationProjectWorkspacePage(getDriver());
    }
}