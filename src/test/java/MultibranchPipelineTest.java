import model.home.HomePage;
import model.projects.multibranch.MultibranchPipelineProjectPage;
import model.projects.multibranch.MultibranchPipelineConfigPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class MultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = TestUtils.getRandomStr(7);
    private static final String URL_GITHUB = "https://github.com/GitForProjects/javaJenkins";
    private static final String PIPELINE_NAME = "MultiPipeline";

    private final String NAME = multiPipelineName();

    private String multiPipelineName() {
        return TestUtils.getRandomStr(7);
    }

    @Test
    public void testCreateNewJob() {
        int numberOfNamesFound = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PROJECT_NAME)
                .setMultiBranchPipelineProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .goHome()
                .getSizeOfProjectLinkByName(PROJECT_NAME);

        Assert.assertEquals(numberOfNamesFound, 1);
    }

    @Test (dependsOnMethods = "testCreateNewJob")
    public void testValidGitHubLink() {
        new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .clickAddSourceButton()
                .clickGitHubSelectorMenu()
                .setInputRepositoryUrl(URL_GITHUB)
                .clickValidateButton()
                .assertEquals(MultibranchPipelineConfigPage::getValidateText, String.format("Credentials ok. Connected to %s.", URL_GITHUB))
                .saveProjectConfiguration();
    }

    @Test (dependsOnMethods = "testValidGitHubLink")
    public void testAddGitHubLink() {
        String actualUrl = new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .getRepositoryUrlText();

        Assert.assertEquals(actualUrl, URL_GITHUB);
    }

    @Test (dependsOnMethods = "testAddGitHubLink")
    public void testScanResult() {
        String scanLog = new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .getSideMenu()
                .clickLinkScanRepositoryLog()
                .clickLinkViewAsPlainText()
                .getConsoleText();

        Assert.assertTrue(scanLog.contains("Finished: SUCCESS"));
    }

    @Test
    public void testCreateMultibranchPipelineWithValidData() {
        boolean projectIsPresent = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(PIPELINE_NAME)
                .setMultiBranchPipelineProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .goHome()
                .isProjectNamePresent(PIPELINE_NAME);

        Assert.assertTrue(projectIsPresent);
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithValidData")
    public void testDeleteMultibranchPipelineProject() {
        HomePage homePage = new HomePage(getDriver())
                .clickMultibranchPipelineName(PIPELINE_NAME)
                .getSideMenu()
                .clickMenuDelete()
                .clickBtnYesConfirmDelete();

        Assert.assertFalse(homePage.isItemPresent(PIPELINE_NAME));
    }

    @Test
    public void testMultibranchDisable() {
        String attributeClassIconProject = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(NAME)
                .setMultiBranchPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickCheckboxDisable()
                .saveProjectConfiguration()
                .assertEquals(MultibranchPipelineProjectPage::isIconProjectDisabledDisplayed, true)
                .assertEquals(MultibranchPipelineProjectPage::getWarningDisableText, "This Multibranch Pipeline is currently disabled \nEnable")
                .clickLinkDashboard()
                .getProjectIconAttributeClass(NAME);

        Assert.assertEquals(attributeClassIconProject, "icon-folder-disabled icon-lg");
    }

    @Test(dependsOnMethods = "testMultibranchDisable")
    public void testMultibranchEnable() {
        String attributeClassIconProject = new HomePage(getDriver())
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuMyView()
                .moveToProjectName(NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorMultibranchConfigure()
                .clickCheckboxDisable()
                .saveProjectConfiguration()
                .assertEquals(MultibranchPipelineProjectPage::istIconProjectEnabled, true)
                .clickLinkDashboard()
                .getProjectIconAttributeClass(NAME);

        Assert.assertEquals(attributeClassIconProject, "icon-pipeline-multibranch-project icon-lg");
    }
}
