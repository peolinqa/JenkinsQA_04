package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BuildHistoryPage extends BasePage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td/a[contains(@href, 'job/')][1]")
    private List<WebElement> listBuildHistory;

    public BuildHistoryPage collectListBuildHistoryAndAssert(List<String> namesBuilds) {

        for (int i = 0; i < namesBuilds.size(); i++) {

            Assert.assertEquals(listBuildHistory.get(i).getText(), namesBuilds.get(i));
        }
        return this;
    }
}
