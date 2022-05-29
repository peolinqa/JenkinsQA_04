package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

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


    /**
     * TC_12_07
     * Выберите любой язык программирования (из меню BROWSE LANGUAGES) и любую версию решения
     * (из раздела Alternative Versions, если такой раздел существует  для выбранного языка)
     * Подтвердите, что пользователь может сделать закладку на это решение на сайте Reddit
     * (нажав на иконку сайта Reddit, пользователь перейдет на Логин страницу сайта Reddit)
     */

    @Test
    public void testBookmarkTheVersionInSiteReddit() {
        String expectedResult = "Log in";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul/li/a[@ href='j.html']")).click();
        getDriver().findElement(By.xpath("//table/tbody/tr/td/a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//table/tbody/tr/td/a[@href='language-java-4.html']")).click();
        getDriver().findElement(By.xpath("//div[@id='voting']/p/a[@title='reddit']")).click();

        String actualResult =
                getDriver().findElement(By.xpath("//div[@class='Step__content']/h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }


    /**
     * TC_12_08 Подтвердите, что решение на языке Shakespeare входит в топ 20 всех решений,
     * в топ 10 решений на Esoteric Languages и в топ 6 решений-хитов. Но решение на языке Shakespeare
     * не входит в список топовых решений на реальных языках программирования.
     * (Можно написать одним тестом, но так, чтобы все Asserts были в конце теста. Или можно написать
     * отдельные тесты на каждый requirenment.)
     */

    @Test
    public void testTop20Shakespeare() {
        Boolean expectedResult = false;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();

        String[] result = new String[22];
        for (int i = 1; i < result.length; i++) {
            if (getDriver().findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);
    }

    @Test
    public void testTop10Shakespeare() {
        Boolean expectedResult = false;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul/li/a[@href='./toplist_esoteric.html']")).click();

        String[] result = new String[12];
        for (int i = 1; i < result.length; i++) {
            if (getDriver().findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);
    }

    @Test
    public void testTop6Shakespeare() {
        Boolean expectedResult = false;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul/li/a[@href='./tophits.html']")).click();

        String[] result = new String[8];
        for (int i = 1; i < result.length; i++) {
            if (getDriver().findElement(By.xpath("//tr[" + i + "]")).getText().contains("Shakespeare")) {
                expectedResult = true;
            }
        }

        Assert.assertTrue(expectedResult);
    }


    /**
     * TC_12_09 Подтвердите, что существует 6 версий решений на языке программирования Java.
     */

    @Test
    public void testVersionsOfJava() {
        int expectedResult = 6;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href ='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();

        int actualResult =
                getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td/a")).size() + 1;

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_10 Подтвердите, что самое большое количество комментариев для решений на языке Java
     * имеет версия “object-oriented version”
     */

    @Test
    public void testJavaObjectOrientedVersion() {
        int expectedResult = 33;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href ='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//tr/td/a[@href='language-java-1148.html']")).click();

        int[] result = new int[6];
        int max = Integer.parseInt(getDriver().findElement(By.xpath("//*[@id='category']/tbody/tr/td[4]")).getText());
        for (int i = 1; i < result.length; i++) {
            result[i] = Integer.parseInt(getDriver().findElement(By.xpath("//tr[" + (i + 1) + "]/td[4]")).getText());
            if (result[i] >= max) {
                max = result[i];
            }
        }

        Assert.assertEquals(max, expectedResult);
    }

}