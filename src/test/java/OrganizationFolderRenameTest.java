import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OrganizationFolderRenameTest extends BaseTest {
    private final String ORGANIZATION_FOLDER_NAME = "Test folder name";
    private final String NEW_ORGANIZATION_FOLDER_NAME = "New test folder name";

    private void preconditionCreateOrganizationFolder() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(ORGANIZATION_FOLDER_NAME);
        getDriver().findElement(By.xpath("//span[text()='Organization Folder']/..")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

    }

    private void postConditionCreateOrganizationFolder() {
        getDriver().findElement(By.linkText(NEW_ORGANIZATION_FOLDER_NAME)).click();
        getDriver().findElement(By.linkText("Delete Organization Folder")).click();
        getDriver().findElement(By.xpath("//button[@type = 'submit']")).click();

    }

    @Test
    public void testRenameOrganizationFolder() {

        preconditionCreateOrganizationFolder();

        getDriver().findElement(By.linkText(ORGANIZATION_FOLDER_NAME)).click();
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.xpath("//div/input[@checkdependson = 'newName']")).clear();
        getDriver().findElement(
                        By.xpath("//div/input[@checkdependson = 'newName']"))
                .sendKeys(NEW_ORGANIZATION_FOLDER_NAME);
        getDriver().findElement(By.xpath("//button[@type = 'submit']")).click();
        String actualResult = getDriver()
                .findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualResult, NEW_ORGANIZATION_FOLDER_NAME);

        postConditionCreateOrganizationFolder();
    }

}
