import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;


public class DeleteFreestyleProject extends BaseTest {

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

        List<WebElement> titles = getDriver().findElements(
                By.xpath("//a[contains(@class,'model-link') and contains(@href,'job/')]"));

        for (WebElement projects : titles) {

            Assert.assertFalse(projects.getAttribute("href").contains("project-freestyle"));
        }
    }
}

