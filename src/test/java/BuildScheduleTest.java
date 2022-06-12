import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.concurrent.TimeUnit;
public class BuildScheduleTest extends BaseTest {

    private final String PIPELINE_NAME = "AT024001";
    private final String NEW_ITEM_CSS="a.task-link";
    private final String NEWITEM_PAGE_PIPELINE ="img[src$='pipelinejob.svg']";
    private final String BUILD_SCHEDULE_BUTTON="[href*=\"job/".concat(PIPELINE_NAME).concat("/build\"]");
    private final String DETAIL_PAGE_BUILD_HISTORY_RECORDS="[class=\"build-row-cell\"]";
    private final String DETAIL_PAGE_DELETE_PIPELINE="[data-message~=\"Delete\"]";

    protected void goToDashboard(){
        getDriver().get("http://localhost:8080/");
    }

    protected void createPipeline(){
        getDriver().findElement(By.cssSelector(NEW_ITEM_CSS)).click();
        getDriver().findElement(By.cssSelector("input#name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.cssSelector(NEWITEM_PAGE_PIPELINE)).click();
        getDriver().findElement(By.cssSelector("button#ok-button")).click();
    }

    protected void deletePipeline(){
        getDriver().get("http://localhost:8080/job/".concat(PIPELINE_NAME).concat("/"));
        getDriver().findElement(By.cssSelector(DETAIL_PAGE_DELETE_PIPELINE)).click();
        getDriver().switchTo().alert().accept();
    }

    @Test (invocationCount = 20)
    public void BuildSchedulePositiveTest() {
        createPipeline();
        goToDashboard();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().findElement(By.cssSelector(BUILD_SCHEDULE_BUTTON)).click();
        getDriver().findElement(By.partialLinkText(PIPELINE_NAME)).click();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().findElement(By.cssSelector(DETAIL_PAGE_BUILD_HISTORY_RECORDS)).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(PIPELINE_NAME));
        deletePipeline();
    }

    @Test (invocationCount = 20)
    public void BuildScheduleNegativeTest() {
        createPipeline();
        goToDashboard();
        getDriver().findElement(By.partialLinkText(PIPELINE_NAME)).click();
        System.out.println("text: " + getDriver().findElement(By.cssSelector("[id='no-builds']")).getText());
        Assert.assertEquals(getDriver().findElement(By.cssSelector("[id='no-builds']")).getText(), "No builds");
        deletePipeline();
    }
}
