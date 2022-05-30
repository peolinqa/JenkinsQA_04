package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.Arrays;
import static java.lang.Integer.parseInt;

public class HW12KononirinIrinaShTest extends BaseTest {

    @Test
    public void testTitleSubmenuByJ() {

        String expectedResult = "All languages starting with the letter J " +
                "are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']"))
                .click();

        getDriver()
                .findElement(
                        By.xpath("//li/a[@href='j.html']"))
                .click();

        String actualResult =
                getDriver()
                        .findElement(
                                By.xpath("//div[@id='main']/p"))
                        .getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLastLangMySQL() {

        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement menuBrowseLang = getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']")
                );
        menuBrowseLang.click();

        WebElement subMenuM = getDriver()
                .findElement(
                        By.xpath("//a[@href='m.html']")
                );
        subMenuM.click();

        WebElement lastLangMySQL = getDriver()
                .findElement(By.xpath("//tr[last()]/td/a")
                );

        String actualResult = lastLangMySQL.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testHeaderOfTable() {

        String expectedResult = "Language Author Date Comments Rate";

        getDriver().get("http://99-bottles-of-beer.net/");

        WebElement menuBrowseLang = getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']"));
        menuBrowseLang.click();

        WebElement headerOfTable = getDriver().findElement(
                By.xpath("//table/tbody/tr"));

        String actualResult = headerOfTable.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableOfMathematica() {

        String expectedResultOne = "Brenton Bostick";
        String expectedResultTwo = "03/16/06";
        String expectedResultThree = "1";

        getDriver().get("http://www.99-bottles-of-beer.net/abc.html");

        WebElement subMenuM = getDriver()
                .findElement(
                        By.xpath("//a[@href='m.html']"));
        subMenuM.click();

        WebElement langMathematika = getDriver().findElement(
                By.xpath("//a[@href='language-mathematica-1090."
                        + "html']"));
        langMathematika.click();

        WebElement tableOfMathemAuthor = getDriver().findElement(
                By.xpath("//table[@style='margin: 7px; " +
                        "padding: 0;;']/tbody/tr[2]/td[last()]")
        );
        String actualResultOne = tableOfMathemAuthor.getText();

        WebElement tableOfMathemDate = getDriver().findElement(
                By.xpath("//table[@style='margin: 7px; " +
                        "padding: 0;;']/tbody/tr[1]/td[last()]")
        );
        String actualResultTwo = tableOfMathemDate.getText();

        WebElement tableOfMathemComment = getDriver().findElement(
                By.xpath("//table[@style='margin: 7px; " +
                        "padding: 0;;']/tbody/tr[4]/td[last()]")
        );
        String actualResultThree = tableOfMathemComment.getText();

        Assert.assertEquals(actualResultOne, expectedResultOne);
        Assert.assertEquals(actualResultTwo, expectedResultTwo);
        Assert.assertEquals(actualResultThree, expectedResultThree);
    }

    @Test
    public void testExistTenLangsStartsDigits() {

        getDriver().get("http://99-bottles-of-beer.net/");

        WebElement menuBrowseLang = getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']")
                );
        menuBrowseLang.click();

        WebElement submenuZeroNine = getDriver().
                findElement(By.xpath("//ul[@id='submenu']/" +
                        "li/a[@href='0.html']")
                );
        submenuZeroNine.click();

        String[] countLines = new String[10];

        WebElement firstLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[2]/td/a")
                );
        countLines[0] = firstLine.getText();

        WebElement secondLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[3]/td/a")
                );
        countLines[1] = secondLine.getText();

        WebElement thirdLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[4]/td/a")
                );
        countLines[2] = thirdLine.getText();

        WebElement fourthLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[5]/td/a")
                );
        countLines[3] = fourthLine.getText();

        WebElement fifthLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[6]/td/a")
                );
        countLines[4] = fifthLine.getText();

        WebElement sixthLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[7]/td/a")
                );
        countLines[5] = sixthLine.getText();

        WebElement seventhLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[8]/td/a")
                );
        countLines[6] = seventhLine.getText();

        WebElement eighthLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[9]/td/a")
                );
        countLines[7] = eighthLine.getText();

        WebElement ninethLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[10]/td/a")
                );
        countLines[8] = ninethLine.getText();

        WebElement tenthLine = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[11]/td/a")
                );
        countLines[9] = tenthLine.getText();

        System.out.println(Arrays.toString(countLines));

        Assert.assertEquals(countLines.length, 10);
    }

    @Test
    public void testIncorrectSecurityCode() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(
                        By.xpath("//div[@id='main']/form/p/" +
                                "input[@name='name']"))
                .sendKeys("Irina Sh.");

        getDriver().findElement(
                        By.xpath("//div[@id='main']/form/p/" +
                                "input[@name='location']"))
                .sendKeys("RF");

        getDriver().findElement(
                        By.xpath("//div[@id='main']/form/p/" +
                                "input[@name='email']"))
                .sendKeys("test@gmail.com");

        getDriver().findElement(
                        By.xpath("//div[@id='main']/form/p/" +
                                "input[@name='homepage']"))
                .sendKeys("http://test.com");

        String randomDigit = "" + ((int) (Math.random() * 900) + 100);
        getDriver().findElement(
                        By.xpath("//div[@id='main']/form/p/" +
                                "input[@name='captcha']"))
                .sendKeys(randomDigit);

        getDriver().findElement(
                        By.xpath("//textarea[@name='comment']"))
                .sendKeys("Frist comment");

        getDriver().findElement(
                By.xpath("//input[@type='submit']")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconReddit() {

        String expectedResult = "Log in";

        getDriver().get("http://99-bottles-of-beer.net/");

        WebElement menuBrowseLang = getDriver()
                .findElement(
                        By.xpath("//li/a[@href='/abc.html']")
                );
        menuBrowseLang.click();

        WebElement langAssembler = getDriver().
                findElement(By.xpath("//table[@id='category']/" +
                        "tbody/tr[68]/td/a")
                );
        langAssembler.click();

        WebElement alternativeLangSparc = getDriver().
                findElement(By.xpath("//div[@id='alternatives']" +
                        "/table/tbody/tr[3]/td/a")
                );
        alternativeLangSparc.click();

        WebElement iconReddit = getDriver().
                findElement(By.xpath("//div[@id='voting']/p" +
                        "/a[@title='reddit']")
                );
        iconReddit.click();

        String actualResult = getDriver().
                findElement(
                        By.xpath("//div[@class='Step__content']" +
                                "/h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLangShakespeareTopRated() {

        boolean expectedResult = true;

        getDriver().get("http://www.99-bottles-of-beer.net/toplist.html");

        WebElement numberOfRowOfShakespeareLang =
                getDriver().findElement(
                        By.xpath("//div[@id='main']/table" +
                                "/tbody/tr[17]/td")
                );

        String numberOfRate = numberOfRowOfShakespeareLang.getText();
        int numOfLasttIndex = numberOfRate.lastIndexOf('.');
        String numberWithoutDot = numberOfRate.substring(0, numOfLasttIndex);
        int numOfRate = parseInt(numberWithoutDot);

        boolean actualResult = false;
        if (numOfRate <= 20) {
            actualResult = true;
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLangShakespeareTopRatedEsotericLang() {

        boolean expectedResult = true;

        getDriver().get("http://www.99-bottles-of-beer.net/toplist.html");

        WebElement submenuTopRatedEsotericLang = getDriver().
                findElement(By.xpath("//li/a[@href='." +
                        "/toplist_esoteric.html']")
                );
        submenuTopRatedEsotericLang.click();

        WebElement numberOfRowOfShakespeareLang =
                getDriver().findElement(
                        By.xpath("//div[@id='main']/table" +
                                "/tbody/tr[8]/td")
                );

        String numberOfRate = numberOfRowOfShakespeareLang.getText();
        int numOfLastIndex = numberOfRate.lastIndexOf('.');
        String numberWithoutDot = numberOfRate.substring(0, numOfLastIndex);
        int numOfRate = parseInt(numberWithoutDot);

        boolean actualResult = false;
        if (numOfRate <= 10) {
            actualResult = true;
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLangShakespeareTopRatedTopHits() {

        boolean expectedResult = true;

        getDriver().get("http://www.99-bottles-of-beer.net/toplist.html");

        WebElement submenuTopRatedTopHits = getDriver().
                findElement(By.xpath("//a[@href='." +
                        "/tophits.html']")
                );
        submenuTopRatedTopHits.click();

        WebElement numberOfRowOfShakespeareLang =
                getDriver().findElement(
                        By.xpath("//div[@id='main']" +
                                "/table/tbody/tr[7]/td")
                );

        String numberOfRate = numberOfRowOfShakespeareLang.getText();
        int numOfLasttIndex = numberOfRate.lastIndexOf('.');
        String numberWithoutDot = numberOfRate.substring(0, numOfLasttIndex);
        int numOfRate = parseInt(numberWithoutDot);

        boolean actualResult = false;
        if (numOfRate <= 6) {
            actualResult = true;
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLangShakespeareTopRatedRealLang() {

        boolean expectedResult = true;

        getDriver().get("http://www.99-bottles-of-beer.net/toplist.html");

        WebElement submenuTopRatedRealLang = getDriver().
                findElement(By.xpath("//a[@href='." +
                        "/toplist_real.html']")
                );
        submenuTopRatedRealLang.click();

        String[] tableOfLangsRate = new String[25];
        boolean actualResult = false;

        for (int i = 0; i < tableOfLangsRate.length; i++) {
            int index = i + 2;
            tableOfLangsRate[i] =
                    getDriver().findElement(By.xpath(
                            "//table[@id='category']" +
                                    "/tbody/tr[" + index + "]")).getText()
            ;
            if (!tableOfLangsRate[i].contains("Shakespeare")) {
                actualResult = true;
            }
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testExistSixVersuionsOfJava() {

        int expectedResult = 6;
        int actualResult = 0;

        getDriver().get("http://www.99-bottles-of-beer.net/abc.html");

        WebElement submenuJ = getDriver().
                findElement(By.xpath("//ul[@id='submenu']" +
                        "/li/a[@href='j.html']")
                );
        submenuJ.click();

        WebElement langJava = getDriver().
                findElement(By.
                        xpath("//a[@href='language-java-3.html']")
                );
        langJava.click();

        String nameOfVersion = getDriver().
                findElement(By.xpath("//div[@id='main']" +
                        "/p[@style='padding-top: 0; padding-bottom: 0;']"))
                .getText();

        actualResult++;

        String[] versionArray = new String[5];
        for (int i = 0; i < versionArray.length; i++) {
            int index = i + 2;
            versionArray[i] = getDriver().
                    findElement(By.xpath("//table" +
                            "[@id='category']/tbody/tr[" + index + "]"))
                    .getText();

            if (!versionArray[i].contains(nameOfVersion)) {
                actualResult++;
            }
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCountCommentsOfObjOrientVers() {

        int expectedResult = 33;

        getDriver().get("http://www.99-bottles-of-beer.net/abc.html");

        WebElement submenuJ = getDriver().
                findElement(By.xpath("//ul[@id='submenu']" +
                        "/li/a[@href='j.html']")
                );
        submenuJ.click();

        WebElement langJava = getDriver().
                findElement(By.
                        xpath("//a[@href='language-java-3.html']")
                );
        langJava.click();

        WebElement countCommentsOfObjOrientVers = getDriver()
                .findElement(By.xpath("//table" +
                        "[@style='margin: 7px; padding: 0;;']" +
                        "/tbody/tr[4]/td[2]"));
        int actualResult = parseInt(countCommentsOfObjOrientVers.getText());

        int[] countOfAllComments = new int[5];
        for (int i = 0; i < countOfAllComments.length; i++) {
            int index = i + 2;
            countOfAllComments[i] = parseInt(getDriver().
                    findElement(By.xpath("//table" +
                            "[@id='category']/tbody/tr[" + index + "]/td[4]"))
                    .getText());

            if (countOfAllComments[i] > actualResult) {
                actualResult = countOfAllComments[i];
            }
        }

        Assert.assertEquals(actualResult, expectedResult);
    }
}
