package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

@Ignore
public class Song99BottlesJuliaSabTest extends BaseTest {

    private List<WebElement> textOfSong;
    private List<WebElement> tableHeaders;

    private static final String URL_BASE = "http://www.99-bottles-of-beer.net/";
    private static final By XPATH_BROWSE_LANGUAGES_LINK = By.xpath("//li/a[@href='/abc.html']");
    private static final By XPATH_TOP_LIST_LINK = By.xpath("//li/a[@href='/toplist.html']");
    private static final By XPATH_TOP_RATED_ESOTERIC = By.xpath("//a[@href='./toplist_esoteric.html']");
    private static final By XPATH_TOP_HITS = By.xpath("//a[@href='./tophits.html']");
    private static final By XPATH_TOP_RATED_REAL = By.xpath("//a[@href='./toplist_real.html']");
    private static final By XPATH_LETTER_J = By.xpath("//a[@href='j.html']");
    private static final By XPATH_LETTER_M = By.xpath("//a[@href='m.html']");
    private static final By XPATH_LETTER_A = By.xpath("//a[@href='a.html']");
    private static final By XPATH_MENU_NUMBERS = By.xpath("//a[@href = '0.html']");
    private static final By XPATH_DESCRIPTION_CATEGORY_J = By.xpath("//p[contains(text(), 'All languages')]");
    private static final By XPATH_TEXT_OF_SONG = By.xpath("//div[@id='main']/p");
    private static final By XPATH_LAST_LANGUAGE = By.xpath("//tr[last()]/td/a");
    private static final By XPATH_FIRST_TABLE_HEADERS = By.xpath("//tbody/tr/th");
    private static final By XPATH_TABLE_WITHOUT_HEADERS_START_NUMBER = By.xpath("//tbody/tr[position()>1]");
    private static final By XPATH_GUESTBOOK_MENU = By.xpath("//li/a[@href='/guestbookv2.html']");
    private static final By XPATH_SIGN_GUESTBOOK_MENU = By.xpath("//li/a[@href='./signv2.html']");
    private static final By XPATH_INPUT_NAME = By.xpath("//input[@name='name']");
    private static final By XPATH_INPUT_LOCATION = By.xpath("//input[@name='location']");
    private static final By XPATH_INPUT_EMAIL = By.xpath("//input[@name='email']");
    private static final By XPATH_INPUT_HOME_PAGE = By.xpath("//input[@name='homepage']");
    private static final By XPATH_INPUT_SECURITY_CODE = By.xpath("//input[@name='captcha']");
    private static final By XPATH_INPUT_MESSAGE = By.xpath("//textarea[@name='comment']");
    private static final By XPATH_SUBMIT_BUTTON = By.xpath("//input[@name='submit']");
    private static final By XPATH_ERROR_MESSAGE = By.xpath("//div[@id='main']/p");
    private static final By XPATH_ABAL_LANGUAGE = By.xpath("//a[@href='language-abal-712.html']");
    private static final By XPATH_JAVA_LANGUAGE = By.xpath("//a[@href=\"language-java-3.html\"]");
    private static final By XPATH_TABLE_ALTERNATIVE_VERSION = By.xpath("//div[@id='alternatives']/table");
    private static final By XPATH_JAVA_STANDARD_VERSION = By.xpath("//a[@href='language-java-4.html']");
    private static final By XPATH_ADD_TO_REDDIT = By.xpath("//a[@title='reddit']");
    private static final By XPATH_TABLE_CELL_OF_LANGUAGE = By.xpath("//tr/td[2]");

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

    @Test
    public void testConfirmLastLanguage() {
        String expectedResult = "MySQL";

        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_M).click();

        String actualResult = getDriver().findElement(XPATH_LAST_LANGUAGE).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmTableHeaders() {
        List<String> expectedTableHeaders = Arrays.asList("Language", "Author", "Date", "Comments", "Rate");
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        tableHeaders = getDriver().findElements(XPATH_FIRST_TABLE_HEADERS);
        int actualResultSize = tableHeaders.size();

        Assert.assertEquals(actualResultSize, 5);

        List<String> actualTableHeaders = new ArrayList<>();
        for (WebElement value : tableHeaders) {
            actualTableHeaders.add(value.getText());
        }

        Assert.assertEquals(actualTableHeaders, expectedTableHeaders);
    }

