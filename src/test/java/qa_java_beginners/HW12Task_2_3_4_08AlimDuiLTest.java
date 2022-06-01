package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class HW12Task_2_3_4_08AlimDuiLTest extends BaseTest {

    private String BASE_URL = "http://www.99-bottles-of-beer.net/";

    /*
    TC_12_02
    Шаги:
    1. Открыть базовую страницу
    2. Нажать на пункт меню BROWSE LANGUAGES
    3. Нажать на подменю M
    4. Подтвердить, что последний язык программирования на странице - MySQL
     */
    @Test
    public void testLastlanguagesInTable() {

        String expectedResult = "MySQL";

        getDriver().get(BASE_URL);
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='submenu']/li/a[@href='m.html']"))
                .click();
        WebElement lastLguages = getDriver().findElement(
                By.xpath("//tbody/tr[last()]/td/a[@href='language-mysql-1252.html']"
                )
        );
        String actualResult = lastLguages.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    /*
    TC_12_03
    Шаги:
    1. Открыть базовую страницу
    2. Нажать на пункт меню BROWSE LANGUAGES
    3. Найти в таблице первую строку
    4. Подтвердить, что строка содержит заголовки  Language, Author, Date,
    Comments, Rate
     */
    @Test
    public void testTableWithTitles() {

        String[] expectedResult = {"Language", "Author", "Date", "Comments", "Rate"};

        getDriver().get(BASE_URL);
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();
        String[] actualResult = new String[5];
        for (int i = 0; i < actualResult.length; i++) {
            int index = i + 1;
            actualResult[i] = getDriver()
                    .findElement(By.xpath("//tbody/tr/th[" + index + "]"))
                    .getText();
        }
        Assert.assertEquals(actualResult, expectedResult);
    }
    /*
    TC_12_04
    Подтвердите, что создатель решения на языке Mathematica -
    Brenton Bostick, дата обновления решения на этом языке - 03/16/06,
    и что это решение имеет 1 комментарий
    Шаги:
    1. Открыть базовую страницу
    2. Нажать на пункт меню BROWSE LANGUAGES
    3. Нажать на подменю M
    4. В таблице нажать на  язык Mathematica
    5. Подтвердить requirenments
    */
    @Test
    public void testauthorOfLangMathematica() {

        boolean expectedResult = true;

        getDriver().get(BASE_URL);
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//ul[@id='submenu']/li/a[@href='m.html']"))
                .click();
        getDriver()
                .findElement(
                        By.xpath("//td/a[@href='language-mathematica-1090.html']"))
                .click();
        if (getDriver()
                .findElement(By.xpath("//tbody/tr[1]/td[2]"))
                .getText() == "03/16/06" &&
                getDriver()
                        .findElement(By.xpath("//tbody/tr[2]/td[2]"))
                        .getText() == "Brenton Bostick" &&
                getDriver()
                        .findElement(By.xpath("//tbody/tr[4]/td[2]"))
                        .getText() == "1") {

            boolean actualResult = true;

            Assert.assertEquals(actualResult, expectedResult);
        }
    }
}
