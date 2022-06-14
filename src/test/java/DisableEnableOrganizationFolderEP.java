import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DisableEnableOrganizationFolderEP extends BaseTest {
    private final static String DASHBOARD_XPATH = "//a[contains(text(),'Dashboard')]";
    private final static String JOB_INPUT_NAME_ID = "name";
    private final static String EV_JOB_NAME = "First Job";
    private final static String NEW_ITEM_LINK_TEXT = "New Item";
    private final static String DISABLE_XPATH = "//button[text()='Disable Organization Folder']";
    private final static String PROJECT_TYPES = "Organization Folder";
    private final static String ENABLE_XPATH = "//span[@class='first-child']";

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    public void createNewOrganizationFolder() {
        String jobName = EV_JOB_NAME;
        getDriver().findElement(By.linkText(NEW_ITEM_LINK_TEXT)).click();
        getDriver().findElement(By.id(JOB_INPUT_NAME_ID)).sendKeys(jobName);
        NewItemPageCategoryHoveringExperienceTest.clickProjectItem(getDriver(),PROJECT_TYPES);
        clickOKButton();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
    }

    @BeforeMethod
    public void deleteNewJob() {
        DeleteBuildTest.deleteJobsWithPrefix(getDriver(), EV_JOB_NAME);
    }

    @Test
    public void testDisableEnableOrganizationFolder() {
        createNewOrganizationFolder();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[@href='job/First%20Job/']")).click();
        getDriver().findElement(By.xpath(DISABLE_XPATH)).click();
        String pageFoundText = getDriver().findElement(By.xpath("//form[contains(text(),\"This Organization Folder is currently disabled\")]\t")).getText();
        Assert.assertTrue(pageFoundText.contains("This Organization Folder is currently disabled"));
        WebElement iconDisable = getDriver().findElement(By.xpath("//*[@class='icon-folder-disabled icon-xlg']"));
        Assert.assertTrue(iconDisable.isDisplayed());
        getDriver().findElement(By.xpath(ENABLE_XPATH)).click();
        WebElement iconEnable = getDriver().findElement(By.xpath("//*[@class='icon-branch-api-organization-folder icon-xlg']"));
        Assert.assertTrue(iconEnable.isDisplayed());
    }
}