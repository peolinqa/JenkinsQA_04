package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewNodePage extends BasePage{

    @FindBy(xpath = "//input[@id='name']")
    WebElement nodeNameField;

    @FindBy(xpath = "//label[text()='Permanent Agent']")
    WebElement permanentAgentRadioButton;

    @FindBy(xpath = "//input[@id='ok']")
    WebElement createButton;

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
