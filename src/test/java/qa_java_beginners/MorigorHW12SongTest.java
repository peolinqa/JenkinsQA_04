package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MorigorHW12SongTest extends BaseTest {

    @Test
    public void testAreLanguagesSortedByLetter() {
        String expectedResult
                = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")
        ).click();

        String actualResult = getDriver()
                .findElement(By.xpath("//div[@id='main']/p[text()]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmIfLanguageCorrect() {
        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")
        ).click();

        String actualResult = getDriver()
                .findElement(
                        By.xpath("//table[@id='category']/tbody/tr/td" +
                                "/a[@href='language-mysql-1252.html']")
                ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
