import model.projects.freestyle.FreestyleConfigPage;
import model.projects.freestyle.FreestyleProjectPage;
import model.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class FreestyleTest extends BaseTest {
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
                .setFreestyleProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .getProjectNameText();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testSaveButtonAfterProjectCreated() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .saveProjectConfiguration()
                .getProjectNameText();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testSaveButtonAfterProjectCreated")
    public void testApplyButtonAfterProjectCreated() {
        boolean alertIsDisplayed = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .clickApplyAndGetAlert();

        Assert.assertTrue(alertIsDisplayed);
    }

    @Test(dependsOnMethods = "testApplyButtonAfterProjectCreated")
    public void testAddDescription() {
        String description = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .setDescription(DESCRIPTION_TEXT)
                .saveProjectConfiguration()
                .getDescriptionText();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testEditDescription() {
        String editDescription = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickEditDescription()
                .editProjectDescription(EDITED_RANDOM_DESCRIPTION)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(editDescription, EDITED_RANDOM_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testDisableProject() {
        FreestyleProjectPage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickDisableProjectButton();

        Assert.assertEquals(freestylePage.getWarningDisableText()[0], "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {
        FreestyleProjectPage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickEnableProjectButton();

        Assert.assertEquals(freestylePage.getDisableButtonText(), "Disable Project");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testHelpButtonPopupGeneral() {
        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuConfigure();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesGeneral(), "Help for feature: Discard old builds");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupGeneral")
    public void testHelpButtonPopupBuildPeriodically() {
        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .clickTabBuildTriggers();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesBuildTriggers(), "Help for feature: Build periodically");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupBuildPeriodically")
    public void testRenameFreestyleProject() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRename()
                .getProjectNameText();

        Assert.assertEquals(projectName, EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testNewFreestyleWithSpecialCharacters() {
        String projectName = new HomePage(getDriver())
                .clickFreestyleName(EDITED_RANDOM_NAME)
                .getSideMenu()
                .clickMenuRename()
                .setNewProjectName(NAME_WITH_SPECIAL_CHARACTERS)
                .clickRename()
                .getProjectNameText();

        Assert.assertEquals(projectName, NAME_WITH_SPECIAL_CHARACTERS);
    }

    @Test(dependsOnMethods = {"testCreateFreestyleProject",
            "testSaveButtonAfterProjectCreated", "testNewFreestyleWithSpecialCharacters"})
    public void testDeleteFreestyleProject() {
        new HomePage(getDriver())
                .clickFreestyleName(NAME_WITH_SPECIAL_CHARACTERS)
                .getSideMenu()
                .clickMenuDeleteProjectAndConfirm()
                .assertTrue(page -> !page.isProjectNamePresent(NAME_WITH_SPECIAL_CHARACTERS));
    }
}
