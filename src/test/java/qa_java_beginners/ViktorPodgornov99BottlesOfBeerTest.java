package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * TC_12_03 Подтвердите, что в меню BROWSE LANGUAGES существует таблица с заголовками
     * Language, Author, Date, Comments, Rate
     * Steps:
     * Open base url
     * Click on the menu item BROWSE LANGUAGES
     * Confirm that on the BROWSE LANGUAGES page a user can see the table with the next headers:
     * Language, Author, Date, Comments, Rate
     */

    @Test
    public void testConfirmThatTableExistsWithCorrectNamesInBrowserLanguageMenu() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();

        List<WebElement> tableHeadersNamesElementsActual = getDriver().findElements(
                By.xpath("//table[@id='category']//th"));
        List<String> tableHeadersNamesActual = new ArrayList<>();
        tableHeadersNamesElementsActual.stream().map(WebElement::getText).forEach(tableHeadersNamesActual::add);

        List<String> tableHeadersNamesExpected = new ArrayList<>(Arrays.asList(
                "Language", "Author", "Date", "Comments", "Rate"));

        Assert.assertEquals(tableHeadersNamesActual.toString(), tableHeadersNamesExpected.toString());
    }
}
