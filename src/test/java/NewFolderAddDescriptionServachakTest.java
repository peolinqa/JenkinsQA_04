import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NewFolderAddDescriptionServachakTest extends BaseTest {

    private void deleteFolder(){

        getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']")).click();
        getDriver().findElement(
                By.xpath("//a[@href='job/New%20Folder%20TC_011.003/']")).click();
        getDriver().findElement(By.xpath("//a[@title='Delete Folder']")).click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();
    }

    @Test
    public void testAddDescriptionAfterCreatedNewFolder(){
        final String NAME_FOLDER = "New Folder TC_011.003";
        final String FOLDER_DESCRIPTION = "TC_011.003";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys(NAME_FOLDER);
        getDriver().findElement(
                By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(
                By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//span[@id='yui-gen6']")).click();
        getDriver().findElement(By.xpath("//a[@href='editDescription']")).click();
        getDriver().findElement(
                By.xpath("//textarea[@name='description']"))
                .sendKeys(FOLDER_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        Assert.assertEquals(actualResult, FOLDER_DESCRIPTION);

        deleteFolder();
    }
}