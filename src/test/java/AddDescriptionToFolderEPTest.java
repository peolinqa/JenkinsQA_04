import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AddDescriptionToFolderEPTest extends BaseTest {
    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String TEST_FOLDER_NAME = "First Job";

    public void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    @BeforeMethod
    public void deletePreviousFolder() {
        DeleteBuildTest.deleteJobsWithPrefix(getDriver(), TEST_FOLDER_NAME);
    }

    @Test
    public void testAddDescriptionToFolderEP () {
        DeleteFolderTest.createFolder(getDriver(),TEST_FOLDER_NAME);
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[@href='job/First%20Job/']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@class='setting-input   ']")).sendKeys("Done");
        getDriver().findElement(By.id("yui-gen1-button")).click();
        String description = getDriver().findElement(By.xpath("//div[@id ='description']")).getText();
        Assert.assertTrue(description.contains("Done"));
    }
}