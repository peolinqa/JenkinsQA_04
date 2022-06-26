import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _ManagepluginsTest extends BaseTest {

    private final By NAME_FILTER = By.xpath("//thead/tr/th/a[text()='Name']");
    private final By NAME_ARROW = By.xpath("//thead/tr/th/a[text()='Name']/span");
    private final By PLAGIN_NAMES = By.xpath("//tbody/tr/td[2]");

    @Test
    public void ManagePluginsCheckNameFilterTest() {
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']/dl/dt")).click();

        Assert.assertTrue(getDriver().findElement(NAME_FILTER).isDisplayed());
        Assert.assertEquals(getDriver().findElement(NAME_ARROW).getText(),
                "  ↓");

        List<WebElement> tdTable = getDriver().findElements(PLAGIN_NAMES);
        List<String> tdList = new ArrayList<>();

        for (WebElement alltd : tdTable) {
            tdList.add(alltd.getAttribute("data"));
        }

        for (int i = 0; i < tdList.size(); i++) {
            tdList.set(i, tdList.get(i).toLowerCase());
        }

        List noSortList = List.copyOf(tdList);
        Collections.sort(tdList);
        Assert.assertTrue(tdList.equals(noSortList));

        getDriver().findElement(NAME_FILTER).click();
        Assert.assertEquals(getDriver().findElement(NAME_ARROW).getText(),
                "  ↑");

        List<WebElement> tdTable2 = getDriver().findElements(PLAGIN_NAMES);
        List<String> tdList2 = new ArrayList<>();

        for (WebElement alltd2 : tdTable2) {
            tdList2.add(alltd2.getAttribute("data"));
        }

        for (int i = 0; i < tdList2.size(); i++) {
            tdList2.set(i, tdList2.get(i).toLowerCase());
        }

        Collections.sort(tdList, Collections.reverseOrder());
        Assert.assertTrue(tdList.equals(tdList2));
    }
}
