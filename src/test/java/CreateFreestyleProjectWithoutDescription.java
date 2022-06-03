import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjectWithoutDescription extends BaseTest {
    private static final String PROJECT_NAME = "New Freestyle project";

    private void dashboardClick() {
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
    }

    private void startToCreateNewFreestyleProject() {
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void addDescription() {
        getDriver().findElement(By.cssSelector("[name='description']"))
                .sendKeys("This is a description for a Freestyle project");
        dashboardClick();
    }

    private void completeCreateNewFreestyleProject() {
        getDriver().switchTo().alert().dismiss();
        dashboardClick();
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.xpath(String.format("//a[text()='%s']", PROJECT_NAME))).click();
    }

    @Test
    public void testUserCanConfigureNewProject() {
        startToCreateNewFreestyleProject();
        boolean projectConfig = getDriver().findElements(By.cssSelector(".config-section-activator")).size() > 0;

        addDescription();
        String alert = String.valueOf(ExpectedConditions.alertIsPresent());

        completeCreateNewFreestyleProject();
        WebElement description = getDriver().findElement(By.cssSelector(".jenkins-buttons-row"));

        Assert.assertTrue(projectConfig);
        Assert.assertEquals(alert, "alert to be present");
        Assert.assertEquals(description.getText(), "Add description");
    }
}
