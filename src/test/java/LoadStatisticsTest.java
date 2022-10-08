import model.home.HomePage;
import model.manageJenkins.LoadStatisticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LoadStatisticsTest extends BaseTest {

    @Test
    public void testCheckToolTipForEachTimeSpan() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics();

        Assert.assertEquals(loadStatisticsPage.getShortTooltip(), "Every tick is 10 seconds");
        Assert.assertEquals(loadStatisticsPage.getMediumTooltip(), "Every tick is one minute");
        Assert.assertEquals(loadStatisticsPage.getLongTooltip(), "Every tick is one hour");
    }

    @Test
    public void checkClickShortTimespan() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickLinkShortTimespan();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "span");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "a");
    }

    @Test(dependsOnMethods = "checkClickShortTimespan")
    public void checkClickMediumTimespan() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickLinkMediumTimespan();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "span");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "a");
    }

    @Test(dependsOnMethods = "checkClickMediumTimespan")
    public void checkClickLongTimespan() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickLinkLongTimespan();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "span");
    }
}
