import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatianaMaxTest extends BaseTest {
    public static final String URL = "http://www.99-bottles-of-beer.net/";
    @Test
    public void testValidateTitleOnPageSongLyrics() {
        getDriver().get(URL);
        String expectedResult = "Lyrics of the song 99 Bottles of Beer";

        WebElement menuStart = getDriver().findElement(By.xpath("//body/div[@id='wrap']/div[@id='navigation']"
                + "/ul[@id='menu']/li/a[@href='/']"));
        menuStart.click();

        WebElement SubmenuSongLyrics = getDriver().findElement(By.xpath("//div[@id='navigation']"
                + "/ul[@id='submenu']/li[2]/a"));
        SubmenuSongLyrics.click();

        WebElement h2 = getDriver().findElement(By.xpath("//div[@id='main']/h2"));
        String actualResult = h2.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}