package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class EugeniaSrednitskayaTest extends BaseTest {

    private final String url_home = "https://moregeo.com/";
    private final String url_login = "https://moregeo.com/auth/login";
    private final String email = "evgeneya@ukr.net";
    private final String password = "qwerty";
    private final String textSearch = "биосфера";

    @Test
    public void EugeniaSrednitskayaTestTopic() throws InterruptedException //throws InterruptedException
    {
        getDriver().get(url_home);
        getDriver().manage().window().maximize();

        WebElement classGeo = getDriver().findElement(By.xpath("//a[text() = '6 класс уроки']"));
        classGeo.click();
        Thread.sleep(2000);

    }

    @Test
    public void EugeniaSrednitskayaTestLoginFail() throws InterruptedException {

        getDriver().get(url_login);
        getDriver().manage().window().maximize();

        Assert.assertTrue(getDriver().getCurrentUrl().contains(url_login));

        WebElement itemLogin = getDriver().findElement(By.xpath("//input[@id = 'username']"));
        itemLogin.sendKeys(email);

        WebElement itemPassword = getDriver().findElement(By.xpath("//input[@id = 'password']"));
        itemPassword.sendKeys(password);

        WebElement itemEnterAccount = getDriver().findElement(By.xpath("//input[@id = 'login']"));
        itemEnterAccount.submit();
        Thread.sleep(2000);

        String errorLabel = "Вы ввели неверное имя пользователя или неверный пароль";
        Assert.assertTrue(getDriver().getPageSource().contains(errorLabel));
    }

    @Test
    public void EugeniaSrednitskayaTestFoundSearchField() throws InterruptedException {

        getDriver().get(url_home);

        WebElement itemSearch = getDriver().findElement(By.xpath("//input[contains(@name, 'title')]"));
        itemSearch.sendKeys(textSearch, Keys.ENTER);
        Thread.sleep(2000);

        WebElement searchBoxEnd = getDriver().findElement(By.xpath("//div[@class='menu-search']"));
        Assert.assertNull(searchBoxEnd.getAttribute("value"));
    }
}