import model.HomePage;
import model.ConfigureSystemPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _ConfigureSystemTest extends BaseTest {

    private static final String RANDOM_SYSTEM_MESSAGE = TestUtils.getRandomStr();
    @Test
    public void testConfigureSystemMessagePositive() {
        String previewActualResult = new HomePage(getDriver())
                .clickManageJenkins()
                .clickConfigureSystem()
                .setSystemMessage(RANDOM_SYSTEM_MESSAGE)
                .clickPreviewSystemMessage()
                .getPreviewSystemMessageText();

        String actualResult = new ConfigureSystemPage(getDriver())
                .clickSaveButton()
                .clickDashboardButton()
                .getSystemMessageText();

        Assert.assertEquals(previewActualResult, RANDOM_SYSTEM_MESSAGE);
        Assert.assertEquals(actualResult, RANDOM_SYSTEM_MESSAGE);
    }
}