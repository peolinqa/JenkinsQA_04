import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ManageCredentialChekMenuTest extends BaseTest {

    public static final String NEW_USERNAME = "Felix";
    public static final String NEW_PASSWORD = "QA12345";

    @Test
    public void testManageCredentials() {

        WebElement hoverable = getDriver().findElement(By.xpath("//a[@class='model-link inside inverse']"));
        new Actions(getDriver())
                .moveToElement(hoverable)
                .perform();

        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(By.id("yui-gen4")).click();
        getDriver().findElement(By.xpath("//a[@title='User']")).click();
        getDriver().findElement(By.xpath("//table/tbody/tr[2]/td[2]/a")).click();
        getDriver().findElement(By.xpath("//div[2]/span/a/span[2]")).click();
        getDriver().findElement(By.xpath("//input[@name='_.username']"))
                .sendKeys(NEW_USERNAME);
        getDriver().findElement(By.xpath("//input[@name='_.password']"))
                .sendKeys(NEW_PASSWORD);
        getDriver().findElement(By.id("yui-gen1-button")).click();
        WebElement newUser = getDriver().findElement(By.xpath("//div[@id='main-panel']/table"));

        Assert.assertTrue(newUser.getText().contains(NEW_USERNAME));
    }
}
