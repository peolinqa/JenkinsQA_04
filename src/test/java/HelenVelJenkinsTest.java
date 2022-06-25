import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HelenVelJenkinsTest extends BaseTest {

    static String newItemButton  = "//div[@id='tasks']/div[1]/span/a/span[2]";
    static String itemNameField = "//div/input[@id='name']";
    static String itemPipeline = "//div[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[1]";
    static String itemButtonOk = "//div/span/button[@id='ok-button']";
    static String itemDashboardClick = "//div/ul/li/a[@class='model-link inside breadcrumbBarAnchor']";
    static String dashboardName = "//*[@id=\"job_Pipeline\"]/td[3]/a";
    static String itemNamePipeline = "Pipeline";
    static String itemButtonSave="//*[@id='yui-gen6-button']";

    @Test
    public void testTC_017_015_createAPipeline() {

        getDriver().findElement(By.xpath(newItemButton)).click();
        getDriver().findElement(By.xpath(itemNameField)).sendKeys("Pipeline");
        getDriver().findElement(By.xpath(itemPipeline)).click();
        getDriver().findElement(By.xpath(itemButtonOk)).click();
        getDriver().findElement(By.xpath(itemButtonSave)).click();
        getDriver().findElement(By.xpath(itemDashboardClick)).click();
        WebElement jobItem = getDriver().findElement(By.xpath(dashboardName));
        String actualResult = jobItem.getText();

        Assert.assertEquals(actualResult, itemNamePipeline);
    }
}
