package model.nodesAndClouds;

import model.base.BaseSideMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class ManageNodesAndCloudsPage extends BaseSideMenuPage<ManageNodesAndCloudsPage, ManageNodesAndCloudsPageSideMenuFrame> {

    @FindBy(xpath = "//table[@id='computers']//td[2]")
    private List<WebElement> listComputerNames;

    @FindBy(xpath = "//div[@id='menuSelector']")
    private WebElement menuSelector;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnYes;

    public ManageNodesAndCloudsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ManageNodesAndCloudsPageSideMenuFrame getSideMenu() {
        return new ManageNodesAndCloudsPageSideMenuFrame(getDriver());
    }

    public ManageNodesAndCloudsPageSelectorMenuFrame clickDropDownMenu(String computerName) {

        for (WebElement s : listComputerNames) {
            if (s.getText().contains(computerName)) {
                getActions().moveToElement(s).build().perform();
            }
        }
        menuSelector.click();

        return new ManageNodesAndCloudsPageSelectorMenuFrame(getDriver());
    }

    public ManageNodesAndCloudsPage confirmDelete() {
        btnYes.click();

        return new ManageNodesAndCloudsPage(getDriver());
    }

    public List<String> getComputerNamesList() {
        return listComputerNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
