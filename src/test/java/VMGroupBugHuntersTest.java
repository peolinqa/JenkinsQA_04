import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


    public class VMGroupBugHuntersTest extends BaseTest {

        @Test
        public void veranikaMalazhavayaFirstTest() {

            getDriver().get("https://www.booking.com/");

            getDriver().findElement(By.cssSelector("[data-modal-id='language-selection']")).click();
            getDriver().findElement(By.cssSelector("[data-lang='en-us']")).click();

            WebElement searchBox = getDriver().findElement(By.className("c-autocomplete__input"));
            searchBox.sendKeys("Charlotte");
            getDriver().findElement(By.className("xp__button")).click();

            searchBox = getDriver().findElement(By.name("ss"));

            Assert.assertEquals(searchBox.getAttribute("value"),"Charlotte");

        }
    }

