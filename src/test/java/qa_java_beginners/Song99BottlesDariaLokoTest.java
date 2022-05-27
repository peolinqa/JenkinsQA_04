package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BottlesDariaLokoTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testBrowseJ() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        WebElement title = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));
        String actualResult = title.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
