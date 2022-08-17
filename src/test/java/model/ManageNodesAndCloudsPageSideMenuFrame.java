package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class ManageNodesAndCloudsPageSideMenuFrame extends BaseModel<ManageNodesAndCloudsPageSideMenuFrame> {

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    @FindBy (xpath = "//span[text()='New Node']")
    private WebElement menuNewNode;

    public ManageNodesAndCloudsPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public NewNodePage clickMenuNewNode(){
        menuNewNode.click();

        return new NewNodePage(getDriver());
    }
}
