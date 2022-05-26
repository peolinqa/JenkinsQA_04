package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MorigorHW12SongTest extends BaseTest {

    @Test
    public void testAreLanguagesSortedByLetter() {
        String expectedResult
                = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")
        ).click();

        String actualResult = getDriver()
                .findElement(By.xpath("//div[@id='main']/p[text()]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmIfLanguageCorrect() {
        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")
        ).click();

        String actualResult = getDriver()
                .findElement(
                        By.xpath("//table[@id='category']/tbody/tr/td" +
                                "/a[@href='language-mysql-1252.html']")
                ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmIfTableHeadExist() {
        String expectedResult = "Language, Author, Date, Comments, Rate,";

        getDriver().get("https://www.99-bottles-of-beer.net/abc.html");

        String[] tableArr = new String[5];
        String actualresult = "";
        for(int i = 0; i < tableArr.length; i++) {
            tableArr[i] = getDriver()
                    .findElement(
                            By.xpath("//tbody/tr/th[" + (i + 1) + "]")
                    ).getText();
            actualresult = actualresult.concat(tableArr[i] + ", ");
        }

        Assert.assertEquals(actualresult.trim(), expectedResult);
    }

    @Test
    public void testMathematicaLanguageData() {
        getDriver().get("https://www.99-bottles-of-beer.net/abc.html");
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        getDriver()
                .findElement(
                By.xpath("//tbody/tr/td/a[@href='language-mathematica-1090.html']"))
                .click();

        WebElement author
                = getDriver()
                .findElement(
                        By.xpath("//table[@style='margin: 7px; padding: 0;;']/tbody/tr[2]/td[last()]"));
        WebElement update
                = getDriver()
                .findElement(
                        By.xpath("//table[@style='margin: 7px; padding: 0;;']/tbody/tr[1]/td[last()]"));
        WebElement comments
                = getDriver()
                .findElement(
                        By.xpath("//table[@style='margin: 7px; padding: 0;;']/tbody/tr[4]/td[last()]"));

        Assert.assertEquals(author.getText(), "Brenton Bostick");
        Assert.assertEquals(update.getText(), "03/16/06");
        Assert.assertEquals(comments.getText(), "1");
    }

    @Test
    public void testLanguagesWithFigureFirst() {
        getDriver().get("https://www.99-bottles-of-beer.net/abc.html");
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']/li/a[@href='0.html']"))
                .click();

        String[] languageArr = new String[10];
        int numberOfLanguages = 0;
        for(int i = 0; i < languageArr.length; i++) {
            languageArr[i]
                    = getDriver()
                    .findElement(
                            By.xpath("//table[@id='category']/tbody/tr[" + (i + 2) + "]"))
                    .getText();
            if(languageArr[i].length() > 0) {
                numberOfLanguages ++;
            }
        }

        Assert.assertEquals(numberOfLanguages, 10);
    }

}
