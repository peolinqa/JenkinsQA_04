import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ViewTest extends BaseTest {

    @Test
    public void testCreateNewView() {
        final String viewName = "Test view 1234567890123456789012345678901234567890";
        final String viewDescription = "My first Test view ;-)";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@title = 'New View']")).click();

        driver.findElement(By.id("name")).sendKeys(viewName);
        driver.findElement(By.xpath("//label[text() = 'List View']")).click();
        driver.findElement(By.id("ok")).click();

        driver.findElement(By.name("description")).sendKeys(viewDescription);
        driver.findElement(By.id("yui-gen13-button")).click();

        Assert.assertEquals(viewName, driver.findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='item']/a[starts-with(@href, '/view')]")).getText());
        Assert.assertEquals(viewDescription, driver.findElement(By.xpath("//div[@id='description']/div[1]")).getText());
    }
}
