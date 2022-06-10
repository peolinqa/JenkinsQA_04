import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateMultiConfigurationProject_US_041_Test extends BaseTest {

    private final String PROJECT_NAME = "Mcproject";

    private void createMCProject(String name) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    private void deleteMCProject() {
        getDriver().findElement(By.linkText("Delete Multi-configuration project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void TC_041_006_CreateMultiConfigurationProjectTest() {
        String expectedResult = "Mcproject";

        createMCProject(PROJECT_NAME);

        String actualResult = getDriver().findElement(By.xpath("//div/ul/li/a[@href='/job/" + PROJECT_NAME +"/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteMCProject();
    }

    @Test
    public void TC_042_002_AddDescriptionTest(){
        String expectedResult = "test";
        createMCProject(PROJECT_NAME);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div/textarea[@name=\"description\"]")).sendKeys("test");
        getDriver().findElement(By.id("yui-gen2-button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteMCProject();
    }
}
