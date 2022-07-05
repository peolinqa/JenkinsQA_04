package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAgentPage extends BasePage {

    @FindBy(xpath = "//button[@id='yui-gen1-button']")
    WebElement yesButton;

    public DeleteAgentPage(WebDriver driver) {
        super(driver);
    }

    public ManageNodesAndCloudsPage confirmToDeleteComputerNode() {
        yesButton.click();
        return new ManageNodesAndCloudsPage(getDriver());
    }
}
