import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AntonTest extends BaseTest {

    @Test
    public void testCheckTitle() {
        getDriver().get("https://www.gismeteo.ru/");
        Assert.assertEquals(getDriver().getTitle(), "GISMETEO: Погода в России, прогноз погоды на сегодня, завтра, 3 дня, выходные, неделю, 10 дней, месяц.");
    }

    @Test
    public void testCheckWeatherInCity() {
        getDriver().get("https://www.gismeteo.ru/");
        getDriver().findElement(By.xpath("//div[@class='search-label']/input")).sendKeys("Москва" + Keys.ENTER);
        getDriver().findElement(By.xpath("//div[@class='city-title '][normalize-space()='Москва']")).click();
        WebElement pageTitleElement = getDriver().findElement(By.xpath("//div[@class='page-title']"));
        Assert.assertEquals(pageTitleElement.getText(), "Погода в Москве сегодня");
    }
}