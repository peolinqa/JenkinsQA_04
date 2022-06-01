package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class DVRudoi99BottlesOfBeerTest extends BaseTest {
    public WebElement searchLetter(String a) {
        return getDriver().findElement(
                By.xpath(String.format("//a[@href = '%s.html']", a)));
    }

    public static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void test1LetterJ() {
        getDriver().get(URL);

        WebElement buttonBrowseLanguages = getDriver().findElement(
                By.linkText("Browse Languages"));
        buttonBrowseLanguages.click();

        WebElement buttonLetterJ = searchLetter("j");
        buttonLetterJ.click();

        String actualText = getDriver()
                .findElement(By.xpath("//p[contains(text(),'All')]"))
                .getText();

        Assert.assertEquals(
                actualText, "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void test2LastMySql() {
        getDriver().get(URL);

        WebElement buttonBrowseLanguages = getDriver().findElement(
                By.xpath("//li/a[@href = '/abc.html']"));
        buttonBrowseLanguages.click();

        WebElement buttonLetterM =  searchLetter("m");
        buttonLetterM.click();

        String actualText = getDriver()
                .findElement(By.xpath("//tr[last()]//a"))
                .getText();

        Assert.assertEquals(actualText, "MySQL");
    }

    @Test
    public void test3Menu() {
        getDriver().get(URL);

        WebElement buttonBrowseLanguages = getDriver().findElement(
                By.xpath("//li/a[@href = '/abc.html']"));
        buttonBrowseLanguages.click();

        String actualText = getDriver()
                .findElement(By.xpath("//tr[1]"))
                .getText();

        Assert.assertEquals(actualText, "Language Author Date Comments Rate");
    }

    @Test
    public void test4Mathematical() {
        String expectedAuthor = "Brenton Bostick";
        String expectedDate = "03/16/06";
        String expectedComments = "1";

        getDriver().get(URL);

        WebElement buttonBrowseLanguages = getDriver().findElement(
                By.xpath("//li/a[@href = '/abc.html']"));
        buttonBrowseLanguages.click();

        WebElement buttonLetterM = getDriver().findElement(
                By.xpath("//a[@href = 'm.html']"));
        buttonLetterM.click();

        String actualAuthor = getDriver()
                .findElement(By.xpath("//tr[22]/td[2]"))
                .getText();

        String actualDate = getDriver()
                .findElement(By.xpath("//tr[22]/td[3]"))
                .getText();

        String actualComments = getDriver()
                .findElement(By.xpath("//tr[22]/td[4]"))
                .getText();

        Assert.assertEquals(actualAuthor, expectedAuthor);
        Assert.assertEquals(actualDate, expectedDate);
        Assert.assertEquals(actualComments, expectedComments);
    }

    @Test
    public void test5SizeNumbers() {
        getDriver().get(URL);

        WebElement buttonBrowseLanguages = getDriver().findElement(
                By.xpath("//li/a[@href = '/abc.html']"));
        buttonBrowseLanguages.click();

        WebElement buttonLetter = getDriver().findElement(By.xpath("//a[@href = '0.html']"));
        buttonLetter.click();

        int actualRowCount = getDriver().findElements(
                By.xpath("//tbody/tr[contains(@onmouseover,'setPointer')]")).size();

        Assert.assertEquals(actualRowCount, 10);
    }

    @Test
    public void test6SignGuestbook() {
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        WebElement inputName = getDriver().findElement(
                By.name("name"));
        inputName.sendKeys("DDDDD");

        WebElement inputLocation = getDriver().findElement(
                By.name("location"));
        inputLocation.sendKeys("DDDDD");

        WebElement inputEmail = getDriver().findElement(
                By.name("email"));
        inputEmail.sendKeys("DDDDD");

        WebElement inputHomepage = getDriver().findElement(
                By.name("homepage"));
        inputHomepage.sendKeys("DDDDD");

        WebElement inputSecurityCode = getDriver().findElement(
                By.name("captcha"));
        inputSecurityCode.sendKeys(Integer.toString(100 + (int) (Math.random() * 899)));

        WebElement inputMessage = getDriver().findElement(
                By.name("comment"));
        inputMessage.sendKeys("333");

        WebElement button = getDriver().findElement(
                By.name("submit"));
        button.click();

        String actualError = getDriver()
                .findElement(By.xpath("//div[@id='main']/p"))
                .getText();

        Assert.assertEquals(actualError, "Error: Error: Invalid security code.");
    }

    @Test
    public void test7LoginDiigo() {
        String expectedLoginDiigo = "Sign in diigo";

        getDriver().get(URL);

        getDriver().findElement(By.linkText("Browse Languages")).click();
        getDriver().findElement(By.linkText("M")).click();
        getDriver().findElement(By.linkText("Mathematica")).click();
        getDriver().findElement(By.xpath("//a[@href='language-mathematica-1712.html']")).click();
        getDriver().findElement(By.xpath("//a[@title='diigo']")).click();

        String actualSignInDiigo = getDriver().findElement(By.className("tile")).getText();

        Assert.assertEquals(actualSignInDiigo, expectedLoginDiigo);
    }

    @Test
    public void test8ShakespeareTop() {
        boolean expectedShakespeareRating = true;
        boolean actualTopRated = false;
        boolean actualEsotericLanguages = false;
        boolean actualTopHits = false;
        boolean actualTopRatedReal = true;

        getDriver().get(URL);

        getDriver().findElement(By.linkText("Top Lists")).click();
        String positionTopRated = getDriver().findElement(
                        By.xpath("//a[@href='language-shakespeare-664.html']/ancestor::tr/td[1]"))
                .getText()
                .replace(".", "");
        int rang1 = Integer.parseInt(positionTopRated);
        if (rang1 < 21) {
            actualTopRated = true;
        }

        getDriver().findElement(By.linkText("Top Rated Esoteric")).click();
        String positionEsotericLanguages = getDriver().findElement(
                        By.xpath("//a[@href='language-shakespeare-664.html']/ancestor::tr/td[1]"))
                .getText()
                .replace(".", "");
        int rang2 = Integer.parseInt(positionEsotericLanguages);
        if (rang2 < 11) {
            actualEsotericLanguages = true;
        }

        getDriver().findElement(By.linkText("Top Hits")).click();
        String positionTopHits = getDriver().findElement(
                        By.xpath("//a[@href='language-shakespeare-664.html']/ancestor::tr/td[1]"))
                .getText()
                .replace(".", "");
        int rang = Integer.parseInt(positionTopHits);
        if (rang < 7) {
            actualTopHits = true;
        }

        getDriver().findElement(By.linkText("Top Rated Real")).click();
        String[] arrTopReal = new String[25];
        for (int i = 0; i < arrTopReal.length; i++) {
            int index = i + 2;
            arrTopReal[i] = getDriver().
                    findElement(By.xpath("//tr[" + index + "]"))
                    .getText();
            if (arrTopReal[i].toLowerCase().contains("shakespeare")) {
                actualTopRatedReal = false;
            }
        }

        Assert.assertEquals(actualTopRated, expectedShakespeareRating);
        Assert.assertEquals(actualEsotericLanguages, expectedShakespeareRating);
        Assert.assertEquals(actualTopHits, expectedShakespeareRating);
        Assert.assertEquals(actualTopRatedReal, expectedShakespeareRating);
    }

    @Test
    public void test9SixVersionsJava() {
        getDriver().get(URL);

        getDriver().findElement(By.linkText("Search Languages")).click();
        getDriver().findElement(By.name("search")).sendKeys("Java");
        getDriver().findElement(By.name("submitsearch")).click();

        int actualResult = 0;
        for (int i = 0; i < 14; i++) {
            int index = i + 2;
            if (getDriver().findElement(
                    By.xpath("//tr[" + index + "]/td[1]")).getText().contains("Java (")
                    || getDriver().findElement(
                    By.xpath("//tr[" + index + "]/td[1]")).getText().equals("Java")) {
                actualResult++;
            }
        }

        Assert.assertEquals(actualResult, 6);
    }

    @Test
    public void test10СommentsJava() {
        String expectedResult = "Java (object-oriented version)";

        getDriver().get(URL);

        getDriver().findElement(By.linkText("Search Languages")).click();
        getDriver().findElement(By.name("search")).sendKeys("Java");
        getDriver().findElement(By.name("submitsearch")).click();

        int numberOfComments = Integer.MIN_VALUE;
        String actualResult = "";
        for (int i = 0; i < 14; i++) {
            int index = i + 2;
            if (getDriver().findElement(
                    By.xpath("//tr[" + index + "]/td[1]")).getText().contains("Java (")
                    || getDriver().findElement(
                    By.xpath("//tr[" + index + "]/td[1]")).getText().equals("Java")) {
                if (numberOfComments < Integer.parseInt(getDriver().findElement(
                        By.xpath("//tr[" + index + "]/td[4]")).getText())){
                    numberOfComments = Integer.parseInt(getDriver().findElement(
                            By.xpath("//tr[" + index + "]/td[4]")).getText());
                    actualResult = getDriver().findElement(
                            By.xpath("//tr[" + index + "]/td[1]")).getText();

                }
            }
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

}