    @Test
    public void testConfirmColumnValuesInRow() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_M).click();
        String[] expectedlArray = {"Mathematica", "Brenton Bostick", "03/16/06", "1"};
        String[] actualArray = new String[4];
        for (int i = 0; i < 4; i++) {
            int position = i + 1;
            actualArray[i] = getDriver().findElement(By
                            .xpath("//a[@href='language-mathematica-1090.html']/ancestor-or-self::tr/td[" + position + "]"))
                    .getText();
        }

        Assert.assertEquals(actualArray, expectedlArray);
    }

    @Test
    public void testConfirmCountOfLanguages() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_MENU_NUMBERS).click();
        List<WebElement> languagesStartWithNumber = getDriver()
                .findElements(XPATH_TABLE_WITHOUT_HEADERS_START_NUMBER);

        Assert.assertEquals(languagesStartWithNumber.size(), 10);
    }

    @Test
    public void testFillRegistrationFormAndConfirmErrorNegative() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_GUESTBOOK_MENU).click();
        getDriver().findElement(XPATH_SIGN_GUESTBOOK_MENU).click();
        getDriver().findElement(XPATH_INPUT_NAME).sendKeys("Name");
        getDriver().findElement(XPATH_INPUT_LOCATION).sendKeys("London");
        getDriver().findElement(XPATH_INPUT_EMAIL).sendKeys("email_test@mail.com");
        getDriver().findElement(XPATH_INPUT_HOME_PAGE).sendKeys("testing");
        int randomNumber = (int) (100 + Math.random() * 900);
        String code = Integer.toString(randomNumber);
        getDriver().findElement(XPATH_INPUT_SECURITY_CODE).sendKeys(code);
        getDriver().findElement(XPATH_INPUT_MESSAGE).sendKeys("testing");
        getDriver().findElement(XPATH_SUBMIT_BUTTON).click();
        String actualResult = getDriver().findElement(XPATH_ERROR_MESSAGE).getText();

        Assert.assertEquals(actualResult, "Error: Error: Invalid security code.");
    }

    @Test
    public void testConfirmNoAlternativeVersion() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_A).click();
        getDriver().findElement(XPATH_ABAL_LANGUAGE).click();
        List<WebElement> table = getDriver().findElements(XPATH_TABLE_ALTERNATIVE_VERSION);

        Assert.assertEquals(table.size(), 0);
    }

    @Test
    public void testConfirmAlternativeVersionAndReddit() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_J).click();
        getDriver().findElement(XPATH_JAVA_LANGUAGE).click();

        List<WebElement> table = getDriver().findElements(XPATH_TABLE_ALTERNATIVE_VERSION);
        Assert.assertTrue(table.size() > 0);

        getDriver().findElement(XPATH_JAVA_STANDARD_VERSION).click();
        getDriver().findElement(XPATH_ADD_TO_REDDIT).click();
        String currentURL = getDriver().getCurrentUrl();

        Assert.assertTrue(currentURL.contains("www.reddit.com/login"));
    }

    @Test
    public void testConfirmIncludedInCommonTopShakespeare() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_TOP_LIST_LINK).click();
        int actualIndex = getIndexFromTableTopLists("Shakespeare");

        Assert.assertTrue(actualIndex < 21);
    }

    @Test
    public void testConfirmIncludedInEsotericTopShakespeare() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_TOP_LIST_LINK).click();
        getDriver().findElement(XPATH_TOP_RATED_ESOTERIC).click();
        int actualIndex = getIndexFromTableTopLists("Shakespeare");

        Assert.assertTrue(actualIndex < 11);
    }

    @Test
    public void testConfirmIncludedInHitsTopShakespeare() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_TOP_LIST_LINK).click();
        getDriver().findElement(XPATH_TOP_HITS).click();
        int actualIndex = getIndexFromTableTopLists("Shakespeare");

        Assert.assertTrue(actualIndex < 7);
    }

    @Test
    public void testConfirmNotIncludedInRealTopShakespeare() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_TOP_LIST_LINK).click();
        getDriver().findElement(XPATH_TOP_RATED_REAL).click();
        int actualIndex = getIndexFromTableTopLists("Shakespeare");

        Assert.assertEquals(actualIndex, Integer.MAX_VALUE);
    }

    @Test
    public void testConfirmNumberOfVersionJava() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_J).click();
        getDriver().findElement(XPATH_JAVA_LANGUAGE).click();
        List<WebElement> table = getDriver().findElements(By.xpath("//table[@id='category']//td[1]"));
        int actualResult = table.size() + 1;

        Assert.assertEquals(actualResult, 6);
    }

    @Test
    public void testConfirmMaxNumberOfCommentsJava() {
        getDriver().get(URL_BASE);
        getDriver().findElement(XPATH_BROWSE_LANGUAGES_LINK).click();
        getDriver().findElement(XPATH_LETTER_J).click();
        getDriver().findElement(XPATH_JAVA_LANGUAGE).click();
        List<WebElement> valueCellVersionFromTable = getDriver().findElements(By.xpath("//table[@id='category']//td[1]"));
        List<WebElement> valueCellCommentsFromTable = getDriver().findElements(By.xpath("//table[@id='category']//td[4]"));

        Map<String, Integer> versionWithComments = new HashMap<>();
        String oopJava = getDriver().findElement(By.xpath("//table/preceding-sibling::p")).getText();
        int numberOfCommentsOOPJava = Integer.parseInt(getDriver()
                .findElement(By
                        .xpath("//strong[contains(text(), 'Comments:')]/following::td[1]"))
                .getText());
        versionWithComments.put(oopJava, numberOfCommentsOOPJava);

        for (int i = 0; i < 5; i++) {
            String keyVersion = valueCellVersionFromTable.get(i).getText();
            int valueNumberComments = Integer.parseInt(valueCellCommentsFromTable.get(i).getText());
            versionWithComments.put(keyVersion, valueNumberComments);
        }

        int maxComments = 0;
        String versionWithMaxComments = "";

        for (Map.Entry<String, Integer> entry : versionWithComments.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            if (maxComments < entry.getValue()) {
                maxComments = entry.getValue();
                versionWithMaxComments = entry.getKey();
            }
        }
        Assert.assertEquals(versionWithMaxComments, "(object-oriented version)");
    }

    private int getIndexFromTableTopLists(String language) {
        List<WebElement> table = getDriver().findElements(XPATH_TABLE_CELL_OF_LANGUAGE);
        int index = Integer.MAX_VALUE;
        for (WebElement languageCell : table) {
            if (languageCell.getText().contains(language)) {
                index = table.indexOf(languageCell) + 1;
                break;
            }
        }
        return index;
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
