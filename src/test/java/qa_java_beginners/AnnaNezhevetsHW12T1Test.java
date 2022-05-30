package qa_java_beginners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnnaNezhevetsHW12T1Test extends BaseTest{
    public final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testSubmenuJ(){
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        WebElement browseLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        browseLanguage.click();
        WebElement subMenuJ = getDriver().findElement(By.xpath("//ul[@id='submenu']//li/a[@href='j.html']"));
        subMenuJ.click();
        WebElement subMenuJAll = getDriver().findElement(By.xpath("//div[@id='main']/p"));

        Assert.assertEquals(subMenuJAll.getText(), expectedResult);
    }
}