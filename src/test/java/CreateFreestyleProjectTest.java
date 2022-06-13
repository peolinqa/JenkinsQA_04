import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateFreestyleProjectValidName() {

        String textString = "MSTest";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(textString);

        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();

        String actualResult = getDriver().findElement(By.id("main-panel")).findElement(By.linkText("MSTest")).getText();

        Assert.assertEquals(actualResult, textString);
    }

}
