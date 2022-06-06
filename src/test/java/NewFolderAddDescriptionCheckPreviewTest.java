import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NewFolderAddDescriptionCheckPreviewTest extends BaseTest {
    private static final By NEW_ITEM = By.className("task-link-text");
    private static final By INPUT_LINE = By.id("name");
    private static final String NAME = "ProjectsSg28832842";

    private void deleteFolder() {
        getDriver().findElement(By.xpath("//a[@href='/job/".concat(NAME).concat("/delete']"))).click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {
        final String expectedResult = "General";

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_LINE).sendKeys(NAME);
        getDriver().findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("_.description")).sendKeys("General");
        getDriver().findElement(By.className("textarea-show-preview")).click();
        String actualPreview = getDriver().findElement(By.className("textarea-preview")).getText();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        String actualFolderPage = getDriver().findElement(By.id("view-message")).getText();

        Assert.assertEquals(actualPreview, expectedResult);
        Assert.assertEquals(actualFolderPage, expectedResult);

        deleteFolder();
    }
}