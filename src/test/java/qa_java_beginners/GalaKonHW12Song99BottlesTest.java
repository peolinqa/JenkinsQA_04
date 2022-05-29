package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Random;

public class GalaKonHW12Song99BottlesTest extends BaseTest {

    public static final String URL = "http://www.99-bottles-of-beer.net";

    @Test
    public void testLanguageNamesStartFromJ() throws InterruptedException {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);

        getDriver().findElement(
                By.xpath("//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();

        getDriver().findElement(
                By.xpath("//a[@href='j.html']")
        ).click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='main']/p[1]")
        ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSubmenuMhasMyAQL() throws InterruptedException {

        String expectedResult = "MySQL";

        getDriver().get(URL);

        getDriver().findElement(
                By.xpath("//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();

        getDriver().findElement(
                By.xpath("//a[@href='m.html']")
        ).click();

        String findMySQL = getDriver().findElement(
                By.xpath("//table")
        ).getText();

        if (findMySQL.contains("MySQL")) {
            Assert.assertTrue(true);
        }

        String actualResult = getDriver().findElement(
                By.xpath("//a[@href='language-mysql-1252.html']")
        ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableHasTitlesLanguageAuthorDateCommentsRate() throws InterruptedException {

        String expectedResultLanguage = "Language";
        String expectedResultAuthor = "Author";
        String expectedResultDate = "Date";
        String expectedResultComments = "Comments";
        String expectedResultRate = "Rate";

        getDriver().get(URL);

        getDriver().findElement(
                By.xpath("//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();

        String findMySQL = getDriver().findElement(
                By.xpath("//tr")
        ).getText();

        if (findMySQL.contains("Language")) {
            Assert.assertTrue(true);
        }
        if (findMySQL.contains("Author")) {
            Assert.assertTrue(true);
        }
        if (findMySQL.contains("Date")) {
            Assert.assertTrue(true);
        }
        if (findMySQL.contains("Comments")) {
            Assert.assertTrue(true);
        }
        if (findMySQL.contains("Rate")) {
            Assert.assertTrue(true);
        }

        String actualResultLanguage = getDriver().findElement(
                By.xpath("//th[@style='width: 40%;']")
        ).getText();
        String actualResultAuthor = getDriver().findElement(
                By.xpath("//th[@style='width: 30%;']")
        ).getText();
        String actualResultDate = getDriver().findElement(
                By.xpath("//th[3]")
        ).getText();
        String actualResultComments = getDriver().findElement(
                By.xpath("//th[4]")
        ).getText();
        String actualResultRate = getDriver().findElement(
                By.xpath("//th[5]")
        ).getText();

        Assert.assertEquals(actualResultLanguage, expectedResultLanguage);
        Assert.assertEquals(actualResultAuthor, expectedResultAuthor);
        Assert.assertEquals(actualResultDate, expectedResultDate);
        Assert.assertEquals(actualResultComments, expectedResultComments);
        Assert.assertEquals(actualResultRate, expectedResultRate);
    }

    @Test
    public void testSecuritiCodeSpace() throws InterruptedException {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get(URL);

        getDriver().findElement(
                By.xpath("//a[@href='/guestbookv2.html']")
        ).click();

        getDriver().findElement(
                By.xpath("//a[@href='./signv2.html']")
        ).click();

        getDriver().findElement(
                By.xpath("//input[@name='name']")
        ).sendKeys("Gala");

        getDriver().findElement(
                By.xpath("//input[@name='email']")
        ).sendKeys("gala.kon13@gmail.com");

        Random random = new Random();
        int randomNumber = random.nextInt();
        String captcha = Integer.toString(randomNumber);

        getDriver().findElement(
                By.xpath("//input[@name='captcha']")
        ).sendKeys(captcha);

        getDriver().findElement(
                By.xpath("//textarea")
        ).sendKeys("Hello");

        getDriver().findElement(
                By.xpath("//input[@type='submit']")
        ).click();

        String actualResult = getDriver(
        ).findElement(
                By.xpath(
                        "//p[@style='border: 1px solid red; background-color: #ffe0e0; padding: 5px; margin: 5px 10px 5px 10px;']"
                )).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
