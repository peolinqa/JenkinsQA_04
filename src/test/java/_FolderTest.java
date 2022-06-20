import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import java.util.List;

public class _FolderTest extends BaseTest {

    private static final String NAME_FOLDER = "Configure";
    private static final char[] INVALID_SYMBOLS =
            {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};
    protected static final char[] CHARS =
            {',', 39, '`', '~', '-',' ','(',')','{','}','+','=', '_', '"'};
    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String FOLDERS_NAMES_XPATH = "//*[contains(text(),\"Folder\")]";
    private final static String TEST_FOLDER_NAME = "First Job";
    private final static String DELETE_FOLDER_XPATH = "//span[text()='Delete Folder']";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String MY_VIEWS_XPATH = "//a[@href='/me/my-views']";
    private final static String SEARCH_XPATH = "//*[@id=\"search-box\"]\t";
    private static final By NAME = By.id("name");
    private final String FOLDER_NAME = "genashepel";
    private final String NEW_ITEM = "//span[@class='task-link-wrapper ']/a[@href='/view/all/newJob']";
    private static final By INPUT_LINE = By.id("name");
    private static final String F_NAME = "ProjectsSg28832842";

    private void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    private void clickFolderItem() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
    }

    private void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void createFolderWithoutSaveButton(String folderName) {
      clickNewItem();
      getDriver().findElement(NAME).sendKeys(folderName);
      clickFolderItem();
      clickOKButton();
  }

    private void clickJenkinsHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private static void createFolder(WebDriver driver, String folderName) {
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(NAME).sendKeys(folderName);
        driver.findElement(By.xpath("//span[text()='Folder']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("yui-gen6-button")).click();
    }

    private boolean isFolderPresent(String name) {

    boolean isPresent = false;

    List<WebElement> projectsOnDashboard = getDriver().findElements(
            By.xpath("//table[@id='projectstatus']//tbody//td[3]"));
    for (WebElement jobs : projectsOnDashboard) {
      if (jobs.getText().contains(name)) {
        isPresent= true;
      }
    }

    return isPresent;
  }

    private void deleteFolder(String nameFolder) {
        clickJenkinsHome();
        nameFolder.replace(" ","%20");
        getDriver().findElement(By.xpath("//a[@href='job/" + nameFolder + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Folder')]")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private String createRandomName() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(7, nameSubstrate);
    }

    private void сreateFolderForRename(String folderName) {
        getDriver().findElement(By.partialLinkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void clickMenuRenameFolder(String folderName) {
        Actions renameFolder = new Actions(getDriver());
        renameFolder.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='job/" + folderName + "/']")))
                .build().perform();
        renameFolder.moveToElement(getDriver().findElement(
                        By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        renameFolder.moveToElement(getDriver().findElement(
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

    private void deleteFolderName(String name) {
        if (name != null && !name.equals("")) {
            getDriver().findElement(By.xpath("//li[@class='item']/a")).click();
            getDriver().findElement(By.xpath("//td/a[@href='job/".concat(FOLDER_NAME).concat("/']"))).click();
            getDriver()
                    .findElement(By.xpath("//span[@class='task-link-wrapper ']/a[@title='Delete Folder']"))
                    .click();
            getDriver().findElement(By.id("yui-gen1-button")).click();
        }
    }

    private boolean checkNameforNewFolder(String name) {

        getDriver().findElement(By.xpath("//li[@class='item']/a")).click();

        List<WebElement> getElementsDashboard = getDriver()
                .findElements(By.xpath("//table[@id='projectstatus']/tbody"));

        if (getElementsDashboard != null && getElementsDashboard.size() != 0) {
            for (WebElement element : getElementsDashboard) {
                if (element.getText().contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deleteFolderFromSideMenu() {
        getDriver().findElement(By.xpath("//a[@href='/job/".concat(F_NAME).concat("/delete']"))).click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();
    }

    @Test
    public void testConfigurePage () {

      final String expected = "General";

      createFolderWithoutSaveButton(NAME_FOLDER);
      getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins")));

      WebElement actual = getDriver().findElement(
              By.xpath("//div[@class='jenkins-config-widgets']//div[text()='General']")
      );

      Assert.assertEquals(actual.getText(), expected);
    }

    @Test(dependsOnMethods = {"testConfigurePage"})
    public void testCreateFolderPositive () {

      Assert.assertTrue(isFolderPresent(NAME_FOLDER));

      deleteFolder(NAME_FOLDER);
    }

    @Test
    public void testCycleCreateFolderWithInvalidData () {

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
    public void testCycleTypeAnItemNameWithValidSpecialCharacters() {
        getDriver().findElement(By.className("task-link-text")).click();

        WebElement text = getDriver().findElement(By.className("input-help"));

        for (char x : CHARS) {
            getDriver().findElement(By.id("name")).sendKeys(Character.toString(x));

            getWait5().until(ExpectedConditions.textToBePresentInElement(text,
                    "» Required field"));

            Assert.assertEquals(text.getText(), "» Required field");

            getDriver().findElement(By.id("name")).clear();
        }
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

        createFolder(getDriver(), nameFolder);
        clickJenkinsHome();
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

    @Test
    public void testCheckButtonSave() {

        String expectedResult = "genashepel_folder";

        if (checkNameforNewFolder(FOLDER_NAME)) {
            deleteFolderName(FOLDER_NAME);
        }

        getDriver().findElement(By.xpath(NEW_ITEM)).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver()
                .findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main'] /input"))
                .sendKeys("genashepel_folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        String actualResult = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckButtonApply(){

        String expectedResult = "notif-alert-show";
        if (checkNameforNewFolder(FOLDER_NAME)) {
            deleteFolderName(FOLDER_NAME);
        }

        getDriver().findElement(By.xpath(NEW_ITEM)).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver()
                .findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen4-button")).click();

        String allert = getDriver().findElement(By.id("notification-bar")).getAttribute("class");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notif-alert-clear")));

        Assert.assertTrue(allert.contains(expectedResult));
    }

    @Test
    public void testRenameFolderPositive() {

        String randomFolderName = createRandomName();
        String newRandomFolderName = createRandomName();

        сreateFolderForRename(randomFolderName);
        clickJenkinsHome();
        clickMenuRenameFolder(randomFolderName);
        setNewFolderName(newRandomFolderName);

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(newRandomFolderName, actualResult);

        deleteFolder(newRandomFolderName);
    }

    @Test
    public void testRenameFolderWithUnsafeCharacters() {

        String unsafeCharacters = "&.!@#$%^*/|\\:?";
        String folderName = createRandomName();

        сreateFolderForRename(folderName);
        clickJenkinsHome();
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

        deleteFolder(folderName);
    }

    @Test
    public void testRenameFolderWithSpaceAsAName() {

        String folderName = createRandomName();
        String newFolderName = " ";
        String[] expectedResult = new String[] {"Error", "No name is specified"};

        сreateFolderForRename(folderName);
        clickJenkinsHome();
        clickMenuRenameFolder(folderName);
        setNewFolderName(newFolderName);

        String[] actualResult = new String[] {
                getDriver().findElement(By.xpath("//h1")).getText(),
                getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText()
        };

        clickJenkinsHome();
        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(expectedResult, actualResult);
        asserts.assertTrue(getDriver().findElement(
                By.xpath("//a[@href='job/" + folderName + "/']")
        ).isDisplayed());
        asserts.assertAll();

        deleteFolder(folderName);
    }

    @Test
    public void testCheckDescriptionInPreviewAndOnTheFolderPage() {
        final String expectedResult = "General";

        clickNewItem();
        getDriver().findElement(INPUT_LINE).sendKeys(F_NAME);
        getDriver().findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        clickOKButton();
        getDriver().findElement(By.name("_.description")).sendKeys("General");
        getDriver().findElement(By.className("textarea-show-preview")).click();
        String actualPreview = getDriver().findElement(By.className("textarea-preview")).getText();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        String actualFolderPage = getDriver().findElement(By.id("view-message")).getText();

        Assert.assertEquals(actualPreview, expectedResult);
        Assert.assertEquals(actualFolderPage, expectedResult);

        deleteFolderFromSideMenu();
    }
}