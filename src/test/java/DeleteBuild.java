import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteBuild extends BaseTest {

    private final static String BASE_URL = "http:localhost:8080";
    private final static String EV_JOB_NAME = "First Job";
    private final static String BUILDS_XPATH = "//table[@class='pane jenkins-pane stripped']//a[@class='model-link inside build-link display-name']";
    private final static String DELETE_BUILD_XPATH = "//span[contains(text(),'Delete build ')]/..";
    private final static String YES_BUTTON_XPATH = "//button[contains(text(),'Yes')]";

    @Test
    public void deleteBuild() {
        getDriver().findElement(By.xpath("//span[contains(text(),'" + EV_JOB_NAME + "')]")).click();
        List<WebElement> buildsNumbers = getBuildsNumbers();
        WebElement lastBuild = buildsNumbers.get(0);
        String lastBuildNumber = lastBuild.getText();
        getDriver().get(lastBuild.getAttribute("href"));
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(driver -> driver.findElement(By.xpath(DELETE_BUILD_XPATH)).isDisplayed());
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
     * @return List of builds
     */
    private List<WebElement> getBuildsNumbers() {
        List<WebElement> buildsNumbers = getDriver().findElements(By.xpath(BUILDS_XPATH));

        if (buildsNumbers.isEmpty()) {
            getDriver().findElement(By.xpath("//a[@href='/job/First%20Job/build?delay=0sec']")).click();
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            wait.until(driver -> driver.findElements(By.xpath(BUILDS_XPATH)).size() > 0);
        }
        return getDriver().findElements(By.xpath(BUILDS_XPATH));
    }
}