import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class NoobiesTest extends BaseTest {

    public static final String URL = "http://www.99-bottles-of-beer.net/";


    @Test
    public void testOneProgramInTitle() {
        getDriver().get(URL);

        WebElement text = getDriver().findElement(By.xpath("//div[@id='header']/h2/strong"));

        String expectedResult = "1500";
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFindAuthorOfZombie() {
        getDriver().get(URL);

        WebElement topLists = getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']"));
        topLists.click();

        WebElement zombie = getDriver().
                findElement(By.xpath("//table[@id='category']//a[@href='language-zombie-2562.html']"));
        zombie.click();

        WebElement taneb = getDriver().findElement(By.xpath("//div[@id='main']//td[(contains(text(),'Taneb'))]"));

        String expectedResult = "Taneb";
        String actualResult = taneb.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
