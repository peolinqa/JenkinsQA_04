import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class _FolderTest extends BaseTest {

    private static final By NAME = By.id("name");

    private final String PROP_PORT = "8080";
    private final String NAME_FOLDER = "Configure";
    private final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};
    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String FOLDERS_NAMES_XPATH = "//*[contains(text(),\"Folder\")]";
    private final static String TEST_FOLDER_NAME = "First Job";
    private final static String DELETE_FOLDER_XPATH = "//span[text()='Delete Folder']";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String MY_VIEWS_XPATH = "//a[@href='/me/my-views']";
    private final static String SEARCH_XPATH = "//*[@id=\"search-box\"]\t";
    private final static String ALL_NAMES_IN_TABLE_XPATH = "//table[@id='projectstatus']/tbody/tr/td[3]/a";
    private final static String BASE_URL = "http:localhost:8080";

    private void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    private void clickFolderItem() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
    }

    private void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void clickJenkinsHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private void createFolder(String nameFolder) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(nameFolder);
        clickFolderItem();
        clickOKButton();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        clickJenkinsHome();
    }

    private static void createFolder(WebDriver driver, String folderName) {
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(NAME).sendKeys(folderName);
        driver.findElement(By.xpath("//span[text()='Folder']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("yui-gen6-button")).click();
    }

    private static void deleteJobsWithPrefix(WebDriver driver, String prefix) {
        driver.findElement(By.xpath(DASHBOARD_XPATH)).click();
        List<String> jobsNames = driver.findElements(By.xpath(ALL_NAMES_IN_TABLE_XPATH))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        jobsNames
                .forEach(jobsName -> {
                    if (jobsName.startsWith(prefix)) {
                        String jobWithPercent = jobsName.replace(" ", "%20");
                        driver.get(BASE_URL + "/job/" + jobWithPercent + "/delete");
                        driver.findElement(By.id("yui-gen1-button")).click();
                    }
                });
    }

    private boolean isFolderPresent(String name) {
        try {
            getDriver().findElement(By.xpath("//tr[@id='job_" + name + "']//td[3]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    private void deleteFolder(String nameFolder) {
        clickJenkinsHome();
        getDriver().findElement(By.xpath("//a[@href='job/" + nameFolder + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Folder')]")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testConfigurePage() {
        _FolderTest.deleteJobsWithPrefix(getDriver(), NAME_FOLDER);
        final String expectedUrl = String.format("http://localhost:%s", PROP_PORT)
                .concat("/job/")
                .concat(NAME_FOLDER)
                .concat("/");
        _FolderTest.createFolder(getDriver(), NAME_FOLDER);
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins")));
        String actualURL = getDriver().getCurrentUrl();
        Assert.assertEquals(actualURL, expectedUrl);
        deleteJobsWithPrefix(getDriver(), NAME_FOLDER);
    }

    @Test
    public void testCycleCreateFolderWithInvalidData() {

        getDriver().findElement(By.className("task-link-text")).click();

        WebElement inputField = getDriver().findElement(By.id("name"));

        for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
            inputField.sendKeys(Character.toString(INVALID_SYMBOLS[i]));
            WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

            getWait5().until(ExpectedConditions.textToBePresentInElement(warningText,
                    "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character"));

            String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character";
            Assert.assertEquals(warningText.getText(), expectedResult);
            inputField.clear();
        }
    }

    @Test
    public void testCreateFolderWithDot() {

        getDriver().findElement(By.className("task-link-text")).click();
        WebElement inputField = getDriver().findElement(By.id("name"));

        inputField.sendKeys(".");
        WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

        getWait5().until(ExpectedConditions.textToBePresentInElement(warningText, "» “.” is not an allowed name"));
        Assert.assertEquals(warningText.getText(), "» “.” is not an allowed name");
        inputField.clear();
    }

    @Test
    public void testDeleteFolder() {
        deleteJobsWithPrefix(getDriver(), TEST_FOLDER_NAME);
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

    @Test
    public void testCreateFolderPositive() {

        clickNewItem();

        getDriver().findElement(NAME).sendKeys("TestRomanFolder_1");
        clickFolderItem();
        clickOKButton();

        getDriver().findElement(By.name("_.description")).sendKeys("Description for the new folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']")).click();

        boolean isFound;
        try {
            getDriver().findElement(By.id("job_TestRomanFolder_1"));
            isFound = true;
        } catch (NoSuchElementException e) {
            isFound = false;
        }

        Assert.assertTrue(isFound);
    }

    @Test
    public void testCreateFolderThatStartsWithUnsafeCharacter() {

        final String symbols = "!@#$%^&*:;<>?/\\.";

        clickNewItem();

        WebElement name = getDriver().findElement(NAME);

        for (int i = 0; i < symbols.length(); i++) {
            name.clear();
            String s = String.valueOf(symbols.charAt(i));
            name.sendKeys(s);
            clickFolderItem();

            String expectedResult = "";
            if (s.equals(".")) {
                expectedResult = "» “" + s + "” is not an allowed name";
            } else {
                expectedResult = "» ‘" + s + "’ is an unsafe character";
            }

            String actualResult = getDriver().findElement(By.id("itemname-invalid")).getText();

            Assert.assertEquals(actualResult, expectedResult);
        }
    }

    @Test
    public void testCreateFolderWithUnsafeCharacter() {

        final String expectedErrorMessage = "» ‘@’ is an unsafe character";
        final String expectedError = "Error";

        clickNewItem();

        getDriver().findElement(NAME).sendKeys("TestFolder@Jenkins");
        clickFolderItem();
        String actualErrorMessage1 = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(actualErrorMessage1, expectedErrorMessage);

        clickOKButton();

        String actualError = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        String actualErrorMessage2 = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualError, expectedError);
        Assert.assertEquals(actualErrorMessage2, expectedErrorMessage.substring(2));
    }

    @Test
    public void testCreateFolderWithTheSameName() {

        final String nameFolder = "TestRomanFolder";
        final String expectedErrorMessage = "» A job already exists with the name ‘" + nameFolder + "’";
        final String expectedError = "Error";

        createFolder(nameFolder);

        clickNewItem();

        getDriver().findElement(NAME).sendKeys(nameFolder);
        clickFolderItem();
        WebElement actualErrorMessage1 = getWait20()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("itemname-invalid")));

        Assert.assertEquals(actualErrorMessage1.getText(), expectedErrorMessage);

        clickOKButton();

        String actualError = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        String actualErrorMessage2 = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualError, expectedError);
        Assert.assertEquals(actualErrorMessage2, expectedErrorMessage.substring(2));

        deleteFolder(nameFolder);
    }
}