package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnaLamaHW12Tests extends BaseTest {

        @Test
        public void testBrowseJLanguage(){
    String baseUrl = "http://www.99-bottles-of-beer.net/";
    String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

    getDriver().get(baseUrl);

    getDriver().findElement(By.xpath(
            "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']"))
            .click();
    getDriver().findElement(By.xpath(
            "//ul[@id='submenu']/li/a[@href='j.html']")).click();

    WebElement letJ = getDriver().findElement(By.xpath("//div[@id='main']/p"));
    String actualResult = letJ.getText();

            Assert.assertEquals(actualResult, expectedResult);
        }

        @Test
        public void testMLastLanguageMySQL(){

            String expectedResult = "MySQL";

            getDriver().get("http://www.99-bottles-of-beer.net/");

            getDriver().findElement(By.xpath(
                            "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")).click();

            getDriver().findElement(By.xpath(
                    "//ul[@id='submenu']/li/a[@href='m.html']")).click();

            WebElement mLet = getDriver().findElement(By.xpath("//tr[last()]/td/a"));

            String actualResult = mLet.getText();

            Assert.assertEquals(actualResult, expectedResult);

        }
}
