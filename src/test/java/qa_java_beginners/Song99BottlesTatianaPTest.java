package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class Song99BottlesTatianaPTest extends BaseTest {
    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    private void getBottles(StringBuilder lyrics, int number, String btl) {
        lyrics.append(number).append(btl);
    }

    private void getNoMore(StringBuilder lyrics, String noMore, String btl) {
        lyrics.append(noMore).append(btl);
    }

    private String createLyrics() {
        final String BOTTLES_WALL_CS = " bottles of beer on the wall, ";
        final String BOTTLES_DOT_LN = " bottles of beer.\n";
        final String BOTTLES_DOT = " bottles of beer on the wall.";
        final String TAKE = "Take one down and pass it around, ";
        final String GO = "Go to the store and buy some more, ";
        final String NO_MORE = "No more";

        StringBuilder lyrics = new StringBuilder();

        getBottles(lyrics, 99, BOTTLES_WALL_CS);
        getBottles(lyrics, 99, BOTTLES_DOT_LN);

        for (int i = 98; i > -1; i--) {
            lyrics.append(TAKE);

            if (i == 1) {
                getBottles(lyrics, i, BOTTLES_DOT.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_WALL_CS.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_DOT_LN.replace("s", ""));

            } else if (i == 0) {
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT);
                getNoMore(lyrics, NO_MORE, BOTTLES_WALL_CS);
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_LN);
            } else {
                getBottles(lyrics, i, BOTTLES_DOT);
                getBottles(lyrics, i, BOTTLES_WALL_CS);
                getBottles(lyrics, i, BOTTLES_DOT_LN);
            }
        }
        lyrics.append(GO);
        getBottles(lyrics, 99, BOTTLES_DOT);

        return lyrics.toString();
    }

    @Test
    public void testSongLyricsText() {
        final String expectedResult = createLyrics();
        By pTags = By.xpath("//div[@id='main']/p");
        By menuSongLyrics = By.linkText("Song Lyrics");

        getDriver().get(BASE_URL);
        getDriver().findElement(menuSongLyrics).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> pAll = getDriver().findElements(pTags);

        Assert.assertFalse(pAll.isEmpty());

        for (WebElement p : pAll) {
            actualResult.append(p.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    public void HW_12_1test() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='j.html']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p[contains(text(),'All languages')]")).getText(), expectedResult);
    }

    @Test
    public void TC_12_02test() {

        String expectedResult = "MySQL";
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//td/a[@href='language-mysql-1252.html']")).getText(), expectedResult);
    }

    @Test
    public void TC_12_03test() throws InterruptedException {

        String expectedLanguage = "Language";
        String expectedAuthor = "Author";
        String expectedDate = "Date";
        String expectedComment = "Comments";
        String expectedRate = "Rate";

        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(expectedLanguage).append(expectedAuthor).append(expectedDate)
                .append(expectedComment).append(expectedRate);

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElements(By.xpath("//tr/th"));

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> tableTitles = getDriver().findElements(By.xpath("//tr/th"));

        for (WebElement trs : tableTitles) {
            actualResult.append(trs.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult.toString());
    }

    @Test
    public void TC_12_04test() {

        String expectedTitle = "Mathematica";
        String authorExpected = "Brenton Bostick";
        String dateExpected = "03/16/06";
        String commentExpected = "1";
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(expectedTitle).append(authorExpected).append(dateExpected).append(commentExpected);

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='m.html']")).click();
        List<WebElement> trs = getDriver().findElements(By.xpath("//tbody/tr/td"));

        List<String> actualResult = new ArrayList<>();

        for (WebElement tr : trs) {
            if (tr.getText().contains(expectedTitle)) {
                actualResult.add(tr.getText());
            }
        }
        Assert.assertEquals(actualResult.size(), 1);
    }

    @Test
    public void TC_12_05Test() {
        int expectedResult = 10;
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath(("//a[@href='0.html']"))).click();

        List<WebElement> tableNum = getDriver().findElements(By.xpath("//tbody/tr"));
        int actualResult = tableNum.size() - 1;

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void TC_12_06() {
        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("Tatiana");
        getDriver().findElement(By.xpath("//input[@name='location']")).sendKeys("Canada>,SK");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("tatianapak0210@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='homepage']")).submit();

        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys(Integer.toString((int) (Math.random() * 1000 + 100)));
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("great work");

        getDriver().findElement(By.xpath("//input[@name='submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p")).getText(), expectedResult);
    }

    @Test
    public void TC_12_07() {
        String expectedResult = "reddit.com: Log in";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-1162.html']")).click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        Assert.assertEquals(getDriver().getTitle(), expectedResult);
    }

    @Test
    public void TC_12_09() {
        int expectedResult = 6;

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/search.html']")).click();
        getDriver().findElement(By.name("search")).sendKeys("Java");
        getDriver().findElement(By.name("submitsearch")).click();
        getDriver().findElement(By.xpath("//a[@href='/language-java-3.html']")).click();

        int javaVersion = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td[1]")).size() + 1;

        Assert.assertEquals(javaVersion, expectedResult);
    }

    @Test
    public void TC_12_10() {
        int expectedResult = 33;

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//li/a[@href='/search.html']")).click();
        getDriver().findElement(By.name("search")).sendKeys("Java");
        getDriver().findElement(By.name("submitsearch")).click();

        List<WebElement> java = getDriver().findElements(By.xpath("//tbody/tr/td[4]"));

        int maxValue = Integer.MIN_VALUE;
        for (WebElement comments : java) {
            if (maxValue < Integer.parseInt(comments.getText())) {
                maxValue = Integer.parseInt(comments.getText());
            }
        }

        Assert.assertEquals(maxValue, expectedResult);
    }
}