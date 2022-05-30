package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HW12SabinaSaadTest extends BaseTest {

    public static String URL = "http://www.99-bottles-of-beer.net/";

    @Ignore
    @Test(invocationCount = 20)
  
    public void testSubMenuJBrowseLanguages() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div/div[@id='main']/p[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSubMenuMLastLanguage() {
        String expectedResult = "MySQL";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        String actualResult = getDriver().findElement(By.xpath("//body/div/div/table/tbody/tr[last()]/td/a")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBrowseLanguagesHeaders() {
        String[] expectedResult = {"Language", "Author", "Date", "Comments", "Rate"};
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();

        int arrayLength = getDriver().findElements(By.xpath("//table/tbody/tr[1]/th")).size();
        String[] actualResult = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            actualResult[i] = getDriver().findElement(By.xpath("//table/tbody/tr[1]/th[" + (i + 1) + "]")).getText();
        }

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testMathematicaAuthorDateComments() {
        String[] expectedResult = {"Brenton Bostick", "03/16/06", "1"};

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/search.html']")).click();
        getDriver().findElement(By.xpath("//div/div/form/p/input[@name='search']"))
                .sendKeys("Mathematica")
        ;
        getDriver().findElement(By.xpath("//div/div/form/p/input[@value='GO']")).click();
        String[] actualResult = new String[3];
        for (int i = 0; i < actualResult.length; i++) {
            actualResult[i] = getDriver().findElement(By.xpath("//tbody/tr[2]/td[" + (i + 2) + "]")).getText();
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
}
