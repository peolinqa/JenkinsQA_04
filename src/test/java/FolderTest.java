import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FolderTest extends BaseTest {
  private final String NAME_FOLDER = "Configure";

  protected void createFolder(){
    getDriver().findElement(By.linkText("New Item")).click();
    WebElement itemName = getDriver().findElement(By.id("name"));
    itemName.sendKeys(NAME_FOLDER);
    getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
    getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
  }

  protected void createFolder(String folderName){
    getDriver().findElement(By.linkText("New Item")).click();
    WebElement itemName = getDriver().findElement(By.id("name"));
    itemName.sendKeys(folderName);
    getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
    getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
  }

  protected void deleteFolder(){
    getDriver().findElement(By.id("jenkins-home-link")).click();
    getDriver().findElement(By.xpath("//a[contains(text(),'" +NAME_FOLDER+ "')]")).click();
    getDriver().findElement(By.linkText("Delete Folder")).click();
    getDriver().findElement(By.id("yui-gen1-button")).click();
  }

  protected void deleteFolder(String folderName){
    getDriver().findElement(By.id("jenkins-home-link")).click();
    getDriver().findElement(By.xpath("//a[contains(text(),'" +folderName+ "')]")).click();
    getDriver().findElement(By.linkText("Delete Folder")).click();
    getDriver().findElement(By.id("yui-gen1-button")).click();
  }

  @Test
  public void testConfigurePage_TC_015_001() throws InterruptedException {
    final String expectedUrl = "http://localhost:8080/job/".concat(NAME_FOLDER).concat("/configure");

    createFolder();
    Thread.sleep(1000);

    String actualURL = getDriver().getCurrentUrl();

    Assert.assertEquals(actualURL,expectedUrl);
    deleteFolder();
  }
}