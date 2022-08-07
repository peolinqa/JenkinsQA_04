import model.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import java.util.Random;

public class _FolderTest extends BaseTest {

    private static final String RANDOM_FOLDER_NAME = TestUtils.getRandomStr();
    private static final String RANDOM_FOLDER_NAME1 = TestUtils.getRandomStr();
    private static final String RANDOM_FOLDER_NAME2 = TestUtils.getRandomStr();
    private static final String FOLDER_NAME_FOR_RENAME1 = TestUtils.getRandomStr();
    private static final String FOLDER_DESCRIPTION = "Folder Description";


    protected static final char[] CHARS =
            {',', 39, '`', '~', '-', ' ', '(', ')', '{', '}', '+', '=', '_', '"'};

    private static final String WARNING_TEXT_UNSAFE = "’ is an unsafe character";

    @Test
    public void testCreateFolder() {
        String folderName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME1)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .getProjectName();

        Assert.assertEquals(folderName, RANDOM_FOLDER_NAME1);
    }

    @Test
    public void testFolderIsCreatedWithoutSave() {
        boolean folderIsPresent = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .goHome()
                .checkProjectNameIsPresent(RANDOM_FOLDER_NAME);

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
                .setProjectTypeFolder()
                .getNameErrorText();

            String expectedResult = String.format("» ‘%s%s", invalidSymbols[result], WARNING_TEXT_UNSAFE);

            Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateFolderWithDot() {
        String warningText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(".")
                .setProjectTypeFolder()
                .getNameErrorText();

        Assert.assertEquals(warningText, "» “.” is not an allowed name");
    }

    @Test
    public void testCycleTypeAnItemNameWithValidSpecialCharacters() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

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
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(folderName)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
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
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(RANDOM_FOLDER_NAME2)
                .clickToMoveMousePointer()
                .getNameErrorText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testRenameFolderPositive() {
        String actualResult = new HomePage(getDriver())
                .clickFolderName(RANDOM_FOLDER_NAME1)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .setNewProjectName(FOLDER_NAME_FOR_RENAME1)
                .clickRenameAndGoToProjectPage()
                .getProjectName();

        Assert.assertEquals(actualResult, FOLDER_NAME_FOR_RENAME1);
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive"})
    public void testRenameFolderWithSpaceAsAName() {
        String actualResult = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage()
                .setNewProjectName(" ")
                .clickRenameAndGoToProjectPage()
                .getErrorPageIfPresent()
                .getErrorMessage();

        Assert.assertEquals(actualResult, "No name is specified");
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testRenameFolderPositive", "testRenameFolderWithSpaceAsAName"})
    public void testRenameFolderWithUnsafeCharacters() {
        final String unsafeCharacters = "&.!@#$%^*/|\\:?";

        RenamePage<FolderProjectPage, FolderProjectPageSideMenuFrame> folderForRenameTest = new HomePage(getDriver())
                .clickFolderName(FOLDER_NAME_FOR_RENAME1)
                .getSideMenu()
                .clickMenuRenameAndGoToRenamePage();

        for (int i = 0; i < unsafeCharacters.length(); i++) {
            String newFolderName = unsafeCharacters.substring(i, (i + 1));
            if (newFolderName.equals("&")) {
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToProjectPage()
                        .getErrorPageIfPresent()
                        .getErrorMessage();

                Assert.assertEquals(actualResult, "‘&amp;’ is an unsafe character");
                getDriver().navigate().back();
                continue;
            }
            if (newFolderName.equals(".")) {
                String actualResult = folderForRenameTest
                        .setNewProjectName(newFolderName)
                        .clickRenameAndGoToProjectPage()
                        .getErrorPageIfPresent()
                        .getErrorMessage();
                Assert.assertEquals(actualResult, "“.” is not an allowed name");
                getDriver().navigate().back();
                continue;
            }
            String actualResult = folderForRenameTest
                    .setNewProjectName(newFolderName)
                    .clickRenameAndGoToProjectPage()
                    .getErrorPageIfPresent()
                    .getErrorMessage();
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
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .setFolderDescription(FOLDER_DESCRIPTION)
                .clickFolderDescriptionPreview()
                .getFolderDescriptionPreviewText();

        String folderDescriptionOnFolderPage = new FolderConfigPage(getDriver())
                .saveConfigAndGoToFolderPage()
                .getFolderDescription();

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
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .openFolderMenuSelector(folderName)
                .clickDeleteOnMenuSelector()
                .clickYesButton()
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
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .clickConfigure()
                .clickMetricsButton()
                .clickAddMetricButton()
                .clickMetricsItem()
                .saveConfigAndGoToFolderPage()
                .clickConfigure()
                .clickMetricsButton()
                .getMetricElement()
                .getText();

        Assert.assertEquals(actualResult, "Child item with worst health");
    }

    @Test
    public void testRemoveHealthMetrics() {
        String folderName = TestUtils.getRandomStr();
        try {
            WebElement metric = new HomePage(getDriver())
                    .getSideMenu()
                    .clickMenuNewItem()
                    .setProjectName(folderName)
                    .setProjectTypeFolder()
                    .clickOkAndGoToConfig()
                    .saveConfigAndGoToFolderPage()
                    .clickConfigure()
                    .clickMetricsButton()
                    .clickAddMetricButton()
                    .clickMetricsItem()
                    .saveConfigAndGoToFolderPage()
                    .clickConfigure()
                    .clickMetricsButton()
                    .deleteHealthMetric()
                    .saveConfigAndGoToFolderPage()
                    .clickConfigure()
                    .clickMetricsButton()
                    .getMetricElement();

            Assert.assertThrows(() -> metric.isDisplayed());
        } finally {
            new FolderConfigPage(getDriver())
                    .saveConfigAndGoToFolderPage();
        }
    }

    @Test(dependsOnMethods = "testFolderIsCreatedWithoutSave")
    public void testCreateJobInsideFolder() {
        final String randomJobName = TestUtils.getRandomStr();

        String actualResult = new HomePage(getDriver())
                .clickFolderName(RANDOM_FOLDER_NAME)
                .createJobInsideFolder()
                .setProjectName(randomJobName)
                .setProjectTypeFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFolderPage()
                .clickDashboardButton()
                .clickFolderName(RANDOM_FOLDER_NAME)
                .getJobName();

        Assert.assertEquals(actualResult, randomJobName);
    }
}