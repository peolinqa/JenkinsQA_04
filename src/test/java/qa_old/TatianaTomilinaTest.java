package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class TatianaTomilinaTest extends BaseTest {
    @Ignore
    @Test
    public void testFirstSelenium() {

        getDriver().get("https://www.verizon.com/");

        WebElement searchButton = getDriver().findElement(By.id("gnav20-Shop-L1"));
        searchButton.click();
        searchButton = getDriver().findElement(By.id("gnav20-Shop-L2-5"));
        searchButton.click();
        WebElement button = getDriver().findElement(By.id("gnav20-Shop-L3-13"));
        searchButton.click();

        Assert.assertEquals(button.getAttribute("aria-label"), "Unlimited");

    }
}

