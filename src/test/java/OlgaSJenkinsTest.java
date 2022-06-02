import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaSJenkinsTest extends BaseTest {

    @Test
    public void testCreatedFirstTest () {
        Assert.assertTrue(getDriver().findElement(By.id("side-panel")).isDisplayed());
    }
}
