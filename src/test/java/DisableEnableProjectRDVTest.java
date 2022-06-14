import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DisableEnableProjectRDVTest extends BaseTest {

    private static String sendKeys = RandomStringUtils.randomAlphabetic(5);

    private void clickNewItem(){
        getDriver().findElement(By.className("task-link-text")).click();
    }

    private void createDisabledFreeStyleProject(){
        getDriver().findElement(By.id("name")).sendKeys(sendKeys);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("disable")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void deleteItem() {
        getDriver().findElement(By.linkText(sendKeys)).click();
        getDriver().findElement(By.linkText("Delete Project")).click();
        getWait20().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    private void clickButtonEnable(){
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testDisabledFreestyleProject() {
        clickNewItem();
        createDisabledFreeStyleProject();

        String actualText = getDriver()
                .findElement(By.id("enable-project"))
                .getText();

        deleteItem();

        Assert.assertTrue(actualText.contains("This project is currently disabled"));
    }

    @Test
    public void testDisabledEnabledFreestyleProject() {
        clickNewItem();
        createDisabledFreeStyleProject();
        clickButtonEnable();

        String actualText = getDriver()
                .findElement(By.id("yui-gen1-button"))
                .getText();

        deleteItem();

        Assert.assertEquals(actualText,"Disable Project");
    }
}