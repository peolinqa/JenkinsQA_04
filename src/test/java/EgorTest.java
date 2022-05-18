import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class EgorTest extends BaseTest {
    @Ignore
    @Test
    public void egorNovenkov() {
        getDriver().get("http://automationpractice.com/index.php");

        getDriver().findElement(By.id("search_query_top")).sendKeys("T-shirt");
        WebElement searchBox = getDriver().findElement(By.name("submit_search"));
        searchBox.click();

        WebElement clickElement = getDriver().findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]" +
                "/div[2]/ul/li/div/div[1]/div/a[1]"));
        clickElement.click();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,350)");

        WebElement addToCart = getDriver().findElement(By.id("add_to_cart"));
        addToCart.click();

        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement openCart = getDriver().findElement(By.cssSelector("[title='View my shopping cart']"));
        openCart.click();

    }
}
