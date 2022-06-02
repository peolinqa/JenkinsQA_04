import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;


public class KorelovPipelineAddTest extends BaseTest {
    @Ignore
    @Test
    public void testAddPipeline() {

        final String namePipeline = "Pipeline";

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(namePipeline);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1"))
                .getText().contains(namePipeline));

        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//td/a[contains(@href," + namePipeline + ")]")).getText(), namePipeline);
    }
}
