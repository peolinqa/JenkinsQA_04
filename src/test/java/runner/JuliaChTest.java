package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JuliaChTest extends BaseTest {

    @BeforeMethod
    public void testBefore() {
        getDriver().get("https://www.wocabee.app/");
        getDriver().manage().deleteAllCookies();
    }

    @Test
    public void testJuliaChWocabeeOpenPage() throws InterruptedException {

        String expectedResult = "Zvolte typ objednávky";


        WebElement wocabeeOpenPage = getDriver().findElement(By.xpath("//*[@id=\"page-top\"]"));
        wocabeeOpenPage.click();
        Thread.sleep(500);

        WebElement searchButton = getDriver().findElement(By.xpath("//*[@id=\"page-top\"]/header/div/div[2]/div[1]/div/a[1]/div"));
        Thread.sleep(1000);
        searchButton.click();

       String actualResult = getDriver().findElement(By.xpath("//*[@id=\"trialModalLabel\"]")).getText();
       Thread.sleep(1000);
       Assert.assertEquals(actualResult, expectedResult);
    }
}
