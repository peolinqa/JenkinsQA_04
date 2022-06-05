import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class JulSabSingleCategoryUITest extends BaseTest {
    private static final By XPATH_NEW_ITEM = By.xpath("//span[contains(text(), 'New Item')]");
    private static final By XPATH_LIST_OF_LABEL = By.xpath("//li/label");
    private static final By XPATH_LIST_DESC_OF_LABEL = By.xpath("//div[@class='desc']");
    private static final By XPATH_LIST_ICON = By.xpath("//div[@class='icon']/img");

    @Test
    public void testCheckLabelStyle129001() {
        String expectedFontWeightResult = "700";
        String expectedFontSizeResult = "16px";
        String expectedColorResult = "rgba(51, 51, 51, 1)";

        getDriver().findElement(XPATH_NEW_ITEM).click();
        List<WebElement> labels = getDriver().findElements(XPATH_LIST_OF_LABEL);
        for (WebElement value : labels) {
            Assert.assertEquals(value.getCssValue("font-weight"), expectedFontWeightResult);
            Assert.assertEquals(value.getCssValue("font-size"), expectedFontSizeResult);
            Assert.assertEquals(value.getCssValue("color"), expectedColorResult);
        }
    }

    @Test
    public void testCheckDescriptionStyle129002() {
        String expectedFontSizeResult = "14px";
        String expectedColorResult = "rgba(51, 51, 51, 1)";

        getDriver().findElement(XPATH_NEW_ITEM).click();
        List<WebElement> descriptions = getDriver().findElements(XPATH_LIST_DESC_OF_LABEL);
        for (WebElement value : descriptions) {
            Assert.assertEquals(value.getCssValue("font-size"), expectedFontSizeResult);
            Assert.assertEquals(value.getCssValue("color"), expectedColorResult);
        }
    }

    @Test
    public void testCheckIconAvailabilityDisplaying129003() {
        getDriver().findElement(XPATH_NEW_ITEM).click();
        List<WebElement> icons = getDriver().findElements(XPATH_LIST_ICON);
        for (WebElement icon : icons) {
            Assert.assertTrue(icon.isDisplayed());
            Assert.assertTrue(icon.isEnabled());
        }
    }
}
