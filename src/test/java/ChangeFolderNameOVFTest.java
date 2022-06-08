import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ChangeFolderNameOVFTest extends BaseTest {

    private String createRandomName() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(7, nameSubstrate);
    }

    private void сreateFolder(String folderName) {
        getDriver().findElement(By.xpath("//span[normalize-space(text())='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    private void deleteFolder(String newFolderName) {
        clickBreadcrumbDashboard();

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

    private void clickBreadcrumbDashboard() {
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();
    }

    private void clickMenuRenameFolder(String folderName) {
        Actions renameFolder = new Actions(getDriver());
        renameFolder.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();
        renameFolder.moveToElement(getDriver().findElement(By.xpath(
                        "//div[@id='menuSelector']")))
                .click().build().perform();
        renameFolder.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + folderName + "/confirm-rename']")))
                .click().build().perform();
    }

    private void setNewFolderName(String newFolderName) {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).sendKeys(newFolderName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private String checkErrorMessage() {

        return getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText();
    }

    @Test
    public void testRenameFolderPositive() {
        String folderName = createRandomName();
        String newFolderName = createRandomName();

        сreateFolder(folderName);
        clickBreadcrumbDashboard();
        clickMenuRenameFolder(folderName);
        setNewFolderName(newFolderName);

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(newFolderName, actualResult);

        deleteFolder(newFolderName);
    }

    @Test
    public void testRenameFolderWithUnsafeCharacters() {
        String unsafeCharacters = "&.!@#$%^*/|\\;:?";
        String folderName = createRandomName();

        сreateFolder(folderName);
        clickBreadcrumbDashboard();
        clickMenuRenameFolder(folderName);

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            String newFolderName = unsafeCharacters.substring(i, (i + 1));
            if (newFolderName.equals("&")) {
                setNewFolderName(newFolderName);
                String expectedResult = "‘&amp;’ is an unsafe character";
                Assert.assertEquals(expectedResult, checkErrorMessage());
                break;
            }
            if (newFolderName.equals(".")) {
                setNewFolderName(newFolderName);
                String expectedResult = "“.” is not an allowed name";
                Assert.assertEquals(expectedResult, checkErrorMessage());
                break;
            }
            setNewFolderName(newFolderName);
            String expectedResult = "‘" + newFolderName + "’ is an unsafe character";
            Assert.assertEquals(expectedResult, checkErrorMessage());
            getDriver().navigate().back();
        }

        deleteFolder(folderName);
    }
}