import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MultiConfigurationProjectTest extends BaseTest {
  private final String NAME_FOLDER = "TestMultiConfigurationProject";

  protected void createMultiConfigFolder() {
    getDriver().findElement(By.linkText("New Item")).click();
    WebElement itemName = getDriver().findElement(By.id("name"));
    itemName.sendKeys(NAME_FOLDER);
    getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[3]")).click();
    getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
  }

  protected void deleteFolder(){
    getDriver().findElement(By.id("jenkins-home-link")).click();
    getDriver().findElement(By.linkText(NAME_FOLDER)).click();
    getDriver().findElement(By.xpath("//a[contains(@class,'confirmation-link')]")).click();
    Alert alert = getDriver().switchTo().alert();
    alert.accept();
  }

  protected void returnToMainPage() {
    getDriver().findElement(By.id("jenkins-home-link")).click();
  }

  protected boolean isElementPresent() {
    try {
      getDriver().findElement(By.xpath("//tr[@id='job_" + NAME_FOLDER + "']//td[3]"));
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  @Test
  public void testCreateMultiConfigFolder_TC_041_001() {

    createMultiConfigFolder();
    returnToMainPage();

    WebElement nameOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + NAME_FOLDER + "']//td[3]"));

    Assert.assertEquals(nameOnDashboard.getText(), NAME_FOLDER);
  }

  @Test
  public void testDeleteMultiConfigFolder_TC_041_002(){

    deleteFolder();

    Assert.assertFalse(isElementPresent());
  }
}