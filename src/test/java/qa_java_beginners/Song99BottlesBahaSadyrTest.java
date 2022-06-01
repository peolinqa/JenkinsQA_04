package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Ignore
public class Song99BottlesBahaSadyrTest extends BaseTest {

    public static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testLanguageJ_TC_12_01() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        String expectedRes = "All languages starting with the letter J are shown, sorted by Language.";
        String actualRes = getDriver().findElement(By.xpath("//p[contains(text(),'All languages starting with the letter')]")).getText().trim();

        Assert.assertEquals(actualRes, expectedRes);
    }

    @Test
    public void test_TC_12_02() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String actualRes = getDriver().findElement(By.xpath("//table[@id='category']//tr[last()]//td//a")).getText();

        Assert.assertEquals(actualRes, "MySQL");
    }

    @Test
    public void test_TC_12_03() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        List<WebElement> listWithHeaders = getDriver().findElements(By.xpath("//table[@id='category']//tr//th"));
        List<String> actualRes = new ArrayList<>();
        for (int i = 0; i < listWithHeaders.size(); i++) {
            actualRes.add(listWithHeaders.get(i).getText());
        }
        List<String> expectRes = Arrays.asList("Language", "Author", "Date", "Comments", "Rate");

        Assert.assertEquals(actualRes, expectRes);
    }

    @Test
    public void test_TC_04() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String actualRes = getDriver().findElement(By.xpath("//table[@id='category']//tbody//tr//a[@href='language-mathematica-1090.html']//parent::td//parent::tr")).getText();
        String expectRes = "Mathematica Brenton Bostick 03/16/06 1";

        Assert.assertEquals(actualRes, expectRes);
    }

    @Test
    public void test_TC_05() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'0-9')]")).click();
        List<WebElement> listWithAllLang = getDriver().findElements(By.xpath("//table[@id='category']//tbody//tr//td//a"));

        Assert.assertEquals(listWithAllLang.size(),10);
    }
    @Test
    public void test_TC_06(){
        /**
         * Подтвердите, что если на странице Sign Guestbook http://www.99-bottles-of-beer.net/signv2.html
         * вы заполните все поля формы, но введете случайно сгенерированное
         трехзначное число в поле  Security Code: , то вы получите сообщение об ошибке  Error: Error: Invalid security code.
         */
        Random random = new Random();
        int generateNum = random.nextInt(900) + 100;
        String rnd = String.valueOf(generateNum);
        getDriver().get(BASE_URL + "/signv2.html");
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("Baha");
        getDriver().findElement(By.xpath("//input[@name='location']")).sendKeys("Chicago");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("someemail@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='homepage']")).sendKeys("pikabu.com");
        getDriver().findElement(By.xpath("//input[@name='captcha']")).sendKeys(rnd);
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("some comment");
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();
        String actualRes = getDriver().findElement(By.xpath("//div[@id='main']//p[contains(text(),' Error: Invalid security code.')]")).getText();
        String expectedRes = "Error: Error: Invalid security code.";

        Assert.assertEquals(actualRes,expectedRes);


    }
    @Test
    public void test_TC_07(){

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'A+')]")).click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1[contains(text(),'Log in') and @class='Title m-no-margin']")).isDisplayed());
    }
}
