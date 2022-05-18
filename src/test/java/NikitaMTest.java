import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NikitaMTest extends BaseTest {

    @Test
    public void testSearchW3schools() throws InterruptedException{

        final String searchWord = "Java Tutorial";

        getDriver().get("https://www.w3schools.com/");

        Assert.assertEquals(getDriver().getTitle(), "W3Schools Online Web Tutorials");

        getDriver().findElement(By.id("search2")).sendKeys(searchWord);
        Thread.sleep(1000);
        getDriver().findElement(By.id("learntocode_searchbtn")).click();

        Assert.assertEquals(getDriver().getTitle(), searchWord);
    }
}