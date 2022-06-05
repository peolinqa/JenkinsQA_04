import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GroupBugHuntersEditBuildInformationTest extends BaseTest {

    public void createFreestyleProject() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("NewFreestyleProject");
        getDriver().findElement(By.xpath("//li[contains(@class,'hudson_model_FreeStyleProject')]")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();
        getDriver().findElement(By.xpath("//a[@title='Build Now']")).click();
        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();
        Thread.sleep(2000);
        getDriver().navigate().refresh();
        getDriver().findElement(By.xpath("//*[local-name() = 'svg' and @tooltip='Success']"));
    }

    @Test
    public void testVerifyChangeOnBuildStatusPage_TC_032_001() throws InterruptedException {
        createFreestyleProject();
        getDriver().findElement(By.xpath("//a[@href='job/NewFreestyleProject/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//a[@title='Edit Build Information']")).click();
        getDriver().findElement(By.xpath("//input[@name='displayName']")).sendKeys("New build 123");
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Build 123 description test");
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();
        String buildName = getDriver().findElement(By.xpath("//span[@class='jenkins-icon-adjacent']")).getText();
        String buildDescription = getDriver().findElement(By.id("description")).getText();
        Assert.assertEquals(getDriver().getCurrentUrl(),"http://localhost:8080/job/NewFreestyleProject/lastBuild/");
        Assert.assertTrue(buildName.contains("Build New build 123"));
        Assert.assertTrue(buildDescription.contains("Build 123 description test"));
    }
}
