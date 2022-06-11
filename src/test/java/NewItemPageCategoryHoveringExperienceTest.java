import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NewItemPageCategoryHoveringExperienceTest extends BaseTest {

    private final static String BASE_URL = "http://localhost:8080";
    private final static String DASHBOARD_XPATH = "//a[contains(text(),'Dashboard')]";
    private final static String NEW_ITEM_LINK_TEXT = "New Item";
    private final static String ALL_NAMES_IN_TABLE_XPATH = "//table[@id='projectstatus']/tbody/tr/td[3]/a";
    private final static String EV_JOB_NAME = "First Job";
    private final static String OK_BUTTON_ID = "ok-button";
    private final static String YES_DELETE_BUTTON_ID = "yui-gen1-button";
    private final static String JOB_INPUT_NAME_ID = "name";

    private static final String[] PROJECT_TYPES = {
            "Freestyle project",
            "Pipeline",
            "Multi-configuration project",
            "Folder",
            "Multibranch Pipeline",
            "Organization Folder"
    };

    @BeforeMethod
    public void setUp() {
        CreateFreestyleProjectTestEP.deleteJobsWithPrefix(getDriver(), EV_JOB_NAME);
    }

    @Test
    public void testNewItemPageCategoryHoveringExperience() {
        for (int i = 0; i < PROJECT_TYPES.length; i++) {
            String jobName = EV_JOB_NAME + " " + PROJECT_TYPES[i];
            getDriver().findElement(By.linkText(NEW_ITEM_LINK_TEXT)).click();
            getDriver().findElement(By.id(JOB_INPUT_NAME_ID)).sendKeys(jobName);
            clickProjectItem(PROJECT_TYPES[i]);
            clickOKButton();
            getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
            getDriver().get(BASE_URL + "/job/" + jobName.replace(" ", "%20"));
            String deleteProjectName = i == 0 ? "Project" : PROJECT_TYPES[i];
            String deleteProjectXpath = "//span[text()='Delete " + deleteProjectName + "']";
            Assert.assertTrue(getDriver().findElement(By.xpath(deleteProjectXpath)).isDisplayed());
            getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        }
    }

    private void clickOKButton() {
        getDriver().findElement(By.id(OK_BUTTON_ID)).click();
    }

    private void clickProjectItem(String name) {
        WebElement project = getDriver().findElement(By.xpath("//span[text()='" + name + "']/../.."));
        if (!project.isDisplayed())
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", project);
        project.click();
    }
}