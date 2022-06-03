import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.assertTrue;

public class RusMJenkinsTest extends BaseTest {

    @Test
    public void testFreestyleNewItemValidName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("item 123");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@id='yui-gen25-button']")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();

        assertTrue(getDriver().findElement(By.xpath("//tr[@id='job_item 123']/td[3]/a"))
                .isDisplayed());
    }
}
