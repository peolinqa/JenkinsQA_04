package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AlyTest extends BaseTest {

    @Test
    public void findSongLyrics() {
        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement searchButton = getDriver().findElement(By.xpath("//*[@id=\"submenu\"]/li[2]/a"));
        searchButton.click();

        String text = getDriver().findElement(By.xpath("//*[@id=\"main\"]/h2")).getText();
        Assert.assertEquals(text, "Lyrics of the song 99 Bottles of Beer");
    }

}
