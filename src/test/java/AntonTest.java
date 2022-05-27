import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class AntonTest extends BaseTest {

    public static final String URL = "https://99-bottles-of-beer.net/";

    @Test
    public void testCheckTitle() {
        getDriver().get(URL);
        Assert.assertEquals(getDriver().getTitle(), "99 Bottles of Beer | Start");
    }
@Ignore
    @Test
    public void testCheckWeatherInCity() {
        final String JAVA = "Java";

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Search Languages")).click();
        getDriver().findElement(By.xpath("//input[@name='search']")).sendKeys("Java" + Keys.ENTER);

        List<WebElement> trs  = getDriver().findElements(By.xpath("//tbody/tr[@onmouseover]"));
        for (WebElement element : trs) {
           Assert.assertTrue(element.getText().contains(JAVA));
        }
    }
}