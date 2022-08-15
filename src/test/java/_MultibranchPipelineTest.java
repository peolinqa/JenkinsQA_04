import model.HomePage;
import model.MultibranchPipelineProjectPage;
import model.MultibranchPipelineConfigPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _MultibranchPipelineTest extends BaseTest {

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
                .saveConfigAndGoToMultibranchPipelinePage()
                .goHome()
                .getSizeOfProjectLinkByName(PROJECT_NAME);

        Assert.assertEquals(numberOfNamesFound, 1);
    }

    @Test (dependsOnMethods = "testCreateNewJob")
    public void testValidGitHubLink() {
        String validationResult = new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .clickAddSourceButton()
                .clickGitHubField()
                .setRepositoryUrl(URL_GITHUB)
                .clickValidateButton()
                .getValidateText();

        Assert.assertEquals(validationResult, String.format("Credentials ok. Connected to %s.", URL_GITHUB));

        new MultibranchPipelineConfigPage(getDriver()).saveConfigAndGoToMultibranchPipelinePage();
    }

    @Test (dependsOnMethods = "testValidGitHubLink")
    public void testAddGitHubLink() {
        String actualUrl = new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .getSideMenu()
                .clickMenuConfigure()
                .getTextRepositoryUrl();

        Assert.assertEquals(actualUrl, URL_GITHUB);
    }

    @Test (dependsOnMethods = "testAddGitHubLink")
    public void testScanResult() {
        String scanLog = new HomePage(getDriver())
                .clickMultibranchPipelineName(PROJECT_NAME)
                .clickScanRepositoryLog()
                .clickViewAsPlainText()
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
                .saveConfigAndGoToMultibranchPipelinePage()
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
                .confirmDeleteAndGoHomePage();

        Assert.assertFalse(homePage.isItemPresent(PIPELINE_NAME));
    }

    @Test
    public void testMultibranchDisable() {
        String attributeClassIkonProject = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(NAME)
                .setMultiBranchPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickCheckboxDisable()
                .saveConfigAndGoToMultibranchPipelineProject()
                .assertEquals(MultibranchPipelineProjectPage::isIconFolderDisabledDisplayed, true)
                .assertEquals(MultibranchPipelineProjectPage::textMessageDisabled, "This Multibranch Pipeline is currently disabled \nEnable")
                .clickLinkDashboard()
                .getProjectIconAttributeClass(NAME);

        Assert.assertEquals(attributeClassIkonProject, "icon-folder-disabled icon-lg");
    }

    @Test(dependsOnMethods = "testMultibranchDisable")
    public void testMultibranchEnable() {
        String attributeClassIkonProject = new HomePage(getDriver())
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuMyView()
                .moveToProjectName(NAME)
                .clickProjectDropDownMenu()
                .clickMenuSelectorMultibranchConfigure()
                .clickCheckboxDisable()
                .saveConfigAndGoToMultibranchPipelineProject()
                .assertEquals(MultibranchPipelineProjectPage::istIconFolderEnabled, true)
                .clickLinkDashboard()
                .getProjectIconAttributeClass(NAME);

        Assert.assertEquals(attributeClassIkonProject, "icon-pipeline-multibranch-project icon-lg");
    }
}
