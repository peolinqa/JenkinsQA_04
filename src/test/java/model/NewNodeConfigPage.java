package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewNodeConfigPage extends BasePage {

    @FindBy(xpath = "//button[@id='yui-gen7-button']")
    private WebElement saveButton;

    public NewNodeConfigPage(WebDriver driver) {
        super(driver);
    }

    public ManageNodesAndCloudsPage clickSaveButton() {
        saveButton.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }
}
