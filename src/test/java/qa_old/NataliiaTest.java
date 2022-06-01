package qa_old;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class NataliiaTest extends BaseTest {

        @Test
        public void testNataliiaBorisenko() {

            getDriver().get("https://www.duolingo.com/");
            Assert.assertEquals(getDriver().getTitle(), "Duolingo - The world's best way to learn a language");

            getDriver().findElement(By.xpath("//a[@class = '_24dlP _3HhhB _2NolF _275sd _1ZefG _6TCdY']")).click();
            Assert.assertEquals(getDriver().findElement(
                    By.xpath("//h1[@class = '_1Ii2h']")).getText(), "I want to learn...");
        }
}
