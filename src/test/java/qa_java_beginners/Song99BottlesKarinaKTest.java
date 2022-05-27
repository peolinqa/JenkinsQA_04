package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;

public class Song99BottlesKarinaKTest extends BaseTest {

    public static final String baseURL = "http://www.99-bottles-of-beer.net/";

        /* 1. Открыть базовую страницу
        2. Нажать на пункт меню BROWSE LANGUAGES
        3. Нажать на подменю J
        4. Подтвердить, что пользователь видит текст “All languages starting with the letter J are shown, sorted by Language.”
        */

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

    /*@Test
    //TEST NOT FINISHED

    //Подтвердите, что создатель решения на языке Mathematica - Brenton Bostick,
    // дата обновления решения на этом языке - 03/16/06, и что это решение имеет 1 комментарий
    public void TC_12_04() {

        String expectedResult = getDriver().findElement(By.xpath("//tbody/tr[22]/td[2]")).getText();

        getDriver().get(baseURL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//tbody/tr[22]/td[1]/a")).getText();
        //WebElement table = getDriver().findElement(By.xpath("//table[@id='category']"));

        Assert.assertEquals(actualResult, expectedResult);

     */
    }
