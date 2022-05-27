package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BottlesNataliaMonaTest extends BaseTest {

    @Test
    public void testConfirmThePresenceOfASubmenuJInTheMenuBrowseLanguages() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath(
                "//div[@id='navigation']/ul[@id='submenu']/li/a[@href ='j.html']")).click();
        WebElement text = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
