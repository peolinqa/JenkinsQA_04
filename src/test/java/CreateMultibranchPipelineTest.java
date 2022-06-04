import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class CreateMultibranchPipelineTest extends BaseTest {

    private final String PROJECT_NAME = "MultibranchPipelineTestProject";
    private final char[] unsafeCharacters = {'!', '@', '#', '$', '%', '^', '&', '*', '[', ']', ';', ':'};

    private void deleteProject() {

        getDriver().findElement(By.partialLinkText(PROJECT_NAME)).click();
        getDriver().findElement(By.partialLinkText("Delete Multibranch Pipeline")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testCreateMultiBranchNegative() {

        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectNameField = getDriver().findElement(By.name("name"));

        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-nested-projects']//li[2]")
        ).click();

        for (char unsafeChar : unsafeCharacters) {
            String expectedError = String.format("» ‘%s’ is an unsafe character", unsafeChar);

            projectNameField.sendKeys(String.format("%s%s", unsafeChar, PROJECT_NAME));
            String actualError = getDriver().findElement(By.id("itemname-invalid")).getText();

            Assert.assertEquals(actualError, expectedError);

            projectNameField.clear();
        }
    }

    @Test
    public void testCreateMultiBranchPositive() {

        String expectedURL = String.format("http://localhost:8080/job/%s/", PROJECT_NAME);

        if (getDriver().findElements(By.partialLinkText(PROJECT_NAME)).size() > 0) {
            deleteProject();
        }

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectNameField = getDriver().findElement(By.name("name"));
        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-nested-projects']//li[2]")
        ).click();
        projectNameField.sendKeys(PROJECT_NAME);

        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")
        ).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
    }

    @Test
    public void testProjectIsOnDashboard() {

        boolean isProjectOnDashboard = getDriver().findElement(
                By.partialLinkText(PROJECT_NAME)
        ).isDisplayed();

        Assert.assertTrue(isProjectOnDashboard);

        deleteProject();
    }
}
