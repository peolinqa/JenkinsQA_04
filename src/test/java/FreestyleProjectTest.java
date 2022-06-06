import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectTest extends BaseTest {

    @Test
    public void testAddDescription() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();

        getDriver().findElement(By.id("name")).
                sendKeys("new Freestyle project");
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.cssSelector("[name='description']"))
                .sendKeys("New description");
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1[text() = 'Project new Freestyle project']")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(),
                "New description");
    }
}
