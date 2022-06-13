import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class OrgFolderAddPropChildHealthMetricsTest extends BaseTest {
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
