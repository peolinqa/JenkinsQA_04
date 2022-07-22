import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _AboutJenkinsTest extends BaseTest {
    private int amountLinks;
    @Test
    public void testAmountLinksTabMavenizedDependencies() {
        amountLinks = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickMavenizedDependencies()
                .countLinksInTab("Mavenized dependencies");

        Assert.assertEquals(amountLinks, 107);
    }

    @Test(dependsOnMethods = "testAmountLinksTabMavenizedDependencies")
    public void testAmountLinksTabStaticResources() {
        amountLinks = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickStaticResources()
                .countLinksInTab("Static resources");

        Assert.assertEquals(amountLinks, 4);
    }

    @Test(dependsOnMethods = "testAmountLinksTabMavenizedDependencies")
    public void testAmountLinksTabLicenseAndDependencyInformationForPlugins() {
        amountLinks = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickLicenseAndDependencyInformationForPlugins()
                .countLinksInTab("License and dependency information for plugins");

        Assert.assertEquals(amountLinks, 85);
    }

    @Test(dependsOnMethods = "testAmountLinksTabMavenizedDependencies")
    public void testLinkAntLRParserGenerator() {
        new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickLinkAntLRParserGenerator();

        Assert.assertTrue(TestUtils.getOpenTabTitles(getDriver()).contains("ANTLR"));
    }
}