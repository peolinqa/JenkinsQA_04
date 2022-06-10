import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Pipeline_RenamePipelineTest extends BaseTest {
    private static final By NEW_ITEM = By.className("task-link-text");
    private static final By INPUT_LINE = By.id("name");
    private static final String PIPELINE_NAME = "Project_General";
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.id("yui-gen6-button");
    private static final By RENAME_MENU = By.xpath("//a[@href='/job/".concat(PIPELINE_NAME).concat("/confirm-rename']"));
    private static final By RENAME_BUTTON = By.id("yui-gen1-button");
    private static final By RENAME_INPUT_LINE = By.xpath("//input[@checkdependson='newName']");
    private static final By WARNING_MESSAGE = By.className("error");
    private static final By ERROR_MESSAGE = By.xpath("//div[@id='main-panel']/h1");

    private void createPipeline() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void deletePipeline() {
        getDriver().findElement(By.xpath("//a[@href='/job/".concat(PIPELINE_NAME).concat("/']"))).click();
        getDriver().findElement(By.xpath("//a[@data-url='/job/".concat(PIPELINE_NAME).concat("/doDelete']"))).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testRenamePipelineAddNumberAndSymbolToPipelineName() {
        final String expectedResult = "Error";

        createPipeline();

        getDriver().findElement(RENAME_MENU).click();
        getDriver().findElement(RENAME_INPUT_LINE).sendKeys("#3");
        getDriver().findElement(RENAME_BUTTON).click();
        String actualResult = getDriver().findElement(ERROR_MESSAGE).getText();

        Assert.assertEquals(actualResult, expectedResult);

        deletePipeline();
    }

    @Test
    public void testRenamePipelineTheSameNameWithAllCapitalLetters() {

        createPipeline();

        getDriver().findElement(RENAME_MENU).click();
        getDriver().findElement(By.xpath("//input[@value='Project_General']")).clear();
        getDriver().findElement(RENAME_INPUT_LINE).sendKeys("PROJECT_GENERAL");
        getDriver().findElement(By.id("main-panel")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE));
        String actualWarning = getDriver().findElement(WARNING_MESSAGE).getText();
        getDriver().findElement(RENAME_BUTTON).click();
        String actualError = getDriver().findElement(ERROR_MESSAGE).getText();

        Assert.assertEquals(actualWarning,"The name “PROJECT_GENERAL” is already in use.");
        Assert.assertEquals(actualError, "Error");

        deletePipeline();
    }
}
