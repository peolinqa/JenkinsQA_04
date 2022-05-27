package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaksPtHomeWork12Test extends BaseTest {

    private static final String URL = "http://www.99-bottles-of-beer.net/";

    private WebElement searchBrowseLanguages() {

        return getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
    }

    private WebElement searchSubmenuMLanguages() {

        return getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']"));
    }

    private WebElement searchSubmenuJLanguages() {

        return getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']"));
    }

    @Test
    public void testLanguagesTextJ() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        searchSubmenuJLanguages().click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p[text()]")).getText(),
                "All languages starting with the letter J are shown, sorted by Language.");
    }

    @Test
    public void testLanguagesMLastMySQL() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        searchSubmenuMLanguages().click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//a[@href='language-mysql-1252.html']")).getText(), "MySQL");
    }

    @Test
    public void testLanguagesTableHeader() {
        final String expectedResult = "Language, Author, Date, Comments, Rate";

        getDriver().get(URL);

        searchBrowseLanguages().click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//table[@id ='category']/tbody/tr[1]")).getText(),
                expectedResult.replace(",", ""));
    }

    @Test
    public void testMathematicaLanguage() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        searchSubmenuMLanguages().click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='language-mathematica-1090.html']/../..")).getText(),
                "Mathematica Brenton Bostick 03/16/06 1");
    }

    @Test
    public void testNamesStartNumbersLanguages() {
        getDriver().get(URL);

        searchBrowseLanguages().click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();

        Assert.assertEquals(getDriver()
                .findElements(By.xpath("//table[@id='category']/tbody/tr[@onmouseover]")).size(), 10);
    }

    @Test
    public void testGuestBookSecurityCodeError() {
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");

        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("Max");
        getDriver().findElement(By.xpath("//input[@name='location']")).sendKeys("Rostov");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("rostov@aaanet.com");
        getDriver().findElement(By.xpath("//input[@name='homepage']")).sendKeys("rostov.com");
        getDriver().findElement(By.xpath("//input[@name='captcha']"))
                .sendKeys(Integer.toString((int) (Math.random() * 900 + 100)));
        getDriver().findElement(By.xpath("//input[@name='homepage']")).sendKeys("rostov.com");
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Hello World!");
        getDriver().findElement(By.xpath("//input[@name='submit']")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//div[@id='main']/p")).getText(), "Error: Error: Invalid security code.");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p/b")).getTagName(), "b");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p")).getAttribute("style"),
                "border: 1px solid red; background-color: rgb(255, 224, 224); padding: 5px; margin: 5px 10px;");
    }

    @Test
    public void testLanguagesReddit() {
        final String expectedResult = "https://www.reddit.com/login/?dest=https%3A%2F%2F" +
                "www.reddit.com%2Fsubmit%3Furl%3Dhttps%253A%252F%252Fwww.99-bottles-of-beer.net%252Flanguage-java-4" +
                ".html%26title%3D99%2520Bottles%2520of%2520Beer%2520%257C%2520Language%2520Java";

        getDriver().get(URL);

        searchBrowseLanguages().click();
        searchSubmenuJLanguages().click();
        getDriver().findElement(By.xpath("//td/a[@href='language-java-3.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-java-4.html']")).click();
        getDriver().findElement(By.xpath("//div[@id='voting']/p/a[@title='reddit']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResult);
    }
}