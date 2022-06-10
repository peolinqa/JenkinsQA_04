import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AddDescriptionRDVTest extends BaseTest {
    private void clickNewItem(){
        getDriver().findElement(By.className("task-link-text")).click();
    }

    public void deleteItem() throws InterruptedException {
        getDriver().findElement(By.linkText("Test Add Description")).click();
        getDriver().findElement(By.linkText("Delete Project")).click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }


    @Test
    public void testFreestyleProjectAddDescription() throws InterruptedException {
        String expectedDescription = "Test Add Description RDV";

        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys("Test Add Description");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("description")).sendKeys("Test Add Description RDV");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        String actualDescription = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        deleteItem();

        Assert.assertEquals(actualDescription, expectedDescription);
    }

}
