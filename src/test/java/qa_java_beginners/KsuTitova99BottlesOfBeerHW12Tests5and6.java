package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KsuTitova99BottlesOfBeerHW12Tests5and6 extends BaseTest {

    /**
     * TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
     */

    @Test
    public void testLanguagesNamesStartWithNumbers() {
        int expectedResult = 10;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        String[] actualResult = new String[10];
        for (int i = 0; i < actualResult.length; i++) {
            actualResult[i] = getDriver().findElement(
                    By.xpath("//tbody/tr[@onmouseover][" + (i + 1) + "]")
            ).getText();
        }

        Assert.assertEquals(actualResult.length, expectedResult);
    }


    /**
     * TC_12_06 Подтвердите, что если на странице Sign Guestbook
     * http://www.99-bottles-of-beer.net/signv2.html вы заполните все поля формы,
     * но введете случайно сгенерированное трехзначное число в поле  Security Code: ,
     * то вы получите сообщение об ошибке  Error: Error: Invalid security code.
     */

    @Test
    public void testSecurityCodeField() {
        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath
                ("//form/p/input[@name='name']")).sendKeys("Ksenia");
        getDriver().findElement(By.xpath
                ("//form/p/input[@name='location']")).sendKeys("Sankt-Peterburg");
        getDriver().findElement(By.xpath
                ("//form/p/input[@name='email']")).sendKeys("ksunchik123@yandex.ru");
        getDriver().findElement(By.xpath
                ("//form/p/input[@name='homepage']")).sendKeys("https://www.google.com/");
        getDriver().findElement(By.xpath("//form/p/input[@name='captcha']")).sendKeys
                (Integer.toString((int) (Math.random() * 1000 + 100)));
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys
                ("Test");

        getDriver().findElement(By.xpath("//input[@name='submit']")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }






}