import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RusllWebAppTest extends BaseTest {

    @Test
    public void testHomePageMenuStaff() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://kimo76aeoliancafe.wordpress.com/");
        WebElement aboutUsButton = driver.findElement(By
                .xpath("//ul[@id='menu-primary']//a[contains(text(), 'About Us')]"));
        Actions action = new Actions(driver);
        action.moveToElement(aboutUsButton)
                .build()
                .perform();
        action.moveToElement(driver.findElement(
                        By.xpath("//ul[@id='menu-primary']//a[contains(text(), 'Staff')]")))
                .click()
                .build()
                .perform();
        Assert.assertEquals(driver.getTitle(), "Staff – Aeolian Cafe");
        Thread.sleep(2000);
    }
}
