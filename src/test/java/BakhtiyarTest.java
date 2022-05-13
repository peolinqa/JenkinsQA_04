import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BakhtiyarTest extends BaseTest {

    @Test
    public void bakhtiyarMametyarov() throws InterruptedException {
        getDriver().get("https://habr.com/ru/all/");
        WebElement searchButton = getDriver().findElement(By.xpath("//a[@href=\"/ru/search/\"]"));
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.className("tm-input-text-decorated__input"));
        searchBox.sendKeys("Основы Postman для самых маленьких"+ "\n");
        searchButton = getDriver().findElement(By.className("tm-article-snippet__title-link"));
        searchButton.click();
        Thread.sleep(1000);
        String text = getDriver().findElement(By.xpath("//*/article/div[1]/div/h1/span")).getText();
        Assert.assertEquals(text, "Основы Postman для самых маленьких");
    }

}