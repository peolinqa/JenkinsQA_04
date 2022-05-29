package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliyaUA99BottlesOfBeer1HW12Test extends BaseTest {
    public static String url = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testBrowseLanguageMenuTest () {
        String expectedResult = "All languages starting with the letter J " +
                "are shown, sorted by Language.";

        getDriver().get(url);

        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();

        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();

        WebElement text = getDriver().findElement(
                By.xpath("//div[@id='main']/p"));

        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);






    }
}
