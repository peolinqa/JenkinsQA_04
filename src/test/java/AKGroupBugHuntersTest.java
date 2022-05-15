import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AKGroupBugHuntersTest extends BaseTest {

    @Test
    public void ArtuomKudryashovMarlin() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.trekbikes.com");
        Thread.sleep(1000);
        WebElement searchFieldMountain = driver.findElement(By.xpath("(//*[@class='nav-categories-link font-heading font-bold lg:font-body'])[11]"));
        searchFieldMountain.click();
        Thread.sleep(600);

        WebElement search29 = driver.findElement(By.xpath("(//span[text()='29er bikes'])[2]"));
        search29.click();
        Thread.sleep(1000);
        // Начинаем искать элемент с эффектом прокрутки страницы создаем драйвер для мотания страницы
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement supercaliber = driver.findElement(By.xpath("//*[@class='product-tile__title' and contains(text(),'Supercaliber 9.9 XX1 AXS')]"));
        jse.executeScript("arguments[0].scrollIntoView(false);", supercaliber);
        Thread.sleep(2000);
        supercaliber.click();

        String resultStr = driver.findElement(By.xpath("//*[@class='buying-zone__title' and contains(text(),'Supercaliber 9.9 XX1 AXS')]")).getText();
        Assert.assertEquals(resultStr, "Supercaliber 9.9 XX1 AXS");
    }

    @Test
    public void ArtuomKudryashovMarlin4() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://www.trekbikes.com");
        Thread.sleep(500);
        WebElement searchFieldMountain = driver.findElement(By.xpath("(//*[@class='nav-categories-link font-heading font-bold lg:font-body'])[11]"));
        searchFieldMountain.click();
        Thread.sleep(600);

        WebElement search29 = driver.findElement(By.xpath("(//span[text()='29er bikes'])[2]"));
        search29.click();
        Thread.sleep(1000);

        //Находим поле поиска
        WebElement searchFieldMean = driver.findElement(By.xpath("(//*[contains(@class,'js-site-search-input siteSearchInput ')])[3]"));
        searchFieldMean.sendKeys("Marlin 4");
        searchFieldMean.sendKeys(Keys.ENTER);
        //Находим элемент на странице и заходим к нему в личный кабинет
        WebElement searchM4 = driver.findElement(By.xpath("//*[@class='product-tile__title' and contains(text(),'Marlin 4')]"));
        searchM4.click();
        String resultStr = driver.findElement(By.xpath("//*[@class='buying-zone__title' and contains(text(),'Marlin 4')]")).getText();

        Assert.assertEquals(resultStr, "Marlin 4");
    }
}
