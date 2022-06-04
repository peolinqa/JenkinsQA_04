import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class SGNewItemNameSpecialCharacterTest extends BaseTest {
    private static final By NEW_ITEM = By.className("task-link-text");
    private static final By INPUT_LINE = By.id("name");
    private static final By ERROR_TEXT = By.id("itemname-invalid");

    @Test
    public void testNameWithOpenSquareBracket() {
        String expectedResult = "» ‘[’ is an unsafe character";

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys("[");
        String actualResult = getDriver().findElement(ERROR_TEXT).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testNameWithClosedSquareBracket() {
        String expectedResult = "» ‘]’ is an unsafe character";

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys("]");
        String actualResult = getDriver().findElement(ERROR_TEXT).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}




