import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MultiConfigurationProjectDisableAndDeleteTest extends BaseTest {

    @Test
    public void testMultiConfigurationProjectMadeDisabled() {
        String projectName = "My Multi-configuration project";
        String projectDescription = "Creating test project";
        String expectedResult = "This project is currently disabled";

        getDriver()
                .findElement(
                        By.xpath("//a[@href='/view/all/newJob']/span[@class='task-link-text']"))
                .click();

        getDriver().findElement(By.xpath("//div[@class='add-item-name']/input"))
                .sendKeys(projectName);
        getDriver().findElement(
                By.xpath("//li[@class='hudson_matrix_MatrixProject']/label")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//div[@class='setting-main']/textarea[@name='description']"))
                .sendKeys(projectDescription);
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span[@name='Submit']"))
                .click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), projectDescription);

        getDriver().findElement(By.xpath("//button[text()='Disable Project']")).click();

        String actualResult = getDriver()
                .findElement(By.xpath("//div[@class='warning']")).getText();

        Assert.assertTrue(actualResult.contains(expectedResult));
    }
}

