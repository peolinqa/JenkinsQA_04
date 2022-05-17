import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OVFTest extends BaseTest {
    @Test
    public void testOVFtestbase() {

        String url = "http://testbase.ru/";
        String expectedResult = "Как найти границы на клиенте и сервере";

        getDriver().get(url);

        WebElement linkExploreBorders = getDriver().findElement(
                By.xpath("//a[contains(@href,'p=1517')]")
        );
        linkExploreBorders.click();

        WebElement linkArticle = getDriver().findElement(
                By.xpath("//a[contains(@href,'510458')]")
        );
        linkArticle.click();

        String originalWindow = getDriver().getWindowHandle();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }

        WebElement h1 = getDriver().findElement(
                By.xpath("//h1")
        );

        String actualResult = h1.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
