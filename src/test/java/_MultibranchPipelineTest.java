import model.HomePage;
import model.MultibranchPipelinePage;
import model.MultibranchPipelineConfigPage;
import model.ProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.Random;

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
                .clickNewItem()
                .setProjectName(PROJECT_NAME)
                .setProjectTypeMultiBranchPipeline()
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
                .clickConfigureProject()
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
                .clickConfigureProject()
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

        WebElement newName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(PIPELINE_NAME)
                .setProjectTypeMultiBranchPipeline()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToMultibranchPipelinePage()
                .goHome()
                .getProjectLinkByName(PIPELINE_NAME);

        Assert.assertEquals(newName.getText(), PIPELINE_NAME);
    }

    @Test (dependsOnMethods = "testCreateMultibranchPipelineWithValidData")
    public void testDeleteMultibranchPipelineProject() {

        HomePage homePage = new HomePage(getDriver())
                .clickMultibranchPipelineName(PIPELINE_NAME)
                .clickDeleteMultibranchPipeline()
                .clickYesButton();

        Assert.assertFalse(homePage.isItemPresent(PIPELINE_NAME));
    }

    @Test
    public void testCreateMultibranchWithInvalidData() {

        final String[] characterName = {"!", "@", "#", "$", ";", "%", "^", "&", "?", "*", "[", "]", "/", ":"};

        Random random = new Random();
        int low = 0;
        int high = characterName.length + 1;
        int result = random.nextInt(high - low) + low;

        String alertMessage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeMultiBranchPipeline()
                .setProjectName(characterName[result])
                .getNameErrorText();

        String expectedResult = String.format("» ‘%s’ is an unsafe character", characterName[result]);

        Assert.assertEquals(alertMessage, expectedResult);
    }

    @Test
    public void testMultibranchDisable() {
        MultibranchPipelinePage multibranchPipelinePage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(NAME)
                .setProjectTypeMultiBranchPipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxDisable()
                .saveConfigAndGoToMultibranchPipelineProject();

        Assert.assertTrue(multibranchPipelinePage.getIconFolderDisabled().isDisplayed());
        Assert.assertEquals(multibranchPipelinePage.getMessageDisabled().getText(),
                "This Multibranch Pipeline is currently disabled \nEnable");

        HomePage homePage = new MultibranchPipelinePage(getDriver()).clickDashboardButton();

        Assert.assertEquals(homePage.getProjectIconByName(NAME).getAttribute("class"),
                        "icon-folder-disabled icon-lg");
    }

    @Test(dependsOnMethods = "testMultibranchDisable")
    public void testMultibranchEnable() {
        MultibranchPipelinePage multibranchPipelinePage = new ProjectPage(getDriver())
                .clickDashboardButton()
                .clickMyView()
                .moveToElement(NAME)
                .selectOptionInMenuSelector1("Configure")
                .clickCheckboxDisable()
                .saveConfigAndGoToMultibranchPipelineProject();

        Assert.assertTrue(multibranchPipelinePage.getIconFolderEnabled().isDisplayed());

        HomePage homePage = multibranchPipelinePage.clickDashboardButton();

        Assert.assertEquals(homePage.getProjectIconByName(NAME).getAttribute("class"),
                "icon-pipeline-multibranch-project icon-lg");
    }
}