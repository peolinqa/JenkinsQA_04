package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class Song99BottlesPatriotby07Test extends BaseTest {
    public static final String URL = "http://www.99-bottles-of-beer.net/";
    private static final By BROWSE_LANGUAGES = By.xpath(
            "//ul//a[@href='/abc.html']");
    private static final By SUBMENU_M = By.xpath("//a[@href='m.html']");
    private static final By SUBMENU_J = By.cssSelector("[href='j.html']");
    private static final String SHAKESPEARE = "Shakespeare";
    private static final By LANGUAGE_JAVA = By.cssSelector(
            "[href='language-java-3.html']");
    private static final By OBJECT_ORIENTED_VERSION = By.xpath(
            "//p[text()='(object-oriented version)']");

    @Test
    public void testSongLyricsText() {
        getDriver().get(URL);

        String expectedResult = "Lyrics of the song 99 Bottles of Beer\n" +
                "99 bottles of beer on the wall, 99 bottles of beer.\n" +
                "Take one down and pass it around, 98 bottles of beer on the wall.\n" +
                "98 bottles of beer on the wall, 98 bottles of beer.\n" +
                "Take one down and pass it around, 97 bottles of beer on the wall.\n" +
                "97 bottles of beer on the wall, 97 bottles of beer.\n" +
                "Take one down and pass it around, 96 bottles of beer on the wall.\n" +
                "96 bottles of beer on the wall, 96 bottles of beer.\n" +
                "Take one down and pass it around, 95 bottles of beer on the wall.\n" +
                "95 bottles of beer on the wall, 95 bottles of beer.\n" +
                "Take one down and pass it around, 94 bottles of beer on the wall.\n" +
                "94 bottles of beer on the wall, 94 bottles of beer.\n" +
                "Take one down and pass it around, 93 bottles of beer on the wall.\n" +
                "93 bottles of beer on the wall, 93 bottles of beer.\n" +
                "Take one down and pass it around, 92 bottles of beer on the wall.\n" +
                "92 bottles of beer on the wall, 92 bottles of beer.\n" +
                "Take one down and pass it around, 91 bottles of beer on the wall.\n" +
                "91 bottles of beer on the wall, 91 bottles of beer.\n" +
                "Take one down and pass it around, 90 bottles of beer on the wall.\n" +
                "90 bottles of beer on the wall, 90 bottles of beer.\n" +
                "Take one down and pass it around, 89 bottles of beer on the wall.\n" +
                "89 bottles of beer on the wall, 89 bottles of beer.\n" +
                "Take one down and pass it around, 88 bottles of beer on the wall.\n" +
                "88 bottles of beer on the wall, 88 bottles of beer.\n" +
                "Take one down and pass it around, 87 bottles of beer on the wall.\n" +
                "87 bottles of beer on the wall, 87 bottles of beer.\n" +
                "Take one down and pass it around, 86 bottles of beer on the wall.\n" +
                "86 bottles of beer on the wall, 86 bottles of beer.\n" +
                "Take one down and pass it around, 85 bottles of beer on the wall.\n" +
                "85 bottles of beer on the wall, 85 bottles of beer.\n" +
                "Take one down and pass it around, 84 bottles of beer on the wall.\n" +
                "84 bottles of beer on the wall, 84 bottles of beer.\n" +
                "Take one down and pass it around, 83 bottles of beer on the wall.\n" +
                "83 bottles of beer on the wall, 83 bottles of beer.\n" +
                "Take one down and pass it around, 82 bottles of beer on the wall.\n" +
                "82 bottles of beer on the wall, 82 bottles of beer.\n" +
                "Take one down and pass it around, 81 bottles of beer on the wall.\n" +
                "81 bottles of beer on the wall, 81 bottles of beer.\n" +
                "Take one down and pass it around, 80 bottles of beer on the wall.\n" +
                "80 bottles of beer on the wall, 80 bottles of beer.\n" +
                "Take one down and pass it around, 79 bottles of beer on the wall.\n" +
                "79 bottles of beer on the wall, 79 bottles of beer.\n" +
                "Take one down and pass it around, 78 bottles of beer on the wall.\n" +
                "78 bottles of beer on the wall, 78 bottles of beer.\n" +
                "Take one down and pass it around, 77 bottles of beer on the wall.\n" +
                "77 bottles of beer on the wall, 77 bottles of beer.\n" +
                "Take one down and pass it around, 76 bottles of beer on the wall.\n" +
                "76 bottles of beer on the wall, 76 bottles of beer.\n" +
                "Take one down and pass it around, 75 bottles of beer on the wall.\n" +
                "75 bottles of beer on the wall, 75 bottles of beer.\n" +
                "Take one down and pass it around, 74 bottles of beer on the wall.\n" +
                "74 bottles of beer on the wall, 74 bottles of beer.\n" +
                "Take one down and pass it around, 73 bottles of beer on the wall.\n" +
                "73 bottles of beer on the wall, 73 bottles of beer.\n" +
                "Take one down and pass it around, 72 bottles of beer on the wall.\n" +
                "72 bottles of beer on the wall, 72 bottles of beer.\n" +
                "Take one down and pass it around, 71 bottles of beer on the wall.\n" +
                "71 bottles of beer on the wall, 71 bottles of beer.\n" +
                "Take one down and pass it around, 70 bottles of beer on the wall.\n" +
                "70 bottles of beer on the wall, 70 bottles of beer.\n" +
                "Take one down and pass it around, 69 bottles of beer on the wall.\n" +
                "69 bottles of beer on the wall, 69 bottles of beer.\n" +
                "Take one down and pass it around, 68 bottles of beer on the wall.\n" +
                "68 bottles of beer on the wall, 68 bottles of beer.\n" +
                "Take one down and pass it around, 67 bottles of beer on the wall.\n" +
                "67 bottles of beer on the wall, 67 bottles of beer.\n" +
                "Take one down and pass it around, 66 bottles of beer on the wall.\n" +
                "66 bottles of beer on the wall, 66 bottles of beer.\n" +
                "Take one down and pass it around, 65 bottles of beer on the wall.\n" +
                "65 bottles of beer on the wall, 65 bottles of beer.\n" +
                "Take one down and pass it around, 64 bottles of beer on the wall.\n" +
                "64 bottles of beer on the wall, 64 bottles of beer.\n" +
                "Take one down and pass it around, 63 bottles of beer on the wall.\n" +
                "63 bottles of beer on the wall, 63 bottles of beer.\n" +
                "Take one down and pass it around, 62 bottles of beer on the wall.\n" +
                "62 bottles of beer on the wall, 62 bottles of beer.\n" +
                "Take one down and pass it around, 61 bottles of beer on the wall.\n" +
                "61 bottles of beer on the wall, 61 bottles of beer.\n" +
                "Take one down and pass it around, 60 bottles of beer on the wall.\n" +
                "60 bottles of beer on the wall, 60 bottles of beer.\n" +
                "Take one down and pass it around, 59 bottles of beer on the wall.\n" +
                "59 bottles of beer on the wall, 59 bottles of beer.\n" +
                "Take one down and pass it around, 58 bottles of beer on the wall.\n" +
                "58 bottles of beer on the wall, 58 bottles of beer.\n" +
                "Take one down and pass it around, 57 bottles of beer on the wall.\n" +
                "57 bottles of beer on the wall, 57 bottles of beer.\n" +
                "Take one down and pass it around, 56 bottles of beer on the wall.\n" +
                "56 bottles of beer on the wall, 56 bottles of beer.\n" +
                "Take one down and pass it around, 55 bottles of beer on the wall.\n" +
                "55 bottles of beer on the wall, 55 bottles of beer.\n" +
                "Take one down and pass it around, 54 bottles of beer on the wall.\n" +
                "54 bottles of beer on the wall, 54 bottles of beer.\n" +
                "Take one down and pass it around, 53 bottles of beer on the wall.\n" +
                "53 bottles of beer on the wall, 53 bottles of beer.\n" +
                "Take one down and pass it around, 52 bottles of beer on the wall.\n" +
                "52 bottles of beer on the wall, 52 bottles of beer.\n" +
                "Take one down and pass it around, 51 bottles of beer on the wall.\n" +
                "51 bottles of beer on the wall, 51 bottles of beer.\n" +
                "Take one down and pass it around, 50 bottles of beer on the wall.\n" +
                "50 bottles of beer on the wall, 50 bottles of beer.\n" +
                "Take one down and pass it around, 49 bottles of beer on the wall.\n" +
                "49 bottles of beer on the wall, 49 bottles of beer.\n" +
                "Take one down and pass it around, 48 bottles of beer on the wall.\n" +
                "48 bottles of beer on the wall, 48 bottles of beer.\n" +
                "Take one down and pass it around, 47 bottles of beer on the wall.\n" +
                "47 bottles of beer on the wall, 47 bottles of beer.\n" +
                "Take one down and pass it around, 46 bottles of beer on the wall.\n" +
                "46 bottles of beer on the wall, 46 bottles of beer.\n" +
                "Take one down and pass it around, 45 bottles of beer on the wall.\n" +
                "45 bottles of beer on the wall, 45 bottles of beer.\n" +
                "Take one down and pass it around, 44 bottles of beer on the wall.\n" +
                "44 bottles of beer on the wall, 44 bottles of beer.\n" +
                "Take one down and pass it around, 43 bottles of beer on the wall.\n" +
                "43 bottles of beer on the wall, 43 bottles of beer.\n" +
                "Take one down and pass it around, 42 bottles of beer on the wall.\n" +
                "42 bottles of beer on the wall, 42 bottles of beer.\n" +
                "Take one down and pass it around, 41 bottles of beer on the wall.\n" +
                "41 bottles of beer on the wall, 41 bottles of beer.\n" +
                "Take one down and pass it around, 40 bottles of beer on the wall.\n" +
                "40 bottles of beer on the wall, 40 bottles of beer.\n" +
                "Take one down and pass it around, 39 bottles of beer on the wall.\n" +
                "39 bottles of beer on the wall, 39 bottles of beer.\n" +
                "Take one down and pass it around, 38 bottles of beer on the wall.\n" +
                "38 bottles of beer on the wall, 38 bottles of beer.\n" +
                "Take one down and pass it around, 37 bottles of beer on the wall.\n" +
                "37 bottles of beer on the wall, 37 bottles of beer.\n" +
                "Take one down and pass it around, 36 bottles of beer on the wall.\n" +
                "36 bottles of beer on the wall, 36 bottles of beer.\n" +
                "Take one down and pass it around, 35 bottles of beer on the wall.\n" +
                "35 bottles of beer on the wall, 35 bottles of beer.\n" +
                "Take one down and pass it around, 34 bottles of beer on the wall.\n" +
                "34 bottles of beer on the wall, 34 bottles of beer.\n" +
                "Take one down and pass it around, 33 bottles of beer on the wall.\n" +
                "33 bottles of beer on the wall, 33 bottles of beer.\n" +
                "Take one down and pass it around, 32 bottles of beer on the wall.\n" +
                "32 bottles of beer on the wall, 32 bottles of beer.\n" +
                "Take one down and pass it around, 31 bottles of beer on the wall.\n" +
                "31 bottles of beer on the wall, 31 bottles of beer.\n" +
                "Take one down and pass it around, 30 bottles of beer on the wall.\n" +
                "30 bottles of beer on the wall, 30 bottles of beer.\n" +
                "Take one down and pass it around, 29 bottles of beer on the wall.\n" +
                "29 bottles of beer on the wall, 29 bottles of beer.\n" +
                "Take one down and pass it around, 28 bottles of beer on the wall.\n" +
                "28 bottles of beer on the wall, 28 bottles of beer.\n" +
                "Take one down and pass it around, 27 bottles of beer on the wall.\n" +
                "27 bottles of beer on the wall, 27 bottles of beer.\n" +
                "Take one down and pass it around, 26 bottles of beer on the wall.\n" +
                "26 bottles of beer on the wall, 26 bottles of beer.\n" +
                "Take one down and pass it around, 25 bottles of beer on the wall.\n" +
                "25 bottles of beer on the wall, 25 bottles of beer.\n" +
                "Take one down and pass it around, 24 bottles of beer on the wall.\n" +
                "24 bottles of beer on the wall, 24 bottles of beer.\n" +
                "Take one down and pass it around, 23 bottles of beer on the wall.\n" +
                "23 bottles of beer on the wall, 23 bottles of beer.\n" +
                "Take one down and pass it around, 22 bottles of beer on the wall.\n" +
                "22 bottles of beer on the wall, 22 bottles of beer.\n" +
                "Take one down and pass it around, 21 bottles of beer on the wall.\n" +
                "21 bottles of beer on the wall, 21 bottles of beer.\n" +
                "Take one down and pass it around, 20 bottles of beer on the wall.\n" +
                "20 bottles of beer on the wall, 20 bottles of beer.\n" +
                "Take one down and pass it around, 19 bottles of beer on the wall.\n" +
                "19 bottles of beer on the wall, 19 bottles of beer.\n" +
                "Take one down and pass it around, 18 bottles of beer on the wall.\n" +
                "18 bottles of beer on the wall, 18 bottles of beer.\n" +
                "Take one down and pass it around, 17 bottles of beer on the wall.\n" +
                "17 bottles of beer on the wall, 17 bottles of beer.\n" +
                "Take one down and pass it around, 16 bottles of beer on the wall.\n" +
                "16 bottles of beer on the wall, 16 bottles of beer.\n" +
                "Take one down and pass it around, 15 bottles of beer on the wall.\n" +
                "15 bottles of beer on the wall, 15 bottles of beer.\n" +
                "Take one down and pass it around, 14 bottles of beer on the wall.\n" +
                "14 bottles of beer on the wall, 14 bottles of beer.\n" +
                "Take one down and pass it around, 13 bottles of beer on the wall.\n" +
                "13 bottles of beer on the wall, 13 bottles of beer.\n" +
                "Take one down and pass it around, 12 bottles of beer on the wall.\n" +
                "12 bottles of beer on the wall, 12 bottles of beer.\n" +
                "Take one down and pass it around, 11 bottles of beer on the wall.\n" +
                "11 bottles of beer on the wall, 11 bottles of beer.\n" +
                "Take one down and pass it around, 10 bottles of beer on the wall.\n" +
                "10 bottles of beer on the wall, 10 bottles of beer.\n" +
                "Take one down and pass it around, 9 bottles of beer on the wall.\n" +
                "9 bottles of beer on the wall, 9 bottles of beer.\n" +
                "Take one down and pass it around, 8 bottles of beer on the wall.\n" +
                "8 bottles of beer on the wall, 8 bottles of beer.\n" +
                "Take one down and pass it around, 7 bottles of beer on the wall.\n" +
                "7 bottles of beer on the wall, 7 bottles of beer.\n" +
                "Take one down and pass it around, 6 bottles of beer on the wall.\n" +
                "6 bottles of beer on the wall, 6 bottles of beer.\n" +
                "Take one down and pass it around, 5 bottles of beer on the wall.\n" +
                "5 bottles of beer on the wall, 5 bottles of beer.\n" +
                "Take one down and pass it around, 4 bottles of beer on the wall.\n" +
                "4 bottles of beer on the wall, 4 bottles of beer.\n" +
                "Take one down and pass it around, 3 bottles of beer on the wall.\n" +
                "3 bottles of beer on the wall, 3 bottles of beer.\n" +
                "Take one down and pass it around, 2 bottles of beer on the wall.\n" +
                "2 bottles of beer on the wall, 2 bottles of beer.\n" +
                "Take one down and pass it around, 1 bottle of beer on the wall.\n" +
                "1 bottle of beer on the wall, 1 bottle of beer.\n" +
                "Take one down and pass it around, no more bottles of beer on the wall.\n" +
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.";

        getDriver().findElement(By.xpath("//a[@href='lyrics.html']")).click();
        WebElement songCheck = getDriver().findElement(
                By.xpath("//div[@id='main']"));

        Assert.assertEquals(songCheck.getText(), expectedResult);
    }

    @Test
    public void testPagesStartingLanguagesWithLetterJ() {
        getDriver().get(URL);

        String expectedResult = "All languages starting with the letter J " +
                "are shown, sorted by Language.";

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        WebElement textCheck = getDriver().findElement(By.xpath("//p"));

        Assert.assertEquals(textCheck.getText(), expectedResult);
    }

    @Test
    public void testLastLanguageInTable() {
        getDriver().get(URL);

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_M).click();
        WebElement lastLanguageInTable = getDriver().findElement(
                By.xpath("//a[@href='language-mysql-1252.html']"));

        Assert.assertEquals(lastLanguageInTable.getText(), "MySQL");
    }

    @Test
    public void testTableHeaders() {
        getDriver().get(URL);

        List<String> expectedResult = Arrays.asList("Language", "Author",
                "Date", "Comments", "Rate");

        getDriver().findElement(BROWSE_LANGUAGES).click();
        List<WebElement> tableHeaders = getDriver().findElements(
                By.tagName("th"));

        for (int i = 0; i < expectedResult.size(); i++) {

            Assert.assertEquals(tableHeaders.get(i).getText(),
                    expectedResult.get(i));
        }
    }

    @Test
    public void testRowsInTable() {
        getDriver().get(URL);

        List<String> expectedResult = Arrays.asList(
                "Mathematica", "Brenton Bostick", "03/16/06", "1");

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_M).click();

        String solutionLanguage = getDriver().findElement(By.xpath(
                "//a[@href='language-mathematica-1090.html']")).getText();
        String authorLanguage = getDriver().findElement(
                By.xpath("//td[text()='Brenton Bostick']")).getText();
        String dateUpdate = getDriver().findElement(
                By.xpath("//td[text()='03/16/06']")).getText();
        String numberComments = getDriver().findElement(
                By.xpath("//a[text()='Mathematica']/parent::td" +
                        "/following-sibling::td[3]")).getText();

        List<String> actualResult = Arrays.asList(solutionLanguage,
                authorLanguage, dateUpdate, numberComments);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableNumberLanguages() {
        getDriver().get(URL);

        List<String> expectedResult = Arrays.asList("1C Enterprize", "3code",
                "4D", "4DOS Batch", "4Test", "6502 Assembler",
                "6800 Assembler", "8008 Assembler", "96", "99");

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(By.cssSelector("a[href='0.html']")).click();
        List<WebElement> numberLanguages = getDriver().findElements(
                By.cssSelector("[href|='language']"));

        for (int i = 0; i < numberLanguages.size(); i++) {

            Assert.assertEquals(numberLanguages.get(i).getText(),
                    expectedResult.get(i));
        }
    }

    @Test
    public void TestErrorMessage() {
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Igor");
        getDriver().findElement(By.xpath("//input[@name='location']"))
                .sendKeys("Belarus");
        getDriver().findElement(By.xpath("//input[@name='email']"))
                .sendKeys("test@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='homepage']"))
                .sendKeys("http://");
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys("123");
        getDriver().findElement(By.xpath("//textarea[@name='comment']"))
                .sendKeys("Hello World!!!");
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();
        WebElement errorMessage = getDriver().findElement(
                By.cssSelector("#main>p"));

        Assert.assertEquals(errorMessage.getText(),
                "Error: Error: Invalid security code.");
    }

    @Test
    public void testBookmarksReddit() {
        getDriver().get(URL);

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        getDriver().findElement(By.cssSelector("[href*='julie']")).click();
        getDriver().findElement(By.cssSelector("[title='reddit']")).click();
        String goingWebsiteReddit = getDriver().getTitle();

        Assert.assertTrue(goingWebsiteReddit.contains("reddit.com"));
    }

    @Test
    public void testPositionRankingLanguagesShakespeare() {
        getDriver().get(URL);

        getDriver().findElement(
                By.cssSelector("a[href='/toplist.html']")).click();

        String[] topRated = new String[20];
        boolean positionInTop20 = false;

        for (int i = 0; i < topRated.length; i++) {
            topRated[i] = getDriver().findElement(
                    By.xpath("//tr[" + (i + 2) + "]")).getText();
            if (topRated[i].contains(SHAKESPEARE)) {
                positionInTop20 = true;
                break;
            }
        }

        getDriver().findElement(
                By.cssSelector("a[href='./toplist_esoteric.html']")).click();

        boolean positionInTop10 = false;

        for (int i = 0; i < 10 ; i++) {
            if (getDriver().findElement(By.xpath("//tr[" + (i + 2) + "]"))
                    .getText().contains(SHAKESPEARE)) {
                positionInTop10 = true;
            }
        }

        getDriver().findElement(
                By.cssSelector("a[href='./tophits.html']")).click();

        boolean positionInTop6 = false;

        for (int i = 0; i < 6; i++) {
            if (getDriver().findElement(By.xpath("//tr[" + (i + 2) + "]"))
                    .getText().contains(SHAKESPEARE)) {
                positionInTop6 = true;
            }
        }


        getDriver().findElement(
                By.cssSelector("a[href='./toplist_real.html']")).click();

        String[] topRatedReal = new String[25];
        boolean positionInTop25 = false;

        for (int i = 0; i < topRatedReal.length; i++) {
            topRatedReal[i] = getDriver().findElement(
                    By.xpath("//tr[" + (i + 2) + "]")).getText();
            if (!topRatedReal[i].contains(SHAKESPEARE)) {
                positionInTop25 = true;
            }
        }

        Assert.assertTrue(positionInTop20);
        Assert.assertTrue(positionInTop10);
        Assert.assertTrue(positionInTop6);
        Assert.assertTrue(positionInTop25);
    }

    @Test
    public void testNumberSolutionsVersionsJava() {
        getDriver().get(URL);

        List<String> alternativeVersionsFromTable = Arrays.asList(
                "standard version",
                "exception oriented",
                "bytecode-version with loader",
                "Java 5.0 object-oriented version",
                "Singing with Java Speech API");

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        getDriver().findElement(LANGUAGE_JAVA).click();
        WebElement versionLanguageJava = getDriver().findElement(
                OBJECT_ORIENTED_VERSION);

        List<WebElement> versionsLanguagesFromTable = getDriver().findElements(
                By.xpath("//table[@id='category']//td[1]"));

        for (int i = 0; i < alternativeVersionsFromTable.size(); i++) {

            Assert.assertEquals(versionsLanguagesFromTable.get(i).getText(),
                    alternativeVersionsFromTable.get(i));
        }

        Assert.assertEquals(versionLanguageJava.getText(),
                "(object-oriented version)");
    }

    @Test(description = "Version № 2")
    public void testNumberSolutionsVersionsJava2() {
        getDriver().get(URL);

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        getDriver().findElement(LANGUAGE_JAVA).click();

        int versionsLanguages = getDriver().findElements(
                By.xpath("//table[@id='category']//td[1]")).size() +
                getDriver().findElements(OBJECT_ORIENTED_VERSION).size();

        Assert.assertEquals(versionsLanguages, 6);
    }

    @Test
    public void testNumberComments() {
        getDriver().get(URL);

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        getDriver().findElement(LANGUAGE_JAVA).click();

        int index = Integer.parseInt(getDriver().findElement(
                By.xpath("//strong[text()='Comments:']/parent::td" +
                        "//following-sibling::td")).getText());

        boolean result = true;
        List<WebElement> tableResult = getDriver().findElements(
                By.xpath("//tr/td[4]"));

        for (int i = 0; i < tableResult.size(); i++) {
            if (index < Integer.parseInt(tableResult.get(i).getText())) {
                result = false;
            }
        }
        Assert.assertTrue(result);
    }

    @Test(description = "Version № 2")
    public void testNumberComments2() {
        getDriver().get(URL);

        getDriver().findElement(BROWSE_LANGUAGES).click();
        getDriver().findElement(SUBMENU_J).click();
        getDriver().findElement(LANGUAGE_JAVA).click();

        int objectOrientedVersionComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//strong[text()='Comments:']/parent::td" +
                        "//following-sibling::td")).getText());
        int standardVersionComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//a[@href='language-java-4.html']" +
                        "/parent::td//following-sibling::td[3]")).getText());
        int exceptionOrientedComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//a[@href='language-java-866.html']" +
                        "/parent::td//following-sibling::td[3]")).getText());
        int bytecodeVersionWithLoaderComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//a[@href='language-java-1162.html']" +
                        "/parent::td//following-sibling::td[3]")).getText());
        int java5ObjectOrientedVersionComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//a[@href='language-java-950.html']" +
                        "/parent::td//following-sibling::td[3]")).getText());
        int singingWithJavaSpeechApiComments = Integer.parseInt(getDriver().
                findElement(By.xpath("//a[@href='language-java-1148.html']" +
                        "/parent::td//following-sibling::td[3]")).getText());

        int maxComments = Math.max(objectOrientedVersionComments,
                Math.max(standardVersionComments,
                        exceptionOrientedComments));
        int maxComments2 = Math.max(bytecodeVersionWithLoaderComments,
                Math.max(java5ObjectOrientedVersionComments,
                        singingWithJavaSpeechApiComments));

        Assert.assertEquals(Math.max(maxComments, maxComments2),
                objectOrientedVersionComments);
    }
}
