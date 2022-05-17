import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import runner.BaseTest;

public class EugeniaSrednitskaya extends BaseTest {

    //переменные для адресов ресурсов
    private final String url_home = "https://moregeo.com/";
    private final String url_class = "https://moregeo.com/index/category/id/1";
    private final String url_article = "https://moregeo.com/index/post/id/12";
    private final String url_login = "https://moregeo.com/auth/login";
    private final String email = "evgeneya@ukr.net";
    private final String password = "qwerty";
    private final String textSearch = "биосфера";

    @Test
    public void EugeniaSrednitskayaTestTopic() //throws InterruptedException
    {
// запуск браузера и максимизация окна
        getDriver().get(url_home);
        getDriver().manage().window().maximize();
// поиск элемента на странице, который отвечает за номер класса
        WebElement classGeo = getDriver().findElement(By.xpath("//*[contains(text(), '6 класс уроки')]"));
        classGeo.click();
// проверка перехода
        //Assert.assertTrue(getDriver().getCurrentUrl().contains(url_class));
// поиск элемента, что отвечает за заголовок статьи
        WebElement topicFirst = getDriver().findElement(By.xpath("//*[contains(text(), 'Объект')]"));
        topicFirst.click();
// проверка перехода
        Assert.assertTrue(getDriver().getCurrentUrl().contains(url_article));

    }

    @Test
    public void EugeniaSrednitskayaTestLoginFail() throws InterruptedException {
// запуск браузера и максимизация окна
        getDriver().get(url_home);
        getDriver().manage().window().maximize();

// поиск элемента для перехода в окно для входа в аккаунт
        WebElement itemLoginButton = getDriver().findElement(By.xpath("//*[contains(@class, 'desktop_menus')]"));

// включить ожидание перед нажатием элемента
        new WebDriverWait(getDriver(), 100).until(ExpectedConditions.elementToBeClickable(itemLoginButton)).click();

// временно невозможно реализовать, потому что в виду всплывающей рандомно рекламы
// текущий url отличается от ожидаемого
// проверка перехода
        //Assert.assertEquals(getDriver().getCurrentUrl(), url_login);
        //Assert.assertTrue(getDriver().getCurrentUrl().contains(url_login));

// ввод логина - пароля
        WebElement itemLogin = getDriver().findElement(By.id("username"));
        itemLogin.sendKeys(email);

        WebElement itemPassword = getDriver().findElement(By.id("password"));
        itemPassword.sendKeys(password);

        WebElement itemEnterAccount = getDriver().findElement(By.id("login"));
        //new WebDriverWait(getDriver(), 100).until(ExpectedConditions.elementToBeClickable(itemEnterAccount)).click();
        itemEnterAccount.submit();
// через wait приводит к ошибкам обработки элементов, вынужденная мера
        Thread.sleep(2000);

// проверка некорректного логина/пароля
        String errorLabel = "Вы ввели неверное имя пользователя или неверный пароль";
// при ошибочном логине/пароле элмент, которые отвечает за сообщение
// о неверных данных не имеет класса, ID или других удобных аттрибутов.
// Вынужденно используется поиск по всей странице
        Assert.assertTrue(getDriver().getPageSource().contains(errorLabel));
    }

    @Test
    public void EugeniaSrednitskayaTestFoundSearchField() throws InterruptedException {

        getDriver().get(url_home);

// проверка поля с поиском
        WebElement itemSearch = getDriver().findElement(By.xpath("//*[contains(@placeholder, 'поищи здесь')]"));
        itemSearch.sendKeys(textSearch, Keys.ENTER);
        Thread.sleep(2000);

        WebElement searchBoxEnd = getDriver().findElement(By.xpath("//*[@class='menu-search']"));
// не зависимо от того, есть ли статьи, содержащие искомое слово, ресурс автоматически очищает поле ввода для поиска
        Assert.assertNull(searchBoxEnd.getAttribute("value"));
    }

    @AfterTest
    public void EugeniaSrednitskayaTestAfterTest() {
// выход из бразуреа
        getDriver().quit();
    }
}
