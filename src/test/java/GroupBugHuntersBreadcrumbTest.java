import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class GroupBugHuntersBreadcrumbTest extends BaseTest {
    @Test
    public void testCheckBreadcrumbs() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        String title = getDriver().getTitle();
        List<WebElement> breadcrumbs = getDriver().findElements(By.xpath("//ul[@id='breadcrumbs']/li"));

        Assert.assertEquals(title, "New Item [Jenkins]");
        Assert.assertEquals(breadcrumbs.get(0).getText(), "Dashboard");
        Assert.assertEquals(breadcrumbs.get(2).getText(), "All");

    }
}
