import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OkDisableTest extends BaseTest {
    @Test
    public void testTC_126_001_OKD() {

        String expectedError = "» This field cannot be empty, please enter a valid name";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.cssSelector("div.btn-decorator")).click();
        String actualError = getDriver().findElement(
                By.cssSelector("#itemname-required.input-validation-message")).getText();

        Assert.assertEquals(actualError, expectedError);

    }
}