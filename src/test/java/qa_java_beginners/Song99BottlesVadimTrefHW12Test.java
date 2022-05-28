package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class Song99BottlesVadimTrefHW12Test extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void test12_01ContentInSubmenuJOfMenuBrowseLanguages() {

        String expectedResult = "All languages starting with the letter J "
                + "are shown, sorted by Language.";

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement paragraphText = getDriver().findElement(
                By.xpath("//div[@id='main']/p"));
        String actualResult = paragraphText.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_02ContentInSubmenuMOfMenuBrowseLanguages() {

        String expectedResult = "MySQL";

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        WebElement lastLanguage = getDriver().findElement(
                By.xpath("//a[@href='language-mysql-1252.html']"));
        String actualResult = lastLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_03TableHeaderInMenuItemBrowseLanguages() {

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Language");
        expectedResult.add("Author");
        expectedResult.add("Date");
        expectedResult.add("Comments");
        expectedResult.add("Rate");

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        List<WebElement> tableHeader = getDriver().findElements(
                By.xpath("//tbody/tr[1]/th"));
        List<String> actualResult = new ArrayList<>();
        for (WebElement name : tableHeader) {
            actualResult.add(name.getText());
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_04InfoOfMathematicaLanguage() {

        String[] expectedResult = {"Brenton Bostick",
                                   "03/16/06",
                                   "1"
                                  };

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String[] actualResult = new String[3];
        for (int i = 0; i < 3; i++) {
            actualResult[i] = getDriver().findElement(By.xpath(
                    "//a[text()='Mathematica']/../../td[" + (i + 2) + "]"))
                    .getText();
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_05_10LanguagesNamesStartWithNumber() {

        int expectedResult = 10;

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();
        List<WebElement> languages = getDriver().findElements(
                By.xpath("//tr[@onmouseover]"));
        int actualResult = languages.size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_06ErrorAppearsIfEnterInvalidSecurityCode() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/guestbookv2.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./signv2.html']")).click();
        getDriver().findElement(By.name("name")).sendKeys("Vadim");
        getDriver().findElement(By.name("location")).sendKeys("NY");
        getDriver().findElement(By.name("email")).sendKeys("vtavta@gmail.com");
        String randomNumber = "" + (int) (Math.random() * (1000 - 100) + 100);
        getDriver().findElement(By.name("captcha")).sendKeys(randomNumber);
        getDriver().findElement(By.name("comment"))
                .sendKeys("I love this song!!!");
        getDriver().findElement(By.name("submit")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main']/p")).getText(), expectedResult);
    }

    @Test
    public void test12_07RedirectToRedditLoginPage() {

        String expectedResultURL = "https://www.reddit.com/login/"
                + "?dest=https%3A%2F%2Fwww.reddit"
                + ".com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww"
                + ".99-bottles-of-beer.net%252Flanguage-java-3"
                + ".html%26title%3D99%2520Bottles%2520of%2520"
                + "Beer%2520%257C%2520Language%2520Java";
        String expectedResultTitle = "reddit.com: Log in";

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResultURL);
        Assert.assertEquals(getDriver().getTitle(), expectedResultTitle);
    }

    @Test
    public void test12_08_01IsSnakespeareIn20TopRated() {

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='./toplist.html']"))
                .click();
        String[] topRated20 = new String[20];
        boolean actualResult = false;
        for (int i = 0; i < topRated20.length; i++) {
            topRated20[i] = getDriver().findElement(
                    By.xpath("//tbody/tr[" + (i + 2) + "]//a")).getText();
            if (topRated20[i].contains("Shakespeare")) {
                actualResult = true;
                break;
            }
        }

        Assert.assertTrue(actualResult);
    }

    @Test
    public void test12_08_02IsSnakespeareIn10TopRatedEsoteric() {

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/toplist.html']")).click();
        getDriver().findElement(
                By.xpath("//a[@href='./toplist_esoteric.html']")).click();
        String topRatedEsoteric10 = "";
        boolean actualResult = false;
        for (int i = 0; i < 10; i++) {
            topRatedEsoteric10 += getDriver().findElement(
                    By.xpath("//tbody/tr[" + (i + 2) + "]//a")).getText();
            if (topRatedEsoteric10.contains("Shakespeare")) {
                actualResult = true;
                break;
            }
        }

        Assert.assertTrue(actualResult);
    }

    @Test
    public void test12_08_03IsSnakespeareIn6TopHits() {

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/toplist.html']")).click();
        getDriver().findElement(
                By.xpath("//a[@href='./tophits.html']")).click();
        String topHits6 = "";
        boolean actualResult = false;
        for (int i = 0; i < 6; i++) {
            topHits6 = topHits6.concat(getDriver().findElement(
                    By.xpath("//tbody/tr[" + (i + 2) + "]//a")).getText());
            if (topHits6.contains("Shakespeare")) {
                actualResult = true;
                break;
            }
        }

        Assert.assertTrue(actualResult);
    }

    @Test
    public void test12_08_04IsSnakespeareNotInTopRatedReal() {

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='./toplist_real.html']"))
                .click();

        List<WebElement> topRatedReal = getDriver().findElements(
                By.xpath("//tbody/tr//a"));
        boolean actualResult = false;
        for (WebElement language : topRatedReal) {
            if (language.getText().equals("Shakespeare")) {
                actualResult = true;
            }
        }

        Assert.assertFalse(actualResult);
    }

    @Test
    public void test12_09_6VersionsOfLanguageJava() {

        int expectedResult = 6;

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']"))
                .click();
        List<WebElement> allJavaVersion = getDriver().findElements(
                By.xpath("//tbody/tr[@onmouseover]"));

        Assert.assertEquals(allJavaVersion.size() + 1, expectedResult);
    }

    @Test
    public void test12_10IsMostBigCountOfCommentsJavaObjectOrientedVersion() {

        getDriver().get(URL);

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']"))
                .click();

        int countCommentsOOVersion =Integer.parseInt(getDriver().findElement(
                By.xpath("//div[@id='main']/table/tbody/tr[4]/td[2]"))
                .getText());
        List<WebElement> otherCommentsJavaVersion = getDriver().findElements(
                By.xpath("//tbody/tr[@onmouseover]/td[4]"));
        boolean actualResult = true;
        for (WebElement count : otherCommentsJavaVersion) {
            if (Integer.parseInt(count.getText()) > countCommentsOOVersion) {
                actualResult = false;
                break;
            }
        }

        Assert.assertTrue(actualResult);
    }
}





