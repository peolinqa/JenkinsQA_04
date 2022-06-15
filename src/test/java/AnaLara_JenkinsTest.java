import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnaLara_JenkinsTest extends BaseTest {
    @Test
    public void testAddDescription() {
        WebDriver driver = getDriver();
        driver.get("http://localhost:8080/view/all/newJob");
        driver.findElement(By.id("name")).sendKeys("MyFolder");

        driver.findElement(By.xpath("//span[text()=\"Folder\"]/ancestor::li")).click();
        driver.findElement(By.id("ok-button")).click();

        driver.findElement(By.xpath("//form[@name=\"config\"]//input[@name=\"_.displayNameOrNull\"]")).sendKeys("MyFolder");
        driver.findElement(By.id("yui-gen6-button")).click();

        driver.get("http://localhost:8080/job/MyFolder/");

        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,"All [MyFolder] [Jenkins]");

    }
}

