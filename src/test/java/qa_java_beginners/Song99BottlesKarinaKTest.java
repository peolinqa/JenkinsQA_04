package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;

@Ignore
public class Song99BottlesKarinaKTest extends BaseTest {

    public static final String baseURL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void test_TC_12_01() {

        getDriver().get(baseURL);

        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li[11]")).click();

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p")).getText(), expectedResult);
    }

    @Test
    // TC_12_02 Подтвердите, что в меню BROWSE LANGUAGES, подменю  M, последний программный язык в таблице -  MySQL
    public void TC_12_02() {


        getDriver().get(baseURL);

        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li[14]")).click();

        String expectedResult = "MySQL";
        String actualResult = getDriver().findElement(By.cssSelector("#category > tbody > tr:nth-child(89) > td:nth-child(1) > a")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    //TEST NOT FINISHED

    //Подтвердите, что создатель решения на языке Mathematica - Brenton Bostick,
    // дата обновления решения на этом языке - 03/16/06, и что это решение имеет 1 комментарий
    public void TC_12_04() {

        getDriver().get(baseURL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//div/ul[@id='submenu']/li/a[@href='m.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr[22]/td[1]/a")).getText(); //Mathematica

        String name = getDriver().findElement(By.xpath("//tbody/tr[22]/td[2]")).getText();
        String date = getDriver().findElement(By.xpath("//tbody/tr/td[text()='03/16/06']")).getText();
        String comment = getDriver().findElement(By.xpath("//tbody/tr/td[text()='1']")).getText();


        String expectedResult1 = "Brenton Bostick";
        String expectedResult2 = "03/16/06";
        String expectedResult3 = "1";

        Assert.assertEquals(name, expectedResult1);
        Assert.assertEquals(date, expectedResult2);
        Assert.assertEquals(comment, expectedResult3);
    }

    @Test
    public void TC_12_05() {

        //TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
        getDriver().get(baseURL);
        String expectedResult = "Brenton Bostick".concat("03/16/06").concat("1");

        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='m.html']")).click();
        String text = getDriver().findElement(By.xpath("//tr/td/a[@href='language-mathematica-1090.html']")).getText();

        String[] columns = new String[4];
        for (int i = 0; i < columns.length; i++) {
            int index = i + 1;
            columns[i] = getDriver().findElement(By.xpath("//tr/td/a[@href='language-mathematica-1090.html']/following::td[" + index + "]")).getText();
        }

        String actualResult = "";
        for (int i = 0; i < columns.length; i++) {
            actualResult = actualResult + columns[i];
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
}