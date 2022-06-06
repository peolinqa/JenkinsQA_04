import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateMultibranchPipelineWithValidDataTest extends BaseTest {

    @Test
    public void testCreateMultibranchPipelineWithValidData_TC_049_003() {

        getDriver().findElement(By.className("task-link-text")).click();

        getDriver().findElement(By.id("name")).sendKeys("MultiPipeline");
        getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@id='yui-gen8-button']")).click();

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        String newName = getDriver().findElement(By.xpath("//a[@href='job/MultiPipeline/']")).getText();
        Assert.assertEquals(newName, "MultiPipeline");


        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'job/MultiPipeline/')]")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Multibranch Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[text()='Yes']")).click();
    }
}