package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KseniyaTestDelish extends BaseTest {

    /**
     * Шаги:
     * 1. Открыть вебсайт на базовой странице
     * 2. Перейти на вкладку Sign In
     * 3. Подтвердить, что выводится "Sign In"
     * 4. Закрыть браузер
     */

    @Test
    public void testKseniyaTitovaDelishSignIn() {

        String expectedResult = "Sign In";

        getDriver().get("https://www.delish.com/cooking/recipe-ideas/");

        WebElement signIn = getDriver().findElement(By.xpath("//*[@id=\"__next\"]/div/nav/div/div[2]/a/span"));
        signIn.click();

        WebElement result = getDriver().findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div[2]/h1"));

        Assert.assertEquals(result.getText(), expectedResult);
    }

}
