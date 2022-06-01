package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class Song99BottlesNataliiaOliverHW12Test extends BaseTest {
    private static final String BASE_URL = "http://99-bottles-of-beer.net/lyrics.html";
    private static final String BROWSE_LANGUAGES = "//ul[@id='menu']/li/a[@href='/abc.html']";

    @Test
    public void testLanguagesStartJ_TC_12_01() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES)).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";
        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMLastTableLanguageMySQL_TC_12_02() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES)).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String expectedResult = "MySQL";
        String actualResult = getDriver().findElement(By.xpath("//tr[last()]/td/a")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableHeaders_TC_12_03() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES)).click();
        String[] expectedResult = {"Language", "Author", "Date", "Comments", "Rate"};
        String[] actualResult = new String[5];
        for (int i = 0; i < actualResult.length; i++) {
            int index = i + 1;
            actualResult[i] = getDriver()
                    .findElement(By.xpath("//tbody/tr/th[" + index + "]"))
                    .getText();
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMathematica_TC_12_04() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES)).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']"));

        String expectedResultCreator = "Brenton Bostick";
        String expectedResultData = "03/16/06";
        String expectedResultComment = "1";

        String actualResultCreator = getDriver().findElement(By.xpath("//tbody/tr/td[text()='Brenton Bostick']")).getText();
        String actualResultData = getDriver().findElement(By.xpath("//tbody/tr/td[text()='03/16/06']")).getText();
        String actualResultComment = getDriver().findElement(By.xpath("//tbody/tr/td[text()='1']")).getText();

        Assert.assertEquals(actualResultCreator, expectedResultCreator);
        Assert.assertEquals(actualResultData, expectedResultData);
        Assert.assertEquals(actualResultComment, expectedResultComment);
    }

    @Test
    public void testTenLanguagesStartWithNumbers_TC_12_05() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANGUAGES)).click();
        getDriver().findElement(By.xpath("//a[@href='0.html'] ")).click();

        int expectedResult = 10;
        int actualResult = getDriver().findElements(By.xpath("//tbody/tr/td/a")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
