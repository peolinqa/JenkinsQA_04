package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ViktorPodgornov99BottlesOfBeerTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String BROWSE_LANGUAGES_MENU_XPATH = "//ul[@id='menu']//a[text()='Browse Languages']";

    /**
     * TC_12_01 Подтвердите, что в меню BROWSE LANGUAGES, подменю  J, пользователь может найти
     * описание страницы, на которой перечеслены все программные языки, начинающиеся с буквы J,
     * отсортированные по названию
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю J
     * Подтвердить, что пользователь видит текст
     * “All languages starting with the letter J are shown, sorted by Language.”
     */

    @Test
    public void testUserCanSeeDescriptionOfPageWhereAllLanguageBeginJ() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();

        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[text()='J']")).click();

        Assert.assertEquals(
                getDriver().findElement(
                                By.xpath("//p[contains(text(),'All languages starting with the letter')]"))
                        .getText(),
                "All languages starting with the letter J are shown, sorted by Language.");
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
    public void testConfirmThatInBrowserLanguagesMenuSubmenuMLastLanguageIsMySql() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();

        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[text()='M']")).click();

        Assert.assertEquals(
                getDriver().findElement(
                                By.xpath("//tr[last()]//a[text()='MySQL']"))
                        .getText(),
                "MySQL");
    }
}
