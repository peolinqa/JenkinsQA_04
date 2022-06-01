package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class LadaTest extends BaseTest {
    @Test
    public void testLadaZhivitsaForOnliner() {
        getDriver().get("https://www.onliner.by/");

        WebElement button = getDriver().findElement(By.xpath("//div[text()='Вход']"));
        button.click();

        WebElement fieldNick = getDriver().findElement(By.xpath("//input[@Placeholder='Ник или e-mail']"));
        WebElement fieldPassword = getDriver().findElement(By.xpath("//input[@type = 'password']"));
        WebElement submit = getDriver().findElement(By.xpath("//button[contains(@class,'auth-button_primary')]"));

        fieldNick.sendKeys("invalidNick");
        fieldPassword.sendKeys("invalidPassword");
        submit.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[contains(@class,'description_error')]"));

        Assert.assertEquals(errorMessage.getText(), "Неверный логин или пароль");

    }

}
