import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LidiyaTest extends BaseTest {


    @Test
    public void lidiyaMakarovaTestForBBCPositiveCheck() throws InterruptedException {
        getDriver().get("https://www.bbc.com/");

        WebElement iconSignIn = getDriver().findElement(By.xpath("//*[@id='idcta-username']"));
        iconSignIn.click();
        Thread.sleep(1000);

        WebElement fieldLogin = getDriver().findElement(By.id("user-identifier-input"));
        WebElement fieldPassword = getDriver().findElement(By.id("password-input"));
        WebElement submitButton = getDriver().findElement(By.id("submit-button"));

        fieldLogin.sendKeys("li-ma@mail.ru");
        fieldPassword.sendKeys("R4e3w2Q1");
        submitButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(),"https://www.bbc.com/");

        WebElement iconYourAccount = getDriver().findElement(By.id("idcta-username"));
        Assert.assertEquals("Your account", iconYourAccount.getText());
    }

    @Test
    public void lidiyaMakarovaTestForBBCNegativeCheck() throws InterruptedException {
        getDriver().get("https://www.bbc.com/");
        WebElement iconSignIn = getDriver().findElement(By.xpath("//*[@id='idcta-username']"));
        iconSignIn.click();
        Thread.sleep(1000);

        WebElement fieldLogin = getDriver().findElement(By.id("user-identifier-input"));
        WebElement fieldPassword = getDriver().findElement(By.id("password-input"));
        WebElement submitButton = getDriver().findElement(By.id("submit-button"));

        fieldLogin.sendKeys("li-ma@mail.ru");
        fieldPassword.sendKeys("R4e3w2Q2");
        submitButton.click();

        WebElement errorText = getDriver().findElement(By.xpath("//*[@id='form-message-password']"));
        Assert.assertEquals(errorText.getText(),"Uh oh, that password doesn’t match that account. Please try again.");
    }
}


