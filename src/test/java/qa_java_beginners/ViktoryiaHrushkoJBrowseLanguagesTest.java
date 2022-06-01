package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class ViktoryiaHrushkoJBrowseLanguagesTest extends BaseTest {
    @Test
    public void testBrowseLanguagesJ(){
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver()
                .findElement(
                By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();

        getDriver()
                .findElement(
                        By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/a[@href='j.html']"))
                .click();

        WebElement jText = getDriver().findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/p"));
        String actualResult = jText.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
