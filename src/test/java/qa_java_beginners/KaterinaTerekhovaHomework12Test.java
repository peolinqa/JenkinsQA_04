package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KaterinaTerekhovaHomework12Test extends BaseTest {
    /**
     * TC_12_07 Выберите любой язык программирования (из меню BROWSE LANGUAGES) и любую версию решения
     * (из раздела Alternative Versions, если такой раздел существует  для выбранного языка).
     * Подтвердите, что пользователь может сделать закладку на это решение на сайте Reddit
     * (нажав на иконку сайта Reddit, пользователь перейдет на Логин страницу сайта Reddit)
     */
    @Ignore
    @Test
    public void testApprovalRaddit(){

        String expectedResultLogin = "Log in";

        getDriver().get("https://www.99-bottles-of-beer.net/abc.html");
        getDriver().
                findElement(By.xpath("//table[@id='category']/tbody/tr/td/a[@href='language-abap-12.html']")
                ).click();
        getDriver().
                findElement(By.xpath("//table[@id='category']/tbody/tr/td/a[@href='language-abap-1919.html']")
                ).click();
        getDriver().
                findElement(By.xpath("//div[@id='voting']/p/a[@title='reddit']")).click();
        WebElement login = getDriver().
                findElement
                        (By.xpath("//main[@class='Login']/div/div[@class='Step']/div[@class='Step__content']/h1"));
        String actualResultLogin = login.getText();

        Assert.assertEquals(actualResultLogin, expectedResultLogin);
    }
}
