
import org.testng.annotations.Ignore;
import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlexanderKTest extends BaseTest{

    private static final String PRODUCT_NAME = "DELL PROFESSIONAL P2411Hb 24zoll WIDESCREEN LCD MONITOR";

    @Ignore
    @Test
    public void testSearchProductEbay () throws InterruptedException {

        getDriver().get("https://www.ebay.com/");

        getDriver().findElement(By.name("_nkw")).sendKeys(PRODUCT_NAME);
        Thread.sleep(1000);
        getDriver().findElement(By.id("gh-btn")).click();

        WebElement foundProduct = getDriver().findElement
                (By.xpath("//h3[contains(text(),'DELL PROFESSIONAL P2411Hb 24zoll WIDESCREEN LCD MO')]"));
        Assert.assertEquals(foundProduct.getText(), PRODUCT_NAME);
    }

}
