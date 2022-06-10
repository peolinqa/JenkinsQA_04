import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class CreateOrganizationFolder_Test extends BaseTest {

    private final String VALID_FOLDER_NAME = "folder1";
    private final By BUTTON_NEW_ITEM = By.linkText("New Item");
    private final By INPUT_ITEM_NAME = By.id("name");
    private final By BUTTON_ORGANIZATION_FOLDER = By.xpath(
            "//ul[@class='j-item-options']/li[@class='jenkins_branch_OrganizationFolder']");
    private final By OK_BUTTON = By.id("ok-button");
    private final By SAVE_BUTTON = By.id("yui-gen17-button");
    private final By FOLDER1_ON_DASHBOARD = By.xpath(
            "//table[@id='projectstatus']/tbody/tr[@id='job_folder1']/td/a[@href='job/folder1/']");
    private final By JENKINS = By.id("jenkins-home-link");

    private void fillNameAndClickOrganizationFolder() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys(VALID_FOLDER_NAME);
        getDriver().findElement(BUTTON_ORGANIZATION_FOLDER).click();
    }

    private void clickOkAndSaveButtons() {
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void deleteFolder() {
        getDriver().findElement(JENKINS).click();
        Actions action = new Actions(getDriver());
        WebElement folder1 = getDriver().findElement(FOLDER1_ON_DASHBOARD);
        action.moveToElement(folder1, 20, 0);
        action.click();
        action.perform();
        getDriver().findElement(By.xpath("//ul[@class='first-of-type']/li/a[@href='/job/folder1/delete']/span"))
                .click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void TC34_002CreateOrganizationFolderSameItemName() {
        fillNameAndClickOrganizationFolder();
        clickOkAndSaveButtons();
        getDriver().findElement(By.linkText("Up")).click();

        Assert.assertTrue(getDriver().findElement(FOLDER1_ON_DASHBOARD)
                .isDisplayed());

        fillNameAndClickOrganizationFolder();
        WebElement error = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "» A job already exists with the name ‘folder1’");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");

        getDriver().findElement(OK_BUTTON).click();
        WebElement error2 = getDriver().findElement(By.id("main-panel"));
        Assert.assertTrue(error2.isDisplayed());
        Assert.assertEquals(error2.getText(), "Error\nA job already exists with the name ‘folder1’");

        deleteFolder();
    }

    @Test
    public void TC34_003CreateDisableOrganizationFolder() {
        fillNameAndClickOrganizationFolder();
        getDriver().findElement(OK_BUTTON).click();
        WebElement prompt1 = getDriver().findElement(By.xpath(
                "//div[@class='jenkins-form-label help-sibling']/a[@tooltip='Help for feature: Display Name']"));
        WebElement prompt2 = getDriver().findElement(By.xpath(
                "//div[@class='jenkins-form-label help-sibling']/a[@tooltip='Help for feature: Script Path']"));

        Assert.assertEquals(prompt1.getAttribute("title"), "Help for feature: Display Name");
        Assert.assertEquals(prompt2.getAttribute("title"), "Help for feature: Script Path");

        getDriver().findElement(By.xpath("//input[@name='_.disabled']")).click();
        getDriver().findElement(SAVE_BUTTON).click();
        WebElement warning = getDriver().findElement(By.id("enable-project"));

        Assert.assertTrue(warning.isDisplayed());
        Assert.assertEquals(warning.getCssValue("color").toString(), "rgba(196, 160, 0, 1)");

        deleteFolder();
    }

    @Test
    public void TC34_004CreateOrganizationFolderWithMetadataFolderIcon() {
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
    public void TC34_005CreateOrganizationFolderAbortCreation() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys("12345Folder67890");
        getDriver().findElement(BUTTON_ORGANIZATION_FOLDER).click();
        getDriver().findElement(By.linkText("Dashboard")).click();

        List<WebElement> foldersNames = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]"));
        StringBuilder result = new StringBuilder();

        for (WebElement name : foldersNames) {
            result.append(name.getText());
        }

        Assert.assertFalse(result.toString().contains("12345Folder67890"));
    }

    @Test
    public void TC34_006CreateOrganizationFolderIncorrectName() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(INPUT_ITEM_NAME).sendKeys("@");
        WebElement error = getDriver().findElement(By.id("itemname-invalid"));

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
    public void TC34_007CreateOrganizationFolderEmptyName() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        getDriver().findElement(BUTTON_ORGANIZATION_FOLDER).click();
        WebElement error = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(error.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(error.getCssValue("color").toString(), "rgba(255, 0, 0, 1)");
        Assert.assertEquals(getDriver().findElement(OK_BUTTON).getAttribute("class").toString(), "disabled");
    }

    @Test
    public void TC34_008CreateOrganizationFolderNavigation() {
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
    public void TC34_009CreateOrganizationFolderWithDisplayName() {
        fillNameAndClickOrganizationFolder();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys("FolderTestDisplayName");
        getDriver().findElement(By.id("yui-gen13-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='notification-bar']/span")).getText(),
                "Saved");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='notification-bar']/span")).getCssValue("color"),
                "rgba(19, 131, 71, 1)");

        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(JENKINS).click();

        List<String> result = new ArrayList<>();
        List<WebElement> namesFoldersFromTable = getDriver().findElements(By.xpath(
                "//table[@id='projectstatus']/tbody/tr/td[3]/a"));

        for (WebElement name : namesFoldersFromTable) {
            result.add(name.getText());
        }

        Assert.assertTrue(result.contains("FolderTestDisplayName"));
        Assert.assertFalse(result.contains(VALID_FOLDER_NAME));

        getDriver().findElement(FOLDER1_ON_DASHBOARD).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Organization Folder']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

}
