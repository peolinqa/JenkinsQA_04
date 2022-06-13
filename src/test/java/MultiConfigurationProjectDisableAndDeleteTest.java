import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MultiConfigurationProjectDisableAndDeleteTest extends BaseTest {

    private static final String PROJ_NAME = "My Multi-configuration project";
    private static final String EXPECTED_RESULT = "This project is currently disabled";
    private static final String PROJ_DESCRIPT = "Creating test project";

    public void createMultiConfigurationProject() {

        getDriver()
                .findElement(
                        By.xpath("//a[@href='/view/all/newJob']/span[@class='task-link-text']"))
                .click();

        getDriver().findElement(By.xpath("//div[@class='add-item-name']/input"))
                .sendKeys(PROJ_NAME);
        getDriver().findElement(
                By.xpath("//li[@class='hudson_matrix_MatrixProject']/label")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//div[@class='setting-main']/textarea[@name='description']"))
                .sendKeys(PROJ_DESCRIPT);
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span[@name='Submit']"))
                .click();
    }

    @Test
    public void testMultiConfigurationProjectMadeDisabled() {
        createMultiConfigurationProject();
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), PROJ_DESCRIPT);

        getDriver().findElement(By.xpath("//button[text()='Disable Project']")).click();

        String actualResult = getDriver()
                .findElement(By.xpath("//div[@class='warning']")).getText();

        Assert.assertTrue(actualResult.contains(EXPECTED_RESULT));
    }

    @Test
    public void testDeleteMultiConfigurationProject() {
        createMultiConfigurationProject();

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        if(getDriver().findElement(By.id("main-panel")).getText().contains(PROJ_NAME)) {
            getDriver().findElement(By.xpath("//a[@href='job/My%20Multi-configuration%20project/']")).click();
            getDriver().findElement(
                            By.xpath("//span[text()='Delete Multi-configuration project']")
                    )
                    .click();
            getDriver().switchTo().alert().accept();
        }

        Assert.assertFalse(getDriver().findElement(By.id("main-panel")).getText().contains(PROJ_NAME));
    }
}
