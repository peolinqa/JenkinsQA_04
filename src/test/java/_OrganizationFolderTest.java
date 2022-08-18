import model.home.HomePage;
import model.NewItemPage;
import model.projects.orgFolder.OrganizationFolderConfigPage;
import model.projects.orgFolder.OrganizationFolderProjectPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.HashMap;
import java.util.List;

public class _OrganizationFolderTest extends BaseTest {

    private static final String VALID_FOLDER_NAME = TestUtils.getRandomStr(5);
    private static final String VALID_FOLDER_RENAME = TestUtils.getRandomStr(5);
    private static final String VALID_FOLDER_NAME1 = TestUtils.getRandomStr();
    private static final String VALID_FOLDER_NAME2 = TestUtils.getRandomStr();

    @Test
    public void testCreateOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setOrganizationFolderProjectType()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectNameText();

        Assert.assertEquals(projectName, VALID_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .setNewProjectName(VALID_FOLDER_RENAME)
                .clickRename()
                .getProjectNameText();

        Assert.assertEquals(projectName, VALID_FOLDER_RENAME);
    }

    @Test(dependsOnMethods = "testRenameOrganizationFolder")
    public void testCreateOrganizationFolderSameItemName() {
        NewItemPage<OrganizationFolderConfigPage> newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(VALID_FOLDER_RENAME)
                .setOrganizationFolderProjectType();

        Assert.assertTrue(newItemPage.isErrorMsgNameInvalidDisplayed());
        Assert.assertEquals(newItemPage.getErrorMsgNameInvalidText(), String.format("» A job already exists with the name ‘%s’", VALID_FOLDER_RENAME));
        Assert.assertEquals(newItemPage.getErrorMsgNameInvalidCss("color"), "rgba(255, 0, 0, 1)");
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderSameItemName")
    public void testCreateOrganizationFolderWithMetadataFolderIcon() {
        String attributeClassIkonProject = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_RENAME)
                .clickLinkConfigureTheProject()
                .clickAppearanceDropDownList()
                .selectOptionMetadataFolderIcon()
                .saveConfigAndGoToProject()
                .goHome()
                .getProjectIconAttributeClass(VALID_FOLDER_RENAME);

        Assert.assertEquals(attributeClassIkonProject, "icon-branch-api-organization-folder icon-lg");
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderWithMetadataFolderIcon")
    public void testCreateDisableOrganizationFolder() {
        HashMap<String, String> warningMessage = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_RENAME)
                .clickDisableButton()
                .getWarningDisableText();

        Assert.assertEquals(warningMessage.get("Warning Message"), "This Organization Folder is currently disabled");
        Assert.assertEquals(warningMessage.get("Message Color"), "rgba(196, 160, 0, 1)");
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolderWithMetadataFolderIcon", "testCreateDisableOrganizationFolder"})
    public void testDeleteOrganizationFolder() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_RENAME)
                .getSideMenu()
                .clickMenuDelete()
                .confirmDeleteAndGoHomePage()
                .getProjectsOnDashboardList();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_RENAME));
    }

    @Test
    public void testCreateOrganizationFolderAbortSaveButton() {
        List<String> textFolderNames = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setOrganizationFolderProjectType()
                .clickOkAndGoToConfig()
                .goHome()
                .getProjectsOnDashboardList();

        Assert.assertTrue(textFolderNames.contains(VALID_FOLDER_NAME));
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolderAbortSaveButton"})
    public void testProjectsTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .clickLinkConfigureTheProject()
                .clickProjectTab()
                .checkChildProjectIsDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test(dependsOnMethods = {"testProjectsTab"})
    public void testHealthMetricsTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .clickLinkConfigureTheProject()
                .clickHealthMetricsTab()
                .checkHealthMetricsIsDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test(dependsOnMethods = {"testHealthMetricsTab"})
    public void testAutomaticBranchProjectTriggeringTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .clickLinkConfigureTheProject()
                .clickAutomaticBranchProjectTriggeringTab()
                .checkAutomaticBranchProjectTriggeringIsDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test(dependsOnMethods = {"testAutomaticBranchProjectTriggeringTab"})
    public void testCheckNotificationAfterClickApply() {
        OrganizationFolderConfigPage organizationFolderConfigPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(VALID_FOLDER_NAME1)
                .setOrganizationFolderProjectType()
                .clickOkAndGoToConfig()
                .clickApply();

        Assert.assertEquals(organizationFolderConfigPage.getTextFromNotification(), "Saved");
        Assert.assertEquals(organizationFolderConfigPage.getClassAttributeFromNotification(), "notif-alert-success notif-alert-show");
        Assert.assertEquals(organizationFolderConfigPage.getColorValueAttributeFromNotification(), "rgba(19, 131, 71, 1)");
    }

    @Test(dependsOnMethods = "testCheckNotificationAfterClickApply")
    public void testRenameProjectViaInputDisplayNameField() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME1)
                .clickLinkConfigureTheProject()
                .inputDisplayNameField(VALID_FOLDER_NAME2)
                .saveConfigAndGoToProject()
                .goHome()
                .getProjectsOnDashboardList();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_NAME1));
        Assert.assertTrue(textFolderNames.contains(VALID_FOLDER_NAME2));
    }

    @Test(dependsOnMethods = "testRenameProjectViaInputDisplayNameField")
    public void testAddDescriptionViaConfigure() {
        OrganizationFolderProjectPage organizationFolderProjectPage = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME2)
                .clickLinkConfigureTheProject()
                .enterDescription("New project")
                .saveConfigAndGoToProject();

        Assert.assertEquals(organizationFolderProjectPage.getSystemMessageText(), "New project");
    }

    @Test(dependsOnMethods = "testRenameProjectViaInputDisplayNameField")
    public void testAddChildHealthMetrics() {
        boolean actualResult = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME2)
                .clickLinkConfigureTheProject()
                .clickChildHealthMetricsTab()
                .clickMetricsButton()
                .checkChildMetricsIsDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testCreateOrganizationFolderEmptyName() {
        NewItemPage<OrganizationFolderConfigPage> newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setOrganizationFolderProjectType();

        Assert.assertEquals(newItemPage.getErrorMsgNameRequiredText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(newItemPage.getErrorMsgNameInvalidCss("color"), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(newItemPage.getOkButtonAttribute("class"), "disabled");
    }
}
