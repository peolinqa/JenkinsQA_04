import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFolderKKTest extends BaseTest {

    String nameOfFolder = "Hello World";
    public boolean isFolderExist() {
        boolean folderExist = false;
        if (getDriver().findElement(By.xpath("//a[contains(@href, 'job')]")).isDisplayed()) {
            folderExist = true;
        }
        return folderExist;
    }

    @Test
    private void testCreateFolder() {
        if (isFolderExist()) {
            nameOfFolder += String.valueOf((int) (Math.random() * 999));
        }
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(nameOfFolder);
        getDriver().findElement
                (By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li/a")).click();

        Assert.assertTrue(getDriver().findElement(By.linkText(nameOfFolder)).isDisplayed());
    }

    @Test
    public void testDeleteFolderOnSidePanel() {
        if (isFolderExist()) {
            getDriver().findElement(By.partialLinkText(nameOfFolder)).click();
            getDriver().findElement(By.partialLinkText("Delete Folder")).click();
            getDriver().findElement(By.id("yui-gen1-button")).click();

            List<WebElement> folders = getDriver().findElements(By.xpath("//a[contains(@href, 'job')]"));
            List<String> actualNamesOfFolders = new ArrayList<>();
            for (int i = 0; i < folders.size(); i++) {
                actualNamesOfFolders.add(i, folders.get(i).getText());
            }

            Assert.assertFalse((actualNamesOfFolders.contains(nameOfFolder)));
        }
    }
}