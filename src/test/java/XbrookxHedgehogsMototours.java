import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class XbrookxHedgehogsMototours extends BaseTest {

    @Test
    public void testSearchBox() throws InterruptedException {

        getDriver().get("https://moto.tours/");

        WebElement serachButton = getDriver().findElement(By.xpath("//div[@class='search-title-button-text']"));

        serachButton.click();
        WebElement searchBox = getDriver().findElement(By.xpath("//*[@class='search-title-form-input']"));

        searchBox .sendKeys("Кофры", Keys.ENTER);

        WebElement searchBoxEnd = getDriver().findElement(By.xpath("//*[@class='search-suggest']"));
        Assert.assertEquals(searchBoxEnd.getAttribute("value"), "Кофры");

    }
}
