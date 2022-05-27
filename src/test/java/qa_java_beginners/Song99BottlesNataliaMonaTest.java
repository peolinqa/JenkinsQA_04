package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BottlesNataliaMonaTest extends BaseTest{

    @Test
    public void testConfirmThePresenceOfASubmenuJInTheMenuBrowseLanguages(){

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='submenu']/li/a[@href ='j.html']")).click();
        WebElement text = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testConfirmTheLastProgrammingLanguage(){

        String expectedResult = "MySQL";

        getDriver().get("https://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='submenu']/li/a[@href ='m.html']")).click();
        WebElement language = getDriver().findElement(By.xpath(
                "//table[@id='category']/tbody/tr/td/a[@href='language-mysql-1252.html']"));
        String actualResult = language.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testConfirmTheTableWithHeaders(){

        String[] expectedResult = {"Language","Author", "Date","Comments","Rate"};

        getDriver().get("https://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")).click();
        WebElement language = getDriver().findElement(By.xpath(
                "//div[@id='main']/table/tbody/tr/th[@style='width: 40%;']"));
        WebElement author = getDriver().findElement(By.xpath(
                "//div[@id='main']/table/tbody/tr/th[@style='width: 30%;']"));
        WebElement date = getDriver().findElement(By.xpath("//div[@id='main']/table/tbody/tr/th[3]"));
        WebElement comments = getDriver().findElement(By.xpath("//div[@id='main']/table/tbody/tr/th[4]"));
        WebElement rate = getDriver().findElement(By.xpath("//div[@id='main']/table/tbody/tr/th[5]"));
        String[] actualResult = {language.getText(), author.getText(), date.getText(), comments.getText(), rate.getText()};

        Assert.assertEquals(actualResult,expectedResult);
    }
}
