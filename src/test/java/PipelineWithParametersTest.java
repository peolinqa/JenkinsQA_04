import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import java.util.List;

public class PipelineWithParametersTest extends BaseTest {

    private final String VALID_VALUE_FOR_NAME1 = "Test pipeline with parameters";

    protected void createPipelineFolder(){
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME1);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6")).click();
    }

    protected void deletePipelineFolder(){

        List<WebElement> tableOnDashboard =
                getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
        for (WebElement item : tableOnDashboard){
            if (item.getText().equals("Test pipeline with parameters")) {
                getDriver().findElement(By.xpath("//a[text()='Test pipeline with parameters']")).click();
                getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
                getDriver().switchTo().alert().accept();
                break;
            }
        }
    }

    @Test
    public void testPipelineWithParameters() {
        deletePipelineFolder();
        getDriver().navigate().refresh();
        createPipelineFolder();
        getDriver().findElement(By.xpath("//a[text()='Test pipeline with parameters']")).click();
        getDriver().findElement(By.xpath("//span[text()='Configure']")).click();
        getDriver().findElement(By.id("cb8")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
        getDriver().findElement(By.id("yui-gen9")).click();
        getDriver().findElement(By.name("parameter.name")).sendKeys("Kris");
        getDriver().findElement(By.name("parameter.choices")).sendKeys("Drobava");
        getDriver().findElement(By.name("parameter.description")).sendKeys("world");
        getDriver().findElement(By.id("yui-gen6-button")).click();
        getDriver().findElement(By.xpath("//span[@class='task-link-text'][text()='Build with Parameters']")).click();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals
                (getDriver().findElement(By.xpath("//div[@class ='jenkins-form-label help-sibling']")).
                        getText(), "Kris");
        asserts.assertEquals
                (getDriver().findElement(By.name("parameter")).
                        getText(), "Drobava");
        asserts.assertEquals
                (getDriver().findElement(By.className("setting-description")).
                        getText(), "world");
        asserts.assertAll();

        deletePipelineFolder();
    }
}
