package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class Song99BottlesJuliaSabTest extends BaseTest {

    private List<WebElement> textOfSong;
    private static final String URL_BASE = "http://www.99-bottles-of-beer.net/";
    private static final By XPATH_BROWSE_LANGUAGES_LINK = By.xpath("//li/a[@href='/abc.html']");
    private static final By XPATH_LETTER_J = By.xpath("//a[@href='j.html']");
    private static final By XPATH_DESCRIPTION_CATEGORY_J = By.xpath("//p[contains(text(), 'All languages')]");
    private static final By XPATH_TEXT_OF_SONG = By.xpath("//div[@id='main']/p");

    @Test
    public void testConfirmSongLyric() {
        String expectedResult = constructorForSong();
        getDriver().get("http://www.99-bottles-of-beer.net/lyrics.html");
        textOfSong = getDriver().findElements(XPATH_TEXT_OF_SONG);

        StringBuilder actualResult = new StringBuilder();
        for (WebElement value : textOfSong) {
            actualResult.append(value.getText());
        }
        Assert.assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    public void testConfirmThePageDescription() {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_J).click();

        String actualResult = getDriver().findElement(XPATH_DESCRIPTION_CATEGORY_J).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    private static String constructorForSong() {
        StringBuilder song = new StringBuilder();
        String startText = "99 bottles of beer on the wall, 99 bottles of beer.\n";
        String finalText = "Take one down and pass it around, 1 bottle of beer on the wall.1 bottle of beer on the wall, 1 bottle of beer.\n" +
                "Take one down and pass it around, no more bottles of beer on the wall.No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.";

        for (int i = 98; i > 1; i--) {
            song.append(String.format("Take one down and pass it around, " + i + " bottles of beer on the wall." +
                    i + " bottles of beer on the wall, " + i + " bottles of beer.\n"));
        }
        return startText + song + finalText;
    }
}
