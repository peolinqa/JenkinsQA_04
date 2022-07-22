import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _ManageJenkinsTest extends BaseTest {

    @Test
    public void testCheckSectionNames() {
        final List<String> expectedSectionNames = List.of(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions");

        List<String> actualManageJenkinsSectionNames = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualManageJenkinsSectionNames();

        Assert.assertEquals(actualManageJenkinsSectionNames, expectedSectionNames);
    }

    @Test
    public void testCheckSectionContentToolsAndActions() {
        final List<String> expectedContentSectionToolsAndActions = List.of(
                "Reload Configuration from Disk",
                "Jenkins CLI",
                "Script Console",
                "Prepare for Shutdown");

        List<String> actualContentSectionToolsAndActions = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualManageJenkinsSectionContentToolsAndActions();

        Assert.assertEquals(actualContentSectionToolsAndActions, expectedContentSectionToolsAndActions);
    }

    @Test
    public void testSectionSystemConfigurationContent() {
        final List<String> expectedSystemConfigurationContent = List.of(
                "Configure System",
                "Global Tool Configuration",
                "Manage Plugins",
                "Manage Nodes and Clouds");

        List<String> actualSystemConfigurationContent = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualSystemConfigurationContent();

        Assert.assertEquals(actualSystemConfigurationContent, expectedSystemConfigurationContent);
    }
}
