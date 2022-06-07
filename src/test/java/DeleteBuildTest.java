import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    private final static String BUILDS_XPATH = "//table[@class='pane jenkins-pane stripped']//a[@class='model-link inside build-link display-name']";
    private final static String DELETE_BUILD_XPATH = "//span[contains(text(),'Delete build ')]/..";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";
    private final static String ALL_NAMES_IN_TABLE_XPATH = "//table[@id='projectstatus']/tbody/tr/td[3]/a/span";

    public void clickNewItem() {
        getDriver().findElement(By.linkText("New Item")).click();
    }
    public void clickFreestyleProjectItem() {getDriver().findElement(By.xpath("//*[contains(text(),\"Freestyle project\")]")).click();}
    public void clickOKButton() {getDriver().findElement(By.id("ok-button")).click();}
    private static final By NAME = By.id("name");

    @Test
    public void testDeleteBuild() {
        createNewJob(EV_JOB_NAME);
        getDriver().findElement(By.xpath("//span[contains(text(),'" + EV_JOB_NAME + "')]")).click();
        List<WebElement> buildsNumbers = getBuildsNumbers();
        WebElement lastBuild = buildsNumbers.get(0);
        String lastBuildNumber = lastBuild.getText();
        getDriver().get(lastBuild.getAttribute("href"));
        WebElement deleteButton = getDriver().findElement(By.xpath(DELETE_BUILD_XPATH));
        getDriver().get(deleteButton.getAttribute("href"));
        getDriver().findElement(By.xpath(YES_BUTTON_XPATH)).click();

        List<String> buildsNumbersAfterLastDeleted = getDriver().findElements(By.xpath(BUILDS_XPATH))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertFalse(buildsNumbersAfterLastDeleted.contains(lastBuildNumber));

        String lastBuildNumberOnly = lastBuildNumber.replace("#", "");
        getDriver().get(BASE_URL + "/job/First Job/" + lastBuildNumberOnly);
        String buildNotFoundText = getDriver().findElement(By.xpath("//body/h2")).getText();
        Assert.assertEquals(buildNotFoundText, "HTTP ERROR 404 Not Found");
        getDriver().get(BASE_URL);
    }

    /**
     * Returns a list of existing builds or starts a new build and then fetches builds list
     *
     * @return List of builds
     */
    private List<WebElement> getBuildsNumbers() {
        List<WebElement> buildsNumbers = getDriver().findElements(By.xpath(BUILDS_XPATH));
        if (buildsNumbers.isEmpty()) {
            getDriver().findElement(By.xpath("//a[@href='/job/First%20Job/build?delay=0sec']")).click();
        }
        return getDriver().findElements(By.xpath(BUILDS_XPATH));
    }

    public void createNewJob(String jobName) {
        clickNewItem();
        getDriver().findElement(NAME).sendKeys(jobName);
        clickFreestyleProjectItem();
        clickOKButton();
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
    }

    @BeforeMethod
    public void deleteNewJob() {
        getDriver().findElement(By.xpath(DASHBOARD_XPATH)).click();
        boolean jobExists = getDriver().findElements(By.xpath(ALL_NAMES_IN_TABLE_XPATH))
                .stream()
                .anyMatch(x -> x.getText().equals(EV_JOB_NAME));
        if (jobExists) {
            String jobWithPercent = EV_JOB_NAME.replace(" ", "%20");
            getDriver().get(BASE_URL + "/job/" + jobWithPercent + "/delete");
            getDriver().findElement(By.id("yui-gen1-button")).click();
        }
    }
}