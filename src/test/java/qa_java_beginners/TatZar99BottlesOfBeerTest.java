package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TatZar99BottlesOfBeerTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String BROWSE_LANGUAGE = "//ul[@id='menu']//a[text()='Browse Languages']";

    @Test
    public void testBrowseJLanguage() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGE)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        WebElement phrase = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));
        String actualResult = phrase.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMySQL() {

        String expectedResult = "MySQL";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGE)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        WebElement mySQL = getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']"));
        String actualResult = mySQL.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableTitle() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGE)).click();
        List<WebElement> tableTitles = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/th"));
        List<String> ActualResult = new ArrayList<>();
        for (int i = 0; i < tableTitles.size(); i++) {
            ActualResult.add(tableTitles.get(i).getText());
        }
        List<String> expectedResult = new ArrayList<>(Arrays.asList("Language", "Author", "Date", "Comments", "Rate"));

        Assert.assertEquals(ActualResult.toString(), expectedResult.toString());
    }

    @Test
    public void testCreatorUpdateDateComment() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGE)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        WebElement mathematicaRow = getDriver().findElement(By.xpath
                ("//a[contains(text(),'Mathematica')]/ancestor::td/ancestor::tr")
        );
        String actualResult = mathematicaRow.getText();
        String expectedResult = "Mathematica Brenton Bostick 03/16/06 1";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTenLanguages() {

        int expectedResult = 10;
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGE)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();
        List<WebElement> numbers = getDriver().findElements(By.xpath("//tr[@onmouseover]"));
        int actualResult = numbers.size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}


