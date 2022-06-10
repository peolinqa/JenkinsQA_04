import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RenameFreestyleProjectRDVTest extends BaseTest {
    public void deleteItem() {
        getDriver().findElement(By.linkText("Second name")).click();
        getDriver().findElement(By.linkText("Delete Project")).click();

        getWait20().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    private void clickNewItem(){
        getDriver().findElement(By.className("task-link-text")).click();
    }

    @Test
    public void testRenameFreestyleProject() {
        String expectedText = "Project Second name";

        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys("First name");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys("Second name");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        String actualText = getDriver()
                .findElement(By.id("main-panel"))
                .findElement(By.tagName("h1"))
                .getText();

        deleteItem();

        Assert.assertEquals(actualText,expectedText);
    }
}
