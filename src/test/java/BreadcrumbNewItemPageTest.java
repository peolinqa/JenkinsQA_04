import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class BreadcrumbNewItemPageTest extends BaseTest {

  private static final String PAGE_TITLE = "New Item [Jenkins]";
  private static final String BREADCRUMB1 = "Dashboard";
  private static final String BREADCRUMB2 = "All";

  protected final By NEW_ITEM = By.xpath("//a[@title='New Item']");

  @Test
  public void testBreadcrumbNewItemPage(){

    getDriver().findElement(NEW_ITEM).click();
    String actualTitle = getDriver().getTitle();
    WebElement actualBreadCrumb1 = getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//li"));
    WebElement actualBreadCrumb2 = getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//li[3]"));

    Assert.assertEquals(actualTitle,PAGE_TITLE);
    Assert.assertEquals(actualBreadCrumb1.getText(),BREADCRUMB1);
    Assert.assertEquals(actualBreadCrumb2.getText(),BREADCRUMB2);
  }
}
