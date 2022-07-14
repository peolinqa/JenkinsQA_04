import model.HomePage;
import model.MultibranchPipelineConfigPage;
import model.ProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _MultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = TestUtils.getRandomStr(7);
    private static final String URL_GITHUB = "https://github.com/GitForProjects/javaJenkins";
    private static final String PIPELINE_NAME = "MultiPipeline";

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

        char[] unsafeCharacters = {'!', '@', '#', '$', '%', '^', '&', '*', '[', ']', ';', ':'};

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectNameField = getDriver().findElement(By.name("name"));

        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-nested-projects']//li[2]")
        ).click();

        for (char unsafeChar : unsafeCharacters) {
            String expectedError = String.format("» ‘%s’ is an unsafe character", unsafeChar);

            projectNameField.sendKeys(String.format("%s%s", unsafeChar, PROJECT_NAME));
            String actualError = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='itemname-invalid'][@class='input-validation-message']"))
            ).getText();

            Assert.assertEquals(actualError, expectedError);

            projectNameField.clear();
        }
    }

    @Test
    public void testMultibranchDisable() {
        final String name = TestUtils.getRandomStr(7);

        ProjectPage projectPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(name)
                .setProjectTypeMultiBranchPipeline()
                .clickOkAndGoToConfig()
                .clickCheckboxDisable()
                .saveConfigAndGoToProject();

        Assert.assertTrue(projectPage.getIconFolderDisabled().isDisplayed());
        Assert.assertEquals(projectPage.getMessageDisabled().getText(),
                "This Multibranch Pipeline is currently disabled \nEnable");

        HomePage homePage = projectPage.clickDashboardButton();

        Assert.assertEquals(homePage.getProjectIconByName(name).getAttribute("class"),
                        "icon-folder-disabled icon-lg");
    }
}