package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Guestbook08AlimDuiLTest extends BaseTest {
    /*
        TC_12_06
        Шаги:
        1. Открыть страницу http://www.99-bottles-of-beer.net/signv2.html
        2. Ввести в поле "Name:" - Name
        3. Ввести в поле "E-Mail:" - Mail@mail.com
        4. Ввести в поле "Message::" - Hello!
        5. Ввести в поле "Security Code:" - рандомное трехзначное число
        6. Нажать на кнопку "Submit"
        7.  Подтвердить, что на странице есть сообщение об ошибке  Error: Error:
         Invalid security code.
         */
    @Test
    public void testGuestbook() {

        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver()
                .findElement(
                        By.xpath("//div[@id='main']/form/p/input[@name='name']"))
                .sendKeys("Name");
        getDriver()
                .findElement(
                        By.xpath("//div[@id='main']/form/p/input[@name='email']"))
                .sendKeys("Mail@mail.com");
        getDriver()
                .findElement(
                        By.xpath("//div[@id='main']/form/p/textarea[@name='comment']"))
                .sendKeys("Hello!");

        String randomTripleDigit = String.valueOf((int)(Math.random() * 889 + 111));
        getDriver()
                .findElement(
                        By.xpath("//div[@id='main']/form/p/input[@name='captcha']"))
                .sendKeys(randomTripleDigit);
        getDriver()
                .findElement(
                        By.xpath("//div[@id='main']/form/p/input[@type='submit']"))
                .click();

        WebElement textError = getDriver().findElement(
                By.xpath("//div[@id='main']/p"));
        String actualResult = textError.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
}
