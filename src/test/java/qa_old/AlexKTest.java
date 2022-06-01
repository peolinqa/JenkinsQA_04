package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AlexKTest extends BaseTest {
    @Ignore
    @Test
    public void testLogin(){
        String expectedResult = "Hello Alex";

        getDriver().get("https://www.interiordefine.com/");
        getDriver().findElement(By.xpath("//*[@class='id--icon-account']")).click();
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys("Belarus8500@gmail.com");
        getDriver().findElement(By.xpath("//input[@id='pass']")).sendKeys("Belarus_8500");
        getDriver().findElement(By.xpath("//button[@id='send2']")).click();
        WebElement actualResult = getDriver().findElement(By.xpath("//div[@class='account--sidebar']/h3"));

        String actualResultText = actualResult.getText();
        Assert.assertEquals(actualResultText, expectedResult);
    }
}
