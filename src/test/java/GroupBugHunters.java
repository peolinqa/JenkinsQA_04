import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class GroupBugHunters extends BaseTest {

    @Test
    public void oksanaBakatova() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.etsy.com");

        WebElement searchBox = driver.findElement(By.xpath("//input[@name='search_query']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@type ='submit']"));

        searchBox.sendKeys("soap flower bouquet");
        searchButton.click();

        searchBox = driver.findElement(By.xpath("//input[@name='search_query']"));
        Assert.assertEquals(searchBox.getAttribute("value"), "soap flower bouquet");

        driver.quit();
    }

}
