import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectConfigurePageTest extends BaseTest {

    private final String PROJECT_NAME = "NewFreestyleProject";
    private final String PROJECT_NAME_XPATH = "//a[@href='job/" + PROJECT_NAME + "/']";

    private void createProject() {

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectName = getDriver().findElement(By.name("name"));
        projectName.sendKeys(PROJECT_NAME);

        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[1]")
        ).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")
        ).click();
    }

    private void checkProjectIsCreated() {

        if (getDriver().findElements(By.xpath(PROJECT_NAME_XPATH)).size() == 0) {
            createProject();
        }
        homePage();
    }

    private void deleteProject() {

        homePage();
        getDriver().findElement(By.xpath(PROJECT_NAME_XPATH)).click();
        getDriver().findElement(By.partialLinkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    private void openConfigurePage() {

        getDriver().findElement(By.xpath(PROJECT_NAME_XPATH)).click();
        getDriver().findElement(By.partialLinkText("Configure")).click();
    }

    private void homePage() {

        getDriver().get("http://localhost:8080/");
    }

    @Test
    public void testConfigureApplyButton() {

        String expectedAlertMessage = "Saved";

        checkProjectIsCreated();
        openConfigurePage();

        getDriver().findElement(By.xpath("//button[contains(text(),'Apply')]")).click();
        String alertMessage = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='notification-bar'][class='notif-alert-success notif-alert-show']")
        )).getText();

        Assert.assertEquals(alertMessage, expectedAlertMessage);

        deleteProject();
    }

    @Test
    public void testConfigureSaveButton() {

        String expectedURL = "http://localhost:8080/job/" + PROJECT_NAME + "/";

        checkProjectIsCreated();
        openConfigurePage();

        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")
        ).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);

        deleteProject();
    }
}
