package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Song99BottlesJuliaSabTest extends BaseTest {

    private List<WebElement> textOfSong;
    private List<WebElement> tableHeaders;

    private static final String URL_BASE = "http://www.99-bottles-of-beer.net/";

    private static final By XPATH_BROWSE_LANGUAGES_LINK = By.xpath("//li/a[@href='/abc.html']");
    private static final By XPATH_LETTER_J = By.xpath("//a[@href='j.html']");
    private static final By XPATH_LETTER_M = By.xpath("//a[@href='m.html']");
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
    private static final By XPATH_SUBMIT_BUTTON = By.xpath("//input[@name='submit']");
    private static final By XPATH_ERROR_MESSAGE = By.xpath("//div[@id='main']/p");

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
        getDriver().findElement(XPATH_INPUT_SECURITY_CODE).sendKeys(randomNumber + "");
        getDriver().findElement(XPATH_SUBMIT_BUTTON).click();
        String actualResult = getDriver().findElement(XPATH_ERROR_MESSAGE).getText();
        Assert.assertEquals(actualResult, "Error: Please enter at least a message, your email address and the security code.");
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
