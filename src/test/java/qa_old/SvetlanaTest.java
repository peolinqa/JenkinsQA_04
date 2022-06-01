package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class SvetlanaTest extends BaseTest {

    @Test
    public void svetlanaTest() {

        getDriver().get("https://www.voda-nt.ru/");
        WebElement buttonPokazaniya = getDriver().findElement(By.xpath(
                "//div[@class='wrapper container z-depth-3']//a[@href='/pokazaniya']"));
        buttonPokazaniya.click();

        WebElement header = getDriver().findElement(By.xpath(
                "//div[@class='wrapper container z-depth-3']//section//h2[@class='title']"));

        Assert.assertEquals(header.getText(), "ПЕРЕДАЧА ПОКАЗАНИЙ ПРИБОРОВ УЧЕТА");
    }

}