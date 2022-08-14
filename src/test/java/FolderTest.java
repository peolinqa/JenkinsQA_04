import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.Random;

public class FolderTest extends BaseTest {

    private static final String RANDOM_FOLDER_NAME = TestUtils.getRandomStr();
    private static final String RANDOM_FOLDER_NAME1 = TestUtils.getRandomStr();
    private static final String RANDOM_FOLDER_NAME2 = TestUtils.getRandomStr();
    private static final String FOLDER_NAME_FOR_RENAME1 = TestUtils.getRandomStr();
    private static final String FOLDER_DESCRIPTION = "Folder Description";


    protected static final char[] CHARS = {',', 39, '`', '~', '-', ' ', '(', ')', '{', '}', '+', '=', '_', '"'};
    private static final String WARNING_TEXT_UNSAFE = "’ is an unsafe character";

    @Test
    public void testCreateFolder() {
        String folderName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME1)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .getProjectName();

        Assert.assertEquals(folderName, RANDOM_FOLDER_NAME1);
    }

    @Test
    public void testFolderIsCreatedWithoutSave() {
        boolean folderIsPresent = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .goHome()
                .isProjectNamePresent(RANDOM_FOLDER_NAME);

        Assert.assertTrue(folderIsPresent);
    }

    @Test
    public void testCycleCreateFolderWithInvalidData() {
        final char[] invalidSymbols = {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};

        int result = new Random().nextInt(invalidSymbols.length);

        String actualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(Character.toString(invalidSymbols[result]))
                .setFolderProjectType()
                .getErrorMsgNameInvalidText();

        String expectedResult = String.format("» ‘%s%s", invalidSymbols[result], WARNING_TEXT_UNSAFE);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateFolderWithDot() {
        String warningText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(".")
                .setFolderProjectType()
                .getErrorMsgNameInvalidText();

        Assert.assertEquals(warningText, "» “.” is not an allowed name");
    }

    @Test
    public void testItemNameWithValidSpecialCharacters() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        for (char x : CHARS) {
            String actualResult = newItemPage
                    .setProjectName(Character.toString(x))
                    .getMessageInputNameDisabled();

            Assert.assertEquals(actualResult, "» Required field");

            newItemPage.clearNameText();
        }
    }

    @Test
    public void testDeleteFolder() {
        final String folderName = TestUtils.getRandomStr();

        String searchResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(folderName)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .assertEquals(FolderProjectPage::getProjectName, folderName)
                .getSideMenu()
                .clickMenuDelete()
                .confirmDeleteAndGoHomePage()
                .searchText(folderName)
                .getSearchMessageText();

        Assert.assertEquals(searchResult, "Nothing seems to match.");
    }

    @Test
    public void testCreateFolderWithUnsafeCharacter() {
        ErrorPage errorPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName("TestFolder@Jenkins")
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .getErrorPageIfPresent();

        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessageText(), "‘@’ is an unsafe character");
    }

    @Test(dependsOnMethods = "testCheckDescriptionInPreviewAndOnTheFolderPage")
    public void testCreateFolderWithTheSameName() {
        String expectedResult = "» A job already exists with the name ‘" + RANDOM_FOLDER_NAME2 + "’";

        String actualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .clickToMoveMousePointer()
                .getErrorMsgNameInvalidText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testRenameFolderPositive() {
        String actualResult = new HomePage(getDriver())
                .clickFolderName(RANDOM_FOLDER_NAME1)
                .getSideMenu()
                .clickMenuRename()
                .setNewProjectName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(actualResult, FOLDER_NAME_FOR_RENAME1);
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive"})
    public void testRenameFolderWithSpaceInName() {
        String actualResult = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .getSideMenu()
                .clickMenuRename()
                .setNewProjectName(" ")
                .clickRenameAndGoToProjectPage()
                .getErrorPageIfPresent()
                .getErrorMessageText();

        Assert.assertEquals(actualResult, "No name is specified");
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive", "testRenameFolderWithSpaceInName"})
    public void testRenameFolderWithUnsafeCharacters() {
        final String unsafeCharacters = "&.!@#$%^*/|\\:?";

        RenamePage<FolderProjectPage, FolderProjectPageSideMenuFrame> folderForRenameTest = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .getSideMenu()
                .clickMenuRename();

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            String newFolderName = unsafeCharacters.substring(i, (i + 1));
            if (newFolderName.equals("&")) {
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToProjectPage()
                        .getErrorPageIfPresent()
                        .getErrorMessageText();

                Assert.assertEquals(actualResult, "‘&amp;’ is an unsafe character");
                getDriver().navigate().back();
                continue;
            }
            if (newFolderName.equals(".")) {
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToProjectPage()
                        .getErrorPageIfPresent()
                        .getErrorMessageText();
                Assert.assertEquals(actualResult, "“.” is not an allowed name");
                getDriver().navigate().back();
                continue;
            }
            String actualResult = folderForRenameTest
                    .setNewProjectName(newFolderName)
                    .clickRenameAndGoToProjectPage()
                    .getErrorPageIfPresent()
                    .getErrorMessageText();
            String expectedResult = "‘" + newFolderName + WARNING_TEXT_UNSAFE;

            Assert.assertEquals(actualResult, expectedResult);
            getDriver().navigate().back();
        }
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {
        String folderDescriptionInPreviewOnFolderConfigPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .setTextareaDescription(FOLDER_DESCRIPTION)
                .clickLinkDescriptionPreview()
                .getDescriptionPreviewText();

        String folderDescriptionOnFolderPage = new FolderConfigPage(getDriver())
                .saveProjectConfiguration()
                .getViewMessageText();

        Assert.assertEquals(folderDescriptionInPreviewOnFolderConfigPage, FOLDER_DESCRIPTION);
        Assert.assertEquals(folderDescriptionOnFolderPage, FOLDER_DESCRIPTION);
    }

    @Test
    public void testDeleteFolderFromTheTopMenu() {
        final String folderName = TestUtils.getRandomStr();

        String searchResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(folderName)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .clickFolderDropDownMenu(folderName)
                .clickMenuSelectorDeleteFolder()
                .confirmDeleteAndGoHomePage()
                .searchText(folderName)
                .getSearchMessageText();

        Assert.assertEquals(searchResult, "Nothing seems to match.");
    }

    @Test
    public void testAddHealthMetrics() {
        final String folderName = TestUtils.getRandomStr();

        String actualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(folderName)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .getSideMenu()
                .clickMenuConfigure()
                .clickHealthMetricsButton()
                .clickAddMetricButton()
                .clickChildItem()
                .saveProjectConfiguration()
                .getSideMenu()
                .clickMenuConfigure()
                .clickHealthMetricsButton()
                .getChildItemText();

        Assert.assertEquals(actualResult, "Child item with worst health");
    }

    @Test
    public void testRemoveHealthMetrics() {
        String folderName = TestUtils.getRandomStr();
        boolean isAddMetricDisplayed = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(folderName)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .clickHealthMetricsButton()
                .clickAddMetricButton()
                .clickChildItem()
                .saveProjectConfiguration()
                .getSideMenu()
                .clickMenuConfigure()
                .clickHealthMetricsButton()
                .clickDeleteChildItem()
                .saveProjectConfiguration()
                .getSideMenu()
                .clickMenuConfigure()
                .clickHealthMetricsButton()
                .isAddMetricDisplayed();

        Assert.assertTrue(isAddMetricDisplayed);
    }

    @Test(dependsOnMethods = "testFolderIsCreatedWithoutSave")
    public void testCreateJobInsideFolder() {
        final String randomJobName = TestUtils.getRandomStr();

        String actualResult = new HomePage(getDriver())
                .clickFolderName(RANDOM_FOLDER_NAME)
                .createJobInsideFolder()
                .setProjectName(randomJobName)
                .setFolderProjectType()
                .clickOkAndGoToConfig()
                .saveProjectConfiguration()
                .clickLinkDashboard()
                .clickFolderName(RANDOM_FOLDER_NAME)
                .getJobNameText();

        Assert.assertEquals(actualResult, randomJobName);
    }
}
