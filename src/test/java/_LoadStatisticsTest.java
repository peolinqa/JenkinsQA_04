import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

public class _LoadStatisticsTest extends BaseTest {
    private static final By XPATH_TIME_SPAN_LIST = By.xpath("//div[contains(text(), 'Timespan')]/child::*");

    private boolean timeSpanIsSelected(WebElement period) {
        return period.getTagName().equals("span");
    }

    private void goLoadStatisticsPage() {
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.LoadStatistics.click(getDriver());
    }

    @Test
    public void testCheckToolTipForEachTimeSpan() {
        final List<String> expectedToolTips = List.of("Every tick is 10 seconds", "Every tick is one minute", "Every tick is one hour");

        goLoadStatisticsPage();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_TIME_SPAN_LIST));

        var timeSpan = getDriver().findElements(XPATH_TIME_SPAN_LIST);
        List<String> actualToolTips = new ArrayList<>();
        for (var period : timeSpan) {
            getWait5().until(ExpectedConditions.attributeToBeNotEmpty(period, "tooltip"));
            actualToolTips.add(period.getAttribute("tooltip"));
        }
        Assert.assertEquals(actualToolTips, expectedToolTips);
    }

    @Test
    public void testCheckButtonsStatusForEachTimeSpan() {
        SoftAssert asserts = new SoftAssert();

        goLoadStatisticsPage();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_TIME_SPAN_LIST));
        var timeSpan = getDriver().findElements(XPATH_TIME_SPAN_LIST);

        asserts.assertEquals(timeSpan.size(), 3);

        WebElement shortButton = timeSpan.get(0);
        WebElement mediumButton = timeSpan.get(1);
        WebElement longButton = timeSpan.get(2);

        if (timeSpanIsSelected(shortButton)) {
            asserts.assertFalse(timeSpanIsSelected(mediumButton));
            asserts.assertFalse(timeSpanIsSelected(longButton));
        } else if (timeSpanIsSelected(mediumButton)) {
            asserts.assertFalse(timeSpanIsSelected(shortButton));
            asserts.assertFalse(timeSpanIsSelected(longButton));
        } else {
            asserts.assertTrue(timeSpanIsSelected(longButton));
            asserts.assertFalse(timeSpanIsSelected(shortButton));
            asserts.assertFalse(timeSpanIsSelected(mediumButton));
        }
        asserts.assertAll();
    }
}
