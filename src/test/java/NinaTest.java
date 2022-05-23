import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore //added
public class NinaTest extends BaseTest {
    @Ignore
    @Test
    public void testTest(){

        getDriver().get("http://automationpractice.com/index.php");

        WebElement searchField = getDriver().findElement(By.id("search_query_top"));
        searchField.sendKeys("dress\n");

        WebElement item = getDriver().findElement(By.xpath("//a[contains(text(),'Printed Summer Dress')]"));
        item.click();

        WebElement pageItem = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(pageItem.getText(), "Printed Summer Dress");

    }


}
