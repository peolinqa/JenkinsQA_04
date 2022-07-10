import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;

import static runner.ProjectUtils.ProjectType.Folder;

public class _FolderTest extends BaseTest {

    private static final String NAME_FOLDER = "Configure";
    private static final String RANDOM_FOLDER_NAME1 = TestUtils.getRandomStr();
    private static final String FOLDER_NAME_FOR_RENAME1 = TestUtils.getRandomStr();
    private static final String RANDOM_FOLDER_NAME2 = TestUtils.getRandomStr();

    private static final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};
    protected static final char[] CHARS =
            {',', 39, '`', '~', '-', ' ', '(', ')', '{', '}', '+', '=', '_', '"'};

    private static final By NAME = By.id("name");
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
        getActions().moveToElement(getDriver().findElement((
                By.xpath("//a[@href='/job/" + folderName + "/']")))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/" + folderName + "/delete']")));
        getDriver().findElement(By.xpath("//a[@href='/job/" + folderName + "/delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testCreateFolder() {

        String folderName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME1)
                .setProjectType(Folder)
                .createAndGoToFolderConfigPage()
                .saveConfigAndGoToFolderPage()
                .getFolderName();

        Assert.assertEquals(folderName, RANDOM_FOLDER_NAME1);
    }

    @Test
    public void testConfigurePage() {

        String actual = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(NAME_FOLDER)
                .setProjectType(Folder)
                .createAndGoToFolderConfigPage()
                .waitLoadingFolderConfigurePage()
                .getGeneralTabName();

        Assert.assertEquals(actual, "General");
    }

    @Test(dependsOnMethods = "testConfigurePage")
    public void testCreateFolderPositive() {

        Assert.assertTrue(isFolderPresent(NAME_FOLDER));
    }

    @Test
    public void testCycleCreateFolderWithInvalidData() {

        new HomePage(getDriver()).clickNewItem();
        NewItemPage newItemPage = new NewItemPage(getDriver());

        for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
            String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + WARNING_TEXT_UNSAFE;

            newItemPage
                    .setProjectName(Character.toString(INVALID_SYMBOLS[i]))
                    .waitWarningMessage(INVALID_SYMBOLS[i],WARNING_TEXT_UNSAFE)
                    .checkErrorMessage(expectedResult).clearNameText();
        }
    }

    @Test
    public void testCreateFolderWithDot() {

        new HomePage(getDriver()).clickNewItem();

        String warningText = new NewItemPage(getDriver())
                .setProjectName(".")
                .waitDotWarningMessage()
                .getNameErrorText();

        Assert.assertEquals(warningText, WARNING_TEXT_WITH_DOT);
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

        final String folderName = TestUtils.getRandomStr();

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(folderName)
                .setProjectType(Folder)
                .createAndGoToFolderConfigPage()
                .saveConfigAndGoToFolderPage()
                .clickDeleteFolder()
                .clickYesButton();

        String searchResult = new HomePage(getDriver())
                .searchText(folderName)
                .getSearchMessageText();

        Assert.assertEquals(searchResult, "Nothing seems to match.");
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

    @Test(dependsOnMethods = "testCheckDescriptionInPreviewAndOnTheFolderPage")
    public void testCreateFolderWithTheSameName() {

        String expectedResult = "» A job already exists with the name ‘" + RANDOM_FOLDER_NAME2 + "’";

        String actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .clickToMoveMousePointer()
                .getNameErrorText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testRenameFolderPositive() {

        String actualResult = new HomePage(getDriver())
                .clickFolderName(RANDOM_FOLDER_NAME1)
                .clickRenameFolder()
                .setNewProjectName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameAndGoToFolder()
                .getFolderName();

        Assert.assertEquals(actualResult, FOLDER_NAME_FOR_RENAME1);
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive"})
    public void testRenameFolderWithSpaceAsAName() {

        ErrorPage errorPage = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameFolder()
                .setNewProjectName("   ")
                .clickRenameAndGoToFolder()
                .getErrorPageIfPresent();

        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive", "testRenameFolderWithSpaceAsAName"})
    public void testRenameFolderWithUnsafeCharacters() {

        final String unsafeCharacters = "&.!@#$%^*/|\\:?";

        RenamePage folderForRenameTest = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameFolder();

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            String newFolderName = unsafeCharacters.substring(i, (i + 1));
            if (newFolderName.equals("&")) {
                ErrorPage errorPage = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToFolder()
                        .getErrorPageIfPresent();
                Assert.assertNotNull(errorPage);
                Assert.assertEquals(errorPage.getErrorMessage(), "‘&amp;’ is an unsafe character");
                getDriver().navigate().back();
                continue;
            }
            if (newFolderName.equals(".")) {
                ErrorPage errorPage = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToFolder()
                        .getErrorPageIfPresent();
                Assert.assertNotNull(errorPage);
                Assert.assertEquals(errorPage.getErrorMessage(), "“.” is not an allowed name");
                getDriver().navigate().back();
                continue;
            }
            ErrorPage errorPage = folderForRenameTest
                    .setNewProjectName(newFolderName)
                    .clickRenameAndGoToFolder()
                    .getErrorPageIfPresent();
            Assert.assertNotNull(errorPage);
            Assert.assertEquals(errorPage.getErrorMessage(), "‘" + newFolderName + WARNING_TEXT_UNSAFE);
            getDriver().navigate().back();
        }
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {

        final String folderDescription = TestUtils.getRandomStr();

        FolderConfigPage setFolderDescriptionOnFolderPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .setProjectType(Folder)
                .createAndGoToFolderConfigPage()
                .setFolderDescription(folderDescription);

        String folderDescriptionInPreviewOnFolderConfigPage = setFolderDescriptionOnFolderPage
                .clickFolderDescriptionPreview()
                .getFolderDescriptionPreviewText();

        String folderDescriptionOnFolderPage = setFolderDescriptionOnFolderPage
                .saveConfigAndGoToFolderPage()
                .getFolderDescription();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(folderDescriptionInPreviewOnFolderConfigPage, folderDescription);
        asserts.assertEquals(folderDescriptionOnFolderPage, folderDescription);
        asserts.assertAll();
    }

    @Test
    public void testDeleteFolderFromTheTopMenu() {

        final String folderName = TestUtils.getRandomStr();
        createFolderWithoutSaveButton(folderName);
        deleteFolderFromTopMenu(folderName);
        getDriver().findElement(By.id("search-box")).sendKeys(folderName.concat("\n"));

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@class='error']")).getText(), "Nothing seems to match.");
    }
}