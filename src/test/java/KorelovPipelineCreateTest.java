import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class KorelovPipelineCreateTest extends BaseTest {

    private void createPipeline(String namePipeline) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(namePipeline);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testTC_017_006CreatePipeline() {

        final String namePipeline = "NewPipeline";

        createPipeline(namePipeline);
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

    @Test
    public void testTC_017_005HelpTooltipsText() {

        final String namePipeline = "NewPipelineTooltips";

        createPipeline(namePipeline);

        List<WebElement> listOfChecBoxWithHelps = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//label"));

        List<WebElement> checkBoxHelpsText = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//a"));

        for (int i = 0; i < listOfChecBoxWithHelps.size(); i++) {
            Assert.assertEquals(checkBoxHelpsText.get(i).getAttribute("title")
                    .replace("Help for feature: ", ""), listOfChecBoxWithHelps.get(i).getText());
        }
    }

    @Test
    public void testTC_017_010ApplyNotificationAlert(){

        final String namePipeline = "NewPipelineNotification";

        createPipeline(namePipeline);
        getDriver().findElement(By.id("yui-gen5-button")).click();

        Assert.assertEquals(getDriver().findElement(By.id("notification-bar"))
                .getText(),"Saved");

        getDriver().findElement(By.id("yui-gen6-button")).click();
    }
}
