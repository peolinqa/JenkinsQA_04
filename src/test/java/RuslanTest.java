import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class RuslanTest extends BaseTest{
  private static final String EXPECTED_PRICE = "От 9 258 600 ₽ 1";

  @BeforeMethod
  @Override
  protected void beforeMethod() {
    super.beforeMethod();
    getDriver().manage().deleteAllCookies();
  }

  @Test
  public void testVolkswagenSite() {

    getDriver().get("https://www.volkswagen.ru/");
    getDriver().findElement(By.xpath("//button[@aria-label='Меню']")).click();
    getDriver().findElement(By.xpath("//a[@title =\"Модельный ряд\"]")).click();

    JavascriptExecutor scroll = (JavascriptExecutor)getDriver();
    scroll.executeScript("scroll(0, 500);");
    getDriver().findElement(By.xpath("//h3[contains(text(),'Touareg')]")).click();

    WebElement actualPrice= getDriver()
            .findElement(By.xpath("//li[@aria-label='Exclusive']//div[@class='sc-bZQynM heaSyW']//div[1]"));

    Assert.assertEquals(actualPrice.getText(),EXPECTED_PRICE);
    }
  }

