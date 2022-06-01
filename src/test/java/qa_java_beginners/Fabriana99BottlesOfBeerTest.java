package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ignore
public class Fabriana99BottlesOfBeerTest extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/";
    private String actualResult;
    private String expectedResult;

    private WebElement menuBrowseLanguages() {
        return getDriver().findElement(By.xpath("//div[@id='navigation']//*[contains(text(),'Browse Languages')]"));
    }

    /**
     * Возвращаем веб-элемент, в xpath которого в text можно поставить нужную букву из меню,
     * поэтому пишем только 1 веб.эл
     */
    private WebElement idSelectsubMenuLetter(String letter) {
        String subMenuLetter = "//ul[@id='submenu']//*[contains(text(),'%s')]";
        return getDriver().findElement(By.xpath(String.format(subMenuLetter, letter)));
    }


    private WebElement categoryDescription() {
        return getDriver().findElement(By.xpath("//div[@id='main']/p[contains(text(), 'All')]"));
    }

    /**
     * Возвращаем веб-элемент, в xpath которого в text можно поставить нужный язык из списка,
     * поэтому пишем только 1 веб.эл
     */
    private WebElement titleLanguageInList(String language) {
        String titleLanguageInList = "//div//a[contains(text(), '%s')]";
        return getDriver().findElement(By.xpath(String.format(titleLanguageInList, language)));
    }

    private String tableHeader = "//tbody/tr/th";


    @Test //TC_12_01
    public void testFindPageDescription() {
        getDriver().get(URL);
        menuBrowseLanguages().click();
        idSelectsubMenuLetter("J").isDisplayed();
        idSelectsubMenuLetter("J").click();

        actualResult = categoryDescription().getText();
        expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test //TC_12_02
    public void testLastLanguageMySql() {
        getDriver().get(URL);
        menuBrowseLanguages().click();
        idSelectsubMenuLetter("M").click();

        expectedResult = "MySQL";
        actualResult = titleLanguageInList("MySQL").getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test //TC_12_03
    public void testTableColumnsExist() {
        getDriver().get(URL);
        menuBrowseLanguages().click();

        List<String> expectedResult = Arrays.asList("Language", "Author", "Date", "Comments", "Rate");

        List<WebElement> tableHeaders = getDriver().findElements(By.xpath(tableHeader));
        List<String> actualTableHeaders = new ArrayList<>();
        for (WebElement e : tableHeaders) {
            actualTableHeaders.add(e.getText());
        }

        Assert.assertEquals(actualTableHeaders, expectedResult);
        Assert.assertEquals(actualTableHeaders.size(), expectedResult.size());
    }


}
