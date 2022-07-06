package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewNodePage extends BasePage {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nodeNameField;

    @FindBy(xpath = "//label[text()='Permanent Agent']")
   private WebElement permanentAgentRadioButton;

    @FindBy(xpath = "//input[@id='ok']")
    private WebElement createButton;

    public NewNodePage(WebDriver driver) {
        super(driver);
    }

    public NewNodeConfigPage createNewNodeWithPermanentAgentOption(String text){
        nodeNameField.sendKeys(text);
        permanentAgentRadioButton.click();
        createButton.click();

        return new NewNodeConfigPage(getDriver());
    }
}
