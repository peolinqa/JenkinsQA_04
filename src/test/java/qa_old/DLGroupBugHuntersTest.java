package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class DLGroupBugHuntersTest extends BaseTest {

    @Test
    public void testAlmazinka() {

        getDriver().get("https://www.almazinka.ru/");
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@placeholder='Поиск по сайту']"));
        searchBox.click();
        searchBox.sendKeys("Тигр");
        WebElement searchButton = getDriver().findElement(By.xpath("//form[@method='get']//button[@class='uh-btn']"));
        searchButton.click();
        String actualResult = getDriver().findElement(By.xpath("//h1[contains(text(),'Тигр')]")).getText();
        Assert.assertEquals(actualResult,"Результаты поиска по запросу «Тигр»");
    }
}
