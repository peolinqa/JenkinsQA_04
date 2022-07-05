import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class CreatePipelineAndDeleteTest extends BaseTest {

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

    private void buttonBackToDashboard() {
        getDriver().findElement
                (By.xpath("//a[@title='Back to Dashboard']")).click();
    }

    private void checkProjectAfterDelete(String projectName) {

        List<WebElement> actual = getDriver().findElements(By.xpath("//h1"));
        if (actual.size() == 0) {
            List<WebElement> actualDashboardProject = getActualDashboardProject();
            for (WebElement webElement : actualDashboardProject) {
                if (webElement.getText().contains(projectName)) {
                    Assert.fail();
                    break;
                } else {
                    Assert.assertTrue(true);
                }
            }
        } else {
            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText()
                    , "Welcome to Jenkins!");
        }
    }

    @Ignore
    @Test
    public void testTC_017_006CreatePipeline() {

        final String namePipeline = "NewPipeline";

        createPipeline(namePipeline);
        saveButtonPipelineClick();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1"))
                .getText().contains(namePipeline));

        buttonBackToDashboard();

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
                            .replace("Help for feature: ", "")
                    , listOfCheckBoxWithHelps.get(i).getText());
        }
    }

    @Ignore
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

        getDriver().findElement
                (By.xpath("//a[@class='task-link  confirmation-link']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }

    @Ignore
    @Test
    public void testTC_021_004DeletePipelineFromDashboard() {

        final String namePipeline = "DeletePipelineFromDashboard";

        createPipeline(namePipeline);
        saveButtonPipelineClick();
        buttonBackToDashboard();

        new Actions(getDriver()).moveToElement(getDriver().findElement
                (By.xpath("//a[text()='DeletePipelineFromDashboard']"))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();

        getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();

        checkProjectAfterDelete(namePipeline);
    }
}
