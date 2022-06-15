import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class ChangeFolderNameOVFTest extends BaseTest {

    private String createRandomName() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(7, nameSubstrate);
    }

    private void сreateFolder(String folderName) {
        getDriver().findElement(By.partialLinkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void deleteFolder(String newFolderName) {
        clickBreadcrumbDashboard();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='job/" + newFolderName + "/']")))
                .build().perform();
        action.moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();
        action.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='/job/" + newFolderName + "/delete']")))
                .click().build().perform();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void clickBreadcrumbDashboard() {
        getDriver().findElement(By.partialLinkText("Dashboard")).click();
    }

    private void clickMenuRenameFolder(String folderName) {
        Actions renameFolder = new Actions(getDriver());
        renameFolder.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();
        renameFolder.moveToElement(getDriver().findElement(
                        By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        renameFolder.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='/job/" + folderName + "/confirm-rename']")))
                .click().build().perform();
    }

    private void setNewFolderName(String newFolderName) {
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(newFolderName);
        getDriver().findElement(By.name("Submit")).click();
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
        String unsafeCharacters = "&.!@#$%^*/|\\:?";
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
                getDriver().navigate().back();
                continue;
            }
            if (newFolderName.equals(".")) {
                setNewFolderName(newFolderName);
                String expectedResult = "“.” is not an allowed name";
                Assert.assertEquals(expectedResult, checkErrorMessage());
                getDriver().navigate().back();
                continue;
            }
            setNewFolderName(newFolderName);
            String expectedResult = "‘" + newFolderName + "’ is an unsafe character";
            Assert.assertEquals(expectedResult, checkErrorMessage());
            getDriver().navigate().back();
        }

        deleteFolder(folderName);
    }

    @Test
    public void testRenameFolderWithSpaceAsAName() {
        String folderName = createRandomName();
        String newFolderName = " ";
        String[] expectedResult = new String[] {"Error", "No name is specified"};

        сreateFolder(folderName);
        clickBreadcrumbDashboard();
        clickMenuRenameFolder(folderName);
        setNewFolderName(newFolderName);

        String[] actualResult = new String[] {
                getDriver().findElement(By.xpath("//h1")).getText(),
                getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText()
        };

        clickBreadcrumbDashboard();
        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(expectedResult, actualResult);
        asserts.assertTrue(getDriver().findElement(
                By.xpath("//a[@href='job/" + folderName + "/']")
        ).isDisplayed());
        asserts.assertAll();

        deleteFolder(folderName);
    }
}