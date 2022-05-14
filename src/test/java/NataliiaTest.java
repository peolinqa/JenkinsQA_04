import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliiaTest extends BaseTest {

        @Test
        public void testNataliiaBorisenko() throws InterruptedException {

            getDriver().get("https://www.duolingo.com/");
            Thread.sleep(2000);

            Assert.assertEquals(getDriver().getTitle(),
                    "Duolingo - The world's best way to learn a language");
            Thread.sleep(2000);

            WebElement startBox = getDriver().findElement(By.xpath(
                    "//a[@class = '_24dlP _3HhhB _2NolF _275sd _1ZefG _6TCdY']"
            ));
            startBox.click();
            Thread.sleep(1000);

            Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class = '_1Ii2h']")).getText(),
                    "I want to learn...");

            WebElement chooseLanguage = getDriver().findElement(By.xpath("//button[@class = 'uS_Xr _2s7-g zA0zE']"));
            chooseLanguage.click();
            Thread.sleep(3000);

            Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class = 'DYCFd']")).getText(),
                    "How did you hear about us?");
        }
}
