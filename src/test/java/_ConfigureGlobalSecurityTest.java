import model.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class _ConfigureGlobalSecurityTest extends BaseTest {

    @Test
    public void test9ChaptersDisplayedOnGlobalSecurityPage() {
        List<WebElement> chapters = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureGlobalSecurity()
                .getSecurityChapters();

        Assert.assertEquals(chapters.size(), 9);
    }

    @Test
    public void testCheckChaptersNames() {
       List<String> expectedChaptersNames = List.of(
                "Authentication",
                "Security Realm",
                "Authorization",
                "Markup Formatter",
                "Agents",
                "CSRF Protection",
                "Hidden security warnings",
                "API Token",
                "SSH Server");

        List<String> actualSecurityChaptersNames = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureGlobalSecurity()
                .getActualSecurityChaptersNames();

        Assert.assertEquals(actualSecurityChaptersNames, expectedChaptersNames);
    }

    @Test
    public void testCheckHelpButtonSSHServerPOM() {
        String tooltipHelpButtonSSHServer = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureGlobalSecurity()
                .getTextTooltipButtonHelpSSHServerPOM();

        Assert.assertEquals(tooltipHelpButtonSSHServer, "Help for feature: SSHD Port");
    }

    @Test
    public void testCheckTooltipText() {
        List<String> tooltipTextList = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureGlobalSecurity()
                .getTooltipTextList();

        Assert.assertTrue(tooltipTextList.contains("Help"));
    }

    @Test
    public void testAmountTooltip() {
        int actualAmountTooltip = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickConfigureGlobalSecurity()
                .countHelpIcons();

        Assert.assertEquals(actualAmountTooltip, 17);
    }
}
