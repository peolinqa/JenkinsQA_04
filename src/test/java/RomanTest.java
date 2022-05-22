import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RomanTest extends BaseTest {

    private static final String URL = "https://gopro.com/";
    private WebDriverWait wait;

    @BeforeMethod
    private void setWait() {
        wait = new WebDriverWait(getDriver(), 10);
    }

    @Ignore
    @Test
    public void testRomanTGoPro() {
        getDriver().get(URL);

        getDriver().findElement(By.xpath("//button[@aria-label='Search']/i")).click();

        WebElement searchBox = getDriver().findElement(By.id("search-box"));

        searchBox.sendKeys("Hero9 Black");
        searchBox.submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id = '__next']//h1[contains(text(), 'HERO9 Black')]")));

        String actualTitle = getDriver().findElement(
                By.xpath("//div[@id = '__next']//h1[contains(text(), 'HERO9 Black')]")).getText();

        Assert.assertEquals(actualTitle, "HERO9 Black");
    }

    @Ignore
    @Test
    public void testRomanTGoProAddCart() {
        getDriver().get(URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[@href = '/en/us/shop/cameras'])[2]"))).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='SHOP HERO10 Black']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@aria-label= 'ADD TO CART'])[1]")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = 'https://gopro.com/en/us/shop/cart']")))
                .click();

        WebElement actualTotalPrice = getDriver().findElement(By.xpath("//p[@class = 'text-right grand-total']"));

        Assert.assertEquals(actualTotalPrice.getText(), "$349.98");
    }
}