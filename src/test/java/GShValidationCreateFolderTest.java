import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GShValidationCreateFolderTest extends BaseTest {
    private final String FOLDER_NAME = "genashepel";

    private void deleteFolderName(String name) {
        if (name != null && !name.equals("")) {
            getDriver().findElement(By.xpath("//li[@class='item']/a")).click();
            getDriver().findElement(By.xpath("//td/a[@href='job/".concat(FOLDER_NAME).concat("/']"))).click();
            getDriver()
                    .findElement(By.xpath("//span[@class='task-link-wrapper ']/a[@title='Delete Folder']"))
                    .click();
            getDriver().findElement(By.id("yui-gen1-button")).click();
        }
    }

    @Ignore
    @Test
    public void testCreateFolder() {
        getDriver()
                .findElement(By.xpath("//span[@class='task-link-wrapper ']/a[@href='/view/all/newJob']"))
                .click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver()
                .findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualResult, FOLDER_NAME);

        deleteFolderName(FOLDER_NAME);
    }
}
