package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import runner.TestUtils;
import java.util.List;

public final class ManagePluginsPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> listAllPluginNames;

    @FindBy(xpath = "//thead/tr/th/a[text()='Name']/span")
    private WebElement btnSortArrow;

    @FindBy(xpath = "//a[contains(text(),'Updates')]")
    private WebElement tabUpdates;

    @FindBy(xpath = "//a[contains(text(),'Available')]")
    private WebElement tabAvailable;

    @FindBy(xpath = "//table[@id='plugins']//tbody//tr")
    private List<WebElement> listPlugins;

    @FindBy(id = "filter-box")
    private WebElement inputFilterBox;

    public ManagePluginsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllPluginNamesList() {
        List<String> tdList = new ArrayList<>();
        for (WebElement allTd : listAllPluginNames) {
            tdList.add(allTd.getAttribute("data").toLowerCase());
        }

        return tdList;
    }

    public String getBtnSortArrowText() {
        return btnSortArrow.getText();
    }

    public ManagePluginsPage clickBtnSortArrow() {
        btnSortArrow.click();

        return this;
    }

    public ManagePluginsPage sortAlphabeticallyFromAtoZ() {
        if (getBtnSortArrowText().contains("  ↑")) {
            clickBtnSortArrow();
        }

        return this;
    }

    public ManagePluginsPage changeSortAlphabeticallyFromZtoA() {
        if (getBtnSortArrowText().contains("  ↓")) {
            clickBtnSortArrow();
        }

        return this;
    }

    public List<String> getNamesOfCheckboxesText() {

        return TestUtils.getTextFromList(getDriver(),
                By.xpath("//tbody//tr[not(contains(@class, 'jenkins-hidden'))]//a[@class = 'jenkins-table__link']"));
    }

    public ManagePluginsPage setInputFilterBox(String s) {
        inputFilterBox.clear();
        inputFilterBox.sendKeys(s);

        return new ManagePluginsPage(getDriver());
    }


    public ManagePluginsPage clickTabUpdates() {
        tabUpdates.click();

        return this;
    }

    public ManagePluginsPage clickTabAvailable() {
        tabAvailable.click();

        return this;
    }

    public int countPlugins() {
        return listPlugins.size();
    }
}
