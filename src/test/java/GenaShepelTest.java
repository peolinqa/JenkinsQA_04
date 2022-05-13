import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GenaShepelTest extends BaseTest {

    @Test
    public void genaShepelFirstTest() throws InterruptedException {

        getDriver().get("https://www.indeed.com/");

        WebElement searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));
        WebElement searchBox = getDriver().findElement(By.id("text-input-what"));
//
        searchBox.sendKeys("QA");
        Thread.sleep(2000);

        searchButton.click();

        searchBox = getDriver().findElement(By.id("text-input-what"));

        Assert.assertEquals(searchBox.getAttribute("value"), "QA");

        //searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));


    }
    @Test
    public void genaShepelSecondtTest() throws InterruptedException {

        getDriver().get("https://google.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        WebElement searchButton = getDriver().findElement(By.name("btnK"));
//
        searchBox.sendKeys("QA");
        Thread.sleep(2000);

        searchButton.click();

        searchBox = getDriver().findElement(By.name("q"));

        Assert.assertEquals(searchBox.getAttribute("value"), "QA");

        //searchButton = getDriver().findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));


    }

}

