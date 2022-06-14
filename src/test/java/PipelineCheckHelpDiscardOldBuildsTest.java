import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class PipelineCheckHelpDiscardOldBuildsTest extends BaseTest {

    String pipelineName = "My first pipeline";

    protected void createPipelineFolder() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys("My first pipeline");
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }
    protected void deletePipelineFolder(){
        WebElement hiddenButton = getDriver().findElement(By.id("menuSelector"));
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//ul[@id = 'breadcrumbs']//a[text() = '" + pipelineName + "']"))).perform();
        hiddenButton.click();
        getDriver().findElement(By.xpath("//span[text() = 'Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();

    }

    @Test
    public void testCheckHelpIconForDiscardOldBuilds() throws InterruptedException {
        createPipelineFolder();
        getDriver().findElement(By.xpath("//a[@title = 'Configure']")).click();
        WebElement helpButton = getDriver().findElement(By.xpath("//a[@title = 'Help for feature: Discard old builds']"));
        Assert.assertTrue(helpButton.isDisplayed());
        String tooltipTitle = "Help for feature: Discard old builds";

        Actions action = new Actions(getDriver());
        action.moveToElement(helpButton).perform();
        Assert.assertTrue(helpButton.getAttribute("title").isEmpty());
        Assert.assertEquals(helpButton.getAttribute("title"), "");

        SoftAssert asserts = new SoftAssert();
        helpButton.click();
        asserts.assertTrue(getDriver().findElement(By.xpath("//div[@class = 'help-area tr config-table-top-row']/div/div"))
                .getText().contains("This determines when, if ever, build"));
        asserts.assertTrue(getDriver().findElement(By.xpath("//div[@class = 'help-area tr config-table-top-row']/div/div")).isDisplayed());
        asserts.assertAll();
        helpButton.click();
        Assert.assertFalse(getDriver().findElement(By.xpath("//div[@class = 'help-area tr config-table-top-row']/div/div")).isDisplayed());
        deletePipelineFolder();
        Thread.sleep(2000);

    }
}
