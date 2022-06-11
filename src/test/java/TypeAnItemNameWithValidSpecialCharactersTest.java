import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TypeAnItemNameWithValidSpecialCharactersTest extends BaseTest {

    protected static final char[] CHARS =
            {',', 39, '`', '~', '-',' ','(',')','{','}','+','=', '_', '"'};

    @Test
    public void testCycleTypeAnItemNameWithValidSpecialCharacters_TC_009_009() {
        getDriver().findElement(By.className("task-link-text")).click();

        WebElement text = getDriver().findElement(By.className("input-help"));

        for (char x : CHARS) {
            getDriver().findElement(By.id("name")).sendKeys(Character.toString(x));

            getWait5().until(ExpectedConditions.textToBePresentInElement(text,
                    "» Required field"));

            Assert.assertEquals(text.getText(), "» Required field");

            getDriver().findElement(By.id("name")).clear();
        }
    }
}