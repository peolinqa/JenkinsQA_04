import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class JuliaTestWocabee extends BaseTest {

    @Test
    public void testJuliaChWocabeeOpenPage() throws InterruptedException {

        getDriver().get("https://www.wocabee.app/");

        getDriver().findElement(
                By.xpath("//div[@class='container h-100']" +
                        "//div[@class='btn btn-lg btn-success customBtn']")).click();
        WebElement text = getDriver().findElement(
                By.xpath("//div[@class='modal fade show']" +
                        "//h4[@class='modal-title mx-auto font-weight-bold']"));
        Thread.sleep(1000);

        Assert.assertEquals(text.getText(), "Zvolte typ objednávky");

        getDriver().quit();
    }
}
