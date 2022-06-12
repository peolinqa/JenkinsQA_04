import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FolderCheckPreviewAndDeleteFolderTest extends BaseTest {
    private static final By NEW_ITEM = By.className("task-link-text");
    private static final By INPUT_LINE = By.id("name");
    private static final String NAME = "ProjectsSg28832842";
    private static final By FOLDER_IN_THE_TOP_MENU = By.xpath("//a[@href='/job/ProjectsSg28832842/']");
    private static final By DELETE_FOLDER = By.xpath("//a[@href='/job/ProjectsSg28832842/delete']");
    private static final By MENU_SELECTOR = By.id("menuSelector");

    private void createFolder() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(NAME);
        getDriver().findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void deleteFolderFromTopMenu() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement((FOLDER_IN_THE_TOP_MENU))).build().perform();
        action.moveToElement(getDriver().findElement(MENU_SELECTOR)).click().build().perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(DELETE_FOLDER));
        getDriver().findElement(DELETE_FOLDER).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void deleteFolderFromSideMenu() {
        getDriver().findElement(DELETE_FOLDER).click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {
        final String expectedResult = "General";

        createFolder();

        getDriver().findElement(By.name("_.description")).sendKeys("General");
        getDriver().findElement(By.className("textarea-show-preview")).click();
        String actualPreview = getDriver().findElement(By.className("textarea-preview")).getText();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        String actualFolderPage = getDriver().findElement(By.id("view-message")).getText();

        Assert.assertEquals(actualPreview, expectedResult);
        Assert.assertEquals(actualFolderPage, expectedResult);

        deleteFolderFromSideMenu();
    }

    @Test
    public void testDeleteFolderFromTheTopMenu() {
        final String expectedResult = "Nothing seems to match.";

        createFolder();

        deleteFolderFromTopMenu();

        getDriver().findElement(By.id("search-box")).sendKeys(NAME.concat("\n"));
        String actualResult = getDriver().findElement(By.xpath("//div[@class='error']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}