package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HW12SabinaSaadTest extends BaseTest {

    public static String URL = "http://www.99-bottles-of-beer.net/";

    @Test(invocationCount = 20)
    public void testSubMenuJBrowseLanguages() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div/div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
}
