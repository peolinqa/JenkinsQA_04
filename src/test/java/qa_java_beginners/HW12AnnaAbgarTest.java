package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HW12AnnaAbgarTest extends BaseTest {

    @Test
    public void test12_01AllLanguagesWithLetterJ() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        WebElement text = getDriver().findElement(By.xpath("//p[contains(text(),'All')]"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_02VerifyMySQLLanguage() {

        String expectedResult = "MySQL";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        WebElement lastTableLanguage = getDriver().findElement(By.xpath("//a[@href='language-mysql-1252.html']"));
        String actualResult = lastTableLanguage.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_03TableHeaders() {

        String expectedResult = "Language Author Date Comments Rate";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/abc.html']")).click();
        WebElement table = getDriver().findElement(By.xpath("//table[@id='category']"));
        String actualResult = getDriver().findElement(
                By.xpath("//table[@id='category']/tbody/tr[1]")).getText();

        Assert.assertTrue(table.isDisplayed());
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test12_04MathematicaLanguageFeatures() {

        String expectedResult1 = "Brenton Bostick";
        String expectedResult2 = "03/16/06";
        String expectedResult3 = "1";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']"));

        String creator = getDriver().findElement(By.xpath("//tbody/tr/td[text()='Brenton Bostick']")).getText();
        String data = getDriver().findElement(By.xpath("//tbody/tr/td[text()='03/16/06']")).getText();
        String comment = getDriver().findElement(By.xpath("//tbody/tr/td[text()='1']")).getText();

        Assert.assertEquals(creator, expectedResult1);
        Assert.assertEquals(data, expectedResult2);
        Assert.assertEquals(comment, expectedResult3);
    }

    @Test
    public void test12_0510LanguagesStartingWithNumbers() {

        int expectedResult = 10;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html'] ")).click();
        int tenLanguages = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td[1]")).size();

        Assert.assertEquals(tenLanguages, expectedResult);
    }

    @Test
    public void test12_06ErrorMessage() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//a[@href='/guestbookv2.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='./signv2.html']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Anna");
        getDriver().findElement(By.xpath("//input[@name='location']"))
                .sendKeys("Moscow");
        getDriver().findElement(By.xpath("//input[@name='email']"))
                .sendKeys("abgaryananna8@gmail.com");
        String random = "" + ((int) (Math.random() * 900) + 100);
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys(random);
        getDriver().findElement(By.xpath("//textarea[@name='comment']"))
                .sendKeys("Hello");
        getDriver().findElement(By.xpath("//input[@name='submit']")).click();
        String errorMessage = getDriver().findElement(
                By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(errorMessage, expectedResult);
    }

    @Test
    public void test12_07RedditLoginPage() {

        String expectedResult = "https://www.reddit.com/login/?dest=https%3A%2F%2F" +
                "www.reddit.com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer.net%252Flanguage-java-950." +
                "html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520Java";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href ='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-950.html']")).click();
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void test12_096JavaVersions() {

        int expectedResult = 6;

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href ='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();
        int javaVersions = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/td[1]")).size() + 1;

        Assert.assertEquals(javaVersions, expectedResult);
    }

}
