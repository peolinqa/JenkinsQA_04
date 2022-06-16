import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;


public class CreateFreestyleProjectEPTest extends BaseTest {
    private final static String DASHBOARD_XPATH = "//a[contains(text(),\'Dashboard\')]";
    private final static String EV_JOB_NAME = "First Job";
    private final static String RENAME_XPATH = "//span[text()='Rename']";

    public void clickNewItem() {getDriver().findElement(By.linkText("New Item")).click();}

    public void clickFreestyleProjectItem() {getDriver().findElement(By.xpath("//*[contains(text(),\"Freestyle project\")]")).click();}

    public void clickOKButton() {getDriver().findElement(By.id("ok-button")).click();}

    private static final By NAME = By.id("name");

    @BeforeMethod
    public void deleteNewJob() {
        DeleteBuildTest.deleteJobsWithPrefix(getDriver(), EV_JOB_NAME);
    }

    @Test
    public void testCreateFreestyleProject() {
        createNewJob(EV_JOB_NAME);
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[text()='First Job']")).click();
        getDriver().findElement(By.xpath(RENAME_XPATH)).click();
        getDriver().findElement(By.xpath("//input[@class='jenkins-input validated  ']")).clear();
        String newJobName = EV_JOB_NAME + " 2";
        getDriver().findElement(By.xpath("//input[@class='jenkins-input validated  ']")).sendKeys(newJobName);
        getDriver().findElement(By.xpath("//button[text()='Rename']")).click();
        String pageFoundText = getDriver().findElement(By.xpath("//h1[text()='Project " + newJobName + "']")).getText();
        Assert.assertEquals(pageFoundText, "Project " + newJobName);

    }

    public void createNewJob(String jobName) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(jobName);
        clickFreestyleProjectItem();
        clickOKButton();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
    }
}
