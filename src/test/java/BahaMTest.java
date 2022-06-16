import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BahaMTest extends BaseTest {

    private static final String ITEM_NAME = "freestyle-project-()+-_~-1";

    private void createProject(String name) {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testTC_001_001_newFreestyleItem() {

        createProject(ITEM_NAME);

        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[1]/a")).click();

        WebElement jobItem = getDriver().findElement(
                By.xpath("//tr[@class=' job-status-nobuilt']//a[@href='job/" + ITEM_NAME.replace("~", "%7E") + "/']"));
        Assert.assertTrue(jobItem.isDisplayed());
        Assert.assertEquals(jobItem.getText().replace("\n", ""), ITEM_NAME);
    }

    @Test
    public void testTC_001_002_newFreestyleItem_negative() {

        createProject("freestyle-project-2!");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id='main-panel']/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "‘!’ is an unsafe character");
    }

}