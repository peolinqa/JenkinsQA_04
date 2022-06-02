import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Date;

public class GudenkoPipelineCreatePipelineTest extends BaseTest {

    private JavascriptExecutor javascriptExecutor;
    private Date date;

    @BeforeMethod
    public void beforeTest() {
        javascriptExecutor = (JavascriptExecutor) getDriver();
        date = new Date();
    }

    @Test(description = "This test method is testing of 'Jenkins Credentials Provider: Jenkins' window opening ")
    public void testJenkinsCredentialsProviderWindow() {

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
    }

    @AfterMethod
    public void afterTest() {
        WebElement cancelButton = getDriver().findElement(By.xpath("//button[@id='credentials-add-abort-button']"));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", cancelButton);
        cancelButton.click();
    }
}
