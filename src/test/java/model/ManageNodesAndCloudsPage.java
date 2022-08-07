package model;

import model.base.BaseHeaderFooterPage;
import model.base.BaseProjectDeleteWithConfirmPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;
import java.util.List;

public class ManageNodesAndCloudsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//table[@id='computers']//td[2]")
    private List<WebElement> computerNames;

    @FindBy(xpath = "//span[text()='New Node']")
    private WebElement newNodeButton;

    @FindBy(xpath = "//div[@id='menuSelector']")
    private WebElement menuSelectorHiddenButton;

    @FindBy(xpath = "//span[text()='Delete Agent']")
    private WebElement deleteAgentMenuButton;

    public ManageNodesAndCloudsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getComputerNames() {
        return computerNames;
    }

    public NewNodePage newNodeButtonClick() {
        newNodeButton.click();

        return new NewNodePage(getDriver());
    }

    public ManageNodesAndCloudsPage menuSelectorHiddenButtonClick(WebElement computerName) {
        getActions().moveToElement(computerName).build().perform();

        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id='menuSelector']")));
        getActions().moveToElement(menuSelectorHiddenButton).click().build().perform();

        return this;
    }

    public BaseProjectDeleteWithConfirmPage chooseDeleteMenuAfterClickMenuSelector(WebElement computerName) {
        menuSelectorHiddenButtonClick(computerName);

        getWait20().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='Delete Agent']"))).click();

        return new BaseProjectDeleteWithConfirmPage(getDriver());
    }

    public List<String> getTextComputerNamesFromTable() {
        List<String> textComputerNames = TestUtils
                .getTextFromList(getDriver(), By.xpath("//table[@id='computers']//td[2]"));

        return textComputerNames;
    }
}