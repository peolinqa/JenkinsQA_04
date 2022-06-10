import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class OkunCreatePipeline extends BaseTest {

    private void deleteProjectPipeline() {

        getDriver().findElement(By.xpath("//div[@id='tasks']/div/span[@class='task-link-wrapper ']" +
                        "/a[@data-message='Delete the Pipeline ‘Bedryks’?']"))
                .click();
        getDriver().switchTo().alert().accept();

    }

    @Test
    public void testPipelineCreate() {
        final String pipelineProjectTitle = "Bedryks";
        final String descriptionOfProject = "Pipeline Bedryks";


        getDriver().findElement(By.xpath("//span[@class='task-link-text']"))
                .click();
        getDriver().findElement(By.id("name"))
                .sendKeys(pipelineProjectTitle);
        getDriver().findElement(By.xpath("//div[contains(text(),'Orchestrates long-running')]"))
                .click();
        getDriver().findElement(By.id("ok-button"))
                .click();
        getDriver().findElement(By.xpath("//textarea"))
                .sendKeys(descriptionOfProject);
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']"))
                .click();


        String actualResult = getDriver().findElement(By.xpath("//h1"))
                .getText();

        deleteProjectPipeline();

        Assert.assertEquals(actualResult, descriptionOfProject);


    }

}
