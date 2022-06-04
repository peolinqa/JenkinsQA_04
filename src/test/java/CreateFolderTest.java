import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFolderTest extends BaseTest {

    /** TC_009.001 Create Folder
     */
    @Test
    public void testCreateFolderPositive() {

        getDriver().findElement(By.linkText("New Item")).click();

        getDriver().findElement(By.id("name")).sendKeys("TestFolder_1");
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("_.description")).sendKeys("Description for the new folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']")).click();

        boolean isFound;
        try {
            getDriver().findElement(By.id("job_TestFolder_1"));
            isFound = true;
        } catch (NoSuchElementException e) {
            isFound = false;
        }

        Assert.assertTrue(isFound);
    }
}