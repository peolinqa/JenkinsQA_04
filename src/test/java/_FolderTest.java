import model.*;
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

    private static final String WARNING_TEXT_WITH_DOT = "» “.” is not an allowed name";
    private static final String WARNING_TEXT_UNSAFE = "’ is an unsafe character";

    @Test
    public void testCreateFolder() {

        String folderName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME1)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .getFolderName();

        Assert.assertEquals(folderName, RANDOM_FOLDER_NAME1);
    }

    @Test
    public void testConfigurePage() {

        String actual = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(NAME_FOLDER)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .waitLoadingFolderConfigurePage()
                .getGeneralTabName();

        Assert.assertEquals(actual, "General");
    }

    @Test(dependsOnMethods = "testConfigurePage")
    public void testCreateFolderPositive() {

        HomePage homePage = new HomePage(getDriver());

        Assert.assertTrue(homePage.isItemPresent(NAME_FOLDER));
    }

    @Test
    public void testCycleCreateFolderWithInvalidData() {

        NewItemPage newItemPage = new HomePage(getDriver()).clickNewItem();

        for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
            String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + WARNING_TEXT_UNSAFE;

            String actualResult = newItemPage
                    .setProjectName(Character.toString(INVALID_SYMBOLS[i]))
                    .waitWarningMessage(INVALID_SYMBOLS[i],WARNING_TEXT_UNSAFE)
                    .getNameErrorText();

            Assert.assertEquals(actualResult, expectedResult);

            newItemPage.clearNameText();
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

        NewItemPage newItemPage = new HomePage(getDriver()).clickNewItem();

        for (char x : CHARS){
            String actualResult = newItemPage
                    .setProjectName(Character.toString(x))
                    .getHelpInputText();

            Assert.assertEquals(actualResult, "» Required field");

            newItemPage.clearNameText();
        }
    }

    @Test
    public void testDeleteFolder() {

        final String folderName = TestUtils.getRandomStr();

        String searchResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(folderName)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .clickDeleteFolder()
                .clickYesButton()
                .searchText(folderName)
                .getSearchMessageText();

        Assert.assertEquals(searchResult, "Nothing seems to match.");
    }

    @Test
    public void testCreateFolderWithUnsafeCharacter() {

        ErrorPage errorPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName("TestFolder@Jenkins")
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .getErrorPageIfPresent();

        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessage(), "‘@’ is an unsafe character");
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

        String actualResult = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameFolder()
                .setNewProjectName(" ")
                .clickRenameAndGoToFolder()
                .getErrorPageIfPresent()
                .getErrorMessage();

        Assert.assertEquals(actualResult, "No name is specified");
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
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToFolder()
                        .getErrorPageIfPresent()
                        .getErrorMessage();

                Assert.assertEquals(actualResult, "‘&amp;’ is an unsafe character");
                getDriver().navigate().back();
                continue;
            }
            if (newFolderName.equals(".")) {
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToFolder()
                        .getErrorPageIfPresent()
                        .getErrorMessage();
                Assert.assertEquals(actualResult, "“.” is not an allowed name");
                getDriver().navigate().back();
                continue;
            }
            String actualResult = folderForRenameTest
                    .setNewProjectName(newFolderName)
                    .clickRenameAndGoToFolder()
                    .getErrorPageIfPresent()
                    .getErrorMessage();
            String expectedResult = "‘" + newFolderName + WARNING_TEXT_UNSAFE;

            Assert.assertEquals(actualResult, expectedResult);
            getDriver().navigate().back();
        }
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {

        final String folderDescription = TestUtils.getRandomStr();

        FolderConfigPage setFolderDescriptionOnFolderPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
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

        String searchResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(folderName)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .openFolderMenuSelector(folderName)
                .clickDeleteOnMenuSelector()
                .clickYesButton()
                .searchText(folderName)
                .getSearchMessageText();

        Assert.assertEquals(searchResult, "Nothing seems to match.");
    }
}