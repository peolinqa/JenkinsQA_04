import model.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _ConfigureSystemTest extends BaseTest {

    private static final String RANDOM_SYSTEM_MESSAGE = TestUtils.getRandomStr();

    @Test
    public void testSaveSystemMessage() {
        String systemMessageText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureSystem()
                .setSystemMessage(RANDOM_SYSTEM_MESSAGE)
                .clickSaveButton()
                .getSystemMessageText();

        Assert.assertEquals(systemMessageText, RANDOM_SYSTEM_MESSAGE);
    }

    @Test(dependsOnMethods = "testSaveSystemMessage")
    public void testPreviewSystemMessage() {
        String previewActualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureSystem()
                .clickPreviewSystemMessage()
                .getPreviewSystemMessageText();

        Assert.assertEquals(previewActualResult, RANDOM_SYSTEM_MESSAGE);
    }
}
