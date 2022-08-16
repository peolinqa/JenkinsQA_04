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
                .getSideMenu()
                .clickMenuManageJenkins()
                .getActualManageJenkinsSectionNames();

        Assert.assertEquals(actualManageJenkinsSectionNames, expectedSectionNames);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testCheckSectionContentToolsAndActions() {
        final List<String> expectedContentSectionToolsAndActions = List.of(
                "Reload Configuration from Disk",
                "Jenkins CLI",
                "Script Console",
                "Prepare for Shutdown");

        List<String> actualContentSectionToolsAndActions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getActualManageJenkinsSectionContentToolsAndActions();

        Assert.assertEquals(actualContentSectionToolsAndActions, expectedContentSectionToolsAndActions);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testSectionSystemConfigurationContent() {
        final List<String> expectedSystemConfigurationContent = List.of(
                "Configure System",
                "Global Tool Configuration",
                "Manage Plugins",
                "Manage Nodes and Clouds");

        List<String> actualSystemConfigurationContent = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getActualSystemConfigurationContent();

        Assert.assertEquals(actualSystemConfigurationContent, expectedSystemConfigurationContent);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testSectionSecurityContent() {
        final List<String> expectedSecurityContent = List.of(
                "Configure Global Security",
                "Manage Credentials",
                "Configure Credential Providers",
                "Manage Users",
                "In-process Script Approval");

        List<String> actualSecurityContent = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getActualSecurityContent();

        Assert.assertEquals(actualSecurityContent, expectedSecurityContent);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testCaptionsSystemConfiguration(){
        List<String> expectedCaptions = List.of(
                "Configure global settings and paths.",
                "Configure tools, their locations and automatic installers.",
                "Add, remove, disable or enable plugins that can extend the functionality of Jenkins.",
                "There are updates available",
                "Add, remove, control and monitor the various nodes that Jenkins runs jobs on.");

        List<String> actualCaptions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getCaptionsSystemSysConf();

        Assert.assertEquals(actualCaptions, expectedCaptions);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testCaptionsSecurity(){
        List<String> expectedCaptions = List.of(
                "Secure Jenkins; define who is allowed to access/use the system.",
                "Configure credentials",
                "Configure the credential providers and types",
                "Create/delete/modify users that can log in to this Jenkins",
                "Allows a Jenkins administrator to review proposed scripts (written e.g. in Groovy) which run inside the Jenkins process and so could bypass security restrictions.");

        List<String> actualCaptions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getCaptionsSecurity();

        Assert.assertEquals(actualCaptions, expectedCaptions);

    }

    @Test (dependsOnMethods = "testCheckSectionNames")
    public void testCaptionsStatusInfo(){
        List<String> expectedCaptions = List.of(
                "Displays various environmental information to assist trouble-shooting.",
                "System log captures output from java.util.logging output related to Jenkins.",
                "Check your resource utilization and see if you need more computers for your builds.",
                "See the version and license information."
        );

        List<String> actualCaptions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getCaptionsStatusInfo();

        Assert.assertEquals(actualCaptions, expectedCaptions);
    }


    @Test (dependsOnMethods = "testCheckSectionNames")
    public void testCaptionsTroubleshooting(){
        List<String> expectedCaptions = List.of(
                "Scrub configuration files to remove remnants from old plugins and earlier versions."
        );

        List<String> actualCaptions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getCaptionsTroubleshooting();

        Assert.assertEquals(actualCaptions, expectedCaptions);
    }

    @Test (dependsOnMethods = "testCheckSectionNames")
    public void testCaptionsToolsAndActions(){
        List<String> expectedCaptions = List.of(
          "Discard all the loaded data in memory and reload everything from file system. Useful when you modified config files directly on disk.",
          "Access/manage Jenkins from your shell, or from your script.",
          "Executes arbitrary script for administration/trouble-shooting/diagnostics.",
          "Stops executing new builds, so that the system can be eventually shut down safely."
        );

        List<String> actualCaptions = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .getCaptionsToolsAnsActions();

        Assert.assertEquals(actualCaptions, expectedCaptions);
    }
}
