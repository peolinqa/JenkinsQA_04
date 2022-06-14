import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class LoadStatisticsTest extends BaseTest {
    private static final By XPATH_MANAGE_JENKINS = By.xpath("//span[text()='Manage Jenkins']");
    private static final By XPATH_LOAD_STATISTICS = By.xpath("//dt[text()='Load Statistics']");
    private static final By XPATH_TIME_SPAN_LIST = By.xpath("//div[contains(text(), 'Timespan')]/child::*");

    @Test
    public void testCheckToolTipForEachTimeSpan118001() {
        List<String> expectedToolTips = List.of("Every tick is 10 seconds", "Every tick is one minute", "Every tick is one hour");

        getDriver().findElement(XPATH_MANAGE_JENKINS).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_LOAD_STATISTICS));
        getDriver().findElement(XPATH_LOAD_STATISTICS).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_TIME_SPAN_LIST));

        var timeSpan = getDriver().findElements(XPATH_TIME_SPAN_LIST);
        List<String> actualToolTips = new ArrayList<>();
        for (var period : timeSpan) {
            getWait5().until(ExpectedConditions.attributeToBeNotEmpty(period,"tooltip"));
            actualToolTips.add(period.getAttribute("tooltip"));
        }
        Assert.assertEquals(actualToolTips, expectedToolTips);
    }
}
