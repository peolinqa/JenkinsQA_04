import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatjanaS1GeonameTest extends BaseTest {

        @Test
        public void Test1OpenGeonames(){

            getDriver().get("https://www.geonames.org/");

            Assert.assertEquals(getDriver().getTitle(),"GeoNames");
        }

        @Test
        public void Test2OpenDocumentation() throws InterruptedException {
            getDriver().get("https://www.geonames.org/");
            //WebElement serchButton = getDriver().findElement(By.xpath("//*[@id='overview']/div[1]/div[3]/ul[2]/li[2]/a")); // работает
            //WebElement serchButton = getDriver().findElement(By.xpath("//*[@id='overview']/div[1]/div[3]/ul[2]/li[2]/a[contains (text(), 'Documentation')]")); // работает
            //WebElement serchButton = getDriver().findElement(By.xpath("//a[@href = 'https://www.geonames.org/export/web-services.html']")); // работает
            WebElement serchButton = getDriver().findElement(By.xpath("//a[text() = 'Documentation']"));
            serchButton.click();
            Thread.sleep(3000);
            Assert.assertEquals(getDriver().getTitle(), "GeoNames Web Service Documentation");
        }

        @Test
    public void Test3Search() throws InterruptedException {
            getDriver().get("https://www.geonames.org/");

            WebElement searchBox = getDriver().findElement(By.name("q"));
            WebElement searchButton = getDriver().findElement(By.xpath("//input[@type = 'submit']"));

            searchBox.sendKeys("Selenium");
            Thread.sleep(3000);
            searchButton.click();

            WebElement searchBox1 = getDriver().findElement(By.name("q")); //почему тест валится без повторной инициации
            // searchBox1?
            Assert.assertEquals(searchBox1.getAttribute("value"), "Selenium");
        }
}
