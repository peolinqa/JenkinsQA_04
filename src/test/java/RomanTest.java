import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore //added
public class RomanTest extends BaseTest {
    @Ignore
    @Test
    public void testRomanTGoPro() throws InterruptedException {
        getDriver().get("https://gopro.com/");

        getDriver().findElement(By.xpath("//*[@class=' icon icon-product-search']")).click();

        WebElement searchBox = getDriver().findElement(By.id("search-box"));

        searchBox.sendKeys("Hero9 Black");
        searchBox.submit();

        Thread.sleep(1000);

        String assertTitle = getDriver().findElement(By.xpath("//*[@class = 'Headline_title__kCugX ']")).getText();
        Assert.assertEquals(assertTitle, "HERO9 Black");
    }

    @Test
    public void testRomanTGoProAddCart() throws InterruptedException {
        getDriver().get("https://gopro.com/en/us/");

        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//*[@class=\"navmenu CategoryMenu_navmenu__c1663 false\"]/li/a")).click();

        getDriver().findElement(By.xpath("//*/div[1][*=\"SHOP HERO10 Black\"]")).click();
        Thread.sleep(4000);

        getDriver().findElement(By.xpath("//*[@aria-label=\"ADD TO CART\"]")).click();
        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//*[@href=\"https://gopro.com/en/us/shop/cart\"]")).click();

        WebElement result = getDriver().findElement(By.xpath("//*[@class=\"text-right grand-total\"]"));

        Assert.assertEquals(result.getText(), "$349.98");
    }
}
