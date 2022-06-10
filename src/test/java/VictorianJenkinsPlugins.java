import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VictorianJenkinsPlugins extends BaseTest {

    public int getListSize(WebDriver webDriver) {
        return webDriver.findElements(By.xpath("//table[@id='plugins']//tbody//tr")).size();
    }

    @Test
    public void test_Manage_Jenkins_Plugins() {

        WebDriver driver = getDriver();
        driver.get("http://localhost:8080/pluginManager/");
        driver.findElement(By.xpath("//div[@class='tab active']//a[contains(text(),'Updates')]")).click();
        Assert.assertNotEquals(getListSize(driver), 0);
        driver.findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Available')]")).click();
        Assert.assertNotEquals(getListSize(driver), 0);
        driver.findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Installed')]")).click();
        Assert.assertNotEquals(getListSize(driver), 0);
        driver.findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Advanced')]")).click();
        Assert.assertNotEquals(driver.findElements(By.xpath("//input[contains(@class,\"jenkins-input\")]")).size(), 0);
    }
}
