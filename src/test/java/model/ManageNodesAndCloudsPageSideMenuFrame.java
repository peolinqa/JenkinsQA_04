package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class ManageNodesAndCloudsPageSideMenuFrame extends BaseModel<ManageNodesAndCloudsPageSideMenuFrame> {

    @FindBy(id = "yui-gen1-button")
    protected WebElement yesButton;

    public ManageNodesAndCloudsPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public HomePage confirmDeleteAndGoHomePage() {
        yesButton.click();

        return new HomePage(getDriver());
    }
}
