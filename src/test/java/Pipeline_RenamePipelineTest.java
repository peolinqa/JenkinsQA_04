import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Pipeline_RenamePipelineTest extends BaseTest {
    private static final By NEW_ITEM = By.className("task-link-text");
    private static final By INPUT_LINE = By.id("name");
    private static final String PIPELINE_NAME = "Project_General";
    private static final By RENAME_INPUT_LINE = By.xpath("//input[@checkdependson='newName']");

    private void createPipeline() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();
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

        getDriver().findElement(
                By.xpath("//a[@href='/job/".concat(PIPELINE_NAME).concat("/confirm-rename']"))).click();
        getDriver().findElement(RENAME_INPUT_LINE).sendKeys("#3");
        getDriver().findElement(By.id("yui-gen1-button")).click();
        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        deletePipeline();
    }
}