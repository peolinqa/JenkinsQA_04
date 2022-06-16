import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class DeleteFolderTest extends BaseTest {

    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String FOLDERS_NAMES_XPATH = "//*[contains(text(),\"Folder\")]";
    private final static String TEST_FOLDER_NAME = "First Job";
    private final static String DELETE_FOLDER_XPATH = "//span[text()='Delete Folder']";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String MY_VIEWS_XPATH = "//a[@href='/me/my-views']";
    private final static String SEARCH_XPATH = "//*[@id=\"search-box\"]\t";

    public  void clickNewItem() {getDriver().findElement(By.linkText("New Item")).click();}

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private static final By NAME = By.id("name");

    @BeforeMethod
    public void deletePreviousFolder() {
        DeleteBuildTest.deleteJobsWithPrefix(getDriver(), TEST_FOLDER_NAME);
    }

    @Test
    public void testDeleteFolder() {
        createFolder(getDriver(), TEST_FOLDER_NAME);
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[@href='job/First%20Job/']")).click();
        getDriver().findElement(By.xpath(DELETE_FOLDER_XPATH)).click();
        getDriver().findElement(By.xpath(YES_BUTTON_XPATH)).click();
        List<WebElement> foldersNamesAfterDelete = getDriver().findElements(By.xpath(FOLDERS_NAMES_XPATH));

        for (WebElement element : foldersNamesAfterDelete)
            if (element.getText().contains(TEST_FOLDER_NAME))
                throw new TestException("Folder " + TEST_FOLDER_NAME + " has not been deleted");

        getDriver().findElement(By.xpath(MY_VIEWS_XPATH)).click();
        Assert.assertFalse(foldersNamesAfterDelete.contains(TEST_FOLDER_NAME));

        getDriver().findElement(By.xpath(SEARCH_XPATH)).sendKeys(TEST_FOLDER_NAME + "\n");
        String folderNotFoundText = getDriver().findElement(By.xpath("//div [contains(text(),'Nothing seems to match.')]")).getText();
        Assert.assertEquals(folderNotFoundText, "Nothing seems to match.");
    }

    public static void createFolder(WebDriver driver,String folderName) {
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(NAME).sendKeys(folderName);
        driver.findElement(By.xpath("//span[text()='Folder']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("yui-gen6-button")).click();
        driver.findElement(By.xpath(DASHBOARD_XPATH)).click();
    }
}