import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectInvalidDataTest extends BaseTest {
    protected static final char[] invalid_Data =
            {'!', ';', '%', ':', '?', '*', '!', '[', ']', '@', '#', '$', '%', '^', '&', '/', '|'};

    @Test
    public void testInvalidDataCycle() {

        getDriver().findElement(By.className("task-link-text")).click();
        WebElement errorText = getDriver().findElement(By.id("itemname-invalid"));

        for (int i = 0; i < invalid_Data.length; i++) {
            getDriver().findElement(By.name("name")).sendKeys(Character.toString(invalid_Data[i]));

            getWait5().until(ExpectedConditions.textToBePresentInElement(errorText,
                    "» ‘" + invalid_Data[i] + "’ is an unsafe character"));

            String expectedResult = "» ‘" + invalid_Data[i] + "’ is an unsafe character";
            Assert.assertEquals(errorText.getText(), expectedResult);
            getDriver().findElement(By.id("name")).clear();
        }
    }
}
