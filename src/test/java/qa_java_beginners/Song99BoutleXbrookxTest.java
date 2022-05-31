package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;


public class Song99BoutleXbrookxTest extends BaseTest {
    private static final String URL = "http://www.99-bottles-of-beer.net/";

    private void getBotteles(StringBuilder lyrics, int number, String btl) {
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
        getBotteles(lyrics, 99, BOTTLES_WALL_CS);
        getBotteles(lyrics, 99, BOTTLES_DOT_LN);

        for (int i = 98; i > -1; i--) {
            lyrics.append(TAKE);
            if (i == 1) {
                getBotteles(lyrics, i, BOTTLES_DOT.replace("s", ""));
                getBotteles(lyrics, i, BOTTLES_WALL_CS.replace("s", ""));
                getBotteles(lyrics, i, BOTTLES_DOT_LN.replace("s", ""));
            } else if (i == 0) {
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT);
                getNoMore(lyrics, NO_MORE, BOTTLES_WALL_CS);
                getNoMore(lyrics, NO_MORE.toLowerCase(), BOTTLES_DOT_LN);
            } else {
                getBotteles(lyrics, i, BOTTLES_DOT);
                getBotteles(lyrics, i, BOTTLES_WALL_CS);
                getBotteles(lyrics, i, BOTTLES_DOT_LN);
            }
        }

        lyrics.append(GO);
        getBotteles(lyrics, 99, BOTTLES_DOT);

