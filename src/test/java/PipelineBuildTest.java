import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class PipelineBuildTest extends BaseTest {

    private final String PIPELINE_NAME = "TC_019_001";
    private final String PIPELINE_JOB_DELETE = "Delete Pipeline";
    private final String PIPELINE_JOB_BUILD = "Build Now";
    private final String PIPELINE_JOB = "//a[@href = 'job/".concat(PIPELINE_NAME).concat("/']");
    private Actions action;

    protected void goHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    protected void clickMenu(String job) {
        goHome();

        action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement
                (By.xpath(PIPELINE_JOB))).perform();

        action.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']")));
        action.click().build().perform();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,600)");

        getDriver().findElement
                        (By.xpath("//a[@href='#']/span[contains(text(), '" + job + "')]"))
                .click();
    }

    protected void createPipeline() {
        goHome();

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.id("yui-gen6")).click();
    }

    protected void buildPipeline() {
        clickMenu(PIPELINE_JOB_BUILD);
    }

    protected List<WebElement> buildHistory() {
        goHome();
        getDriver().findElement
                (By.xpath(PIPELINE_JOB)).click();

        return getDriver().findElements
                (By.xpath("//a[@class = 'tip model-link inside build-link display-name']"));
    }

    protected List<WebElement> buildPipelinePermalinks() {
        goHome();
        getDriver().findElement
                (By.xpath(PIPELINE_JOB)).click();

        return getDriver().findElements(By.xpath("//li[@class = 'permalink-item']/a[contains(@href, 'last')]"));
    }

    protected void deletePipeline() {
        clickMenu(PIPELINE_JOB_DELETE);
        getDriver().switchTo().alert().accept();
    }

    @Test(priority = 1)
    public void testCreatePipeline_TC_019_001() throws InterruptedException {

        String urlExpected = "http://localhost:8080/job/".concat(PIPELINE_NAME).concat("/");

        createPipeline();
        Thread.sleep(2000);

        Assert.assertEquals(getDriver().getCurrentUrl(), urlExpected);

    }

    @Test(priority = 2, dependsOnMethods = "testCreatePipeline_TC_019_001")
    public void testBuildPipeline_TC_019_001() throws InterruptedException {

        buildPipeline();

        Thread.sleep(10000);
        getDriver().navigate().refresh();

        String statusBuild = getDriver().findElement
                (By.xpath("//tr[@id = 'job_".concat(PIPELINE_NAME).concat("']/td/div/span/span/node()")))
                .getAttribute("tooltip");

        Assert.assertEquals(statusBuild, "Success");
    }

    @Test(priority = 3, dependsOnMethods = "testBuildPipeline_TC_019_001")
    public void testBuildHistoryPipeline_TC_019_001() {

        Boolean BuildHistoryDescOrder = true;
        if (buildHistory().size() != 0) {
            for (int i = 1; i < buildHistory().size(); i++) {
                if (Integer.parseInt(buildHistory().get(i).getText())
                        < Integer.parseInt(buildHistory().get(i - 1).getText())) {
                    BuildHistoryDescOrder = false;
                    break;
                }
            }
        }

        Assert.assertTrue(BuildHistoryDescOrder);
    }

    @Test(priority = 4, dependsOnMethods = "testBuildPipeline_TC_019_001")
    public void testBuildPipelinePermalinks_TC_019_001() {
        String[] buildSuccessfulPermalinksPipeline = new String[] {"Last build", "Last stable build",
                "Last successful build", "Last completed build"};
        String[] buildUnsuccessfulPermalinksPipeline = new String[] {"Last build", "Last stable build",
                "Last successful build", "Last failed build", "Last unsuccessful build", "Last completed build"};

        for (int i = 0; i < buildPipelinePermalinks().size(); i++) {
            Assert.assertTrue((buildPipelinePermalinks().get(i)).getText().contains(buildSuccessfulPermalinksPipeline[i]));
        }
    }

    @Test(priority = 5)
    public void testDeletePipeline_TC_019_001() {
        deletePipeline();
    }

}
