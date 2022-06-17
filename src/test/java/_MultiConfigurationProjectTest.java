import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class _MultiConfigurationProjectTest extends BaseTest {
  private final String NAME_FOLDER = "TestMultiConfigurationProject";

  protected void createMultiConfigFolder(String name) {
    getDriver().findElement(By.linkText("New Item")).click();
    WebElement itemName = getDriver().findElement(By.id("name"));
    itemName.sendKeys(name);
    getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[3]")).click();
    getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
  }

  protected void deleteFolder(String name){

    Actions action = new Actions(getDriver());
    action.moveToElement(getDriver().findElement(
            By.xpath("//a[@href='job/" + name + "/']"))).click().build().perform();
    getDriver().findElement(By.xpath("//span[text()='Delete Multi-configuration project']")).click();
    getDriver().switchTo().alert().accept();
  }

  protected void returnToMainPage() {
    getDriver().findElement(By.id("jenkins-home-link")).click();
  }

  protected boolean isElementPresent(String name) {
    try {
      getDriver().findElement(By.xpath("//tr[@id='job_" + name + "']//td[3]"));
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  protected void runBuildNow(String name){
    getDriver().findElement(By.id("jenkins-home-link")).click();
    getDriver().findElement(By.xpath("//a[contains(text(),'" +name+ "')]")).click();
    getDriver().findElement(By.linkText("Build Now")).click();
  }

  protected void selectTopBuildInHistory(){
    getDriver().findElement(By.className("build-status-link")).click();
  }

  protected boolean isSuccesedBuildIsDipslayed(){
    return getDriver().findElement(
                    By.xpath("//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")).isDisplayed();
  }

  protected void openProjectJob(String name){
    WebElement nameOnDashboard = getDriver().findElement(By.xpath("//a[@href='job/"+name+"/']"));
    nameOnDashboard.click();
  }

  protected String getFolderNameOnDashboard(String name){
    WebElement nameOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + NAME_FOLDER + "']//td[3]"));
    return nameOnDashboard.getText();
  }

  @Test
  public void testCreateMultiConfigFolder_TC_041_001() {

    createMultiConfigFolder(NAME_FOLDER);
    returnToMainPage();

    Assert.assertEquals(getFolderNameOnDashboard(NAME_FOLDER), NAME_FOLDER);
  }

  @Test(dependsOnMethods={"testCreateMultiConfigFolder_TC_041_001"})
  public void testBuildNow_TC_044_001(){

    runBuildNow(NAME_FOLDER);
    getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("build-status-link")));
    selectTopBuildInHistory();

    Assert.assertTrue(isSuccesedBuildIsDipslayed());
  }

  @Test(dependsOnMethods={"testBuildNow_TC_044_001"})
  public void testDeleteMultiConfigFolder_TC_041_002(){
    String nameTestedFolder = "testToDelete";

    createMultiConfigFolder(nameTestedFolder);
    returnToMainPage();
    deleteFolder(nameTestedFolder);

    Assert.assertFalse(isElementPresent(nameTestedFolder));
  }

  @Test
  public void testBuildNowInDisabledProject_TC_045_002(){
    String nameTestedFolder = "disabledFolder";
    boolean isBuildNowDisplayed = false;

    createMultiConfigFolder(nameTestedFolder);
    returnToMainPage();
    openProjectJob(nameTestedFolder);
    getDriver().findElement(By.id("yui-gen1-button")).click();

    List<WebElement> jobMenu = getDriver().findElements(By.xpath("//div[@id='tasks']//span[2]"));
    for(WebElement menu : jobMenu){
      if(menu.getText().contains("Build Now")){
        isBuildNowDisplayed = true;
      }
    }

    Assert.assertFalse(isBuildNowDisplayed);
  }


  private final String PROJECT_NAME = "Mcproject";

  private void createMCProject(String name) {
    getDriver().findElement(By.linkText("New Item")).click();
    getDriver().findElement(By.id("name")).sendKeys(name);
    getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
    getDriver().findElement(By.id("ok-button")).click();
    getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
  }

  private void deleteMCProject() {
    getDriver().findElement(By.linkText("Delete Multi-configuration project")).click();
    getDriver().switchTo().alert().accept();
  }

  @Test
  public void testCreateMultiConfigurationProject() {
    String expectedResult = "Mcproject";

    createMCProject(PROJECT_NAME);

    String actualResult = getDriver().findElement(By.xpath("//div/ul/li/a[@href='/job/" + PROJECT_NAME +"/']")).getText();
    Assert.assertEquals(actualResult, expectedResult);

    deleteMCProject();
  }

  @Test
  public void testAddDescription(){
    String expectedResult = "test";
    createMCProject(PROJECT_NAME);

    getDriver().findElement(By.id("description-link")).click();
    getDriver().findElement(By.xpath("//div/textarea[@name=\"description\"]")).sendKeys("test");
    getDriver().findElement(By.id("yui-gen2-button")).click();

    String actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
    Assert.assertEquals(actualResult, expectedResult);

    deleteMCProject();
  }

  @Test
  public void testRenameMCProject() {
    String expectedResult = "Project McprojectRename";

    createMCProject(PROJECT_NAME);
    getDriver().findElement(By.linkText("Rename")).click();
    getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"))
            .sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "McprojectRename");
    getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

    String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
    Assert.assertEquals(actualResult, expectedResult);

    deleteMCProject();
  }

  @Test
  public void testRenameMCProjectError() {
    String expectedResult = "Error\nThe new name is the same as the current name.";

    createMCProject(PROJECT_NAME);
    getDriver().findElement(By.linkText("Rename")).click();
    getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"));
    getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

    String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText();
    Assert.assertEquals(actualResult, expectedResult);

    getDriver().findElement(By.xpath("//div/ul/li/a[@href='/job/Mcproject/']")).click();
    deleteMCProject();
  }
}