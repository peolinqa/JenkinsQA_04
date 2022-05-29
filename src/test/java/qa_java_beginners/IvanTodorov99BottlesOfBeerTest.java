package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IvanTodorov99BottlesOfBeerTest extends BaseTest {

    @Test
    public void testMenuBrowseLanguagesSubmenuCategoryJTest() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a"));
        menuBrowseLanguages.click();

        WebElement submenuJ = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[11]/a"));
        submenuJ.click();

        WebElement categoryJText = getDriver().findElement(By.xpath("/html/body/div/div[3]/p[1]"));
        String actualResult = categoryJText.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testMenuBrowseLanguagesSubmenuCategoryMLastColumnTextTest() {
        String expectedResult = "MySQL";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a"));
        menuBrowseLanguages.click();

        WebElement submenuJ = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[14]/a"));
        submenuJ.click();

        WebElement categoryMLastColumn = getDriver().findElement(By.xpath(
                "/html/body/div/div[3]/table/tbody/tr[89]/td[1]/a"
        ));
        String actualResult = categoryMLastColumn.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testMenuBrowseLanguagesColumnsTitles() {
        String expectedResult = "Language Author Date Comments Rate";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a"));
        menuBrowseLanguages.click();

        WebElement titlesOfColumn = getDriver().findElement(By.xpath("//*[@id=\"category\"]/tbody/tr[1]"));
        String actualResult = titlesOfColumn.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }



    @Test
    public void testMenuBrowseLanguagesMathematicaAuthorAndDate() {
        String expectedResult1 = "Author: Brenton Bostick";
        String expectedResult2 = "Date: 03/16/06";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLanguages = getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a"));
        menuBrowseLanguages.click();

        WebElement submenuM = getDriver().findElement(By.xpath("//*[@id=\"submenu\"]/li[14]/a"));
        submenuM.click();

        WebElement languageMathematica = getDriver().findElement(By.xpath(
                "//*[@id=\"category\"]/tbody/tr[22]/td[1]/a"
        ));
        languageMathematica.click();

        WebElement date = getDriver().findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]"));
        WebElement author = getDriver().findElement(By.xpath("//*[@id=\"main\"]/table/tbody/tr[2]"));

        String actualResult1 = author.getText();
        String actualResult2 = date.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }



    @Test
    public void testLanguagesStartedByNumbers() {
        boolean expectedResult = true;
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//*[@id=\"menu\"]/li[2]/a")).click();
        getDriver().findElement(By.xpath("//*[@id=\"submenu\"]/li[1]/a")).click();

        WebElement[] arrTable = new WebElement[10];
        for (int i = 2, index = 0; i < 12; i++, index++) {
            arrTable[index] = getDriver().findElement(By.xpath("//*[@id=\"category\"]/tbody/tr["+i+"]/td[1]"));
        }

        boolean actualResult = true;
        for (WebElement webElement : arrTable) {
            if (webElement.getText().charAt(0) <= 0 && webElement.getText().charAt(0) >= 9) {
                actualResult = false;
            }
        }

        Assert.assertEquals(actualResult,expectedResult);
    }



}
