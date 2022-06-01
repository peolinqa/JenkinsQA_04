package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.HashMap;
import java.util.Map;

@Ignore
public class HW12RomTTest extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void test_TC_12_01() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement text = getDriver().findElement(By.xpath("//div[@id='main']/p[contains(text(),'All')]"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_02() {

        String expectedResult = "MySQL";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        WebElement lastTableElement = getDriver().findElement(By.xpath("//tr[last()]/td/a"));
        String actualResult = lastTableElement.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_03() {

        String expectedResult = "Language, Author, Date, Comments, Rate";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();

        String[] headers = new String[5];
        String actualResult = "";
        for(int i = 0; i < headers.length; i++){
            int index = i + 1;
            if(i != 4) {
                headers[i] = getDriver().findElement(
                        By.xpath("//table[@id='category']/tbody/tr/th[" + index + "]")).getText();
                actualResult += headers[i] + ", ";
            } else {
                headers[i] = getDriver().findElement(
                        By.xpath("//table[@id='category']/tbody/tr/th[" + index + "]")).getText();
                actualResult += headers[i];
            }
        }
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_04() {

        String expectedResult1 = "Brenton Bostick";
        String expectedResult2 = "03/16/06";
        String expectedResult3 = "1";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']")).click();

        String author = getDriver().findElement(
                By.xpath("//strong[contains(text(),'Author:')]/parent::td/parent::tr/td[2]"))
                .getText();
        String date = getDriver().findElement(
                By.xpath("//strong[contains(text(),'Date:')]/parent::td/parent::tr/td[2]"))
                .getText();
        String comments = getDriver().findElement(
                By.xpath("//strong[contains(text(),'Comments:')]/parent::td/parent::tr/td[2]"))
                .getText();

        Assert.assertEquals(author,expectedResult1);
        Assert.assertEquals(date,expectedResult2);
        Assert.assertEquals(comments,expectedResult3);
    }

    @Test
    public void test_TC_12_05() {

        int expectedResult = 10;

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        int actualResult = getDriver().findElements(
                By.xpath("//tr[starts-with(@onmouseover,'setPointer')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_06() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/input[@name='name']"))
                .sendKeys("Roman")
        ;
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/input[@name='location']"))
                .sendKeys("USA")
        ;
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/input[@name='email']"))
                .sendKeys("test@gmail.com")
        ;
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/input[@name='homepage']"))
                .sendKeys("http://test.com")
        ;
        String random = "" + ((int) (Math.random() * 900) + 100);
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/input[@name='captcha']"))
                .sendKeys(random)
        ;
        getDriver().findElement(
                By.xpath("//form[@id='addentry']/p/textarea[@name='comment']"))
                .sendKeys("New Message!")
        ;
        getDriver().findElement(By.xpath("//form[@id='addentry']/p/input[@name='submit']")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_07() {

        String expectedResult = "http://reddit.com/submit?url=https://www.99-bottles-of-beer.net/" +
                "language-java-4.html&title=99%20Bottles%20of%20Beer%20|%20Language%20Java";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-4.html']")).click();
        String actualResult = getDriver().findElement(By.xpath("//a[@title='reddit']")).getAttribute("href");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_08() {

        String xPathShakespeare = "//a[@href='language-shakespeare-664.html']/parent::td/parent::tr/td[1]";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();

        WebElement topList = getDriver().findElement(
                By.xpath(xPathShakespeare));
        int actualResultTopList = Integer.parseInt(
                topList.getText().substring(0, topList.getText().length() - 1));

        getDriver().findElement(By.xpath("//a[@href='./toplist_esoteric.html']")).click();
        WebElement topListEsoteric = getDriver().findElement(
                By.xpath(xPathShakespeare));
        int actualResultTopListEsoteric = Integer.parseInt(
                topListEsoteric.getText().substring(0, topListEsoteric.getText().length() - 1));

        getDriver().findElement(By.xpath("//a[@href='./tophits.html']")).click();
        WebElement topHits = getDriver().findElement(
                By.xpath(xPathShakespeare));
        int actualResultTopHits = Integer.parseInt(
                topHits.getText().substring(0, topHits.getText().length() - 1));

        getDriver().findElement(By.xpath("//a[@href='./toplist_real.html']")).click();
        boolean topListReal;
        try {
            getDriver().findElement(
                    By.xpath(xPathShakespeare));
            topListReal = false;
        } catch (NoSuchElementException e) {
            topListReal = true;
        }

        Assert.assertTrue(actualResultTopList <= 20);
        Assert.assertTrue(actualResultTopListEsoteric <= 10);
        Assert.assertTrue(actualResultTopHits <= 6);
        Assert.assertTrue(topListReal);
    }

    @Test
    public void test_TC_12_09() {

        int expectedResult = 6;

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();

        int actualResult = getDriver().findElements(
                By.xpath("//tr[starts-with(@onmouseover,'setPointer')]")).size() + 1;

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TC_12_10() {

        String expectedResult = "object-oriented version";

        getDriver().get(URL);
        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();

        int firstTotalCommentsVersion = Integer.parseInt(getDriver().findElement(
                By.xpath("//strong[contains(text(),'Comments')]/parent::td/parent::tr/td[2]")).getText());
        String firstNameVersion = getDriver().findElement(
                By.xpath("//div[@id='main']/p[contains(text(),'object')]")).getText();

        Map<Integer, String> map = new HashMap<>();
        map.put(firstTotalCommentsVersion, firstNameVersion.replaceAll("[()]", ""));

        int max = firstTotalCommentsVersion;
        for (int i = 0; i < 5; i++) {
            int com = Integer.parseInt(getDriver().findElement(
                    By.xpath("//tr[starts-with(@onmouseover,'setPointer')][" + (i + 1) + "]/td[4]")).getText());
            String name = getDriver().findElement(
                    By.xpath("//tr[starts-with(@onmouseover,'setPointer')][" + (i + 1) + "]/td/a")).getText();
            map.put(com, name);

            if (com > max) {
                max = com;
            }
        }

        Assert.assertEquals(map.get(max), expectedResult);
    }
}
