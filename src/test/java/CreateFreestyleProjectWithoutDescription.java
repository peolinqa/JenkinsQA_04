import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

    @AfterMethod
    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testUserCanConfigureNewProject() {
        startToCreateNewFreestyleProject();
        boolean projectConfig = getDriver().findElements(By.cssSelector(".config-section-activator")).size() > 0;

        addDescription();
        String alert = String.valueOf(ExpectedConditions.alertIsPresent());

        completeCreateNewFreestyleProject();
        String description = getDriver().findElement(By.cssSelector(".jenkins-buttons-row")).getText();

        Assert.assertTrue(projectConfig);
        Assert.assertEquals(alert, "alert to be present");
        Assert.assertEquals(description, "Add description");
    }

    @Test
    public void testUserEnableDisableProject() {
        startToCreateNewFreestyleProject();
        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Save']")).click();

        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Disable Project']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//form[contains(text(), 'This project is currently disabled')]")).isDisplayed());

        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Enable']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Build Now']")).isEnabled());
    }
}
