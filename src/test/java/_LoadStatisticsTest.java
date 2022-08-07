import model.HomePage;
import model.LoadStatisticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _LoadStatisticsTest extends BaseTest {

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
    public void checkClickShortButton() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickShortButton();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "span");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "a");
    }

    @Test(dependsOnMethods = "checkClickShortButton")
    public void checkClickMediumButton() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickMediumButton();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "span");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "a");
    }

    @Test(dependsOnMethods = "checkClickMediumButton")
    public void checkClickLongButton() {
        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickLoadStatistics()
                .clickLongButton();

        Assert.assertEquals(loadStatisticsPage.getShortTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "a");
        Assert.assertEquals(loadStatisticsPage.getLongTagName(), "span");
    }
}
