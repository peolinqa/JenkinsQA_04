package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

@Ignore
public class Song99BottlesDariaKozhTest extends BaseTest {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    private void getBottles(StringBuilder lyrics, int number, String btl) {
        lyrics.append(number).append(btl);
    }

    private void getNoMore(StringBuilder lyrics, String noMore, String btl) {
        lyrics.append(noMore).append(btl);
    }

    private String createLyrics() {

        final String BOTTLES_WALL_CS = " bottles of beer on the wall, ";
        final String BOTTLES_DOT_lN = " bottles of beer.\n";
        final String BOTTLES_DOT = " bottles of beer on the wall.";
        final String TAKE = "Take one down and pass it around, ";
        final String GO = "Go to the store and buy some more, ";
        final String NO_MORE = "No more";

        StringBuilder lyrics = new StringBuilder();

        getBottles(lyrics, 99, BOTTLES_WALL_CS);
        getBottles(lyrics, 99, BOTTLES_DOT_lN);

        for (int i = 98; i > -1; i--) {
            lyrics.append(TAKE);
            if (i == 1) {
                getBottles(lyrics, i, BOTTLES_DOT.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_WALL_CS.replace("s", ""));
                getBottles(lyrics, i, BOTTLES_DOT_lN.replace("s", ""));
            } else if (i == 0) {
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT);
                getNoMore(lyrics, NO_MORE, BOTTLES_WALL_CS);
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_lN);
            } else {
                getBottles(lyrics, i, BOTTLES_DOT);
                getBottles(lyrics, i, BOTTLES_WALL_CS);
                getBottles(lyrics, i, BOTTLES_DOT_lN);
            }
        }

        lyrics.append(GO);
        getBottles(lyrics, 99, BOTTLES_DOT);

        return lyrics.toString();
    }

    @Test
    public void testLyricsText() {

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
    public void test12_01TextInSubmenuJMenuBrowseLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p[contains(text(),'All languages')]"))
                .getText();

        Assert.assertEquals(actualResult, "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void test12_02LastTextInTableSubmenuMMenuBrowseLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//tbody/tr[last()]/td/a[@href='language-mysql-1252.html']"))
                .getText();

        Assert.assertEquals(actualResult, "MySQL");
    }

    @Test
    public void test12_03TitleTableInMenuBrowseLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        WebElement table = getDriver().findElement(By.xpath("//table[@id='category']"));
        String actualResult = getDriver().findElement(By.xpath("//tbody/tr[1]"))
                .getText();

        Assert.assertTrue(table.isDisplayed());
        Assert.assertEquals(actualResult, "Language Author Date Comments Rate");
    }

    @Test
    public void test12_04TrMathematicaInTableSubmenuMMenuBrowseLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        String textMathematicaAuthor = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[2]"))
                .getText();
        String textMathematicaDate = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[3]"))
                .getText();
        String textMathematicaComments = getDriver().findElement(
                        By.xpath("//a[@href='language-mathematica-1090.html']//ancestor::td//ancestor::tr/td[4]"))
                .getText();

        Assert.assertEquals(textMathematicaAuthor, "Brenton Bostick");
        Assert.assertEquals(textMathematicaDate, "03/16/06");
        Assert.assertEquals(textMathematicaComments, "1");
    }

    @Test
    public void test12_05Submenu0_9MenuBrowseLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();
        int languageText = getDriver().findElements(By.xpath("//tbody/tr/td[1]")).size();

        Assert.assertEquals(languageText, 10);
    }

    @Test
    public void test12_06ErrorSecurityCodeInSubMenuSignGuestbook() {
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Daria");
        getDriver().findElement(By.xpath("//input[@name='location']"))
                .sendKeys("Russia");
        getDriver().findElement(By.xpath("//input[@name='email']"))
                .sendKeys("123456@mail.ru");
        getDriver().findElement(By.xpath("//input[@name='homepage']"))
                .sendKeys("123456.ru");
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys("" + ((int) Math.random() * 900 + 100));
        getDriver().findElement(By.xpath("//textarea[@name='comment']"))
                .sendKeys("Hello");
        getDriver().findElement(By.xpath("//input[@name='submit']"))
                .click();
        String error = getDriver().findElement(By.xpath("//div[@id='main']/p"))
                .getText();

        Assert.assertEquals(error, "Error: Error: Invalid security code.");
    }

    @Test
    public void test12_07() {
        String expectedResult = "https://www.reddit.com/login" +
                "/?dest=https%3A%2F%2Fwww.reddit.com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer." +
                "net%252Flanguage-amanda-27.html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520Amanda";

        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr/td/a[@href='language-amanda-27.html']")).click();
        getDriver().findElement(By.xpath("//p/a[@title='reddit']/img")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void test12_08TopRated() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 100;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 20);
    }

    @Test
    public void test12_08TopRated2() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist.html']")).click();

        List<String> result = new ArrayList<>();
        List<WebElement> trAll = getDriver().findElements(By.xpath("//tr/td[2]"));
        for (WebElement tr : trAll) {
            result.add(tr.getText());
        }

        int positionShakespeare = Integer.MAX_VALUE;

        if (result.contains("Shakespeare")) {
            positionShakespeare = result.indexOf("Shakespeare");
        }

        Assert.assertTrue((positionShakespeare + 1 ) <= 20);
    }

    @Test
    public void test12_08TopRatedEsotericLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_esoteric.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 100;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 10);
    }

    @Test
    public void test12_08TopHits() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./tophits.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        int positionShakespeare = 100;

        for (int i = 0; i < trText.length; i++) {
            if (trText[i].contains("Shakespeare")) {
                positionShakespeare = i + 1;
            }
        }

        Assert.assertTrue(positionShakespeare <= 6);
    }

    @Test
    public void test12_08TopRatedRealLanguages() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_real.html']")).click();

        String[] trText = new String[25];
        for (int i = 0; i < trText.length; i++) {
            int index = i + 2;
            trText[i] = getDriver().findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
        }

        Assert.assertFalse(trText.toString().contains("Shakespeare"));
    }

    @Test
    public void test12_09() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr/td/a[@href='language-java-3.html']")).click();

        int javaVersions = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td[1]")).size() + 1;

        Assert.assertEquals(javaVersions, 6);
    }

    @Test
    public void test12_10() {
        getDriver().get(BASE_URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//tbody/tr/td/a[@href='language-java-3.html']")).click();

        int commentsJavaObjectOrientedVersion = Integer.parseInt(getDriver().findElement(By.xpath
                ("//tr/td/strong[contains(text(),'Comments:')]/parent::td//following-sibling::td")).getText());

        int commentsJavaObjectStandardVersion = Integer.parseInt(getDriver().findElement(By.xpath
                        ("//table[@id='category']/tbody/tr/td/a[@href='language-java-4.html']//parent::td//following-sibling::td[3]"))
                .getText());

        int commentsJavaExceptionOriented = Integer.parseInt(getDriver().findElement(By.xpath
                        ("//table[@id='category']/tbody/tr/td/a[@href='language-java-866.html']//parent::td//following-sibling::td[3]"))
                .getText());

        int commentsJavaBytecodeVersion = Integer.parseInt(getDriver().findElement(By.xpath
                        ("//table[@id='category']/tbody/tr/td/a[@href='language-java-1162.html']//parent::td//following-sibling::td[3]"))
                .getText());

        int commentsJava5Version = Integer.parseInt(getDriver().findElement(By.xpath
                        ("//table[@id='category']/tbody/tr/td/a[@href='language-java-950.html']//parent::td//following-sibling::td[3]"))
                .getText());

        int commentsJavaSpeechAPIVersion = Integer.parseInt(getDriver().findElement(By.xpath
                        ("//table[@id='category']/tbody/tr/td/a[@href='language-java-1148.html']//parent::td//following-sibling::td[3]"))
                .getText());

        int max1 = Math.max
                (commentsJavaObjectOrientedVersion,
                        Math.max(commentsJavaObjectStandardVersion, commentsJavaExceptionOriented));

        int max2 = Math.max
                (commentsJava5Version,
                        Math.max(commentsJavaBytecodeVersion, commentsJavaSpeechAPIVersion));

        Assert.assertEquals(Math.max(max1, max2), commentsJavaObjectOrientedVersion);
    }
}


