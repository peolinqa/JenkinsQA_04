package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ManagePluginsPage extends BasePage {
    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> allPluginNamesInTabUpdates;

    @FindBy(xpath = "//thead/tr/th/a[text()='Name']/span")
    private WebElement arrow;

    public ManagePluginsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllPluginNamesInTabUpdates(){
        List<String> tdList = new ArrayList<>();
        for (WebElement alltd : allPluginNamesInTabUpdates) {
            tdList.add(alltd.getAttribute("data").toLowerCase());
        }
        return tdList;
    }

    public ManagePluginsPage sortAlphabeticallyFromAtoZ(){
        if (arrow.getText().contains("  ↑")){
            arrow.click();
        }

        return this;
    }

    public ManagePluginsPage changeSortAlphabeticallyFromZtoA(){
        if (arrow.getText().contains("  ↓")){
            arrow.click();
        }

        return this;
    }
}
