import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FolderTest extends BaseTest {

  private final String PROP_PORT = "8080";
  private final String NAME_FOLDER = "Configure";
  private final char[] INVALID_SYMBOLS =
          {92, ':', ';', '/', '!', '@', '#', '$', '%', '^', '[', ']', '&', '*', '<', '>', '?', '|'};

  private void createFolder(String folderName){
    getDriver().findElement(By.linkText("New Item")).click();
    WebElement itemName = getDriver().findElement(By.id("name"));
    itemName.sendKeys(folderName);
    getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
    getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
  }

  private void deleteFolder(String folderName){
    getDriver().findElement(By.id("jenkins-home-link")).click();
    getDriver().findElement(By.xpath("//a[contains(text(),'" +folderName+ "')]")).click();
    getDriver().findElement(By.linkText("Delete Folder")).click();
    getDriver().findElement(By.id("yui-gen1-button")).click();
  }

  private boolean isFolderPresent(String name) {
    try {
      getDriver().findElement(By.xpath("//tr[@id='job_" + name + "']//td[3]"));
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  @Test
  public void testConfigurePage(){

    final String expectedUrl = String.format("http://localhost:%s", PROP_PORT)
            .concat("/job/")
            .concat(NAME_FOLDER)
            .concat("/configure");

    createFolder(NAME_FOLDER);
    getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins")));

    String actualURL = getDriver().getCurrentUrl();
    Assert.assertEquals(actualURL,expectedUrl);
  }

  @Test
  public void testCycleCreateFolderWithInvalidData() {

    getDriver().findElement(By.className("task-link-text")).click();

    WebElement inputField = getDriver().findElement(By.id("name"));



    for (int i = 0; i < INVALID_SYMBOLS.length; i++) {
      inputField.sendKeys(Character.toString(INVALID_SYMBOLS[i]));
      WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

      getWait5().until(ExpectedConditions.textToBePresentInElement(warningText,
              "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character"));

      String expectedResult = "» ‘" + INVALID_SYMBOLS[i] + "’ is an unsafe character";
      Assert.assertEquals(warningText.getText(),
              expectedResult);
      inputField.clear();
    }
  }

  @Test
  public void testCreateFolderWithDot() {

    getDriver().findElement(By.className("task-link-text")).click();
    WebElement inputField = getDriver().findElement(By.id("name"));

    inputField.sendKeys(".");
    WebElement warningText = getDriver().findElement(By.id("itemname-invalid"));

    getWait5().until(ExpectedConditions.textToBePresentInElement(warningText, "» “.” is not an allowed name"));
    Assert.assertEquals(warningText.getText(), "» “.” is not an allowed name");
    inputField.clear();
  }

  @Test(dependsOnMethods = {"testConfigurePage"})
  public void testDeleteFolder(){

    deleteFolder(NAME_FOLDER);

    Assert.assertFalse(isFolderPresent(NAME_FOLDER));

  }
}