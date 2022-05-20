
import org.testng.annotations.Ignore;
import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlexanderKTest extends BaseTest{

    @Ignore
    @Test
    public void testAleksanderKozlov() throws InterruptedException {

        getDriver().get("https://www.ebay.com/");

        WebElement searhLine = getDriver().findElement(By.name("_nkw"));
        WebElement searhButton = getDriver().findElement(By.id("gh-btn"));

        searhLine.sendKeys("DELL PROFESSIONAL P2411Hb 24zoll WIDESCREEN LCD MONITOR");
        Thread.sleep(1000);
        searhButton.click();

        searhLine = getDriver().findElement
                (By.xpath("//h3[contains(text(),'DELL PROFESSIONAL P2411Hb 24zoll WIDESCREEN LCD MO')]"));

        Assert.assertEquals(searhLine.getText(), "DELL PROFESSIONAL P2411Hb 24zoll WIDESCREEN LCD MONITOR");


    }

}
