package qa_java_beginners;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;
import java.util.Random;

public class IgorKadach99BottlesOfBeerTest extends BaseTest {

    private String url = "http://www.99-bottles-of-beer.net/";

    /**
     * TC_12_01 Подтвердите, что в меню BROWSE LANGUAGES, подменю  J, пользователь может найти описание страницы,
     * на которой перечеслены все программные языки, начинающиеся с буквы J,  отсортированные по названию
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю J
     * Подтвердить, что пользователь видит текст “All languages starting with the letter J are shown, sorted by Language.”
     */

    
    @Test
    public void testHeaderTextBrowseLanguages() {

        String expectedResult1 = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(url);
        getDriver().findElement(
                By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//li/a[@href='j.html']")).click();
        String actualResult1 = getDriver().findElement(
                By.xpath("//p[contains(text(),'All languages starting with the letter')]")
        ).getText();
        Assert.assertEquals(actualResult1, expectedResult1);
    }

    /**
     * TC_12_02 Подтвердите, что в меню BROWSE LANGUAGES, подменю  M, последний программный язык в таблице -  MySQL
     * <p>
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю M
     * Подтвердить, что последний язык программирования на странице - MySQL
     */

    @Test
    public void testLastLanguage() {

        String expectedResult = "MySQL";

        getDriver().get(url);
        getDriver().findElement(
                By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//a[@href='m.html']")).click();
        String actualResult = getDriver().findElement(
                By.xpath("//table[@id='category']/tbody/tr[last()]/td/a")
        ).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_03 Подтвердите, что в меню BROWSE LANGUAGES существует таблица с заголовками
     * Language, Author, Date, Comments, Rate
     */
    @Test
    public void testHeadersOfTable() {
        String expectedResult = "Language Author Date Comments Rate";

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        String actualResult = getDriver().findElement(
                By.xpath("//tbody/tr[1]")
        ).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_04 Подтвердите, что создатель решения на языке Mathematica - Brenton Bostick,
     * дата обновления решения на этом языке - 03/16/06, и что это решение имеет 1 комментарий
     */

    @Test
    public void testAuthorOfMathematica() {

        String expectedResult = "Brenton Bostick 03/16/06 1";

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String language = getDriver().findElement(
                By.xpath("//a[@href='language-mathematica-1090.html']")
        ).getText();

        if (language.equals("Mathematica")) {
            String nameText = getDriver().findElement(
                    By.xpath("//td[contains(text(),'Brenton Bostick')]")
            ).getText();

            String dateText = getDriver().findElement(
                    By.xpath("//td[contains(text(),'03/16/06')]")
            ).getText();

            String commentText = getDriver().findElement(
                    By.xpath("//td[contains(text(),'1')]")
            ).getText();

            String space = " ";
            String actualResult = nameText.concat(space.concat(dateText.concat(space.concat(commentText))));

            Assert.assertEquals(actualResult, expectedResult);
        }
    }

    /**
     * TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
     */
    @Test
    public void testLanguageQuantity() {

        int expectedResult = 10;
        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")
        ).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        int actualResult = getDriver().findElements(
                By.xpath("//tbody/tr[@onmouseover]")
        ).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_06 Подтвердите, что если на странице Sign Guestbook http://www.99-bottles-of-beer.net/signv2.html
     * вы заполните все поля формы, но введете случайно сгенерированное трехзначное число в поле  Security Code: ,
     * то вы получите сообщение об ошибке  Error: Error: Invalid security code.
     */
    @Test
    public void testErrorMessage() {
        String expextedResult = "Error: Error: Invalid security code.";

        getDriver().get(url);
        Random random = new Random();
        int numb = random.nextInt(900) + 100;
        String number = String.valueOf(numb);

        getDriver().findElement(By.xpath("//li/a[@href='/guestbookv2.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='./signv2.html']")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("name");
        getDriver().findElement(By.xpath("//input[@name='location']")).sendKeys("location");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("email@email.com");
        getDriver().findElement(By.xpath("//textarea[@name='comment']")).sendKeys("comment");
        getDriver().findElement(By.xpath("//input[@name='captcha']")).sendKeys(number);
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main']/p[1]")
        ).getText();
        Assert.assertEquals(actualResult, expextedResult);
    }

    /**
     * TC_12_07 Выберите любой язык программирования (из меню BROWSE LANGUAGES) и любую версию решения
     * (из раздела Alternative Versions, если такой раздел существует  для выбранного языка)
     * Подтвердите, что пользователь может сделать закладку на это решение на сайте Reddit
     * (нажав на иконку сайта Reddit, пользователь перейдет на Логин страницу сайта Reddit)
     */
    @Test(priority = 13)
    public void testReddit() {

        String expectedResult = "reddit.com: Log in";

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-awk-1677.html']")).click();
        if (!getDriver().findElement(
                By.xpath("//table[@id='category']/tbody")).isDisplayed()) ;
        {
            getDriver().findElement(
                    By.xpath("//table[@id='category']/tbody/tr[@onmouseover]/td[1]/a[1]")
            ).click();
        }
        getDriver().findElement(By.xpath("//a[@title='reddit']")).click();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_08 Подтвердите, что решение на языке Shakespeare входит в топ 20 всех решений, в топ 10 решений
     * на Esoteric Languages и в топ 6 решений-хитов. Но решение на языке Shakespeare не входит в список
     * топовых решений на реальных языках программирования.
     * (Можно написать одним тестом, но так, чтобы все Asserts были в конце теста. Или можно написать отдельные
     * тесты на каждый requirenment.)
     * @param numsFromText
     */
    /**
     * Метод
     */
    public static void checkTop(String[] numsFromText, int top) {

        String expectedResult1 = "true";
        String actualResult1;

        int[] masNum = new int[numsFromText.length];
        for (int i = 0; i < numsFromText.length; i++) {
            masNum[i] = Integer.parseInt(numsFromText[i]);
        }
        if (masNum[0] <= top) {
            actualResult1 = "true";
            Assert.assertEquals(actualResult1, expectedResult1);
        }
    }

    @Test
    public void testTopAll() {

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/toplist.html']")).click();
        String text = getDriver().findElement(By.xpath("//tr[td//text()[contains(., 'Shakespeare')]]")).getText();
        String[] numsFromText = text.replaceAll("\\D+", " ").split(" ");

        checkTop(numsFromText, 20);
    }

    @Test
    public void testTopEsotericLanguages() {

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='./toplist_esoteric.html']")).click();
        String text = getDriver().findElement(By.xpath("//tr[td//text()[contains(., 'Shakespeare')]]")).getText();
        String[] numsFromText = text.replaceAll("\\D+", " ").split(" ");

        checkTop(numsFromText, 10);
    }

    @Test
    public void testTopHits() {

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//li//a[@href='./tophits.html']")).click();
        String text = getDriver().findElement(By.xpath("//tr[td//text()[contains(., 'Shakespeare')]]")).getText();
        String[] numsFromText = text.replaceAll("\\D+", " ").split(" ");

        checkTop(numsFromText, 6);
    }

    @Test
    public void testRealLunguages() {

        String expectedResult = "Language is not exist.";
        String actualResult;
        boolean isPresent;

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/toplist.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='./toplist_real.html']")).click();

        isPresent = getDriver().findElements(By.xpath("//tr[td//text()[contains(., 'Shakespeare')]]")).size() > 0;

        if (isPresent == false) {
            actualResult = "Language is not exist.";
        } else {
            actualResult = "Language is exist.";
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**
     * TC_12_09 Подтвердите, что существует 6 версий решений на языке программирования Java.
     */
    @Test
    public void testQuantityVersions() {

        int expectedResult = 6;

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='language-java-3.html']")).click();

        int actualResult = getDriver().findElements(By.xpath("//table[@id='category']/tbody/tr")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    /**TC_12_10 Подтвердите, что самое большое количество комментариев
     * для решений на языке Java имеет версия “object-oriented version”
     */
    @Test
    public void testCommentsQuantity(){

        String expectedResult = "object-oriented version";

        getDriver().get(url);
        getDriver().findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//li/a[@href='j.html']")).click();
        getDriver().findElement(By.xpath("//td/a[@href='language-java-3.html']")).click();

        int firstLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[2]/td[4]")).getText());
        int secondLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[3]/td[4]")).getText());
        int thirdLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[4]/td[4]")).getText());
        int fourthLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[5]/td[4]")).getText());
        int fifthLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[6]/td[4]")).getText());

        getDriver().findElement(By.xpath("//td/a[@href='language-java-4.html']")).click();

        int sixthLanguage = Integer.parseInt(getDriver().findElement(By.xpath("//tbody/tr[2]/td[4]")).getText());

        int[] maxQuantity = new int[] {firstLanguage,secondLanguage,thirdLanguage, fourthLanguage,fifthLanguage,sixthLanguage};

        int maxComments = 0;
        for (int i = 0; i < maxQuantity.length; i++) {
            if(maxComments <= maxQuantity[i]){
                maxComments = maxQuantity[i];
            }
        }
        String actualResult = getDriver().findElement(By.xpath("//table[@id='category']/tbody/tr/td[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}

