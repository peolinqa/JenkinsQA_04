import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KarinaKTest extends BaseTest {

    private static String URL = ("http://automationpractice.com/index.php");

    @Test
    public void SearchDress() {

        getDriver().get(URL);

        WebElement searchDressOnPage = getDriver().findElement(By.name("search_query"));
        searchDressOnPage.sendKeys("dress");
        getDriver().findElement(By.name("submit_search")).click();
    }

    @Test
    public void test2() {
        getDriver().get(URL);

        WebElement buttonDress = getDriver().findElement
                (By.xpath("//div[@id='block_top_menu']//a[@href='http://automationpractice.com/index.php?id_category=3&controller=category']"));

        String expectedResult = "WOMEN";
        String actualResult = buttonDress.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
