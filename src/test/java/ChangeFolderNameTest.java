import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.concurrent.TimeUnit;

public class ChangeFolderNameTest extends BaseTest {

    public void сreateFolder(String folderName) {
        getDriver().findElement(By.xpath("//span[normalize-space(text())='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    @Test
    public void testRenameFolderPositive() {
        String folderName = "OVFtestfolder";
        String newFolderName = "OVFtestfolder-2";

        сreateFolder(folderName);
        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();

        action.moveToElement(getDriver().findElement(By.xpath(
                        "//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + folderName + "/confirm-rename']")))
                .click().build().perform();

        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']"))
                .sendKeys(newFolderName + Keys.ENTER);

        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(newFolderName, actualResult);
    }
}
