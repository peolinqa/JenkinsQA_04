import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateOrganizationFolderTest extends BaseTest {

    private final String VALID_VALUE_FOR_NAME = "New organization folder";

    private void deleteFolderAfterTest(){
        getDriver().findElement(By.linkText(VALID_VALUE_FOR_NAME)).click();
        getDriver().findElement(By.linkText("Delete Organization Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testCreateOrganizationFolder () {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME);
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen17-button")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
        Assert.assertTrue(getDriver().findElement(By.linkText(VALID_VALUE_FOR_NAME)).isDisplayed());
        deleteFolderAfterTest();
    }
}
