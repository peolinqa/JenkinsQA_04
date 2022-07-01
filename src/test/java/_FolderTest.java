import model.FolderConfigPage;
import model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.List;

import static runner.ProjectUtils.ProjectType.Folder;

public class _FolderTest extends BaseTest {

    private static final String NAME_FOLDER = "Configure";
    private static final String RANDOM_FOLDER_NAME = TestUtils.getRandomStr();

    private static final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};
    protected static final char[] CHARS =
            {',', 39, '`', '~', '-', ' ', '(', ')', '{', '}', '+', '=', '_', '"'};

    private static final By NAME = By.id("name");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
    private static final String WARNING_TEXT_WITH_DOT = "» “.” is not an allowed name";
    private static final String WARNING_TEXT_UNSAFE = "’ is an unsafe character";
    private static final String ELEMENT1 = "//div[@id='main-panel']/p";
    private static final String ELEMENT2 = "//div[@id='main-panel']/h1";

    private void createFolderWithoutSaveButton(String folderName) {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(NAME).sendKeys(folderName);
        Folder.click(getDriver());
        ProjectUtils.clickOKButton(getDriver());
    }

    private static void createFolder(WebDriver driver, String folderName) {
        new HomePage(driver)
                .clickNewItem()
                .setProjectName(folderName)
                .setProjectType(Folder)
                .createAndGoToConfig()
                .saveConfigAndGoToProject();
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
    public void testCreateFolder() {

        String folderName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME)
                .setProjectType(Folder)
                .createAndGoToConfig()
                .saveConfigAndGoToProject()
                .getFolderName();

        Assert.assertEquals(folderName, RANDOM_FOLDER_NAME);
    }

    @Test
    public void testConfigurePage() {

        createFolderWithoutSaveButton(NAME_FOLDER);
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins")));

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@class='jenkins-config-widgets']//div[text()='General']")).getText(), "General");
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
                    "» ‘" + INVALID_SYMBOLS[i] + WARNING_TEXT_UNSAFE));

            String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + WARNING_TEXT_UNSAFE;

            Assert.assertEquals(warningText.getText(), expectedResult);
            inputField.clear();
        }
    }

    @Test
    public void testCreateFolderWithDot() {

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(NAME).sendKeys(".");

        WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

        getWait5().until(ExpectedConditions.textToBePresentInElement(warningText, WARNING_TEXT_WITH_DOT));
        Assert.assertEquals(warningText.getText(), WARNING_TEXT_WITH_DOT);
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

        ProjectUtils.deleteJobsWithPrefix(getDriver(), randomFolderName);
        createFolder(getDriver(), randomFolderName);

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        getDriver().findElement(By.xpath("//a[@href='job/" + randomFolderName + "/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Folder']")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Yes')]")).click();

        List<WebElement> foldersNamesAfterDelete = getDriver().
                findElements(By.xpath("//*[contains(text(),'Folder')]"));
        for (WebElement element : foldersNamesAfterDelete)
            if (element.getText().contains(randomFolderName))
                throw new TestException("Folder " + randomFolderName + " has not been deleted");
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();

        Assert.assertFalse(foldersNamesAfterDelete.contains(randomFolderName));

        getDriver().findElement(By.xpath("//input[@id='search-box']")).
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
            Folder.click(getDriver());

            String expectedResult = "";
            if (s.equals(".")) {
                expectedResult = "» “" + s + "” is not an allowed name";
            } else {
                expectedResult = "» ‘" + s + WARNING_TEXT_UNSAFE;
            }

            Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), expectedResult);
        }
    }

    @Test
    public void testCreateFolderWithUnsafeCharacter() {

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        getDriver().findElement(NAME).sendKeys("TestFolder@Jenkins");
        Folder.click(getDriver());

        Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(),
                "» ‘@’ is an unsafe character");

        ProjectUtils.clickOKButton(getDriver());

        Assert.assertEquals(getDriver().findElement(By.xpath(ELEMENT1)).getText(), "‘@’ is an unsafe character");
        Assert.assertEquals(getDriver().findElement(By.xpath(ELEMENT2)).getText(), "Error");
    }

    @Test
    public void testCreateFolderWithTheSameName() {

        final String nameFolder = TestUtils.getRandomStr();
        final String expectedErrorMessage = String.format("A job already exists with the name ‘%s’", nameFolder);

        createFolder(getDriver(), nameFolder);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        getDriver().findElement(NAME).sendKeys(nameFolder);
        Folder.click(getDriver());

        Assert.assertEquals(getWait20()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("itemname-invalid"))).getText(), "» " + expectedErrorMessage);

        ProjectUtils.clickOKButton(getDriver());

        Assert.assertEquals(getDriver().findElement(By.xpath(ELEMENT1)).getText(), expectedErrorMessage);
        Assert.assertEquals(getDriver().findElement(By.xpath(ELEMENT2)).getText(), "Error");
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

        Assert.assertTrue(getDriver().findElement(By.id("notification-bar")).getAttribute("class").contains("notif-alert-show"));
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testRenameFolderPositive() {

        final String newRandomFolderName = TestUtils.getRandomStr();

        new HomePage(getDriver()).clickName(RANDOM_FOLDER_NAME);

        String actualResult = new FolderConfigPage(getDriver())
                .clickRenameFolder()
                .renameFolder(newRandomFolderName)
                .getFolderName();

        Assert.assertEquals(actualResult, newRandomFolderName);
    }

    @Test
    public void testRenameFolderWithUnsafeCharacters() {

        final String unsafeCharacters = "&.!@#$%^*/|\\:?";
        final String folderName = TestUtils.getRandomStr();

        createFolder(getDriver(), folderName);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        clickMenuRenameFolder(folderName);

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            String newFolderName = unsafeCharacters.substring(i, (i + 1));
            if (newFolderName.equals("&")) {
                setNewFolderName(newFolderName);
                String expectedResult = "‘&amp;" + WARNING_TEXT_UNSAFE;
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
            String expectedResult = "‘" + newFolderName + WARNING_TEXT_UNSAFE;
            Assert.assertEquals(expectedResult, checkErrorMessage());
            getDriver().navigate().back();
        }
    }

    @Test
    public void testRenameFolderWithSpaceAsAName() {

        final String[] expectedResult = new String[]{"Error", "No name is specified"};

        createFolder(getDriver(), RANDOM_FOLDER_NAME);

        new FolderConfigPage(getDriver())
                .clickRenameFolder()
                .renameFolder(" ");

        String[] actualResult = new String[]{
                getDriver().findElement(By.xpath("//h1")).getText(),
                getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText()
        };

        Assert.assertEquals(actualResult, expectedResult);
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

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='error']")).getText(), "Nothing seems to match.");
    }
}