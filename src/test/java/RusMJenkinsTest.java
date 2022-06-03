import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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

    @Test
    public void testFreestyleRename() {
        Actions dropdown = new Actions(getDriver());
        dropdown.moveToElement(getDriver().findElement(By
                .xpath("//tr[@id='job_item 123']/td[3]/a"))).perform();

        getDriver().findElement(By.xpath("//div[@id='menuSelector']")).click();
        dropdown.moveToElement(getDriver().findElement(By
                        .xpath("/html/body/div[2]/div/div[2]/div[2]/div/div/div/div[1]/ul/li[6]/a")))
                            .click().perform();

        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input"))
                .clear();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input"))
                .sendKeys("project987");
        getDriver().findElement(By.xpath("//button[@id='yui-gen1-button']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id=\"main-panel\"]/h1"))
                .getText(), "Project project987");

        getDriver().findElement(By.xpath("//div/span/a/span[2]")).click();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//tr[@id='job_project987']/td[3]/a")).getText(), "project987");
    }
}
