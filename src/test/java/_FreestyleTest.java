import model.FreestyleConfigPage;
import model.FreestyleProjectPage;
import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _FreestyleTest extends BaseTest {
    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = String.format("New %s", RANDOM_NAME);
    private static final String NAME_WITH_SPECIAL_CHARACTERS = "-()+-_~-1";
    private static final String EDITED_RANDOM_DESCRIPTION = TestUtils.getRandomStr(15);
    private static final String DESCRIPTION_TEXT = "This is a description for a Freestyle project";

    @Test
    public void testCreateFreestyleProject() {
        String projectName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_NAME)
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFreestyleProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testSaveButtonAfterProjectCreated() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .saveConfigAndGoToFreestyleProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testSaveButtonAfterProjectCreated")
    public void testApplyButtonAfterProjectCreated() {
        boolean alertIsDisplayed = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .clickApplyAndGetAlert();

        Assert.assertTrue(alertIsDisplayed);
    }

    @Test(dependsOnMethods = "testApplyButtonAfterProjectCreated")
    public void testAddDescription() {
        String description = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .setDescription(DESCRIPTION_TEXT)
                .saveConfigAndGoToFreestyleProject()
                .getDescriptionName();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testEditDescription() {
        String editDescription = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickEditDescription()
                .editDescription(EDITED_RANDOM_DESCRIPTION)
                .clickMultiButton()
                .getDescriptionName();

        Assert.assertEquals(editDescription, EDITED_RANDOM_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testDisableProject() {
        FreestyleProjectPage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickMultiButton();

        Assert.assertEquals(freestylePage.getDisableName()[0], "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {
        FreestyleProjectPage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickMultiButton();

        Assert.assertEquals(freestylePage._disableButton(), "Disable Project");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testHelpButtonPopupGeneral() {
        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesGeneral(), "Help for feature: Discard old builds");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupGeneral")
    public void testHelpButtonPopupBuildPeriodically() {
        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .clickBuildTriggers();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesBuildTriggers(), "Help for feature: Build periodically");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupBuildPeriodically")
    public void testRenameFreestyleProject() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(projectName, EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testNewFreestyleWithSpecialCharacters() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickRenameAndGoToRenamePage()
                .setNewProjectName(NAME_WITH_SPECIAL_CHARACTERS)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(projectName, NAME_WITH_SPECIAL_CHARACTERS);
    }

    @Test(dependsOnMethods = {"testCreateFreestyleProject",
            "testSaveButtonAfterProjectCreated", "testNewFreestyleWithSpecialCharacters"})
    public void testDeleteFreestyleProject() {
        boolean projectIsPresent = new HomePage(getDriver())
                .clickFreestyleName(NAME_WITH_SPECIAL_CHARACTERS)
                .clickDeleteProjectAndConfirm()
                .checkProjectNameIsPresent(NAME_WITH_SPECIAL_CHARACTERS);

        Assert.assertFalse(projectIsPresent);
    }
}