        return lyrics.toString();
    }

    @Test
    public void testSongLyricsText () {
        final String expectedResult = createLyrics();
        By pTags = By.xpath("//div[@id='main']/p");

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Song Lyrics")).click();

        StringBuilder actualResult = new StringBuilder();

        List<WebElement> listSong = getDriver().findElements(pTags);
        for (WebElement pWeb : listSong) {
            actualResult.append(pWeb.getText());
        }

        Assert.assertEquals(actualResult.toString(), expectedResult);
    }

    @Test //TC_12_01
    public void testCheckDescriptionPage () {

        final String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement pText = getDriver().findElement(By.xpath("//div[@id='main']/p"));
        String pTextstr = pText.getText();

        Assert.assertEquals(pTextstr, expectedResult);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p/strong")).getTagName(), "strong");
    }

    @Test //TC_12_02
    public void testCheckLastLanguageMySQL () {

        String expectedResult = "MySQL";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String lastLanguage = getDriver().findElement(By.xpath("//tr/td/a[@href='language-mysql-1252.html']")).getText();

        Assert.assertEquals(lastLanguage, expectedResult);
    }

    @Test //TC_12_03
    public void testConfirmTableWithHeaders () {
        String expectedResult = "Language, Author, Date, Comments, Rate";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        List<WebElement> list = getDriver().findElements(By.xpath("//div[@id='main']//th"));

        StringBuilder headers = new StringBuilder();
        for (WebElement web : list) {
            headers.append(web.getText()).append(", ");
        }

        Assert.assertEquals(headers.substring(0, headers.length() - 2), expectedResult);
    }

    @Test //TC_12_04
    public void testConfirmCreaterAndDateCreateHaveOneComment () {
        String expectedResult = "Brenton Bostick";
        String expectedResult1 = "03/16/06";
        String expectedResult2 = "1";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String name = getDriver().findElement(By.xpath("//tbody/tr/td[text()='Brenton Bostick']")).getText();
        String data = getDriver().findElement(By.xpath("//tbody/tr/td[text()='03/16/06']")).getText();
        String comment = getDriver().findElement(By.xpath("//tbody/tr/td[text()='1']")).getText();

        Assert.assertEquals(name, expectedResult);
        Assert.assertEquals(data, expectedResult1);
        Assert.assertEquals(comment, expectedResult2);
    }

    @Test //TC_12_05
    public void testCountLanguagesBeginWithNumbers () {
        int expectedResult = 10;

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        List<WebElement> list = getDriver().findElements(By.xpath("//tbody/tr[@onmouseover]"));
        StringBuilder languages = new StringBuilder();

        int count = 0;
        for (WebElement web : list) {
            languages.append(web.getText());
            count++;
        }

        Assert.assertEquals(count, expectedResult);
    }

    @Test //TC_12_06
    public void testFillInAllFieldsAndRandomNumberInSecurityCodeField () {
        String expectedResult = "Error: Error: Invalid security code.";
        String expectedResult1 = "b";
        String expectedResult2 = "border: 1px solid red; background-color: rgb(255, 224, 224); padding: 5px; margin: 5px 10px;";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(By.xpath("//form/p/input[@name='name']")).sendKeys("Valentin");
        getDriver().findElement(By.xpath("//form/p/input[@name='location']")).sendKeys("Moscow");
        getDriver().findElement(By.xpath("//form/p/input[@name='email']")).sendKeys("cricket666@yandex.ru");
        getDriver().findElement(By.xpath("//form/p/input[@name='homepage']")).sendKeys("ya.ru");
        int random = ((int) (Math.random() * 1000));
        String newStr = Integer.toString(random);
        getDriver().findElement(By.xpath("//form/p/input[@name='captcha']")).sendKeys(newStr);
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Hello World!");
        getDriver().findElement(By.xpath("//input[@name='submit']")).click();

        String errorMassage = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(errorMassage, expectedResult);

        String bold = getDriver().findElement(By.xpath("//div[@id='main']/p/b")).getTagName();

        Assert.assertEquals(bold, expectedResult1);

        String style = getDriver().findElement(By.xpath("//div[@id='main']/p")).getAttribute("style");

        Assert.assertEquals(style, expectedResult2);
    }

    @Test //TC_12_07
    public void testMakeBookmark () {

        String expectedResult = "https://www.reddit.com/login/?dest=https%3A%2F%2Fwww.reddit" +
                ".com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer.net%252Flanguage-autohotkey-1333" +
                ".html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520AutoHotkey";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-autohotkey-1857.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-autohotkey-1333.html']")).click();
        WebElement reddit = getDriver().findElement(
                By.xpath("//a[@title='reddit' and contains(@href,'language-autohotkey-1333.html')]"));
        reddit.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    /**
     * TC_12_08
     * Открыть базовую страницу
     * Нажать на пункт меню TOP LISTS
     * Подтвердите, что решение на языке Shakespeare входит в топ 20 всех решений
     */

    @Test //TC_12_08_1
    public void testIncludeInTop20 () {

        String expectedResult = "Shakespeare";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//li/a[@href='/toplist.html']")).click();
        List<WebElement> topList = getDriver().findElements(
                By.xpath("//tr[@onmouseover]/td/a[contains(@href,'language')]"));

        List<String> top20 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            top20.add(topList.get(i).getText());
        }

        Assert.assertTrue(top20.contains(expectedResult));
    }

    @Test //TC_12_08_2
    public void testIncludeInTop10 () {

        String expectedResult = "Shakespeare";

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Top Lists")).click();
        getDriver().findElement(By.linkText("Top Rated Esoteric")).click();
        List<WebElement> esotericLanguages = getDriver().findElements(
                By.xpath("//table[@id='category']//td/a[contains(@href, 'language')]"));

        List<String> languages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            languages.add(esotericLanguages.get(i).getText());
        }

        Assert.assertTrue(languages.contains(expectedResult));
    }

    @Test //TC_12_08_3
    public void testIncludeInTop6 () {

        String expectedResult = "Shakespeare";

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Top Lists")).click();
        getDriver().findElement(By.linkText("Top Hits")).click();
        List<WebElement> topHits = getDriver().findElements(
                By.xpath("//table[@id='category']//tr/td/a[contains(@href, 'language')]"));

        List<String> top6Hits = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            top6Hits.add(topHits.get(i).getText());
        }

        Assert.assertTrue(top6Hits.contains(expectedResult));
    }

    @Test //TC_12_08_4
    public void testNotIncludeInTopReal () {

        String expectedResult = "Shakespeare";

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Top Lists")).click();
        getDriver().findElement(By.linkText("Top Rated Real")).click();
        List<WebElement> topRealList = getDriver().findElements(
                By.xpath("//table[@id='category']//tr/td/a[contains(@href, 'language')]"));

        StringBuilder topRealStr = new StringBuilder();
        for (WebElement web : topRealList) {
            topRealStr.append(web.getText());
        }

        Assert.assertFalse(topRealStr.toString().contains(expectedResult));
    }

    @Test //TC_12_09
    public void testConfirmDecisionOfJava () {

        int expectedResult = 6;

        getDriver().get(URL);
        getDriver().findElement(By.linkText("Search Languages")).click();
        getDriver().findElement(By.name("search")).sendKeys("java", Keys.ENTER);
        List<WebElement> containsJavaLang = getDriver().findElements(
                By.xpath("//td/a[contains(@href, 'language') and (text()='Java' or contains(text(), 'Java ('))]"));

        List<String> javaList = new ArrayList<>();

        for (WebElement web : containsJavaLang) {
            javaList.add(web.getText());
        }

        Assert.assertEquals(javaList.size(), expectedResult);
    }

    @Test
    public void testCountComments () {
        String expectedResult = "(object-oriented version)";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        List<WebElement> listComments = getDriver().findElements(By.xpath("//tr[@onmouseover]/td[4]"));

        List<Integer> listCountComments = new ArrayList<>();
        for (WebElement web : listComments) {
            listCountComments.add(Integer.parseInt(web.getText()));
        }

        Integer max = 0;
        Integer index = 0;

        for (int i = 0; i < listComments.size() - 1; i++) {
            if (max < listCountComments.get(i)) {
                max = listCountComments.get(i);
                index = i;
            }
        }

        WebElement javaComment = getDriver().findElement(
                By.xpath("//strong[text()='Comments:']/parent::td/following-sibling::td"));

        String actualResult = "";

        if (listCountComments.get(index) > Integer.parseInt(javaComment.getText())) {
            actualResult = getDriver().findElement(By.xpath("//tr[@onmouseover]/td[4]/preceding-sibling::td/a")).getText();
        } else {
            actualResult = getDriver().findElement(
                    By.xpath("//h2[text()='Language Java']//following-sibling::p[text()='(object-oriented version)']")).getText();
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
}



