import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;


public class CreatePPipelineDeleteAfterItTest extends BaseTest {

    private static final String JOB_NAME = "pip";
    private static final String pipIconCreatedPipeline = "//a[@href='job/" + JOB_NAME + "/']";

    WebElement pipIconCreatedPipelineElement;

    public void createNewItem() {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();

    }

    public void clickOnBackToDashBoard() {
        WebElement backToDashboardButton = getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']"));
        backToDashboardButton.click();
    }

    public void testDeletePipeline() {

        WebElement pipelineIconToOpen = getDriver().findElement(By.xpath("//tr[@id='job_" + JOB_NAME + "']//td//a[contains(text(),'" + JOB_NAME + "')]"));
        pipelineIconToOpen.click();
        WebElement questionDeletePipeline = getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']"));
        questionDeletePipeline.click();
        getDriver().switchTo().alert().accept();

    }


    @Test(description = "US_017")
    public void testCreatePipeline() throws InterruptedException {
        createNewItem();
        getDriver().findElement(By.id("name")).sendKeys(JOB_NAME);
        WebElement pipelineIcon = getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"));
        pipelineIcon.click();
        getDriver().findElement(By.id("ok-button")).click();
        Thread.sleep(3000);
        WebElement saveButtonForNewItem = getDriver().findElement(By.xpath("//button[@type='submit']"));
        getWait5().until(ExpectedConditions.elementToBeClickable(saveButtonForNewItem));
        saveButtonForNewItem.click();
        clickOnBackToDashBoard();
        pipIconCreatedPipelineElement = getDriver().findElement(By.xpath(pipIconCreatedPipeline));
        Assert.assertTrue(pipIconCreatedPipelineElement.isDisplayed());

        testDeletePipeline();
    }
}
