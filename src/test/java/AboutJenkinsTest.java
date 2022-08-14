import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class AboutJenkinsTest extends BaseTest {

    private int amountLinks;

    @Test
    public void testCountLinksTabMavenizedDependencies() {
        amountLinks = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickAboutJenkins()
                .clickTabMavenizedDependencies()
                .countLinksInTab("Mavenized dependencies");

        Assert.assertEquals(amountLinks, 107);
    }

    @Test(dependsOnMethods = "testCountLinksTabMavenizedDependencies")
    public void testCountLinksTabStaticResources() {
        amountLinks = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickAboutJenkins()
                .clickTabStaticResources()
                .countLinksInTab("Static resources");

        Assert.assertEquals(amountLinks, 4);
    }

    @Test(dependsOnMethods = "testCountLinksTabMavenizedDependencies")
    public void testCountLinksTabLicenseAndDependency() {
        amountLinks = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickAboutJenkins()
                .clickTabLicenseAndDependency()
                .countLinksInTab("License and dependency information for plugins");

        Assert.assertEquals(amountLinks, 85);
    }

    @Test(dependsOnMethods = "testCountLinksTabMavenizedDependencies")
    public void testLinkAntLRParserGenerator() {
        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickAboutJenkins()
                .clickLinkAntLRParserGenerator();

        Assert.assertTrue(TestUtils.getOpenTabTitles(getDriver()).contains("ANTLR"));
    }
}
