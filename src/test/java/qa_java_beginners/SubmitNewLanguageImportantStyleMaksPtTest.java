package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SubmitNewLanguageImportantStyleMaksPtTest extends BaseTest {

    @Test
    public void testSubmitNewLanguageImportantStyle() {

        String expectedResultStyle = "background-color: red; color: white;";
        String expectedResultBolt = "b";
        String expectedResultCapital = "IMPORTANT:";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id = 'menu']/li/a[@href='/submitnewlanguage.html']")).click();

        String actualResultStyle = getDriver()
                .findElement(By.xpath("//div[@id='main']/ul/li/span"))
                .getAttribute("style");
        String actualResultBolt = getDriver().findElement(By.xpath("//div[@id='main']/ul/li/span/b")).getTagName();
        String actualResultCapital = getDriver().findElement(By.xpath("//div[@id='main']/ul/li/span/b")).getText();

        Assert.assertEquals(actualResultStyle, expectedResultStyle);
        Assert.assertEquals(actualResultBolt, expectedResultBolt);
        Assert.assertEquals(actualResultCapital, expectedResultCapital.toUpperCase());
    }
}