import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFolderWithInvalidDataTest extends BaseTest {

    protected static final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};

    @Test
    public void testCycleCreateFolderWithInvalidData_TC_009_003() {

        getDriver().findElement(By.className("task-link-text")).click();
        WebElement text = getDriver().findElement(By.id("itemname-invalid"));

        for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
            getDriver().findElement(By.id("name")).sendKeys(Character.toString(INVALID_SYMBOLS[i]));

            getWait5().until(ExpectedConditions.textToBePresentInElement(text,
                    "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character"));

            String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character";
            Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(),
                    expectedResult);
            getDriver().findElement(By.id("name")).clear();
        }
    }

    @Test
    public void testCreateFolderWithDot_TC_009_003() {

        getDriver().findElement(By.className("task-link-text")).click();

        getDriver().findElement(By.id("name")).sendKeys(".");

        WebElement text = getDriver().findElement(By.id("itemname-invalid"));
        getWait5().until(ExpectedConditions.textToBePresentInElement(text, "» “.” is not an allowed name"));
        Assert.assertEquals(text.getText(), "» “.” is not an allowed name");
        getDriver().findElement(By.id("name")).clear();
    }
}