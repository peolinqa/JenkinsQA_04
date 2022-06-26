import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;
import java.util.stream.Collectors;

public class _FolderTest extends BaseTest {

    private static final String NAME_FOLDER = "Configure";

    private static final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};
    protected static final char[] CHARS =
            {',', 39, '`', '~', '-', ' ', '(', ')', '{', '}', '+', '=', '_', '"'};

    private static final By NAME = By.id("name");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");

    public static void deleteJobsWithPrefix(WebDriver driver, String prefix) {
        ProjectUtils.Dashboard.Main.Dashboard.click(driver);
        List<String> jobsNames = ProjectUtils.getListOfJobs(driver);
        jobsNames
                .forEach(jobsName -> {
                    if (jobsName.startsWith(prefix)) {
                        String jobWithPercent = jobsName.replace(" ", "%20");
                        driver.findElement(By.xpath("//a[@href='job/" + jobWithPercent + "/']")).click();
                        driver.findElement(
                                By.xpath("//a[@data-url='/job/" + jobWithPercent + "/doDelete']")).click();
                        driver.switchTo().alert().accept();
                    }
                });
    }

    private void createFolderWithoutSaveButton(String folderName) {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(NAME).sendKeys(folderName);
        ProjectUtils.NewItemTypes.Folder.click(getDriver());
        ProjectUtils.clickOKButton(getDriver());
    }

    private static void createFolder(WebDriver driver, String folderName) {
        ProjectUtils.Dashboard.Main.NewItem.click(driver);
        driver.findElement(NAME).sendKeys(folderName);
        ProjectUtils.NewItemTypes.Folder.click(driver);
        ProjectUtils.clickOKButton(driver);
        driver.findElement(SUBMIT_BUTTON).click();
    }

    private boolean isFolderPresent(String name) {

        boolean isPresent = false;

        List<WebElement> projectsOnDashboard = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']//tbody//td[3]"));
        for (WebElement jobs : projectsOnDashboard) {
            if (jobs.getText().contains(name)) {
                isPresent = true;
            }
        }

        return isPresent;
    }

    private void deleteFolderFromTopMenu(String folderName) {
        getActions().moveToElement(getDriver().findElement((By.xpath("//a[@href='/job/" + folderName + "/']")))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/job/" + folderName + "/delete']")));
        getDriver().findElement(By.xpath("//a[@href='/job/" + folderName + "/delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void clickMenuRenameFolder(String folderName) {
        getActions().moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();
        getActions().moveToElement(getDriver().findElement(
                        By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        getActions().moveToElement(getDriver().findElement(
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
    public void testConfigurePage() {

        createFolderWithoutSaveButton(NAME_FOLDER);
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins")));

        WebElement actual = getDriver().findElement(
                By.xpath("//div[@class='jenkins-config-widgets']//div[text()='General']"));

        Assert.assertEquals(actual.getText(), "General");
    }

    @Test(dependsOnMethods = {"testConfigurePage"})
    public void testCreateFolderPositive() {

        Assert.assertTrue(isFolderPresent(NAME_FOLDER));
    }

    @Test
    public void testCycleCreateFolderWithInvalidData() {

        getDriver().findElement(By.className("task-link-text")).click();
        WebElement inputField = getDriver().findElement(NAME);

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
        WebElement inputField = getDriver().findElement(NAME);

        inputField.sendKeys(".");
        WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

        getWait5().until(ExpectedConditions.textToBePresentInElement(warningText, "» “.” is not an allowed name"));
        Assert.assertEquals(warningText.getText(), "» “.” is not an allowed name");
    }

    @Test
    public void testCycleTypeAnItemNameWithValidSpecialCharacters() {
        getDriver().findElement(By.className("task-link-text")).click();

        WebElement text = getDriver().findElement(By.className("input-help"));

        for (char x : CHARS) {
            getDriver().findElement(NAME).sendKeys(Character.toString(x));

            getWait5().until(ExpectedConditions.textToBePresentInElement(text, "» Required field"));

            Assert.assertEquals(text.getText(), "» Required field");

            getDriver().findElement(NAME).clear();
        }
    }

    @Test
    public void testDeleteFolder() {

        final String randomFolderName = TestUtils.getRandomStr();

        deleteJobsWithPrefix(getDriver(), randomFolderName);
        createFolder(getDriver(), randomFolderName);

        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        getDriver().findElement(By.xpath("//a[@href='job/" + randomFolderName + "/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Folder']")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Yes')]")).click();

        List<WebElement> foldersNamesAfterDelete = getDriver().
                findElements(By.xpath("//*[contains(text(),\"Folder\")]"));
        for (WebElement element : foldersNamesAfterDelete)
            if (element.getText().contains(randomFolderName))
                throw new TestException("Folder " + randomFolderName + " has not been deleted");
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();

        Assert.assertFalse(foldersNamesAfterDelete.contains(randomFolderName));

        getDriver().findElement(By.xpath("//*[@id=\"search-box\"]\t")).
                sendKeys(randomFolderName + "\n");
        String folderNotFoundText = getDriver().findElement(
                By.xpath("//div [contains(text(),'Nothing seems to match.')]")).getText();

        Assert.assertEquals(folderNotFoundText, "Nothing seems to match.");
    }

    @Test
    public void testCreateFolderThatStartsWithUnsafeCharacter() {

        final String symbols = "!@#$%^&*:;<>?/\\.";

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        for (int i = 0; i < symbols.length(); i++) {
            String s = String.valueOf(symbols.charAt(i));
            TestUtils.clearAndSend(getDriver(), NAME, s);
            ProjectUtils.NewItemTypes.Folder.click(getDriver());

            String expectedResult = "";
            if (s.equals(".")) {
                expectedResult = "» “" + s + "” is not an allowed name";
            } else {
                expectedResult = "» ‘" + s + "’ is an unsafe character";
            }

            Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), expectedResult);
        }
    }

    @Test
    public void testCreateFolderWithUnsafeCharacter() {

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        getDriver().findElement(NAME).sendKeys("TestFolder@Jenkins");
        ProjectUtils.NewItemTypes.Folder.click(getDriver());

        Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(),
                "» ‘@’ is an unsafe character");

        ProjectUtils.clickOKButton(getDriver());

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                "‘@’ is an unsafe character");
    }

    @Test
    public void testCreateFolderWithTheSameName() {

        final String nameFolder = TestUtils.getRandomStr();
        final String expectedErrorMessage = String.format("» A job already exists with the name ‘%s’", nameFolder);

        createFolder(getDriver(), nameFolder);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        getDriver().findElement(NAME).sendKeys(nameFolder);
        ProjectUtils.NewItemTypes.Folder.click(getDriver());
        WebElement actualErrorMessage1 = getWait20()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("itemname-invalid")));

        Assert.assertEquals(actualErrorMessage1.getText(), expectedErrorMessage);

        ProjectUtils.clickOKButton(getDriver());

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                expectedErrorMessage.substring(2));
    }

    @Test
    public void testCheckButtonSave() {

        final String nameFolder = TestUtils.getRandomStr();

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(NAME).sendKeys(nameFolder);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        ProjectUtils.clickOKButton(getDriver());
        getDriver().findElement(By.xpath("//div[@class='setting-main']/input")).sendKeys(nameFolder);
        getDriver().findElement(SUBMIT_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1")).getText(), nameFolder);
    }

    @Test
    public void testCheckButtonApply() {

        final String nameFolder = TestUtils.getRandomStr();

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(NAME).sendKeys(nameFolder);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        ProjectUtils.clickOKButton(getDriver());
        getDriver().findElement(By.id("yui-gen4-button")).click();

        String allert = getDriver().findElement(By.id("notification-bar")).getAttribute("class");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notif-alert-clear")));

        Assert.assertTrue(allert.contains("notif-alert-show"));
    }

    @Test
    public void testRenameFolderPositive() {

        final String randomFolderName = TestUtils.getRandomStr();
        final String newRandomFolderName = TestUtils.getRandomStr();

        createFolder(getDriver(), randomFolderName);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        clickMenuRenameFolder(randomFolderName);
        setNewFolderName(newRandomFolderName);

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(newRandomFolderName, actualResult);
    }

    @Test
    public void testRenameFolderWithUnsafeCharacters() {

        final String unsafeCharacters = "&.!@#$%^*/|\\:?";
        final String folderName = TestUtils.getRandomStr();

        createFolder(getDriver(), folderName);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
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
    }

    @Test
    public void testRenameFolderWithSpaceAsAName() {

        final String folderName = TestUtils.getRandomStr();
        final String newFolderName = " ";
        final String[] expectedResult = new String[]{"Error", "No name is specified"};

        createFolder(getDriver(), folderName);
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        clickMenuRenameFolder(folderName);
        setNewFolderName(newFolderName);

        String[] actualResult = new String[]{
                getDriver().findElement(By.xpath("//h1")).getText(),
                getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText()
        };

        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(expectedResult, actualResult);
        asserts.assertTrue(getDriver().findElement(By.xpath("//a[@href='job/" + folderName + "/']")).isDisplayed());
        asserts.assertAll();
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {

        final String folderName = TestUtils.getRandomStr();
        final String folderDescription = TestUtils.getRandomStr();

        createFolderWithoutSaveButton(folderName);

        getDriver().findElement(By.name("_.description")).sendKeys(folderDescription);
        getDriver().findElement(By.className("textarea-show-preview")).click();
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), folderDescription);
        getDriver().findElement(SUBMIT_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.id("view-message")).getText(), folderDescription);
    }

    @Test
    public void testDeleteFolderFromTheTopMenu() {

        final String folderName = TestUtils.getRandomStr();

        createFolderWithoutSaveButton(folderName);
        deleteFolderFromTopMenu(folderName);

        getDriver().findElement(By.id("search-box")).sendKeys(folderName.concat("\n"));
        String actualResult = getDriver().findElement(By.xpath("//div[@class='error']")).getText();

        Assert.assertEquals(actualResult, "Nothing seems to match.");
    }
}