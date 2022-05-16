import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class GroupDreamTeamJavaTest extends BaseTest {

    @Test
    public void testFelix_IX(){

        getDriver().get("https://habr.com/ru/all/");

        String text = "XPath";
        getDriver().findElement(By
                        .xpath("//*[@class='tm-header-user-menu__item tm-header-user-menu__search']"))
                .click();

        getDriver().findElement(By.xpath("//*[@class='tm-input-text-decorated__input']"))
                .sendKeys(text + "\n");

        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("scroll(0, 2000);");

        getDriver().findElement(By
                        .xpath("//*[contains(text(),'Вот почему мы всегда пишем селекторы на ')]"))
                .click();

        WebElement x =  getDriver().findElement(By
                .xpath("//*[@class='tm-article-snippet__title tm-article-snippet__title_h1']"));

        Assert.assertEquals(x.getText(), "Вот почему мы всегда пишем селекторы на XPath");
    }
    @Test
    public void findAuto_AliaksandrD() throws InterruptedException {
        getDriver().get("https://av.by");

        WebElement autoBrand = getDriver()
                .findElement(By.xpath("//button[@name='p-6-0-2-brand']"));

        autoBrand.click();

        WebElement searchAutoBrand = getDriver()
                .findElement(By.xpath("//input[@placeholder='Поиск']"));
        searchAutoBrand.sendKeys("Audi" + Keys.ENTER);

        WebElement model = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='p-6-0-3-model']")));
        model.click();

        WebElement searchModel = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-item-label='80']")));
        searchModel.click();

        Thread.sleep(3000);
        WebElement buttonShow = getDriver().findElement(By.xpath("//a[@class='button button--secondary button--block']"));

        buttonShow.click();
        //Title result:
        //Купить Audi 80 | 310 объявлений о продаже на av.by | Цены, характеристики, фото.
        String[] title = getDriver()
                .getTitle()
                .split(" ");
        String actual = title[1] + " " + title[2];
        Assert.assertEquals(actual, "Audi 80");
    }

}
