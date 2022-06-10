import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProject_US_001_Test extends BaseTest {

    private void createProject() throws InterruptedException {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProjectTestKT");
        Thread.sleep(1000);

        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//html/body/div[5]/div/div/div/div/form/div[1]/div[12]/div/div[2]/div[2]/span[1]/span/button")
        ).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testIconsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-head-icon")).isDisplayed());
    }

    @Test
    public void testIconsJenkinsStart(){
        Assert.assertTrue(getDriver().findElement( By.id("jenkins-name-icon")).isDisplayed());
    }

    @Test
    public void TC_001_010_CreateFreestyleProjectTest() throws InterruptedException {
        String expectedResult = "FirstProjectTestKT";
        createProject();

        String actualResult = getDriver().findElement(By.xpath("//ul/li/a[@href='/job/FirstProjectTestKT/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }
}

