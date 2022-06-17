import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FreestyleTest extends BaseTest {
    @Test
    public void testUserCanDeleteFreestyleProject() {

        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text() = 'New Item']")).click();
        By.xpath("//input[@id='name']").findElement(getDriver()).sendKeys("project-freestyle");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("New project");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Dashboard']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href='job/project-freestyle/']")).isDisplayed());
        getDriver().findElement(By.xpath("//a[@href='job/project-freestyle/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();
        getDriver().switchTo().alert().accept();

        boolean checkProjectExists;
        try {
            getDriver().findElement(By.linkText("project-freestyle")).isDisplayed();
            checkProjectExists = true;
        } catch (Exception ee) {
            checkProjectExists = false;
        }
        Assert.assertFalse(checkProjectExists);
    }

    private void createProject() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProject");

        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//html/body/div[5]/div/div/div/div/form/div[1]/div[12]/div/div[2]/div[2]/span[1]/span/button")
        ).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testCreateFreestyleProject() {
        String expectedResult = "FirstProject";
        createProject();

        String actualResult = getDriver().findElement(By.xpath("//ul/li/a[@href='/job/FirstProject/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }
}
