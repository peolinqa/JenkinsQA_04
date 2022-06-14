import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class PipelineCheckHelpIconForDiscardOldBuildsTest extends BaseTest {

    String nameOfPipeline = "Create pipeline";

    protected void createPipeLineFolder() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(nameOfPipeline);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    protected void deletePipeLineFolder(){
        WebElement hiddenDropDownButton = getDriver().findElement(By.id("menuSelector"));
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//ul[@id = 'breadcrumbs']//a[text() = '" + nameOfPipeline + "' ]"))).perform();
        hiddenDropDownButton.click();
        getDriver().findElement(By.xpath("//span[text() = 'Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testCheckHelpIconForDiscardOldBuilds(){
        createPipeLineFolder();
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

        deletePipeLineFolder();
    }
}
