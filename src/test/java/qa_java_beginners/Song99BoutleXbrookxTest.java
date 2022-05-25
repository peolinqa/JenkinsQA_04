package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BoutleXbrookxTest extends BaseTest {
    private static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void Song99BoutleXbrookxTest() {

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
        getDriver().findElement(By.xpath("//a[@href='lyrics.html']")).click();

        String[] pText = new String[100];
        for (int i = 0; i < pText.length; i++) {
            pText[i] = getDriver().findElement(By.xpath("//div[@id='main']/p[" + (i + 1) + "]")).getText();
        }

        String actualResult = "";
        for (int i = 0; i < pText.length; i++) {
            actualResult += pText[i];
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test //TC_12_01
    public void testCheckDescriptionPage() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement pText = getDriver().findElement(By.xpath("//div[@id='main']/p"));
        String pTextstr = pText.getText();

        Assert.assertEquals(pTextstr, expectedResult);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p/strong")).getTagName(), "strong");
    }

    @Test //TC_12_02
    public void testCheckLastLanguageMySQL() {

        String expectedResult = "MySQL";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String lastLanguage = getDriver().findElement(By.xpath("//tr/td/a[@href='language-mysql-1252.html']")).getText();
        Assert.assertEquals(lastLanguage, expectedResult);
    }

    @Test //TC_12_03
    public void testConfirmTableWithHeaders() {
        String expectedResult = "Language, Author, Date, Comments, Rate";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();

        String[] headers = new String[5];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = getDriver().findElement(By.xpath("//tbody/tr/th[" + (i + 1) + "]")).getText();
        }

        String headersName = "";
        for (int i = 0; i < headers.length; i++) {
            headersName += headers[i] + ", ";
        }

        Assert.assertEquals(headersName.substring(0, headersName.length() - 2), expectedResult);
    }

    @Test //TC_12_04
    public void testConfirmCreaterAndDateCreateHaveOneComment() {
        String expectedResult = "Brenton Bostick";
        String expectedResult1 = "03/16/06";
        String expectedResult2 = "1";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String name = getDriver().findElement(By.xpath("//tbody/tr/td[text()='Brenton Bostick']")).getText();
        String data = getDriver().findElement(By.xpath("//tbody/tr/td[text()='03/16/06']")).getText();
        String comment = getDriver().findElement(By.xpath("//tbody/tr/td[text()='1']")).getText();

        Assert.assertEquals(name, expectedResult);
        Assert.assertEquals(data, expectedResult1);
        Assert.assertEquals(comment, expectedResult2);
    }

    @Test //TC_12_05
    public void testCountLanguagesBeginWithNumbers() {
        int expectedResult = 10;

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        String[] arrayLanguages = new String[10];
        for (int i = 0; i < arrayLanguages.length; i++) {
            arrayLanguages[i] = getDriver().findElement(By.xpath("//tbody/tr[@onmouseover][" + (i + 1) + "]")).getText();
        }

        Assert.assertEquals(arrayLanguages.length, expectedResult);
    }

    @Test //TC_12_06
    public void testFillInAllFieldsAndRandomNumberInSecurityCodeField() {
        String expectedResult = "Error: Error: Invalid security code.";
        String expectedResult1 = "b";
        String expectedResult2 = "border: 1px solid red; background-color: rgb(255, 224, 224); padding: 5px; margin: 5px 10px;";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(By.xpath("//form/p/input[@name='name']")).sendKeys("Valentin");
        getDriver().findElement(By.xpath("//form/p/input[@name='location']")).sendKeys("Moscow");
        getDriver().findElement(By.xpath("//form/p/input[@name='email']")).sendKeys("cricket666@yandex.ru");
        getDriver().findElement(By.xpath("//form/p/input[@name='homepage']")).sendKeys("ya.ru");
        int random = ((int) (Math.random() * 1000));
        String newStr = Integer.toString(random);
        getDriver().findElement(By.xpath("//form/p/input[@name='captcha']")).sendKeys(newStr);
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Hello World!");
        getDriver().findElement(By.xpath("//input[@name='submit']")).click();

        String errorMassage = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(errorMassage, expectedResult);

        String bold = getDriver().findElement(By.xpath("//div[@id='main']/p/b")).getTagName();

        Assert.assertEquals(bold, expectedResult1);

        String style = getDriver().findElement(By.xpath("//div[@id='main']/p")).getAttribute("style");

        Assert.assertEquals(style, expectedResult2);
    }
}