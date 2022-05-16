import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatyanaMTest extends BaseTest {

  public static final String XPATH = "xpath";
  public static final String NAME = "name";

  public WebElement findElement(String strFind, String typeFind){
    if (typeFind.equals(NAME)) {
      return getDriver().findElement(By.name(strFind));
    } else if (typeFind.equals(XPATH)) {
      return getDriver().findElement(By.xpath(strFind));
    }
    return null;
  }

  public void elementClick(String strFind, String typeFind) {
    findElement(strFind, typeFind).click();
  }

  public void elementSendText(String strFind, String typeFind, String text) {
    WebElement element = findElement(strFind, typeFind);
    element.sendKeys(text);
    element.submit();
  }

  @Test
  public void testTatyanaMakarova() throws InterruptedException {
    getDriver().get("https://jewellerymag.ru/");
    elementClick("//*[@id=\"app\"]/header/div/div/button", XPATH);
    elementSendText("s", NAME, "Чароит");
    elementClick("//*[normalize-space(text())='Чароит']", XPATH);
    Thread.sleep(1000);
    Assert.assertEquals(findElement("//h1", XPATH).getText(), "Чароит");
  }
}
