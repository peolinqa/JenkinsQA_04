package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class Song99BottlesSvetlanaG1011Test extends BaseTest {
    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String BROWSE_LANGUAGES_MENU_XPATH = "//li/a[@href='/abc.html']";

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
        for (WebElement p : pAll) {
            actualResult.append(p.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    public void testBrowseLanguagesSubtitleJText() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();;
        WebElement text = getDriver().findElement(By.xpath("//div[@id='main']/p[1]"));

        Assert.assertEquals(text.getText(), expectedResult);
    }

    @Test
    public void testLastLanguageInTableMySQL() {

        String expectedResult = "MySQL";

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        WebElement lastLanguage = getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']"));

        Assert.assertEquals(lastLanguage.getText(), expectedResult);
    }

    @Test
    public void testBrowseLanguagesTableHeaders() {

        String expectedResult = "Language Author Date Comments Rate";

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"))
                .click();
        String actualResult = getDriver().findElement(
                By.xpath("//table[@id='category']/tbody/tr[1]"))
                .getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test10LanguagesStartsWithNumbers() {

        int expectedResult = 10;

        getDriver().get("https://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//li/a[@href='/abc.html']"))
                .click();
        getDriver().findElement(
                By.xpath("//li/a[@href='0.html']"))
                .click();
        int actualResult = getDriver().findElements(By.xpath("//tbody/tr/td[1]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSignGuestbookWithRandom3digitSecurityCode() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Svetlana");
        getDriver().findElement(By.xpath("//input[@name='location']"))
                .sendKeys("New York");
        getDriver().findElement(By.xpath("//input[@name='email']"))
                .sendKeys("Sg26@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='homepage']"))
                .sendKeys("www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys(Integer.toString((int) (Math.random() * 900) + 100));
        getDriver().findElement(By.xpath("//textarea[@name='comment']"))
                .sendKeys("Thanks");
        getDriver().findElement(By.xpath("//input[@type='submit']"))
                .click();
        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p"))
                .getText();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testBrowseLanguagesAlternativeVersionsReddit() {

        String expectedResult = "https://www.reddit.com/login/?dest=https%3A%2F%2F" +
                "www.reddit.com%2Fsubmit%3Furl%3Dhttps%253A%252F%252F" +
                "www.99-bottles-of-beer.net%252Flanguage-actionscript-16." +
                "html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520ActionScript";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-actionscript-1010.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-actionscript-16.html']")).click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test6VersionsOfSolutionsInJava() {

        int expectedResult  = 6;

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES_MENU_XPATH)).click();
        getDriver().findElement(By.xpath("//li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        int actualResult = getDriver().findElements(
                By.xpath("//table[@id='category']//tr[@onmouseover]")).size() + 1;

        Assert.assertEquals(actualResult, expectedResult);
    }
}
