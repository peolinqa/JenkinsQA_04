import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class RuslanGPipelineCreatePipelineTest extends BaseTest {
    private JavascriptExecutor javascriptExecutor;
    private Date date;

    @BeforeMethod
    public void beforeTest() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        date = new Date();
    }

    @Test(description = "TC_017.008")
    public void testPipelineSyntaxPageOpening() {

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("Ruslan Gudenko Pipeline Project" + date.getTime());
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"))
                .click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", okButton);
        okButton.click();

        getDriver().findElement(By.xpath("//div[@class='tab config-section-activator config_pipeline']"))
                .click();

        String pipelineSyntaxLink = getDriver().findElement(By.xpath("//a[@href='pipeline-syntax']"))
                .getAttribute("href");
        getDriver().get(pipelineSyntaxLink);

        List<WebElement> breadcrumbsOfLinksMenu = getDriver().findElements(By.xpath("//li[@class='item']/a"));
        String breadcrumbsPipelineSyntaxPageItem = breadcrumbsOfLinksMenu.get(2).getAttribute("href");

        Assert.assertTrue(breadcrumbsPipelineSyntaxPageItem.contains("pipeline-syntax"));
    }
}
