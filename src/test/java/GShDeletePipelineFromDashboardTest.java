import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class GShDeletePipelineFromDashboardTest extends BaseTest {
    private final String PIPELINE_NAME = "NewPipelineTooltips";
    private final String DASH_BOARD = "//li[@class='item']/a";
    private final String NEW_ITEM = "//span[@class='task-link-wrapper ']/a[@href='/view/all/newJob']";

    private void createNewPipeline() {
        getDriver()
                .findElement(By.xpath(NEW_ITEM))
                .click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private boolean checkNameforNewPipeline(String name) {

        getDriver().findElement(By.xpath(DASH_BOARD)).click();

        List<WebElement> getElementsDashboard = getDriver()
                .findElements(By.xpath("//table[@id='projectstatus']/tbody"));

        if (getElementsDashboard != null && getElementsDashboard.size() != 0) {
            for (WebElement element : getElementsDashboard) {

                if (element.getText().contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deleteNewPipeline(String name) {
        if (name != null && !name.equals("")) {
            getDriver().findElement(By.xpath(DASH_BOARD)).click();
            getDriver().findElement(By.xpath("//td/a[@href='job/".concat(name).concat("/']"))).click();
            getDriver()
                    .findElement(By.xpath("//span[@class='task-link-wrapper ']/a[@data-url='/job/"
                            + name
                            + "/doDelete']"))
                    .click();
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        }

    }

    @Test
    public void test_TC021_013_DeleteNewPipeline() {

        if (checkNameforNewPipeline(PIPELINE_NAME)) {
            deleteNewPipeline(PIPELINE_NAME);
        }
        createNewPipeline();
        deleteNewPipeline(PIPELINE_NAME);

        Assert.assertFalse(checkNameforNewPipeline(PIPELINE_NAME));
    }

    @Test
    public void checkInvalidNameTest(){
        if (!checkNameforNewPipeline(PIPELINE_NAME)) {
            createNewPipeline();
        }
        getDriver().findElement(By.xpath(DASH_BOARD)).click();

        getDriver().findElement(By.xpath(NEW_ITEM)).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.id("name")).submit();
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

       String expectedResult = "» A job already exists with the name ‘" + PIPELINE_NAME + "’";

       String actualResult = getDriver()
               .findElement(By.xpath("//div[@id='itemname-invalid']"))
               .getText();

       deleteNewPipeline(PIPELINE_NAME);
       Assert.assertEquals(actualResult, expectedResult);

    }
}
