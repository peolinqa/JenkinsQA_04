package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

public class ViktorPodgornov99BottlesOfBeerTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String BROWSE_LANGUAGES_MENU_XPATH = "//ul[@id='menu']//a[text()='Browse Languages']";

    public WebElement getBrowseLanguagesSubmenuXpath(String submenuElement) {

        return getDriver().findElement(By.xpath(String.format("//ul[@id='submenu']//a[text()='%s']", submenuElement)));
    }

    public List<WebElement> getListOfWebElementsDependentOnXpath(String yourXPath) {

        return getDriver().findElements(By.xpath(yourXPath));
    }

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
        getBrowseLanguagesSubmenuXpath("J").click();

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
        getBrowseLanguagesSubmenuXpath("M").click();

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

        List<String> tableHeadersNamesActual = new ArrayList<>();
        getListOfWebElementsDependentOnXpath("//table[@id='category']//th").stream().map(WebElement::getText)
                .forEach(tableHeadersNamesActual::add);

        List<String> tableHeadersNamesExpected = new ArrayList<>(Arrays.asList(
                "Language", "Author", "Date", "Comments", "Rate"));

        Assert.assertEquals(tableHeadersNamesActual.toString(), tableHeadersNamesExpected.toString());
    }

    /**
     * TC_12_04 Подтвердите, что создатель решения на языке Mathematica - Brenton Bostick,
     * дата обновления решения на этом языке - 03/16/06, и что это решение имеет 1 комментарий
     * Steps:
     * Open base url
     * Click on the menu item BROWSE LANGUAGES
     * Click on the submenu M
     * Confirm that the creator of the Mathematica solution is Brenton Bostick,
     * the date of the solution was updated in this language is 03/16/06, and that this solution has 1 comment
     */

    @Test
    public void testConfirmThatCreatorOfMathematicaIsBrentonBostickDateUpdatedIsCorrectHasOneComment() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();
        getBrowseLanguagesSubmenuXpath("M").click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//a[text()='Mathematica']//ancestor::tr"))
                        .getText(),
                "Mathematica Brenton Bostick 03/16/06 1");
    }

    /**
     * TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
     * Steps:
     * Open base url
     * Click on the menu item BROWSE LANGUAGES
     * Click on the submenu 0-9
     * Confirm that there are 10 languages on the site that begin with numbers.
     */

    @Test
    public void testConfirmThatSiteHasTenLanguagesWhoseNamesBeginWithNumbers() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();
        getBrowseLanguagesSubmenuXpath("0-9").click();
        List<WebElement> languagesWhoseNamesBeginWithNumbers = getListOfWebElementsDependentOnXpath(
                "//table[@id='category']//a[contains(@href, 'language')]");

        int countLanguagesNamesBeginWithNumbersActual = 0;
        for (WebElement languagesWhoseNamesBeginWithNumber : languagesWhoseNamesBeginWithNumbers) {
            if (Character.isDigit(languagesWhoseNamesBeginWithNumber.getText().charAt(0))) {
                countLanguagesNamesBeginWithNumbersActual++;
            }
        }
        Assert.assertEquals(countLanguagesNamesBeginWithNumbersActual, 10);
    }
}
