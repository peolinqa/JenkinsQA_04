import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class RuslanGPipelineCreatePipelineTest extends BaseTest {
    private void createPipelineProjectPipelineTab() {
        Date date = new Date();

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("Ruslan Gudenko Pipeline Project+" + date.getTime());
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//div[@class='tab config-section-activator config_pipeline']"))
                .click();
    }

    @Test(description = "TC_017.008")
    public void testPipelineSyntaxPageOpening() {
        createPipelineProjectPipelineTab();

        String pipelineSyntaxLink = getDriver().findElement(By.xpath("//a[@href='pipeline-syntax']"))
                .getAttribute("href");
        getDriver().get(pipelineSyntaxLink);

        List<WebElement> breadcrumbsOfLinksMenu = getDriver().findElements(By.xpath("//li[@class='item']/a"));
        String breadcrumbsPipelineSyntaxPageItem = breadcrumbsOfLinksMenu.get(2).getAttribute("href");

        Assert.assertTrue(breadcrumbsPipelineSyntaxPageItem.contains("pipeline-syntax"));
    }

    @Test(description = "TC_017.012")
    public void testPipelineGroovyPageOpening() {
        createPipelineProjectPipelineTab();

        String useGroovySandboxCheckbox = getDriver().findElement(By.xpath(
                "//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertTrue(useGroovySandboxCheckbox.contains("true"));
    }
}
