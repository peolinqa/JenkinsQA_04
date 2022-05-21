import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ISGroupBugHuntersTest extends BaseTest {

    @Ignore
        @Test
        public void ingaShauchenkaFirstTest() throws InterruptedException {
            getDriver().get("https://www.tutorialspoint.com/");

            WebElement categoryDropdownMenu = getDriver().findElement(
                    By.xpath("//span[@class='d-xl-inline-block dropdown-toggle']")
            );
            categoryDropdownMenu.click();
            Thread.sleep(1000);

            WebElement developmentDropdownItem = getDriver().findElement(
                    By.xpath("//a[@class='dropdown-item']/i[@class='fa fa-cogs cat-icons']")
            );
            developmentDropdownItem.click();
            Thread.sleep(1000);

            Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.tutorialspoint.com/categories/development");
        }

        @Test
        public void ingaShauchenkaSecondTest() {
            getDriver().get("https://www.tutorialspoint.com/");

            WebElement textH1 = getDriver().findElement(By.xpath("//h1"));
            String result = textH1.getText();

            Assert.assertEquals(result, "Simply Easy Learning at your fingertips");
        }
}

