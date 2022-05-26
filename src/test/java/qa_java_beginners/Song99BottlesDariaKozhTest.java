package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Song99BottlesDariaKozhTest extends BaseTest {

    @Test
    public void testLyricsText() {

        String expectedResult = "99 bottles of beer on the wall, 99 bottles of beer.\n" +
                "Take one down and pass it around, 98 bottles of beer on the wall.98 bottles of beer on the wall, 98 bottles of beer.\n" +
                "Take one down and pass it around, 97 bottles of beer on the wall.97 bottles of beer on the wall, 97 bottles of beer.\n" +
                "Take one down and pass it around, 96 bottles of beer on the wall.96 bottles of beer on the wall, 96 bottles of beer.\n" +
                "Take one down and pass it around, 95 bottles of beer on the wall.95 bottles of beer on the wall, 95 bottles of beer.\n" +
                "Take one down and pass it around, 94 bottles of beer on the wall.94 bottles of beer on the wall, 94 bottles of beer.\n" +
                "Take one down and pass it around, 93 bottles of beer on the wall.93 bottles of beer on the wall, 93 bottles of beer.\n" +
                "Take one down and pass it around, 92 bottles of beer on the wall.92 bottles of beer on the wall, 92 bottles of beer.\n" +
                "Take one down and pass it around, 91 bottles of beer on the wall.91 bottles of beer on the wall, 91 bottles of beer.\n" +
                "Take one down and pass it around, 90 bottles of beer on the wall.90 bottles of beer on the wall, 90 bottles of beer.\n" +
                "Take one down and pass it around, 89 bottles of beer on the wall.89 bottles of beer on the wall, 89 bottles of beer.\n" +
                "Take one down and pass it around, 88 bottles of beer on the wall.88 bottles of beer on the wall, 88 bottles of beer.\n" +
                "Take one down and pass it around, 87 bottles of beer on the wall.87 bottles of beer on the wall, 87 bottles of beer.\n" +
                "Take one down and pass it around, 86 bottles of beer on the wall.86 bottles of beer on the wall, 86 bottles of beer.\n" +
                "Take one down and pass it around, 85 bottles of beer on the wall.85 bottles of beer on the wall, 85 bottles of beer.\n" +
                "Take one down and pass it around, 84 bottles of beer on the wall.84 bottles of beer on the wall, 84 bottles of beer.\n" +
                "Take one down and pass it around, 83 bottles of beer on the wall.83 bottles of beer on the wall, 83 bottles of beer.\n" +
                "Take one down and pass it around, 82 bottles of beer on the wall.82 bottles of beer on the wall, 82 bottles of beer.\n" +
                "Take one down and pass it around, 81 bottles of beer on the wall.81 bottles of beer on the wall, 81 bottles of beer.\n" +
                "Take one down and pass it around, 80 bottles of beer on the wall.80 bottles of beer on the wall, 80 bottles of beer.\n" +
                "Take one down and pass it around, 79 bottles of beer on the wall.79 bottles of beer on the wall, 79 bottles of beer.\n" +
                "Take one down and pass it around, 78 bottles of beer on the wall.78 bottles of beer on the wall, 78 bottles of beer.\n" +
                "Take one down and pass it around, 77 bottles of beer on the wall.77 bottles of beer on the wall, 77 bottles of beer.\n" +
                "Take one down and pass it around, 76 bottles of beer on the wall.76 bottles of beer on the wall, 76 bottles of beer.\n" +
                "Take one down and pass it around, 75 bottles of beer on the wall.75 bottles of beer on the wall, 75 bottles of beer.\n" +
                "Take one down and pass it around, 74 bottles of beer on the wall.74 bottles of beer on the wall, 74 bottles of beer.\n" +
                "Take one down and pass it around, 73 bottles of beer on the wall.73 bottles of beer on the wall, 73 bottles of beer.\n" +
                "Take one down and pass it around, 72 bottles of beer on the wall.72 bottles of beer on the wall, 72 bottles of beer.\n" +
                "Take one down and pass it around, 71 bottles of beer on the wall.71 bottles of beer on the wall, 71 bottles of beer.\n" +
                "Take one down and pass it around, 70 bottles of beer on the wall.70 bottles of beer on the wall, 70 bottles of beer.\n" +
                "Take one down and pass it around, 69 bottles of beer on the wall.69 bottles of beer on the wall, 69 bottles of beer.\n" +
                "Take one down and pass it around, 68 bottles of beer on the wall.68 bottles of beer on the wall, 68 bottles of beer.\n" +
                "Take one down and pass it around, 67 bottles of beer on the wall.67 bottles of beer on the wall, 67 bottles of beer.\n" +
                "Take one down and pass it around, 66 bottles of beer on the wall.66 bottles of beer on the wall, 66 bottles of beer.\n" +
                "Take one down and pass it around, 65 bottles of beer on the wall.65 bottles of beer on the wall, 65 bottles of beer.\n" +
                "Take one down and pass it around, 64 bottles of beer on the wall.64 bottles of beer on the wall, 64 bottles of beer.\n" +
                "Take one down and pass it around, 63 bottles of beer on the wall.63 bottles of beer on the wall, 63 bottles of beer.\n" +
                "Take one down and pass it around, 62 bottles of beer on the wall.62 bottles of beer on the wall, 62 bottles of beer.\n" +
                "Take one down and pass it around, 61 bottles of beer on the wall.61 bottles of beer on the wall, 61 bottles of beer.\n" +
                "Take one down and pass it around, 60 bottles of beer on the wall.60 bottles of beer on the wall, 60 bottles of beer.\n" +
                "Take one down and pass it around, 59 bottles of beer on the wall.59 bottles of beer on the wall, 59 bottles of beer.\n" +
                "Take one down and pass it around, 58 bottles of beer on the wall.58 bottles of beer on the wall, 58 bottles of beer.\n" +
                "Take one down and pass it around, 57 bottles of beer on the wall.57 bottles of beer on the wall, 57 bottles of beer.\n" +
                "Take one down and pass it around, 56 bottles of beer on the wall.56 bottles of beer on the wall, 56 bottles of beer.\n" +
                "Take one down and pass it around, 55 bottles of beer on the wall.55 bottles of beer on the wall, 55 bottles of beer.\n" +
                "Take one down and pass it around, 54 bottles of beer on the wall.54 bottles of beer on the wall, 54 bottles of beer.\n" +
                "Take one down and pass it around, 53 bottles of beer on the wall.53 bottles of beer on the wall, 53 bottles of beer.\n" +
                "Take one down and pass it around, 52 bottles of beer on the wall.52 bottles of beer on the wall, 52 bottles of beer.\n" +
                "Take one down and pass it around, 51 bottles of beer on the wall.51 bottles of beer on the wall, 51 bottles of beer.\n" +
                "Take one down and pass it around, 50 bottles of beer on the wall.50 bottles of beer on the wall, 50 bottles of beer.\n" +
                "Take one down and pass it around, 49 bottles of beer on the wall.49 bottles of beer on the wall, 49 bottles of beer.\n" +
                "Take one down and pass it around, 48 bottles of beer on the wall.48 bottles of beer on the wall, 48 bottles of beer.\n" +
                "Take one down and pass it around, 47 bottles of beer on the wall.47 bottles of beer on the wall, 47 bottles of beer.\n" +
                "Take one down and pass it around, 46 bottles of beer on the wall.46 bottles of beer on the wall, 46 bottles of beer.\n" +
                "Take one down and pass it around, 45 bottles of beer on the wall.45 bottles of beer on the wall, 45 bottles of beer.\n" +
                "Take one down and pass it around, 44 bottles of beer on the wall.44 bottles of beer on the wall, 44 bottles of beer.\n" +
                "Take one down and pass it around, 43 bottles of beer on the wall.43 bottles of beer on the wall, 43 bottles of beer.\n" +
                "Take one down and pass it around, 42 bottles of beer on the wall.42 bottles of beer on the wall, 42 bottles of beer.\n" +
                "Take one down and pass it around, 41 bottles of beer on the wall.41 bottles of beer on the wall, 41 bottles of beer.\n" +
                "Take one down and pass it around, 40 bottles of beer on the wall.40 bottles of beer on the wall, 40 bottles of beer.\n" +
                "Take one down and pass it around, 39 bottles of beer on the wall.39 bottles of beer on the wall, 39 bottles of beer.\n" +
                "Take one down and pass it around, 38 bottles of beer on the wall.38 bottles of beer on the wall, 38 bottles of beer.\n" +
                "Take one down and pass it around, 37 bottles of beer on the wall.37 bottles of beer on the wall, 37 bottles of beer.\n" +
                "Take one down and pass it around, 36 bottles of beer on the wall.36 bottles of beer on the wall, 36 bottles of beer.\n" +
                "Take one down and pass it around, 35 bottles of beer on the wall.35 bottles of beer on the wall, 35 bottles of beer.\n" +
                "Take one down and pass it around, 34 bottles of beer on the wall.34 bottles of beer on the wall, 34 bottles of beer.\n" +
                "Take one down and pass it around, 33 bottles of beer on the wall.33 bottles of beer on the wall, 33 bottles of beer.\n" +
                "Take one down and pass it around, 32 bottles of beer on the wall.32 bottles of beer on the wall, 32 bottles of beer.\n" +
                "Take one down and pass it around, 31 bottles of beer on the wall.31 bottles of beer on the wall, 31 bottles of beer.\n" +
                "Take one down and pass it around, 30 bottles of beer on the wall.30 bottles of beer on the wall, 30 bottles of beer.\n" +
                "Take one down and pass it around, 29 bottles of beer on the wall.29 bottles of beer on the wall, 29 bottles of beer.\n" +
                "Take one down and pass it around, 28 bottles of beer on the wall.28 bottles of beer on the wall, 28 bottles of beer.\n" +
                "Take one down and pass it around, 27 bottles of beer on the wall.27 bottles of beer on the wall, 27 bottles of beer.\n" +
                "Take one down and pass it around, 26 bottles of beer on the wall.26 bottles of beer on the wall, 26 bottles of beer.\n" +
                "Take one down and pass it around, 25 bottles of beer on the wall.25 bottles of beer on the wall, 25 bottles of beer.\n" +
                "Take one down and pass it around, 24 bottles of beer on the wall.24 bottles of beer on the wall, 24 bottles of beer.\n" +
                "Take one down and pass it around, 23 bottles of beer on the wall.23 bottles of beer on the wall, 23 bottles of beer.\n" +
                "Take one down and pass it around, 22 bottles of beer on the wall.22 bottles of beer on the wall, 22 bottles of beer.\n" +
                "Take one down and pass it around, 21 bottles of beer on the wall.21 bottles of beer on the wall, 21 bottles of beer.\n" +
                "Take one down and pass it around, 20 bottles of beer on the wall.20 bottles of beer on the wall, 20 bottles of beer.\n" +
                "Take one down and pass it around, 19 bottles of beer on the wall.19 bottles of beer on the wall, 19 bottles of beer.\n" +
                "Take one down and pass it around, 18 bottles of beer on the wall.18 bottles of beer on the wall, 18 bottles of beer.\n" +
                "Take one down and pass it around, 17 bottles of beer on the wall.17 bottles of beer on the wall, 17 bottles of beer.\n" +
                "Take one down and pass it around, 16 bottles of beer on the wall.16 bottles of beer on the wall, 16 bottles of beer.\n" +
                "Take one down and pass it around, 15 bottles of beer on the wall.15 bottles of beer on the wall, 15 bottles of beer.\n" +
                "Take one down and pass it around, 14 bottles of beer on the wall.14 bottles of beer on the wall, 14 bottles of beer.\n" +
                "Take one down and pass it around, 13 bottles of beer on the wall.13 bottles of beer on the wall, 13 bottles of beer.\n" +
                "Take one down and pass it around, 12 bottles of beer on the wall.12 bottles of beer on the wall, 12 bottles of beer.\n" +
                "Take one down and pass it around, 11 bottles of beer on the wall.11 bottles of beer on the wall, 11 bottles of beer.\n" +
                "Take one down and pass it around, 10 bottles of beer on the wall.10 bottles of beer on the wall, 10 bottles of beer.\n" +
                "Take one down and pass it around, 9 bottles of beer on the wall.9 bottles of beer on the wall, 9 bottles of beer.\n" +
                "Take one down and pass it around, 8 bottles of beer on the wall.8 bottles of beer on the wall, 8 bottles of beer.\n" +
                "Take one down and pass it around, 7 bottles of beer on the wall.7 bottles of beer on the wall, 7 bottles of beer.\n" +
                "Take one down and pass it around, 6 bottles of beer on the wall.6 bottles of beer on the wall, 6 bottles of beer.\n" +
                "Take one down and pass it around, 5 bottles of beer on the wall.5 bottles of beer on the wall, 5 bottles of beer.\n" +
                "Take one down and pass it around, 4 bottles of beer on the wall.4 bottles of beer on the wall, 4 bottles of beer.\n" +
                "Take one down and pass it around, 3 bottles of beer on the wall.3 bottles of beer on the wall, 3 bottles of beer.\n" +
                "Take one down and pass it around, 2 bottles of beer on the wall.2 bottles of beer on the wall, 2 bottles of beer.\n" +
                "Take one down and pass it around, 1 bottle of beer on the wall.1 bottle of beer on the wall, 1 bottle of beer.\n" +
                "Take one down and pass it around, no more bottles of beer on the wall.No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().
                findElement(
                        By.xpath("//body/div[@id='wrap']/div[@id='navigation']/ul[@id='submenu']/li/" +
                                "a[@href='lyrics.html']")
                )
                .click();

        String[] pText = new String[100];
        for (int i = 0; i < pText.length; i++) {
            int index = i + 1;
            pText[i] = getDriver().findElement(By.xpath("//body/div[@id='wrap']/div[@id='main']/p[" + index + "]"))
                    .getText();
        }

        String actualResult = "";
        for (int i = 0; i < pText.length; i++) {
            actualResult = actualResult + pText[i];
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_01TextInSubmenuJMenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p[contains(text(),'All languages')]"))
                .getText();

        Assert.assertEquals(actualResult, "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void test12_02LastTextInTableSubmenuMMenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//tbody/tr[last()]/td/a[@href='language-mysql-1252.html']"))
                .getText();

        Assert.assertEquals(actualResult, "MySQL");
    }

    @Test
    public void test12_03TitleTableInMenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        WebElement table = getDriver().findElement(By.xpath("//table[@id='category']"));
        String actualResult = getDriver().findElement(By.xpath("//tbody/tr[1]"))
                .getText();

        Assert.assertTrue(table.isDisplayed());
        Assert.assertEquals(actualResult, "Language Author Date Comments Rate");
    }

    @Test
    public void test12_04TrMathematicaInTableSubmenuMMenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        String textMathematicaAuthor = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[2]"))
                .getText();
        String textMathematicaDate = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[3]"))
                .getText();
        String textMathematicaComments = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[4]"))
                .getText();

        Assert.assertEquals(textMathematicaAuthor, "Brenton Bostick");
        Assert.assertEquals(textMathematicaDate, "03/16/06");
        Assert.assertEquals(textMathematicaComments, "1");
    }

    @Test
    public void test12_05Submenu0_9MenuBrowseLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();
        int languageText = getDriver().findElements(By.xpath("//tbody/tr/td[1]")).size();

        Assert.assertEquals(languageText, 10);
    }

    @Test
    public void test12_06ErrorSecurityCodeInSubMenuSignGuestbook() {

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Daria");
        getDriver().findElement(By.xpath("//input[@name='location']"))
                .sendKeys("Russia");
        getDriver().findElement(By.xpath("//input[@name='email']"))
                .sendKeys("123456@mail.ru");
        getDriver().findElement(By.xpath("//input[@name='homepage']"))
                .sendKeys("123456.ru");
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys("" + ((int) Math.random() * 900 + 100));
        getDriver().findElement(By.xpath("//textarea[@name='comment']"))
                .sendKeys("Hello");
        getDriver().findElement(By.xpath("//input[@name='submit']"))
                .click();
        String error = getDriver().findElement(By.xpath("//div[@id='main']/p"))
                .getText();

        Assert.assertEquals(error, "Error: Error: Invalid security code.");
    }

    @Test
    public void test12_07() {

        String expectedResult = "https://www.reddit.com/login" +
                "/?dest=https%3A%2F%2Fwww.reddit.com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer." +
                "net%252Flanguage-amanda-27.html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520Amanda";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr/td/a[@href='language-amanda-27.html']")).click();
        getDriver().findElement(By.xpath("//p/a[@title='reddit']/img")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void test12_08TopRated() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 0;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 20);
    }

    @Test
    public void test12_08TopRatedEsotericLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_esoteric.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 0;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 10);
    }

    @Test
    public void test12_08TopHits() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./tophits.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 0;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 6);
    }

    @Test
    public void test12_08TopRatedRealLanguages() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_real.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        Assert.assertFalse(trText.toString().contains("Shakespeare"));
    }

    @Test
    public void test12_09() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr/td/a[@href='language-java-3.html']")).click();

        int javaVersions = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td[1]")).size() + 1;

        Assert.assertEquals(javaVersions, 6);
    }
}


