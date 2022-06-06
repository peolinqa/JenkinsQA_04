import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Date;

public class AbrarovPipelineTest extends BaseTest {

    private void homePageClick(){
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();
    }

    private void saveButtonClick(){
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    @BeforeMethod
    public void setUp() {

        Date date = new Date();

        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys("testJob-" + date.getTime());
        getDriver().findElement(By.xpath("//li[contains(@class,'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test(description = "TC_017.011")
    public void testCreatePipeline(){

        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(getDriver().findElement(By.xpath("//input[@name='hasCustomQuietPeriod']")))
                .click()
                .moveToElement(getDriver().findElement(By.name("quiet_period")))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("-1")
                .sendKeys(Keys.TAB)
                .build()
                .perform();

        String actualResult = getDriver().findElement(By.xpath(
                "//input[@name='quiet_period']/../following-sibling::div[contains(@class, 'validation-error-area')]/div/div[@class='error']")).getText();

        Assert.assertEquals(actualResult, "This value should be larger than 0");

        saveButtonClick();
    }

    @Test(description = "TC_021.003")
    public void testDeleteAllPipelinesFromScriptConsole() {

        final String allJobsDeleteScript = "for(j in jenkins.model.Jenkins.theInstance.getAllItems()) {j.delete()}";

        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
        homePageClick();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(getDriver().findElement(By.xpath("//div[@class='CodeMirror-scroll cm-s-default']")))
                .click()
                .sendKeys(allJobsDeleteScript)
                .moveToElement(getDriver().findElement(By.xpath("//button[@type='submit']")))
                .click()
                .build()
                .perform();

        homePageClick();
        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualResult, "Welcome to Jenkins!");
    }
}