package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EugeniaSrednitskayaTest extends BaseTest {

    private final String urlDefault = "http://www.99-bottles-of-beer.net/";

    @Test(priority = 1)
    public void EugeniaSrednitskayaBrowseLanguageLetterJTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        WebElement iLetterJ = getDriver().findElement(By.xpath("//a[@href='j.html']"));
        iLetterJ.click();

        String strExpected = "All languages starting with the letter J are shown, sorted by Language.";
        WebElement iInfoLanguage = getDriver().findElement(By.xpath("//div[@id = 'main']/p"));

        Assert.assertEquals(iInfoLanguage.getText(), strExpected);

    }

    @Test(priority = 2)
    public void EugeniaSrednitskayaBrowseLanguageLetterMTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        WebElement iLetterM = getDriver().findElement(By.xpath("//a[@href='m.html']"));
        iLetterM.click();

        List<WebElement> iLanguageM = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        Assert.assertEquals(iLanguageM.get(iLanguageM.size() - 1).getText(), "MySQL");
    }

    @Test(priority = 3)
    public void EugeniaSrednitskayaBrowseLanguageColumnsNamesTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        List<WebElement> iColumnsNames = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr/th"));

        String[] arrayExpected = {"Language", "Author", "Date", "Comments", "Rate"};

        for (int i = 0; i < iColumnsNames.size(); i++) {
            Assert.assertTrue(Arrays.asList(arrayExpected).contains(iColumnsNames.get(i).getText()));
        }
    }

    @Test(priority = 4)
    public void EugeniaSrednitskayaBrowseLanguageAuthorLanguageTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        WebElement iLetterM = getDriver().findElement(By.xpath("//a[@href='m.html']"));
        iLetterM.click();

        WebElement iLanguageMathematica = getDriver().findElement(By.xpath("//a[contains(@href, 'mathematica')]"));

        WebElement iAuthor = getDriver().findElement
                (By.xpath("//a[contains(@href, 'mathematica')]/parent::node()/following-sibling::td"));
        Assert.assertEquals(iAuthor.getText(), "Brenton Bostick");

        WebElement iDate = getDriver().findElement
                (By.xpath("//a[contains(@href, 'mathematica')]/parent::node()" +
                        "/following-sibling::td/following-sibling::td"));
        Assert.assertEquals(iDate.getText(), "03/16/06");

        WebElement iComments = getDriver().findElement
                (By.xpath("//a[contains(@href, 'mathematica')]/parent::node()" +
                        "/following-sibling::td/following-sibling::td/following-sibling::td"));
        Assert.assertEquals(iComments.getText(), "1");

    }

    @Test(priority = 5)
    public void EugeniaSrednitskayaBrowseLanguagesDigitFirstLetterTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        WebElement iDigits = getDriver().findElement(By.xpath("//a[@href='0.html']"));
        iDigits.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        for (int i = 0; i < iLanguage.size(); i++) {
            Assert.assertTrue(Character.isDigit(iLanguage.get(i).getText().charAt(0)));
        }
    }

    @Test(dependsOnMethods = "EugeniaSrednitskayaBrowseLanguagesDigitFirstLetterTest")
    public void EugeniaSrednitskayaBrowseLanguagesCountDigitFirstLetterTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement iBrowseLanguage = getDriver().findElement(By.xpath("//a[@href='/abc.html']"));
        iBrowseLanguage.click();

        WebElement iDigits = getDriver().findElement(By.xpath("//a[@href='0.html']"));
        iDigits.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        Assert.assertTrue(iLanguage.size() == 10);

    }

    @Test(priority = 7)
    public void EugeniaSrednitskayaSignGuestbookDisplayedTest() throws InterruptedException {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iGuestbook = getDriver().findElement(By.xpath("//a[contains(@href, 'guestbook')]"));
        iGuestbook.click();

        WebElement iSignGuestbook = getDriver().findElement(By.xpath("//a[contains(@href, 'sign')]"));
        iSignGuestbook.click();

        WebElement iMessage = getDriver().findElement(By.xpath("//textarea[@name='comment']"));
        iMessage.sendKeys("Note");

        int min = (int) Math.pow(10, 2);
        int max = (int) Math.pow(10, 3) - 1;
        int range = max - min;
        int number = (int) (Math.random() * (range) + min);

        List<WebElement> iSignElements = getDriver().findElements
                (By.xpath("//div[@id = 'main']/form[@id='addentry']/p/input"));

        for (WebElement element : iSignElements) {
            switch (element.getAttribute("name")) {
                case ("name"):
                    element.sendKeys("Name");
                case ("location"):
                    element.sendKeys("Location");
                case ("email"):
                    element.sendKeys("EMail");
                case ("homepage"):
                    element.sendKeys("Homepage");
                case ("captcha"):
                    element.sendKeys(String.valueOf(number));
                case ("submit"):
                    element.click();
            }
        }

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main']/p")).isDisplayed());
    }

    @Test(dependsOnMethods = "EugeniaSrednitskayaSignGuestbookDisplayedTest")
    public void EugeniaSrednitskayaSignGuestbookErrorMessageTest() throws InterruptedException {

//        getDriver().get(urlDefault);
//        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EugeniaSrednitskayaSignGuestbookDisplayedTest();

        String strExpected = "Error: Error: Invalid security code.";
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main']/p")).getText(), strExpected);

    }

    @Test(priority = 9)
    public void EugeniaSrednitskayaTopListTwentyTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iTopList = getDriver().findElement(By.linkText("Top Lists"));
        iTopList.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        try {
            getDriver().getPageSource().contains("Shakespeare");
            Assert.assertTrue(iLanguage.indexOf("Shakespeare") <= 20);
        } catch (NoSuchElementException ex) {
        }

    }

    @Test(priority = 10)
    public void EugeniaSrednitskayaTopHitsTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iTopList = getDriver().findElement(By.linkText("Top Lists"));
        iTopList.click();

        WebElement iTopEsoteric = getDriver().findElement(By.linkText("Top Hits"));
        iTopEsoteric.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        try {
            getDriver().getPageSource().contains("Shakespeare");
            Assert.assertTrue(iLanguage.indexOf("Shakespeare") <= 6);
        } catch (NoSuchElementException ex) {
        }

    }

    @Test(priority = 11)
    public void EugeniaSrednitskayaTopListTenTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iTopList = getDriver().findElement(By.linkText("Top Lists"));
        iTopList.click();

        WebElement iTopEsoteric = getDriver().findElement(By.linkText("Top Rated Esoteric"));
        iTopEsoteric.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        try {
            getDriver().getPageSource().contains("Shakespeare");
            Assert.assertTrue(iLanguage.indexOf("Shakespeare") <= 10);
        } catch (NoSuchElementException ex) {
        }

    }

    @Test(priority = 12)
    public void EugeniaSrednitskayaTopListRealTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        WebElement iTopList = getDriver().findElement(By.linkText("Top Lists"));
        iTopList.click();

        WebElement iTopEsoteric = getDriver().findElement(By.linkText("Top Rated Real"));
        iTopEsoteric.click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        Assert.assertFalse(getDriver().getPageSource().contains("Shakespeare"));

    }

    @Test(priority = 13)
    public void EugeniaSrednitskayaCountVersionJavaTest() throws InterruptedException {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        getDriver().findElement(By.xpath("//a[@href = '/search.html']")).click();

        getDriver().findElement(By.xpath("//input[@name='search']")).sendKeys("Java");

        getDriver().findElement(By.xpath("//input[@name='submitsearch']")).submit();
        Thread.sleep(2000);

        List<WebElement> iLanguage = getDriver().findElements(By.xpath("//td/a[contains(@href, 'language') " +
                "and (text()='Java' or contains(text(), 'Java ('))]"));
        //(By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language-java-')]"));

        System.out.println(iLanguage.size());
        Assert.assertTrue(iLanguage.size() == 6);
    }

    @Test(priority = 14)
    public void EugeniaSrednitskayaCommentsJavaTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        getDriver().findElement(By.xpath("//a[@href = '/search.html']")).click();

        getDriver().findElement(By.xpath("//input[@name='search']")).sendKeys("Java");

        getDriver().findElement(By.xpath("//input[@name='submitsearch']")).submit();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td[4]"));

        int max = 0;
        int index = 0;

        for (int i = 0; i < iLanguage.size(); i++) {
            if (max < Integer.parseInt(iLanguage.get(i).getText())) {
                max = Integer.parseInt(iLanguage.get(i).getText());
                index = i;
            }
        }

        String strActual = iLanguage.get(index).findElement
                        (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td[4]/preceding-sibling::node()"))
                .getText();

        Assert.assertTrue(strActual.equals("Java (object-oriented version)"));

    }

    @Test(priority = 15)
    public void EugeniaSrednitskayaBrowseLanguagesAlternativeVersionTest() {

        getDriver().get(urlDefault);
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement iBrowseLanguage = getDriver().findElement(By.linkText("Browse Languages"));
        iBrowseLanguage.click();

        List<WebElement> iSubmenu = getDriver().findElements
                (By.xpath("//div[@id='navigation']/ul[@id='submenu']/li"));

        int indexOfRandomSubmenu = new Random().ints(0, iSubmenu.size() - 1)
                .findFirst().getAsInt();

        iSubmenu.get(indexOfRandomSubmenu).click();

        List<WebElement> iLanguage = getDriver().findElements
                (By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td/a[contains(@href, 'language')]"));

        int indexOfRandomElement = new Random().ints(0, iLanguage.size() - 1)
                .findFirst().getAsInt();

        iLanguage.get(indexOfRandomElement).click();

        WebElement iReddit = getDriver().findElement(By.xpath("//a[@title='reddit']"));

        try {
            getDriver().findElement
                    (By.xpath("//div[@id='alternatives']/table[@id='category']/tbody"));

            List<WebElement> iLanguageAltVer = getDriver().findElements
                    (By.xpath("//div[@id='alternatives']//tr[@onmouseover]/td/a"));

            int indexOfRandomAltVer = new Random().ints(0,
                            iLanguageAltVer.size() - 1)
                    .findFirst().getAsInt();

            iLanguageAltVer.get(indexOfRandomAltVer).click();

            Assert.assertTrue(getDriver().getCurrentUrl().contains("reddit"));

        } catch (NoSuchElementException ex) {
        } finally {
            iReddit.click();

            Assert.assertTrue(getDriver().getCurrentUrl().contains("reddit"));
        }
    }
  
}
