import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjectRDVTest extends BaseTest {
    private void clickNewItem(){
        getDriver().findElement(By.className("task-link-text")).click();
    }

    @Test
    public void testTC_001_003_NoEnterNameFreestyleItem() {
        String expectedText = "» This field cannot be empty, please enter a valid name";

        clickNewItem();
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();

        String actualErrorMessage = getDriver().findElement(By.id("itemname-required")).getText();

        Assert.assertEquals(actualErrorMessage,expectedText);
    }

    @Test
    public void testTC_001_004_EnterSeveralSpaces() {
        String expectedText = "No name is specified";

        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys("    ");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        String actualErrorMessage = getDriver().findElement(
                By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualErrorMessage,expectedText);
    }
}
