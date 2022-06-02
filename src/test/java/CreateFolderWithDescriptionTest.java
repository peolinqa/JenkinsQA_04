import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFolderWithDescriptionTest extends BaseTest {

    private final String FOLDER_NAME = "TC_011_001";
    private final String FOLDER_DESCRIPTION = "TC_011_001";

    protected void createFolder() {

        getDriver().findElement(By.id("jenkins-home-link")).click();

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("_.description")).sendKeys(FOLDER_DESCRIPTION);
        getDriver().findElement(By.id("yui-gen6-button")).click();

    }

    protected void deleteFolder() {

        getDriver().findElement(By.id("jenkins-home-link")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + FOLDER_NAME + "')]")).click();
        getDriver().findElement(By.linkText("Delete Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

    }

    @Test
    public void testCreateFolder_TC_017_001() throws InterruptedException {

        String urlExpected = "http://localhost:8080/job/".concat(FOLDER_NAME).concat("/");

        createFolder();
        Thread.sleep(2000);

        Assert.assertEquals(getDriver().getCurrentUrl(), urlExpected);

        Assert.assertEquals(getDriver().findElement
                        (By.xpath("//div[@id='view-message']")).getText(),
                FOLDER_DESCRIPTION);

        deleteFolder();

    }

}
