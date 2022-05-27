package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BottlesBahaSadyrTest extends BaseTest {

    public static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testLanguageJ_TC_12_01() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        String expectedRes = "All languages starting with the letter J are shown, sorted by Language.";
        String actualRes = getDriver().findElement(By.xpath("//p[contains(text(),'All languages starting with the letter')]")).getText().trim();

        Assert.assertEquals(actualRes, expectedRes);
    }

}
