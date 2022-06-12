import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateFreestyleProjWithValidNameTest extends BaseTest {

    @Test
    public void testToShowJenkinsLogo() {

        Assert.assertTrue(getDriver().findElement(By.id("jenkins-head-icon")).isDisplayed());
    }

    @Test
    public void testCreateFreestylePr() {

        getDriver().findElement(By.xpath("//span[text()='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("JobToTestValidName" + Keys.ENTER);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("button[type='submit']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Dashboard']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[text()='JobToTestValidName']")).isDisplayed());
    }

}

