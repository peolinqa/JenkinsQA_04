package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class BakhtiyarTest extends BaseTest {

    @Test
    public void testBakhtiyarMametyarov() {
        getDriver().get("https://habr.com/ru/all/");

        WebElement searchButton = getDriver().findElement(By.xpath("//a[@href='/ru/search/']"));
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.className("tm-input-text-decorated__input"));
        searchBox.sendKeys("Основы Postman для самых маленьких"+ "\n");
        searchButton = getDriver().findElement(By.xpath("(//a[@class='tm-article-snippet__title-link'])[1]"));
        searchButton.click();

        WebElement articleName = getDriver().findElement(By.xpath("//article//h1/span"));
        Assert.assertEquals(articleName.getText(), "Основы Postman для самых маленьких");
    }

}