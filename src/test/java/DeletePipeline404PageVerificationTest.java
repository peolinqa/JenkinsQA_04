import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;


public class DeletePipeline404PageVerificationTest extends BaseTest {
    private void createPipelineProject() {
        Date date = new Date();

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("Ruslan Gudenko Pipeline Project+" + date.getTime());
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//a[text() = 'Dashboard']")).click();
    }

    @Ignore
    @Test(description = "TC_021.002")
    public void test404PageAfterDeletedPipeline() {
        createPipelineProject();

        List<WebElement> pipelineProjects = getDriver().findElements(By.xpath(
                "//a[contains(@href, 'job/Ruslan%20Gudenko%20Pipeline%20Project+')]"));
        String hrefOfLastProject = pipelineProjects.get(pipelineProjects.size() - 2).getAttribute("href");

        getDriver().navigate().to(hrefOfLastProject);
        getDriver().findElement(By.xpath("//a[contains(@data-message, 'Delete the Pipeline ')]")).click();
        getDriver().switchTo().alert().accept();

        getDriver().navigate().back();
        String titleOf404Page = getDriver().getTitle();

        Assert.assertTrue(titleOf404Page.contains("Error 404 Not Found"));
    }

    @Ignore
    @AfterMethod
    public void afterTest() {
        getDriver().get("http://localhost:8080");
    }

}
