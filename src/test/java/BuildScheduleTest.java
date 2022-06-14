import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.concurrent.TimeUnit;
public class BuildScheduleTest extends BaseTest {

    private final String PIPELINE_NAME = "AT024001";
    private final String NEW_ITEM_CLASS ="task-link-text";
    private final String NEWITEM_PAGE_PIPELINE_CLASS ="org_jenkinsci_plugins_workflow_job_WorkflowJob";
    private final String BUILD_SCHEDULE_BUTTON="Schedule a Build for ".concat(PIPELINE_NAME);
    private final String DETAIL_PAGE_BUILD_HISTORY_RECORDS_CLASS ="build-row-cell";


    protected void goToDashboard(){
        getDriver().get("http://localhost:8080/");
    }

    protected void createPipeline(){
        getDriver().findElement(By.className(NEW_ITEM_CLASS)).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.className(NEWITEM_PAGE_PIPELINE_CLASS)).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    protected void deletePipeline(){
        getDriver().get("http://localhost:8080/job/".concat(PIPELINE_NAME).concat("/"));
        getDriver().findElement(By.linkText("Delete Pipeline")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test(description = "TC_024.001 | Pipeline>Schedule build for Pipeline (Positive)")
    public void BuildSchedulePositiveTest() {
        createPipeline();
        goToDashboard();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().findElement(By.linkText(BUILD_SCHEDULE_BUTTON)).click();
        getDriver().findElement(By.partialLinkText(PIPELINE_NAME)).click();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().findElement(By.className(DETAIL_PAGE_BUILD_HISTORY_RECORDS_CLASS)).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(PIPELINE_NAME));
        deletePipeline();
    }

    @Test (description = "TC_024.002 | Pipeline>Schedule build for Pipeline (Negative)")
    public void BuildScheduleNegativeTest() {
        createPipeline();
        goToDashboard();
        getDriver().findElement(By.partialLinkText(PIPELINE_NAME)).click();
        System.out.println("text: " + getDriver().findElement(By.id("no-builds")).getText());
        Assert.assertEquals(getDriver().findElement(By.id("no-builds")).getText(), "No builds");
        deletePipeline();
    }
}
