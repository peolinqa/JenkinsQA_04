package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class Song99BottlesNataliiaOliverTest extends BaseTest {
    private static final String BASE_URL = "http://99-bottles-of-beer.net/lyrics.html";

    private void getBottles(StringBuilder songLyrics, int number, String btl) {
        songLyrics.append(number).append(btl);
    }

    private void getNoMore(StringBuilder songLyrics, String noMore, String btl) {
        songLyrics.append(noMore).append(btl);
    }

    private String createSongLyrics() {
        final String BOTTLES_WALL_COMA_SPACE = " bottles of beer on the wall, ";
        final String BOTTLES_DOT_LINE = " bottles of beer.\n";
        final String BOTTLES_WALL_DOT = " bottles of beer on the wall.";
        final String TAKE_COMA_SPACE = "Take one down and pass it around, ";
        final String NO_MORE = "No more";
        final String GO = "Go to the store and buy some more, ";

        StringBuilder songLyrics = new StringBuilder();

        getBottles(songLyrics, 99, BOTTLES_WALL_COMA_SPACE);
        getBottles(songLyrics, 99, BOTTLES_DOT_LINE);

        for (int i = 98; i > -1; i--) {
            songLyrics.append(TAKE_COMA_SPACE);

            if (i == 1) {
                getBottles(songLyrics, i, BOTTLES_WALL_DOT.replace("s", ""));
                getBottles(songLyrics, i, BOTTLES_WALL_COMA_SPACE.replace("s", ""));
                getBottles(songLyrics, i, BOTTLES_DOT_LINE.replace("s", ""));
            } else if (i == 0) {
                getNoMore(songLyrics, NO_MORE.toLowerCase(), BOTTLES_WALL_DOT);
                getNoMore(songLyrics, NO_MORE, BOTTLES_WALL_COMA_SPACE);
                getNoMore(songLyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_LINE);
            } else {
                getBottles(songLyrics, i, BOTTLES_WALL_DOT);
                getBottles(songLyrics, i, BOTTLES_WALL_COMA_SPACE);
                getBottles(songLyrics, i, BOTTLES_DOT_LINE);
            }
        }

        songLyrics.append(GO);
        getBottles(songLyrics, 99, BOTTLES_WALL_DOT);

        return songLyrics.toString();
    }

    @Test
    public void testSongLyricsText() {
        final String expectedResult = createSongLyrics();
        By pTags = By.xpath("//div[@id='main']/p");
        By menuSongLyrics = By.linkText("Song Lyrics");

        getDriver().get(BASE_URL);
        getDriver().findElement(menuSongLyrics).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> pAllElements = getDriver().findElements(pTags);

        Assert.assertFalse(pAllElements.isEmpty());

        for (WebElement pEachElement : pAllElements) {
            actualResult.append(pEachElement.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);
    }
}
