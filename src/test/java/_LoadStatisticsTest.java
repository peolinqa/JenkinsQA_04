import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _LoadStatisticsTest extends BaseTest {

    @Test
    public void testCheckToolTipForEachTimeSpan() {
        String actualShortTooltip = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getShortTooltip();
        Assert.assertEquals(actualShortTooltip, "Every tick is 10 seconds");

        String actualMediumTooltip = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getMediumTooltip();
        Assert.assertEquals(actualMediumTooltip, "Every tick is one minute");

        String actualLongTooltip = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getLongTooltip();
        Assert.assertEquals(actualLongTooltip, "Every tick is one hour");
    }

    @Test
    public void testCheckButtonsStatusForEachTimeSpan() {
        String actualShortTagName = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getShortTagName();
        String actualMediumTagName = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getMediumTagName();
        String actualLongTagName = new HomePage(getDriver()).clickManageJenkins().clickLoadStatistics().getLongTagName();

        if (actualShortTagName.equals("span")) {
            Assert.assertEquals(actualMediumTagName, "a");
            Assert.assertEquals(actualLongTagName, "a");

        } else if (actualMediumTagName.equals("span")) {
            Assert.assertEquals(actualShortTagName, "a");
            Assert.assertEquals(actualLongTagName, "a");

        } else {
            Assert.assertEquals(actualShortTagName, "a");
            Assert.assertEquals(actualMediumTagName, "a");
            Assert.assertEquals(actualLongTagName, "span");
        }
    }
}
