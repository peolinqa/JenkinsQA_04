package model;

import model.base.BaseSideMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;
import java.util.List;
import java.util.stream.Collectors;

public final class ManageNodesAndCloudsPage extends BaseSideMenuPage<ManageNodesAndCloudsPage, ManageNodesAndCloudsPageSideMenuFrame> {

    @FindBy(xpath = "//table[@id='computers']//td[2]")
    private List<WebElement> computerNames;

    @FindBy(xpath = "//span[text()='New Node']")
    private WebElement newNodeButton;

    @FindBy(xpath = "//div[@id='menuSelector']")
    private WebElement menuSelector;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButton;

    public ManageNodesAndCloudsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ManageNodesAndCloudsPageSideMenuFrame getSideMenu() {
        return new ManageNodesAndCloudsPageSideMenuFrame(getDriver());
    }

    public NewNodePage newNodeButtonClick() {
        newNodeButton.click();

        return new NewNodePage(getDriver());
    }

    public ManageNodesAndCloudsPageSelectorMenuFrame clickDropDownMenu(String computerName) {

        for (WebElement s : computerNames){
            if(s.getText().contains(computerName)){
                getActions().moveToElement(s).build().perform();
            }
        }
        menuSelector.click();

        return new ManageNodesAndCloudsPageSelectorMenuFrame(getDriver());
    }

    public ManageNodesAndCloudsPage confirmDeleteAndGoManageNodesAndCloudsPage() {
        yesButton.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }

    public List<String> getComputerNames() {
        return computerNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}