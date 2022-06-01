package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class TatyanaMTest extends BaseTest {

  @Test
  public void testTatyanaMakarova1() throws InterruptedException {
    getDriver().get("https://jewellerymag.ru/");
    getDriver().findElement(By.xpath("//body[@id='app']/header//button[@aria-label='Открыть поиск']")).click();
    WebElement findBox = getDriver().findElement(By.name("s"));
    findBox.sendKeys("Чароит");
    findBox.submit();
    getDriver().findElement(By.xpath("//span[normalize-space(text())='Чароит']")).click();
    Thread.sleep(1000);
    Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Чароит");
  }

  @Test
  public void testTatyanaMakarova2() {
    getDriver().get("https://jewellerymag.ru/");
    getDriver().findElement(By.xpath("//body[@id='app']/header//button[@aria-label='Открыть поиск']")).click();
    WebElement findBox = getDriver().findElement(By.name("s"));
    findBox.sendKeys("!№;%:?*()");
    findBox.submit();
    Assert.assertEquals(getDriver().findElement(By.xpath("//main//div[@class='text-sm']")).getText(), "Увы, ничего не нашлось.");
  }
}

