import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class LetsBeTestersTest extends BaseTest {

    private void openNokiaPage() {
        getDriver().get("https://www.nokia.com/");
        getDriver().findElement(By.id("modalAcceptAllBtn")).click();
    }

    @Test
    public void testCountOfSectionButtons() {

        openNokiaPage();

        List<WebElement> carousel = getDriver().findElements(By.xpath("//div[contains(@id, 'tns1-item')]//h2"));

        Assert.assertEquals(carousel.size(), 9);
    }

    @Test
    public void testCheckTablet() {

        openNokiaPage();

        Actions action = new Actions(getDriver());

        List<WebElement> menu = getDriver().findElements(By.xpath("//li[contains(@class, 'dropdown-menu')]/a"));
        WebElement forConsumers = menu.stream().filter(el2 -> el2.getText().equals("For consumers")).findFirst().orElse(null);

        action.moveToElement(forConsumers).build().perform();

        getDriver().findElement(By.xpath("//li[@class='dropdown-submenu-item']/a[text()='Phones']")).click();
        getDriver().findElement(By.xpath("//a[@data-gtm-cta='tablets']")).click();
        String actualResult = getDriver().findElement(By.xpath("//li[contains(@class, 'h5')]")).getText();

        Assert.assertEquals(actualResult, "Nokia T20");
    }
}
