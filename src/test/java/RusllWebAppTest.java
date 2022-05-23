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
        getDriver().get("https://kimo76aeoliancafe.wordpress.com/");
        WebElement aboutUsButton = getDriver().findElement(By
                .xpath("//ul[@id='menu-primary']//a[contains(text(), 'About Us')]"));
        Actions action = new Actions(getDriver());
        action.moveToElement(aboutUsButton)
                .build()
                .perform();
        action.moveToElement(getDriver().findElement(
                        By.xpath("//ul[@id='menu-primary']//a[contains(text(), 'Staff')]")))
                .click()
                .build()
                .perform();
        Assert.assertEquals(getDriver().getTitle(), "Staff – Aeolian Cafe");
    }
}
