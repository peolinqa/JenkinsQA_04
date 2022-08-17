import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class ScriptConsoleTest extends BaseTest {
    private static final String RANDOM = TestUtils.getRandomStr(5);

    @Test
    public void testLinkGroovyScript() {
        String actualWebsite = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickScriptConsole()
                .clickLinkGroovyScript()
                .getGroovyTitle();

        Assert.assertEquals(actualWebsite, "The Apache Groovy programming language");
    }

    @Test
    public void testScriptConsoleRun() {
        String actualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickScriptConsole()
                .setTextArea(RANDOM)
                .clickRunButton()
                .getResultText();

        Assert.assertEquals(actualResult, String.format("Result: %s", RANDOM));
    }
}
