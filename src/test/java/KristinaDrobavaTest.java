import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KristinaDrobavaTest {

    @Test
    public void yahooKristinaDrobavaTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.yahoo.com");

        WebElement searchBox = driver.findElement(By.name("p"));
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='ybar-search']"));

        searchBox.sendKeys("Kim Kardashian");

        Thread.sleep(3000);
        searchButton.click();

        searchBox = driver.findElement(By.name("p"));

        Assert.assertEquals(searchBox.getAttribute("value"), "Kim Kardashian");

        driver.quit();
    }
}

