import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _ScriptConsoleTest extends BaseTest {
    @Test
    public void testLinkGroovyScript() {
        String actualWebsite = new HomePage(getDriver())
                .clickManageJenkins()
                .clickScriptConsole()
                .clickGroovyScript()
                .getTitleGroovy();

        Assert.assertEquals(actualWebsite, "The Apache Groovy programming language");
    }
}
