package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatZar99BottlesOfBeerTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testBrowseJLanguage() {


        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();

        WebElement phrase = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));

        String actualResult = phrase.getText();

        Assert.assertEquals(actualResult, expectedResult);


    }

    @Test


    public void testMySQL() {
        String expectedResult = "MySQL";

        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        WebElement mySQL = getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']"));

        String actualResult = mySQL.getText();
        Assert.assertEquals(actualResult, expectedResult);


    }
}

