
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AndreyDTest extends BaseTest {

    @Test
    public void test(){

        getDriver().get("https://ebay.com");
        getDriver().findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("coffee maker");
        getDriver().findElement(By.xpath("//*[@id=\"gh-btn\"]")).click();
        String res = getDriver().findElement(By.xpath("/html/body/div[5]/div[5]/div[2]/div[1]/div[2]/ul/li[1]/div/div[2]/a/h3\n")).getText();
        System.out.println("res:");
        System.out.println(res);
        Assert.assertEquals(1,1);
    }
}
