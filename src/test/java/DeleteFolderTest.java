import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DeleteFolderTest extends BaseTest {

    private final String FOLDER_NAME = "DeleteFolderTest";

    private void deleteFolder() {

        getDriver().findElement(By.partialLinkText(FOLDER_NAME)).click();
        getDriver().findElement(By.partialLinkText("Delete Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private boolean checkFolderIsCreated() {

        return getDriver().findElements(By.partialLinkText(FOLDER_NAME)).size() > 0;
    }

    private void createNewFolder() {

        if (!checkFolderIsCreated()) {
            getDriver().findElement(By.linkText("New Item")).click();

            WebElement projectName = getDriver().findElement(By.name("name"));
            projectName.sendKeys(FOLDER_NAME);

            getDriver().findElement(
                    By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")
            ).click();
            getDriver().findElement(By.id("ok-button")).click();
            getDriver().findElement(
                    By.xpath("//button[contains(text(),'Save')]")
            ).click();
        }
    }

    private void mainPage() {

        getDriver().findElement(By.xpath("//a[@href='/']")).click();
    }

    @Test
    public void testFolderIsOnDashboard() {

        mainPage();

        createNewFolder();

        Assert.assertTrue(checkFolderIsCreated());
    }

    @Test(dependsOnMethods = {"testFolderIsOnDashboard"})
    public void testFolderIsNowDeleted() {

        String expectedResult = "false";

        deleteFolder();

        mainPage();

        Assert.assertEquals(String.valueOf(checkFolderIsCreated()), expectedResult);
    }
}
