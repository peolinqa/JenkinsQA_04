import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;


public class PipelineWithParametersTest extends BaseTest {

    protected void createPipelineFolder(){
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test pipeline with parameters");
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6")).click();
    }


    @Test
    public void testPipelineWithParameters() {
        createPipelineFolder();
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        getDriver().findElement(By.xpath("//a[text()='Test pipeline with parameters']")).click();
        getDriver().findElement(By.xpath("//span[text()='Configure']")).click();
        getDriver().findElement(By.id("cb8")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
        getDriver().findElement(By.id("yui-gen9")).click();


        getDriver().findElement(By.name("parameter.name")).sendKeys("name");
        getDriver().findElement(By.name("parameter.choices")).sendKeys("choices");
        getDriver().findElement(By.name("parameter.description")).sendKeys("description");
        getDriver().findElement(By.id("yui-gen6-button")).click();
        getDriver().findElement(By.xpath("//span[@class='task-link-text'][text()='Build with Parameters']")).click();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals
                (getDriver().findElement(By.xpath("//div[@class='jenkins-form-label help-sibling'][text()='name']")).
                        getText(), "name");
        asserts.assertAll();
    }
}
