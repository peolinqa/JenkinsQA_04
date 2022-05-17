import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OleMarinaTest extends BaseTest {

    @Test
    public void testContactMeForm () throws InterruptedException {

        getDriver().get("https://my-greekitchen.com/?page_id=59");
        getDriver().manage().window().maximize();

        WebElement name = getDriver().findElement(By.name("nf-field-1"));
        name.sendKeys("Masha");

        WebElement email = getDriver().findElement(By.id("nf-field-2"));
        email.sendKeys("a@email.com");


        email = getDriver().findElement(By.id("nf-field-2"));

        Thread.sleep(5000);

        Assert.assertEquals(email.getAttribute("value"), "a@email.com");

    }

}











