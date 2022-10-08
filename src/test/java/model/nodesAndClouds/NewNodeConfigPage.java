package model.nodesAndClouds;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class NewNodeConfigPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//button[@id='yui-gen7-button']")
    private WebElement btnSave;

    public NewNodeConfigPage(WebDriver driver) {
        super(driver);
    }

    public ManageNodesAndCloudsPage clickSaveButton() {
        btnSave.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }
}
