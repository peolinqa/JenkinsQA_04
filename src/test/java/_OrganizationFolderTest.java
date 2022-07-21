import model.HomePage;
import model.NewItemPage;
import model.OrganizationFolderConfigPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
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
    private static final String DISABLED_FOLDER_NAME = TestUtils.getRandomStr();

    @Test
    public void testCreateOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .renameOrganizationFolder()
                .setNewProjectName(VALID_FOLDER_RENAME)
                .clickRenameAndGoToOrganizationFolder()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_RENAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testRenameOrganizationFolder")
    public void testCreateOrganizationFolderSameItemName() {
        boolean isDisplayedNameError = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_RENAME)
                .setProjectTypeOrganizationFolder()
                .isDisplayedNameError();

        NewItemPage newItemPage = new NewItemPage(getDriver());

        Assert.assertTrue(isDisplayedNameError);
        Assert.assertEquals(newItemPage.getNameErrorText(),
                "» A job already exists with the name ‘" + VALID_FOLDER_RENAME + "’");
        Assert.assertEquals(newItemPage.getNameErrorCss("color").toString(),
                "rgba(255, 0, 0, 1)");
    }

    @Test(dependsOnMethods = {"testRenameOrganizationFolder"})
    public void testDeleteOrganizationFolder() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_RENAME)
                .deleteOrganizationFolder()
                .deleteOrganizationFolderAndGoHomePage()
                .getTextFolderNamesOnDashboard();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_RENAME));
    }

    @Test
    public void createDisableOrganizationFolderTest() {

        HashMap<String, String> warningMessage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(DISABLED_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickDisableCheckBox()
                .saveConfigAndGoToProject()
                .getDisabledProjectWarningMessage();

        Assert.assertEquals(warningMessage.get("Warning Message"), "This Organization Folder is currently disabled");
        Assert.assertEquals(warningMessage.get("Message Color"), "rgba(196, 160, 0, 1)");
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolder", "testRenameOrganizationFolder", "testDeleteOrganizationFolder"})
    public void testCreateOrganizationFolderWithMetadataFolderIcon() {
        String projectIcon = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickAppearanceDropDownList()
                .selectOptionMetadataFolderIcon()
                .saveConfigAndGoToProject()
                .clickJenkinsIconAndGoToHomePage()
                .getProjectIconByName(VALID_FOLDER_NAME)
                .getAttribute("class");

        Assert.assertEquals(projectIcon,
                "icon-branch-api-organization-folder icon-lg");
    }

    @Test(dependsOnMethods = "testDeleteOrganizationFolder")
    public void testCreateOrganizationFolderAbortCreation() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME2)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .goHome().getTextFolderNamesOnDashboard();

        Assert.assertTrue(textFolderNames.contains(VALID_FOLDER_NAME2));
    }

    @Test
    public void createOrganizationFolderEmptyName() {
        NewItemPage<OrganizationFolderConfigPage> newItemPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeOrganizationFolder();

        Assert.assertEquals(newItemPage.getErrorNameRequiredText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(newItemPage.getNameErrorCss("color"), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(newItemPage.getAttributeOkButton("class"), "disabled");
    }

    @Test
    public void testProjectsTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickProjectTab()
                .ckeckChildProjectIsDisplayed();

        Assert.assertTrue(actualResult,"Project Tab not found");
    }

    @Test
    public void testHealthMetricsTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickHealthMetricsTab()
                .ckeckhealthMetricsIsDisplayed();

        Assert.assertTrue(actualResult,"Health Metrics Tab not found");
    }

    @Test
    public void testAutomaticBranchProjectTriggeringTab() {
        boolean actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickAutomaticBranchProjectTriggeringTab()
                .ckeckAutomaticBranchProjectTriggeringIsDisplayed();

        Assert.assertTrue(actualResult,"Automatic Branch Project Triggering Tab not found");
    }

    @Test
    public void testCheckNotificationAfterClickApply() {
        OrganizationFolderConfigPage organizationFolderConfigPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME1)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .inputDisplayNameField(VALID_FOLDER_NAME2)
                .clickApply();

        Assert.assertEquals(organizationFolderConfigPage.getTextFromNotification(), "Saved");
        Assert.assertEquals(organizationFolderConfigPage.getClassAttributeFromNotification(), "notif-alert-success notif-alert-show");
        Assert.assertEquals(organizationFolderConfigPage.getColorValueAttributeFromNotification(), "rgba(19, 131, 71, 1)");
    }

    @Test(dependsOnMethods = "testCheckNotificationAfterClickApply")
    public void testCheckNewDisplayNameOnDashboard() {
        List<String> result = new HomePage(getDriver()).getTextFolderNamesOnDashboard();

        Assert.assertFalse(result.contains(VALID_FOLDER_NAME1));
        Assert.assertTrue(result.contains(VALID_FOLDER_NAME2));
    }

    @Test
    public void testUserCanAddProperties() {
        boolean actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .enterDescription("New project")
                .clickChildHealthMetricsTab()
                .clickMetricsButton()
                .clickApplyButton()
                .saveConfigAndGoToProject()
                .clickConfigureProjectButton()
                .clickPropertiesTab()
                .clickMetricsButton()
                .checkChildMetricsIsDisplayed();

        Assert.assertTrue(actualResult);
    }
}
