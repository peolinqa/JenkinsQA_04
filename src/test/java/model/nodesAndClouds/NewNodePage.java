package model.nodesAndClouds;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class NewNodePage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement inputNodeName;

    @FindBy(xpath = "//label[text()='Permanent Agent']")
    private WebElement radioBtnPermanentAgent;

    @FindBy(xpath = "//input[@id='ok']")
    private WebElement btnCreate;

    public NewNodePage(WebDriver driver) {
        super(driver);
    }

    public NewNodePage setInputNodeName(String text){
        inputNodeName.sendKeys(text);

        return this;
    }

    public NewNodePage selectPermanentAgentRadioButton() {
        radioBtnPermanentAgent.click();

        return this;
    }
    public NewNodeConfigPage clickCreateButton() {
        btnCreate.click();

        return new NewNodeConfigPage(getDriver());
    }
}
