package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageNodesAndCloudsPageSelectorMenuFrame extends BasePage {

    @FindBy(xpath = "//span[text()='Delete Agent']")
    private WebElement menuDeleteAgent;

    public ManageNodesAndCloudsPageSelectorMenuFrame(WebDriver driver) {
        super(driver);
    }

    public ManageNodesAndCloudsPage selectMenuDeleteAgentAndGoToManageNodesAndCloudsPage() {
        menuDeleteAgent.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }
}
