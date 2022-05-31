package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnaLamaHW12Tests extends BaseTest {

    private final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private final String BROWSE_LANG = "//div[@id='navigation']/ul[@id='menu']/li/a[@href='/abc.html']";

    @Test
    public void testBrowseJLanguage() {

        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='j.html']")).click();

        WebElement letJ = getDriver().findElement(By.xpath("//div[@id='main']/p"));
        String actualResult = letJ.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testMLastLanguageMySQL() {

        String expectedResult = "MySQL";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        WebElement mLet = getDriver().findElement(By.xpath("//tr[last()]/td/a"));
        String actualResult = mLet.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testTableColumnsTitles() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();

        List<WebElement> tabTitles = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/th"));
        List<String> tabTitlesActual = new ArrayList<>();
        for (int i = 0; i < tabTitles.size(); i++) {
            tabTitlesActual.add(tabTitles.get(i).getText());
        }
        List<String> expectedResult = new ArrayList<>(Arrays.asList("Language", "Author", "Date", "Comments", "Rate"));

        Assert.assertEquals(tabTitlesActual.toString(), expectedResult.toString());
    }
    @Test
    public void testMathLanguageRow() {
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='m.html']")).click();

        WebElement mathRow = getDriver().findElement(By.xpath("//a[contains(text(),'Mathematica')]/parent::td/parent::tr"));
        String actualResult = mathRow.getText();
        String expectedResult = "Mathematica Brenton Bostick 03/16/06 1";

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testNumericalLanguagesNames() {
        int expectedResult = 10;
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']/li/a[@href='0.html']")).click();

        List<WebElement> numNames = getDriver().findElements(By.xpath("//tbody/tr[@onmouseover]"));
        int actualResult = numNames.size();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testErrorMessageGuestBook() {

        String expectedMessage = "Error: Error: Invalid security code.";
        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("Mary");
        getDriver().findElement(By.xpath("//input[@name='location']")).sendKeys("Toronto");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("Khgdvs87@nkj.com");
        String code = "" + ((int) (Math.random() * 200) + 100);
        getDriver().findElement(By.xpath("//input[@name='captcha']")).sendKeys(code);
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("kuku");
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedMessage);
    }
    @Test
    public void testRedditBookMarkC(){
        String expectedResult = "reddit.com: Log in";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath(BROWSE_LANG)).click();
        getDriver().findElement(By.xpath("//a[@href='c.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-csharp-1614.html']")).click();
        getDriver().findElement(By.xpath("//img[@src='./images/reddit.png']")).click();
        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testShakespeareTopLanguage() {
        String expectedResult1 = "99 Bottles of Beer | Top Rated";
        String expectedResult2 = "99 Bottles of Beer | Top Rated Esoteric Languages";
        String expectedResult3 = "99 Bottles of Beer | Top Hits";

        getDriver().get(BASE_URL);
        getDriver().findElement(By.linkText("Top Lists")).click();
        String actResult1 = getDriver().getTitle();
        WebElement shek = getDriver().findElement(
                By.xpath("//a[contains(text(),'Shakespeare')]/parent::td/parent::tr"));
        String[] shek1 = shek.getText().split(" ");
        int topNum = Integer.parseInt(shek1[0].replace(".", ""));

        getDriver().findElement(By.linkText("Top Rated Esoteric")).click();
        String actResult2 = getDriver().getTitle();
        WebElement shekEsot = getDriver().findElement(
                By.xpath("//a[contains(text(),'Shakespeare')]/../.."));
        String[] shekEsot1 = shekEsot.getText().split(" ");
        int topEsotNum = Integer.parseInt(shekEsot1[0].replace(".", ""));

        getDriver().findElement(By.linkText("Top Hits")).click();
        String actResult3 = getDriver().getTitle();
        WebElement shekTopHits = getDriver().findElement(
                By.xpath("//a[contains(text(),'Shakespeare')]/../.."));
        String[] shekTopHits1 = shekTopHits.getText().split(" ");
        int topHitsNum = Integer.parseInt(shekTopHits1[0].replace(".", ""));


        Assert.assertEquals(actResult1, expectedResult1);
        Assert.assertTrue(topNum <= 20);
        Assert.assertEquals(actResult2, expectedResult2);
        Assert.assertTrue(topEsotNum <= 10);
        Assert.assertEquals(actResult3, expectedResult3);
        Assert.assertTrue(topHitsNum <= 6);
    }
}
