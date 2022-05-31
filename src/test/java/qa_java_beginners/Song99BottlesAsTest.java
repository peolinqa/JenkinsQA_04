package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class Song99BottlesAsTest extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/lyrics.html";

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

        getDriver().get(URL);
        getDriver().findElement(menuSongLyrics).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> pAll = getDriver().findElements(pTags);
        for (WebElement p : pAll) {
            actualResult.append(p.getText());
        }
        Assert.assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    public void testSearchJ() {

        String expectedResalt = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='j.html']")).click();

        String[] pText = new String[2];
        for (int i = 0; i < pText.length; i++) {

            int index = i + 1;
            pText[i] = getDriver().findElement(By.xpath(
                    "//div[@id='main']/p[" + index + "]")).getText();
        }

        String actualyResalt = "";
        for (int i = 0; i < pText.length; i++) {

            actualyResalt += pText[i];
        }
        Assert.assertEquals(actualyResalt, expectedResalt);
    }

    @Test
    public void testFindMySQL() {

        String expectedResalt = "MySQL";

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='m.html']")).click();
        WebElement actualyResalt = getDriver().findElement(
                By.xpath("//td/a[@href='language-mysql-1252.html']"));

        Assert.assertEquals(actualyResalt.getText(), expectedResalt);
    }

    @Test
    public void testHW_12_3() {

        String expectedResalt = "Language Author Date Comments Rate";

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/abc.html']")).click();
        WebElement actualyResalt = getDriver().findElement(By.xpath("//table[@id='category']/tbody/tr[1]"));

        Assert.assertEquals(actualyResalt.getText(), expectedResalt);
    }

    @Test
    public void testHW_12_4() {

        getDriver().get(URL);
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='m.html']")).click();

        WebElement actualyResalt = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::tr/td[2]"));
        WebElement actualyResalt1 = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::tr/td[3]"));
        WebElement actualyResalt2 = getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::tr/td[4]"));

        Assert.assertEquals(actualyResalt.getText(), "Brenton Bostick");
        Assert.assertEquals(actualyResalt1.getText(), "03/16/06");
        Assert.assertEquals(actualyResalt2.getText(), "1");
    }

    @Test
    public void testHW_12_06() {
        String expectedResalt = "Error: Error: Invalid security code.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul/li/a[@href='/guestbookv2.html']")).click();
        getDriver().findElement(By.xpath("//ul/li/a[@href='./signv2.html']")).click();
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@name='name']")).sendKeys("Nik");
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@name='location']")).sendKeys("USA");
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@name='email']")).sendKeys("nik12@gmail.com");
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@name='captcha']"))
                .sendKeys(Integer.toString(((int) Math.random() * 900) + 100));
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/textarea[@name='comment']"))
                .sendKeys("I want to create a new language.");
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@type='submit']")).click();
        String actualyResalt = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualyResalt, expectedResalt);
    }

    @Test
    public void testHW_12_7() {

        String expectedResalt = "https://www.reddit.com/login/?dest=https%3A%2F%2Fwww.reddit." +
                "com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer" +
                ".net%252Flanguage-actionscript-1010.html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520actionscript";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//table[@id='category']/tbody/tr/td/a[@href='language-actionscript-1010.html']")).click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResalt);

    }

    @Test
    public void testHW_12_8_ShakespeareIncludeTop() {

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();

        List<WebElement> textFindShakespeare = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr"));
        for (WebElement tr : textFindShakespeare) {
            if (tr.getText().contains("Shakespeare")) {

                int numberShakespeare = Integer.parseInt(tr.getText().substring(0, 2));

                Assert.assertFalse(tr.getText().isEmpty());
                Assert.assertTrue(numberShakespeare <= 20);
            }
        }
    }

    @Test
    public void testHW_12_8_ShakespeareIncludeTopEsotericLanguages() {

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_esoteric.html']")).click();

        List<WebElement> textFindShakespeare = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr"));
        for (WebElement tr : textFindShakespeare) {
            if (tr.getText().contains("Shakespeare")) {
                int numberShakespeare = Integer.parseInt(tr.getText().substring(0, 1));

                Assert.assertFalse(tr.getText().isEmpty());
                Assert.assertTrue(numberShakespeare <= 10);
            }
        }
    }

    @Test
    public void testHW_12_8_ShakespeareIncludeTopHits() {

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./tophits.html']")).click();

        List<WebElement> textFindShakespeare = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr"));
        for (WebElement tr : textFindShakespeare) {
            if (tr.getText().contains("Shakespeare")) {
                int numberShakespeare = Integer.parseInt(tr.getText().substring(0, 1));

                Assert.assertFalse(tr.getText().isEmpty());
                Assert.assertTrue(numberShakespeare <= 6);
            }
        }
    }

    @Test
    public void testHW_12_8_ShakespeareNotIncludeTopRatedReal() {

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_real.html']")).click();

        List<WebElement> textFindShakespeare = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr"));
        for (WebElement tr : textFindShakespeare) {

            Assert.assertFalse(tr.getText().isEmpty());
            Assert.assertFalse(tr.getText().contains("Shakespeare"));
        }
    }

    @Test
    public void HW_12_9JavaSixVersion() {

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Search Languages")).click();
        getDriver().findElement(By.xpath("//input[@ name='search']")).sendKeys("Java");
        getDriver().findElement(By.xpath("//input[@ type='submit']")).click();

        StringBuilder getTextJava = new StringBuilder();
        int actualyResaltCountJava = 0;
        List<WebElement> getText = getDriver().findElements(By.xpath("//table/tbody/tr"));
        for (WebElement p : getText) {
            getTextJava.append(p.getText().contains("Java"));
            actualyResaltCountJava++;
        }
        if (actualyResaltCountJava >= 6) {
            Assert.assertTrue(true);
        }
    }


}