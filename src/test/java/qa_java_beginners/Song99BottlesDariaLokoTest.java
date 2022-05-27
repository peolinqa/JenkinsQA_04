package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class Song99BottlesDariaLokoTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testBrowseJ() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        WebElement title = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));
        String actualResult = title.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBrowseM() {
        String expectedResult = "MySQL";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[@href='m.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']")).getText();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testTableTitles() {
        String[] expectedResult = {"Language", "Author", "Date", "Comments", "Rate"};
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        List<WebElement> actualResult = getDriver().findElements(By.xpath("//table[@id='category']//tr/th"));
        for (int i = 0; i < expectedResult.length; i++) {

            Assert.assertEquals(actualResult.get(i).getText(),expectedResult[i]);
        }
    }
    @Test
    public void testMathematica() {
        String[] expectedResult = {"Mathematica","Brenton Bostick","03/16/06","1"};
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[@href='m.html']")).click();
        String[] actualResult = new String[4];
        actualResult[0] = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']/../../td[1]"))
                .getText();
        actualResult[1] = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']/../../td[2]"))
                .getText();
        actualResult[2] = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']/../../td[3]"))
                .getText();
        actualResult[3] = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']/../../td[4]"))
                .getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testDigitsLanguages() {
        int expectedResult = 10;
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[@href='0.html']")).click();
        List<WebElement> actualResult = getDriver().findElements(By.xpath("//table[@id='category']//tr/td/a"));

        Assert.assertEquals(actualResult.size(),expectedResult);
    }

}
