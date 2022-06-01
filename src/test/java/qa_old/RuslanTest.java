package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class RuslanTest extends BaseTest{

  @Test
  public void testVolkswagenSite(){

    getDriver().get("https://www.volkswagen.ru/");
    getDriver().findElement(By.xpath("//button[@aria-label='Меню']")).click();
    getDriver().findElement(By.xpath("//a[@title =\"Модельный ряд\"]")).click();

    ((JavascriptExecutor)getDriver()).executeScript("scroll(0, 500);");
    getDriver().findElement(By.xpath("//h3[contains(text(),'Touareg')]")).click();

    WebElement actualTextPriceOfTouareg= getDriver()
            .findElement(By.xpath("//li[@aria-label='Exclusive']//div[@class='sc-bZQynM heaSyW']//div[1]"));

    Assert.assertEquals(actualTextPriceOfTouareg.getText(),"От 9 258 600 ₽ 1");
    }
  }

