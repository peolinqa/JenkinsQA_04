package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class KsuTitova99BottlesOfBeerHW12Tests extends BaseTest {

    /**
     * TC_12_01 Подтвердите, что в меню BROWSE LANGUAGES, подменю  J,
     * пользователь может найти описание страницы, на которой перечеслены все программные языки,
     * начинающиеся с буквы J,  отсортированные по названию
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю J
     * Подтвердить, что пользователь видит текст
     * “All languages starting with the letter J are shown, sorted by Language.”
     */


    @Test
    public void testKseniyaTitovaBROWSELANGUAGESsubmenuJ() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("//ul/li/a[@href='/abc.html']"));
        menuBrowseLanguages.click();

        WebElement subtitleJ = getDriver().findElement(By.xpath("//ul/li/a[@href='j.html']"));
        subtitleJ.click();

        WebElement result = getDriver().findElement(By.xpath("//div[@id='main']/p"));

        Assert.assertEquals(result.getText(), expectedResult);
    }

    /**
     * TC_12_02 Подтвердите, что в меню BROWSE LANGUAGES, подменю  M, последний программный язык в таблице -  MySQL
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю M
     * Подтвердить, что последний язык программирования на странице - MySQL
     */

    @Test
    public void testKseniyaTitovaBROWSELANGUAGESsubmenuM() {

        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/abc.html']")).click();

        getDriver().findElement(By.xpath("//ul/li/a[@href='m.html']")).click();

        WebElement result = getDriver().findElement(By.xpath("//table//a[@href='language-mysql-1252.html']"));

        Assert.assertEquals(result.getText(), expectedResult);
    }

    /**
     * TC_12_03 Подтвердите, что в меню BROWSE LANGUAGES существует таблица
     * с заголовками Language, Author, Date, Comments, Rate
     */

    @Test
    public void testKseniyaTitovaBROWSELANGUAGESTable() {

        String expectedResult = "Language, Author, Date, Comments, Rate, ";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul/li/a[@href='/abc.html']")).click();

        String[] pText = new String[5];
        for (int i = 0; i < pText.length; i++) {
            int index = i + 1;
            pText[i] = getDriver().findElement(By.xpath(
                    "//table[@id='category']/tbody/tr/th[" + index + "]")).getText();
        }

        String actualResult = "";
        for (int i = 0; i < pText.length; i++) {
            actualResult += pText[i] + ", ";
        }

        Assert.assertEquals(actualResult, expectedResult);
    }


}
