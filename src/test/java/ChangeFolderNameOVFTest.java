import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.concurrent.TimeUnit;

public class ChangeFolderNameOVFTest extends BaseTest {

    private String createRandomName() {
        String folderNameSubstrate = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            builder.append(folderNameSubstrate.charAt((int)(Math.random() * folderNameSubstrate.length())));
        }
        String folderName = builder.toString();
        return folderName;
    }

    private void сreateFolder(String folderName) {
        getDriver().findElement(By.xpath("//span[normalize-space(text())='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    private void deleteCreatedFolder(String newFolderName) {
        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + newFolderName + "/']")))
                .build().perform();

        action.moveToElement(getDriver().findElement(By.xpath(
                        "//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + newFolderName + "/delete']")))
                .click().build().perform();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void cleareSendClick(String newFolderName) {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).sendKeys(newFolderName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test
    public void testRenameFolderPositive() {
        String folderName = createRandomName();
        String newFolderName = createRandomName();

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
        deleteCreatedFolder(newFolderName);

        Assert.assertEquals(newFolderName, actualResult);
    }

    @Test
    public void testRenameFolderWithUnsafeCharacters() {
        String folderName = createRandomName();
        String newFolderName = "";
        String unsafeCharacters = "!@#$%^*/|\\;:?";

        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        сreateFolder(folderName);
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();
        action.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + folderName + "/confirm-rename']")))
                .click().build().perform();

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            newFolderName = unsafeCharacters.substring(i, (i + 1));
            cleareSendClick(newFolderName);
            String expectedResult = "‘" + newFolderName + "’ is an unsafe character";
            Assert.assertEquals(expectedResult,
                    getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText());
            getDriver().navigate().back();
        }

        newFolderName = "&";
        cleareSendClick(newFolderName);
        String expectedResult = "‘&amp;’ is an unsafe character";
        Assert.assertEquals(expectedResult,
                getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText());
        getDriver().navigate().back();

        newFolderName = ".";
        cleareSendClick(newFolderName);
        expectedResult = "“.” is not an allowed name";
        Assert.assertEquals(expectedResult,
                getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText());
        getDriver().navigate().back();

        deleteCreatedFolder(folderName);
    }
}