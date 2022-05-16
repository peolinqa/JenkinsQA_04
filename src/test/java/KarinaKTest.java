import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KarinaKTest extends BaseTest {

    @Test
    public void TitleTest() throws InterruptedException {
        //WebDriver driver = getDriver();
        getDriver().get("https://shop.acodemy.lv/");

        String searchTitle = getDriver().getTitle();
        Thread.sleep(2500);
        Assert.assertEquals(searchTitle, "Online shop – acodemy – Just another WordPress site");

//            WebElement searchHat = getDriver().findElement(By.xpath("//*[@id=\"main\"]/ul/li[2]/a[1]/h2"));
//            searchHat.click();
//            Thread.sleep(2000);
//            WebElement addToCart = getDriver().findElement(By.name("add-to-cart"));
//            addToCart.click();
    }
}


