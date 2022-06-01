package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Marolespb8HW12Test extends BaseTest {

    @Test
    public void testTC_11_11() {

        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult = "Error: Precondition failed - Incomplete Input.";

        getDriver().get(url);

        getDriver().findElement(By.xpath("//input[@type='submit']")).click();

        getDriver().findElement(By.xpath("//div[@id='main']/p"));

        Assert.assertEquals("Error: Precondition failed - Incomplete Input.", expectedResult);

    }
}




