import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DashboardDropDownItemsTest extends BaseTest {
    @Test
    public void DropDownItemsTest () {
        Actions hover = new Actions(getDriver());
        hover.moveToElement(getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]"))).build().perform();
        hover.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']"))).click().build().perform();

        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='New Item']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='People']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Build History']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='My Views']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='New View']")).isDisplayed());
    }

}
