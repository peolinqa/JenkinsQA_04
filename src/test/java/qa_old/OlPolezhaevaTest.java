package qa_old;

import org.testng.annotations.Ignore;
import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Ignore
public class OlPolezhaevaTest extends BaseTest {

    @Ignore
    @Test
    public void testFCKrasnodar(){

        getDriver().get("https://fckrasnodar.ru/ru/");

        getDriver().findElement(By.cssSelector("header [href='/academy/']")).click();

        WebElement academyBranches = getDriver().findElement(By.cssSelector("main [href='/academy/branches/']"));
        Assert.assertEquals(academyBranches.getText(), "ФИЛИАЛЫ АКАДЕМИИ");
    }

}
