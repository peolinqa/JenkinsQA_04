package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HW12KononirinIrinaShTest extends BaseTest {

    @Test
    public void testTitleSubmenuByJ() {

        String expectedResult = "All languages starting with the letter J "
                + "are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']"))
                .click();

        getDriver()
                .findElement(
                        By.xpath("//li/a[@href='j.html']"))
                .click();

        String actualResult =
                getDriver()
                        .findElement(
                                By.xpath("//div[@id='main']/p"))
                        .getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLastLangMySQL() {

        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLang = getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']"));
        menuBrowseLang.click();

        WebElement subMenuM = getDriver()
                .findElement(
                        By.xpath("//a[@href='m.html']"));
        subMenuM.click();

        WebElement lastLangMySQL = getDriver()
                .findElement(By.xpath("//tr[last()]/td/a"));

        String actualResult = lastLangMySQL.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
