import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;
import java.util.List;

public class CreatePipelinePipelineProjectTest extends BaseTest {
    private final String PIPELINE_PROJECT_NAME = "Ruslan Gudenko Pipeline Project+";

    private final By PIPELINE_ITEM_CONFIGURATION = By.xpath(
            "//div[@class='tab config-section-activator config_pipeline']");

    private Date date;
    private JavascriptExecutor javascriptExecutor;

    private void createPipelineProject() {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(
                PIPELINE_PROJECT_NAME + date.getTime());
        getDriver().findElement(By.xpath(
                "//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    private void closeJenkinsCredProvWindowMethod() {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();",
                getDriver().findElement(By.xpath("//button[@id='credentials-add-abort-button']")));
        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();
    }

    @BeforeMethod
    public void beforeTest() {
        date = new Date();
        javascriptExecutor = (JavascriptExecutor) getDriver();
    }

    @Test(description = "TC_017_007")
    public void testJenkinsCredentialsProviderWindow() {
        createPipelineProject();

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        Select pipelineScriptDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item config_pipeline active']//select")));
        pipelineScriptDropDownList.selectByIndex(1);
        Select scmDropDownList = new Select(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-form-item has-help']//select")));
        scmDropDownList.selectByIndex(1);

        getDriver().findElement(By.xpath("//button[@id='yui-gen15-button']")).click();
        getDriver().findElement(By.xpath("//li[@id='yui-gen17']")).click();

        WebElement titleOfJenkinsCredentialsProviderWindow = getDriver().findElement(By.xpath("//h2"));

        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow.getText(), "Jenkins Credentials Provider: Jenkins");

        closeJenkinsCredProvWindowMethod();
    }

    @Test(description = "TC_017.008")
    public void testPipelineSyntaxPageOpening() {
        createPipelineProject();

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String pipelineSyntaxLink = getDriver().findElement(By.xpath("//a[@href='pipeline-syntax']"))
                .getAttribute("href");
        getDriver().get(pipelineSyntaxLink);

        List<WebElement> breadcrumbsOfLinksMenu = getDriver().findElements(By.xpath("//li[@class='item']/a"));
        String breadcrumbsPipelineSyntaxPageItem = breadcrumbsOfLinksMenu.get(2).getAttribute("href");

        Assert.assertTrue(breadcrumbsPipelineSyntaxPageItem.contains("pipeline-syntax"));
    }

    @Test(description = "TC_017.012")
    public void testPipelineGroovyPageOpening() {
        createPipelineProject();

        getDriver().findElement(PIPELINE_ITEM_CONFIGURATION).click();

        String useGroovySandboxCheckbox = getDriver().findElement(By.xpath(
                "//input[@name='_.sandbox']")).getAttribute("checked");

        Assert.assertEquals(useGroovySandboxCheckbox, "true");
    }

    @Test(description = "TC_017.014")
    public void testTitleConfigPageContainsProjectTitle() {
        createPipelineProject();

        String titleOfConfigurationPipelinePage = getDriver().getTitle();

        Assert.assertTrue(titleOfConfigurationPipelinePage.contains(PIPELINE_PROJECT_NAME + date.getTime()));
    }

    @AfterMethod
    public void afterTest() {
        getDriver().navigate().back();
    }
}

