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
                .findElement(By.xpath("//table[@id='category']/tbody/tr[last()]/td/a")).getText(), "MySQL");
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

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
    }

    @Test
    public void testTopListsShakespeare() {
        getDriver().get(URL);

        getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/toplist.html']")).click();
        String[] actualTopLists = new String[20];
        boolean actualResultTopLists = false;
        for (int i = 0; i < actualTopLists.length; i++) {
            int index = i + 2;
            actualTopLists[i] = getDriver()
                    .findElement(By.xpath("//table[@id='category']/tbody/tr[" + index + "]")).getText();
            if (actualTopLists[i].contains("Shakespeare")) {
                actualResultTopLists = true;
            }
        }

        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_esoteric.html']")).click();
        String[] actualTopEsoteric = new String[10];
        boolean actualResultTopEsoteric = false;
        for (int i = 0; i < actualTopEsoteric.length; i++) {
            int index = i + 2;
            actualTopEsoteric[i] = getDriver()
                    .findElement(By.xpath("//table[@id='category']/tbody/tr[" + index + "]")).getText();
            if (actualTopEsoteric[i].contains("Shakespeare")) {
                actualResultTopEsoteric = true;
            }
        }

        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./tophits.html']")).click();
        String[] actualTopHits = new String[6];
        boolean actualResultTopHits = false;
        for (int i = 0; i < actualTopHits.length; i++) {
            int index = i + 2;
            actualTopHits[i] = getDriver()
                    .findElement(By.xpath("//table[@id='category']/tbody/tr[" + index + "]")).getText();
            if (actualTopHits[i].contains("Shakespeare")) {
                actualResultTopHits = true;
            }
        }

        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='./toplist_real.html']")).click();
        String[] actualTopReal = new String[25];
        boolean actualResultTopReal = false;
        for (int i = 0; i < actualTopReal.length; i++) {
            int index = i + 2;
            actualTopReal[i] = getDriver()
                    .findElement(By.xpath("//table[@id='category']/tbody/tr[" + index + "]")).getText();
            if (!actualTopReal[i].contains("Shakespeare")) {
                actualResultTopReal = true;
            }
        }

        Assert.assertTrue(actualResultTopLists);
        Assert.assertTrue(actualResultTopEsoteric);
        Assert.assertTrue(actualResultTopHits);
        Assert.assertTrue(actualResultTopReal);
    }
}
