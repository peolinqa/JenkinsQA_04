import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;


public class KorelovPipelineCreateTest extends BaseTest {
    @Test
    public void testCreatePipeline() {

        final String namePipeline = "NewPipeline";

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(namePipeline);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1"))
                .getText().contains(namePipeline));

        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();

        List<WebElement> actualDashboardProject = getDriver()
                .findElements(By.xpath("//a[@class='jenkins-table__link model-link inside']"));

        for (WebElement webElement : actualDashboardProject) {
            if (webElement.getText().contains(namePipeline)) {
                Assert.assertTrue(true);
                break;
            }
        }
    }
}
