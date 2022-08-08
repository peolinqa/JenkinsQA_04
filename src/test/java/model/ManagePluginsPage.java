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
    private List<WebElement> allPluginNamesInTabUpdates;

    @FindBy(xpath = "//thead/tr/th/a[text()='Name']/span")
    private WebElement arrow;

    @FindBy(xpath = "//a[contains(text(),'Updates')]")
    private WebElement buttonUpdates;

    @FindBy(xpath = "//a[contains(text(),'Available')]")
    private WebElement buttonAvailable;

    @FindBy(xpath = "//table[@id='plugins']//tbody//tr")
    private List<WebElement> listPlugins;

    @FindBy(id = "filter-box")
    private WebElement searchField;

    public ManagePluginsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllPluginNamesInTabUpdates() {
        List<String> tdList = new ArrayList<>();
        for (WebElement alltd : allPluginNamesInTabUpdates) {
            tdList.add(alltd.getAttribute("data").toLowerCase());
        }

        return tdList;
    }

    public String getTextButtonArrow() {
        return arrow.getText();
    }

    public ManagePluginsPage clickButtonArrow() {
        arrow.click();

        return this;
    }

    public ManagePluginsPage sortAlphabeticallyFromAtoZ() {
        if (getTextButtonArrow().contains("  ↑")) {
            clickButtonArrow();
        }

        return this;
    }

    public ManagePluginsPage changeSortAlphabeticallyFromZtoA() {
        if (getTextButtonArrow().contains("  ↓")) {
            clickButtonArrow();
        }

        return this;
    }

    public List<String> getTextNamesOfCheckboxes() {

        return TestUtils.getTextFromList(getDriver(),
                By.xpath("//tbody//tr[not(contains(@class, 'jenkins-hidden'))]//a[@class = 'jenkins-table__link']"));
    }

    public ManagePluginsPage searchFieldInput(String s) {
        searchField.clear();
        searchField.sendKeys(s);

        return new ManagePluginsPage(getDriver());
    }


    public ManagePluginsPage clickButtonUpdates(){
        buttonUpdates.click();

        return this;
    }

    public ManagePluginsPage clickButtonAvailable(){
        buttonAvailable.click();

        return this;
    }

    public int countPlugins(){
        return listPlugins.size();
    }
}