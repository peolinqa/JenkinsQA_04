package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class DeleteFolderTest extends BaseTest {

    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String FOLDERS_NAMES_XPATH = "//*[contains(text(),\"Folder\")]";
    private final static String TEST_FOLDER_NAME = "TestFolder_1";
    private final static String DELETE_FOLDER_XPATH = "//a[@href='/job/" + TEST_FOLDER_NAME + "/delete']";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String MY_VIEWS_XPATH = "//a[@href='/me/my-views']";
    private final static String SEARCH_XPATH = "//*[@id=\"search-box\"]\t";

    public void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    public void clickFolderItem() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
    }

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private static final By NAME = By.id("name");

    @BeforeMethod
    public void setUp() {
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        createFolder(TEST_FOLDER_NAME);
    }

    @Test
    public void testDeleteFolder() {
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[@href ='job/" + TEST_FOLDER_NAME + "/']")).click();
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

    public void createFolder(String folderName) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(folderName);
        clickFolderItem();
        clickOKButton();
        getDriver().findElement(By.name("_.description")).sendKeys("Description for the new folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
    }
}