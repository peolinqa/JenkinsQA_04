package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class IvanTodorov99BottlesOfBeerTest extends BaseTest {

    @Test
    public void testMenuBrowseLanguagesSubmenuCategoryJTest() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[11]/a")).click();

        String actualResult = getDriver().findElement(By.xpath("/html/body/div/div[3]/p[1]")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testMenuBrowseLanguagesSubmenuCategoryMLastColumnTextTest() {
        String expectedResult = "MySQL";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[14]/a")).click();

        String actualResult = getDriver().findElement(By.xpath(
                "/html/body/div/div[3]/table/tbody/tr[89]/td[1]/a"
        )).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testMenuBrowseLanguagesColumnsTitles() {
        String expectedResult = "Language Author Date Comments Rate";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();

        String actualResult = getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[1]")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }



    @Test
    public void testMenuBrowseLanguagesMathematicaAuthorAndDate() {
        String expectedResult = "Author: Brenton BostickDate: 03/16/06";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[14]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[22]/td[1]/a")).click();

        WebElement date = getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[1]"));
        WebElement author = getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[2]"));

        String actualResult = author.getText() + date.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }



    @Test
    public void testLanguagesStartedByNumbers() {
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[1]/a")).click();

        WebElement[] arrTable = new WebElement[10];
        for (int i = 2, index = 0; i < 12; i++, index++) {
            arrTable[index] = getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr["+i+"]/td[1]"));
        }

        boolean actualResult = true;
        for (WebElement webElement : arrTable) {
            if (webElement.getText().charAt(0) <= 0 && webElement.getText().charAt(0) >= 9) {
                actualResult = false;
            }
        }

        Assert.assertTrue(actualResult);
    }


    @Test
    public void testMenuSignGuestbookErrorSecurityCode() {
        String expectedResult = "Error: Error: Invalid security code.";
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("/html/body/div/div[3]/form/p/input[1]")).sendKeys("Ivan");
        getDriver().findElement(By.xpath(
                "/html/body/div/div[3]/form/p/input[3]")).sendKeys("12345@gmail.com");
        getDriver().findElement(By.xpath("/html/body/div/div[3]/form/p/textarea")).sendKeys("qwerty");
        getDriver().findElement(By.xpath("/html/body/div/div[3]/form/p/input[5]"))
                .sendKeys(Integer.toString((int)(Math.random() * 1000)));

        getDriver().findElement(By.xpath("/html/body/div/div[3]/form/p/input[6]")).click();

        String actualResult = getDriver().findElement(By.xpath("/html/body/div/div[3]/p")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void testRepostLanguageToReddit() {
        String expectedResult = "Log in";
        getDriver().get("https://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[1]/li[2]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[2]/ul[2]/li[11]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[5]/td[1]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[3]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
        getDriver().findElement(By.xpath("/html/body/div/div[3]/div[1]/p[2]/a[11]/img")).click();

        String actualResult = getDriver().findElement(By.xpath(
                "/html/body/div/main/div[1]/div/div[2]/h1"
        )).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }

}
