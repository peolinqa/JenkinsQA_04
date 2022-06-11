import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteBuildTest extends BaseTest {

    private final static String DASHBOARD_XPATH = "//a[contains(text(),\"Dashboard\")]";
    private final static String BASE_URL = "http:localhost:8080";
    private final static String EV_JOB_NAME = "First Job";
    private final static String DELETE_BUILD_XPATH = "//span[contains(text(),'Delete build ')]/..";
    private final static String BUILD_XPATH = "//div[@class='build-icon']";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String BUILD_HISTORY_XPATH = "//a[@href=\"/view/all/builds\"]";
    private final static String ALL_NAMES_IN_TABLE_XPATH = "//table[@id='projectstatus']/tbody/tr/td[3]/a";


    public void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    public void clickFreestyleProjectItem() {
        getDriver().findElement(By.xpath("//*[contains(text(),\"Freestyle project\")]")).click();
    }

    public void clickOKButton() {
        getDriver().findElement(By.id("ok-button")).click();
    }

    private static final By NAME = By.id("name");

    @BeforeMethod
    public void setUp() {
        deleteJobsWithPrefix(getDriver(), EV_JOB_NAME);
    }

    @Test
    public void testDeleteBuild() {
        createNewJob(EV_JOB_NAME);
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath("//a[@href='job/First%20Job/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/First%20Job/build?delay=0sec']")).click();
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BUILD_XPATH)));
        getDriver().get(BASE_URL + "/job/" + EV_JOB_NAME + "/1/");
        WebElement deleteButton = getDriver().findElement(By.xpath(DELETE_BUILD_XPATH));
        getDriver().get(deleteButton.getAttribute("href"));
        getDriver().findElement(By.xpath(YES_BUTTON_XPATH)).click();

        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        getDriver().findElement(By.xpath(BUILD_HISTORY_XPATH)).click();
        List<String> buildNumbersAfterLastDeleted = getDriver().findElements(By.xpath("//table[@id='projectStatus']/tbody/tr/td[2]/a/span"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertFalse(buildNumbersAfterLastDeleted.contains("//table[@id='projectStatus']//tbody//tr//td[2]//span[contains(text(), 'First Job')]/../../..//td[2]//a"));
        List<String> buildNumbersAfterDeletedOnThePageDashBoard = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertFalse(buildNumbersAfterDeletedOnThePageDashBoard.contains("//table[@id='projectstatus']/tbody//td[3]//span[contains(text(), 'First Job')]/../../..//td[4]/a"));
    }

    public void createNewJob(String jobName) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(jobName);
        clickFreestyleProjectItem();
        clickOKButton();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
    }

    public static void deleteJobsWithPrefix(WebDriver driver, String prefix) {
        driver.findElement(By.xpath(DASHBOARD_XPATH)).click();
        List<String> jobsNames = driver.findElements(By.xpath(ALL_NAMES_IN_TABLE_XPATH))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        jobsNames
                .forEach(jobsName -> {
                    if (jobsName.startsWith(prefix)) {
                        String jobWithPercent = jobsName.replace(" ", "%20");
                        driver.get(BASE_URL + "/job/" + jobWithPercent + "/delete");
                        driver.findElement(By.id("yui-gen1-button")).click();
                    }
                });
    }
}