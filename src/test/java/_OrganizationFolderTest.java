import model.HomePage;
import model.NewItemPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import java.util.HashMap;
import java.util.List;

public class _OrganizationFolderTest extends BaseTest {

    private final String VALID_FOLDER_NAME1 = "Organization Test";
    private final String VALID_FOLDER_NAME2 = "folder1";
    private static final String DISABLED_FOLDER_NAME = "DisabledFolderName";
    private final By BUTTON_NEW_ITEM = By.linkText("New Item");
    private final By INPUT_ITEM_NAME = By.id("name");
    private final By BUTTON_ORGANIZATION_FOLDER = By.xpath(
            "//ul[@class='j-item-options']/li[@class='jenkins_branch_OrganizationFolder']");
    private final By OK_BUTTON = By.id("ok-button");
    private final By SAVE_BUTTON = By.id("yui-gen17-button");
    private final By YES_BUTTON = By.id("yui-gen1-button");
    private final By FOLDER_ON_DASHBOARD = By.xpath(
            "//table[@id='projectstatus']/tbody/tr[@id='job_" + VALID_FOLDER_NAME2
                    + "']/td/a[@href='job/" + VALID_FOLDER_NAME2 + "/']");
    private final By JENKINS = By.id("jenkins-home-link");

    private void fillNameAndClickOrganizationFolder() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys(VALID_FOLDER_NAME2);
        getDriver().findElement(BUTTON_ORGANIZATION_FOLDER).click();
    }

    private void clickOkAndSaveButtons() {
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void deleteFolder() {
        getDriver().findElement(JENKINS).click();
        WebElement folder1 = getDriver().findElement(FOLDER_ON_DASHBOARD);
        getActions().moveToElement(folder1, 20, 0).pause(500).click().build().perform();
        getDriver().findElement(By.xpath("//ul[@class='first-of-type']/li/a[@href='/job/"
                        + VALID_FOLDER_NAME2 + "/delete']/span"))
                .click();
        getDriver().findElement(YES_BUTTON).click();
    }

    private void clickMetricsButton() {
        By metricsButtonBy = By.xpath("//button[@id='yui-gen12-button']");
        boolean success = false;
        int maxTries = 0;
        while (!success) {
            try {
                getWait5().until(ExpectedConditions.elementToBeClickable(metricsButtonBy)).click();
                success = true;
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                if (++maxTries > 3) {
                    throw e;
                }
            }
        }
    }

    @Ignore
    @Test
    public void createOrganizationFolderTest() {
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME1)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_NAME1);
    }

    @Ignore
    @Test(dependsOnMethods = "createOrganizationFolderTest")
    public void renameOrganizationFolderTest() {
        String projectName = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME1)
                .renameOrganizationFolder()
                .setNewProjectName(VALID_FOLDER_NAME2)
                .clickRenameAndGoToOrganizationFolder()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_NAME2);
    }

    @Ignore
    @Test(dependsOnMethods = "renameOrganizationFolderTest")
    public void createOrganizationFolderSameItemNameTest() {
        boolean isDisplayedNameError = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME2)
                .setProjectTypeOrganizationFolder()
                .isDisplayedNameError();

        NewItemPage newItemPage = new NewItemPage(getDriver());

        Assert.assertTrue(isDisplayedNameError);
        Assert.assertEquals(newItemPage.getNameErrorText(),
                "» A job already exists with the name ‘" + VALID_FOLDER_NAME2 + "’");
        Assert.assertEquals(newItemPage.getNameErrorScc("color").toString(),
                "rgba(255, 0, 0, 1)");
    }

    @Ignore
    @Test(dependsOnMethods = {"createOrganizationFolderTest", "renameOrganizationFolderTest"})
    public void deleteOrganizationFolderTest() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME2)
                .deleteOrganizationFolder()
                .deleteOrganizationFolderAndGoHomePage()
                .getTextFolderNamesOnDashboard();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_NAME2));
    }

    @Test
    public void createDisableOrganizationFolderTest() {

        HashMap<String, String> warningMessage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(DISABLED_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickDisableCheckBox()
                .saveConfigAndGoToProject()
                .getDisabledProjectWarningMessage();

        Assert.assertEquals(warningMessage.get("Warning Message"), "This Organization Folder is currently disabled");
        Assert.assertEquals(warningMessage.get("Message Color"), "rgba(196, 160, 0, 1)");
    }

    @Test
    public void createOrganizationFolderWithMetadataFolderIconTest() {
        fillNameAndClickOrganizationFolder();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.xpath(
                "//select[@class='setting-input dropdownList']/option[text()='Default Icon']/ancestor::select")).click();
        getDriver().findElement(By.xpath(
                "//select[@class='setting-input dropdownList']/option[text()='Metadata Folder Icon']")).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(JENKINS).click();
        WebElement icon = getDriver().findElement(By.xpath(
                "//tr[@id='job_folder1']/td/div[@class='jenkins-table__cell__button-wrapper']/img"));

        Assert.assertEquals(icon.getAttribute("class").toString(),
                "icon-branch-api-organization-folder icon-lg");

        deleteFolder();
    }

    @Test
    public void createOrganizationFolderAbortCreationTest() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME2)
                .setProjectTypeOrganizationFolder()
                .goHome().getTextFolderNamesOnDashboard();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_NAME2));
    }

    @Test
    public void createOrganizationFolderIncorrectNameTest() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);

        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys("@");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid")));
        Assert.assertEquals(error.getText(), "» ‘@’ is an unsafe character");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(OK_BUTTON).getAttribute("class").toString(), "disabled");

        getDriver().findElement(INPUT_ITEM_NAME).clear();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys("%");
        Assert.assertEquals(error.getText(), "» ‘%’ is an unsafe character");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(OK_BUTTON).getAttribute("class").toString(), "disabled");

        getDriver().findElement(INPUT_ITEM_NAME).clear();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys("&");
        Assert.assertEquals(error.getText(), "» ‘&’ is an unsafe character");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(OK_BUTTON).getAttribute("class").toString(), "disabled");
    }

    @Test
    public void createOrganizationFolderEmptyNameTest() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(BUTTON_ORGANIZATION_FOLDER).click();
        WebElement error = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(error.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(OK_BUTTON).getAttribute("class").toString(), "disabled");
    }

    @Test
    public void createOrganizationFolderNavigationTest() {
        fillNameAndClickOrganizationFolder();
        getDriver().findElement(OK_BUTTON).click();

        getDriver().findElement(By.xpath("//div[@class='tabBar config-section-activators']/div[text()='Projects']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@colspan='4']/div[text()='Projects']")).isDisplayed());

        getDriver().findElement(By.xpath(
                "//div[@class='tabBar config-section-activators']/div[text()='Health metrics']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@colspan='4']/div[text()='Health metrics']"))
                .isDisplayed());

        getDriver().findElement(By.xpath(
                        "//div[@class='tabBar config-section-activators']/div[text()='Automatic branch project triggering']"))
                .click();
        Assert.assertTrue(getDriver().findElement(By.xpath(
                "//div[@colspan='4']/div[text()='Automatic branch project triggering']")).isDisplayed());

        getDriver().findElement(SAVE_BUTTON).click();
        deleteFolder();
    }

    @Test
    public void createOrganizationFolderWithDisplayNameTest() {
        fillNameAndClickOrganizationFolder();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys(VALID_FOLDER_NAME1);
        getDriver().findElement(By.id("yui-gen13-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='notification-bar']/span")).getText(),
                "Saved");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='notification-bar']/span")).getCssValue("color"),
                "rgba(19, 131, 71, 1)");

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(JENKINS).click();

        List<String> result = TestUtils.getTextFromList(getDriver(), By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a"));
        Assert.assertTrue(result.contains(VALID_FOLDER_NAME1));
        Assert.assertFalse(result.contains(VALID_FOLDER_NAME2));

        getDriver().findElement(FOLDER_ON_DASHBOARD).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Organization Folder']")).click();
        getDriver().findElement(YES_BUTTON).click();
    }

    @Test
    public void testUserCanAddProperties() {
        getDriver().findElement(By.xpath("//span[text() = 'New Item']")).click();
        By.xpath("//input[@id='name']").findElement(getDriver()).sendKeys("Folder");
        getDriver().findElement(By.xpath("//span[text()='Organization Folder']/..")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys("New project");
        getDriver().findElement(
                By.xpath("//div[text() = 'Child Health metrics']")).click();
        clickMetricsButton();
        getDriver().findElement(By.id("yui-gen13-button")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//span[text()='Configure the project']")).click();
        List<WebElement> pro = getDriver().findElements(By.className("tab"));
        pro.get(6).click();
        clickMetricsButton();
        WebElement actualResult = getDriver().findElement(
                By.xpath("//div[@descriptorid='com.cloudbees.hudson.plugins.folder.health.WorstChildHealthMetric']"));
        Assert.assertTrue(actualResult.isDisplayed());
    }
}
