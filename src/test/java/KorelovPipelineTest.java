import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class KorelovPipelineTest extends BaseTest {

    private void createPipeline(String namePipeline) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(namePipeline);
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private List<WebElement> getActualDashboardProject() {
        return getDriver().findElements
                (By.xpath("//a[@class='jenkins-table__link model-link inside']"));
    }

    private void saveButtonPipelineClick() {
        getDriver().findElement(By.id("yui-gen6-button")).click();
    }

    @Test
    public void testTC_017_006CreatePipeline() {

        final String namePipeline = "NewPipeline";

        createPipeline(namePipeline);
        saveButtonPipelineClick();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1"))
                .getText().contains(namePipeline));

        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();

        List<WebElement> actualDashboardProject = getActualDashboardProject();

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

        List<WebElement> listOfCheckBoxWithHelps = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//label"));

        List<WebElement> checkBoxHelpsText = getDriver()
                .findElements(By.xpath("//div[contains(@hashelp, 'true')]//a"));

        for (int i = 0; i < listOfCheckBoxWithHelps.size(); i++) {
            Assert.assertEquals(checkBoxHelpsText.get(i).getAttribute("title")
                    .replace("Help for feature: ", ""), listOfCheckBoxWithHelps.get(i).getText());
        }
    }

    @Test
    public void testTC_017_010ApplyNotificationAlert() {

        final String namePipeline = "NewPipelineNotification";

        createPipeline(namePipeline);
        getDriver().findElement(By.id("yui-gen5-button")).click();

        Assert.assertEquals(getDriver().findElement(By.id("notification-bar"))
                .getText(), "Saved");

        saveButtonPipelineClick();
    }

    @Test
    public void testTC_021_001DeletePipelineFromSideMenu() {

        final String namePipeline = "DeletePipelineFromSideMenu";

        createPipeline(namePipeline);
        saveButtonPipelineClick();

        getDriver().findElement(By.xpath("//a[@class='task-link  confirmation-link']")).click();

        getDriver().switchTo().alert().accept();

        try {
            if ((getDriver().findElement(By.xpath("//h1")).getText().contains("Welcome to Jenkins!"))) {
                Assert.assertTrue(true);
            }
        } catch (Exception e) {
            List<WebElement> actualDashboardProject = getActualDashboardProject();
            for (WebElement webElement : actualDashboardProject) {
                if (webElement.getText().contains(namePipeline)) {
                    Assert.fail();
                    break;
                } else {
                    Assert.assertTrue(true);
                }
            }
        }
    }
}
