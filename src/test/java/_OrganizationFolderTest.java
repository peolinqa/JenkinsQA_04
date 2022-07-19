import model.HomePage;
import model.NewItemPage;
import model.OrganizationFolderConfigPage;
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

    private static final String VALID_FOLDER_NAME = TestUtils.getRandomStr(5);
    private static final String VALID_FOLDER_RENAME = TestUtils.getRandomStr(5);
    private static final String VALID_FOLDER_NAME1 = TestUtils.getRandomStr();
    private static final String VALID_FOLDER_NAME2 = TestUtils.getRandomStr();
    private static final String DISABLED_FOLDER_NAME = TestUtils.getRandomStr();

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

    private void deleteFolder() {
        getDriver().findElement(JENKINS).click();
        WebElement folder1 = getDriver().findElement(FOLDER_ON_DASHBOARD);
        getActions().moveToElement(folder1, 20, 0).pause(500).click().build().perform();
        getDriver().findElement(By.xpath("//ul[@class='first-of-type']/li/a[@href='/job/"
                        + VALID_FOLDER_NAME2 + "/delete']/span"))
                .click();
        getDriver().findElement(YES_BUTTON).click();
    }

    @Test
    public void testCreateOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        String projectName = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_NAME)
                .renameOrganizationFolder()
                .setNewProjectName(VALID_FOLDER_RENAME)
                .clickRenameAndGoToOrganizationFolder()
                .getProjectName();

        Assert.assertEquals(projectName, VALID_FOLDER_RENAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testRenameOrganizationFolder")
    public void testCreateOrganizationFolderSameItemName() {
        boolean isDisplayedNameError = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_RENAME)
                .setProjectTypeOrganizationFolder()
                .isDisplayedNameError();

        NewItemPage newItemPage = new NewItemPage(getDriver());

        Assert.assertTrue(isDisplayedNameError);
        Assert.assertEquals(newItemPage.getNameErrorText(),
                "» A job already exists with the name ‘" + VALID_FOLDER_RENAME + "’");
        Assert.assertEquals(newItemPage.getNameErrorCss("color").toString(),
                "rgba(255, 0, 0, 1)");
    }

    @Test(dependsOnMethods = {"testRenameOrganizationFolder"})
    public void testDeleteOrganizationFolder() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickOrganizationFolderName(VALID_FOLDER_RENAME)
                .deleteOrganizationFolder()
                .deleteOrganizationFolderAndGoHomePage()
                .getTextFolderNamesOnDashboard();

        Assert.assertFalse(textFolderNames.contains(VALID_FOLDER_RENAME));
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

    @Test(dependsOnMethods = {"testCreateOrganizationFolder", "testRenameOrganizationFolder", "testDeleteOrganizationFolder"})
    public void testCreateOrganizationFolderWithMetadataFolderIcon() {
        String projectIcon = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .clickAppearanceDropDownList()
                .selectOptionMetadataFolderIcon()
                .saveConfigAndGoToProject()
                .clickJenkinsIconAndGoToHomePage()
                .getProjectIconByName(VALID_FOLDER_NAME)
                .getAttribute("class");

        Assert.assertEquals(projectIcon,
                "icon-branch-api-organization-folder icon-lg");
    }

    @Test(dependsOnMethods = "testDeleteOrganizationFolder")
    public void testCreateOrganizationFolderAbortCreation() {
        List<String> textFolderNames = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME2)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .goHome().getTextFolderNamesOnDashboard();

        Assert.assertTrue(textFolderNames.contains(VALID_FOLDER_NAME2));
    }

    @Ignore
    @Test
    public void testCreateOrganizationFolderIncorrectName() {
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
    public void createOrganizationFolderEmptyName() {
        NewItemPage<OrganizationFolderConfigPage> newItemPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeOrganizationFolder();

        Assert.assertEquals(newItemPage.getErrorNameRequiredText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(newItemPage.getNameErrorCss("color"), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(newItemPage.getAttributeOkButton("class"), "disabled");
    }

    @Ignore
    @Test
    public void testCreateOrganizationFolderNavigation() {
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
    public void testCheckNotificationAfterClickApply() {
        OrganizationFolderConfigPage organizationFolderConfigPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(VALID_FOLDER_NAME1)
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .inputDisplayNameField(VALID_FOLDER_NAME2)
                .clickApply();

        Assert.assertEquals(organizationFolderConfigPage.getTextFromNotification(), "Saved");
        Assert.assertEquals(organizationFolderConfigPage.getClassAttributeFromNotification(), "notif-alert-success notif-alert-show");
        Assert.assertEquals(organizationFolderConfigPage.getColorValueAttributeFromNotification(), "rgba(19, 131, 71, 1)");
    }

    @Test(dependsOnMethods = "testCheckNotificationAfterClickApply")
    public void testCheckNewDisplayNameOnDashboard() {
        List<String> result = new HomePage(getDriver()).getTextFolderNamesOnDashboard();

        Assert.assertFalse(result.contains(VALID_FOLDER_NAME1));
        Assert.assertTrue(result.contains(VALID_FOLDER_NAME2));
    }

    @Test
    public void testUserCanAddProperties() {
        boolean actualResult = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectTypeOrganizationFolder()
                .clickOkAndGoToConfig()
                .enterDescription("New project")
                .clickChildHealthMetricsTab()
                .clickMetricsButton()
                .clickApplyButton()
                .saveConfigAndGoToProject()
                .clickConfigureProjectButton()
                .clickPropertiesTab()
                .clickMetricsButton()
                .checkChildMetricsIsDisplayed();

        Assert.assertTrue(actualResult);
    }
}
