import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RenameFolderTest extends BaseTest {

    @Test
    public void testTC_010_003_RenameFolder() {
        final String newName = "Schedule2022";

        getDriver().findElement(By.xpath("//div[@id='tasks']/div/span/a/span[2]")).click();
        getDriver().findElement(By.name("name")).sendKeys("Schedule");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//li[@class='item']")).click();
        getDriver().findElement(By.xpath("//a[@href='job/Schedule/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Schedule/confirm-rename']/span[2]")).click();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']"))
                .sendKeys("Schedule2022");
        getDriver().findElement(By.id("yui-gen1")).click();
        getDriver().findElement(By.xpath("//li[@class='item']")).click();

        Assert.assertEquals
                (getDriver().findElement(By.xpath("//a[@href='job/Schedule2022/']")).getText(), newName);
    }
}
