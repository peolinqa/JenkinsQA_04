import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BakhtiyarTest extends BaseTest {

    @Test
    public void bakhtiyarMametyarov() {
        getDriver().get("https://habr.com/ru/all/");
        WebElement searchButton = getDriver().findElement(By.xpath("//a[@href=\"/ru/search/\"]"));
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.className("tm-input-text-decorated__input"));
        searchBox.sendKeys("Основы Postman для самых маленьких"+ "\n");
        searchButton = getDriver().findElement(By.xpath("(//a[@class='tm-article-snippet__title-link'])[1]"));
        searchButton.click();

        String text = getDriver().findElement(By.xpath("//article//h1/span")).getText();
        Assert.assertEquals(text, "Основы Postman для самых маленьких");
    }

}