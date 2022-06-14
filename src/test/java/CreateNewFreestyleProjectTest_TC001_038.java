import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class CreateNewFreestyleProjectTest_TC001_038 extends BaseTest {

    private final String NEW_JOB_FREESTYLE_CLASS = "hudson_model_FreeStyleProject";

    protected void goToDashBoard() {
        getDriver().get("http://localhost:8080/");
    }

    public void findItemOnJobsTableAndClick(String itemName) {
        itemName = itemName.replaceAll(" ", "%20");
        getDriver().findElement(By.xpath("//a[@href=\"job/".concat(itemName).concat("/\"]"))).click();
    }

    protected void deleteFreestyleProject(String itemName){
        getDriver().get("http://localhost:8080/job/".concat(itemName).concat("/"));
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    protected void choseNewItemType(String itemType) {
        getDriver().findElement(By.className(itemType)).click();
    }

    @Test (invocationCount = 20) //(description = "TC_001.038 New item > Create Freestyle project ")
    public void createFreestyleProjectWithValidName() {
        String projectBName = "newFreestyleProject";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectBName);
        choseNewItemType(NEW_JOB_FREESTYLE_CLASS);
        getDriver().findElement(By.id("ok-button")).click();
        goToDashBoard();
        findItemOnJobsTableAndClick(projectBName);
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/newFreestyleProject/");

        deleteFreestyleProject(projectBName);
    }

    @Test (invocationCount = 20)  //(description = "TC_001.038 New item > Create Freestyle project ")
    public void createFreestyleProjectWithInvalidName() {
        String projectBName = "[]//*";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectBName);
        getWait20();

        Assert.assertTrue(getDriver().findElement(By.id("itemname-invalid")).isEnabled());
    }

    @Test (invocationCount = 20)  //(description = "TC_001.038 New item > Create Freestyle project ")
    public void createFreestyleProjectWithSpacesInName() {
        String projectBName = "new Freestyle Project";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectBName);
        choseNewItemType(NEW_JOB_FREESTYLE_CLASS);
        getDriver().findElement(By.id("ok-button")).click();
        goToDashBoard();
        findItemOnJobsTableAndClick(projectBName);

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/new%20Freestyle%20Project/");

        deleteFreestyleProject(projectBName);
    }

    @Test (invocationCount = 20)  //(description = "TC_001.038 New item > Create Freestyle project ")
    public void createFreestyleProjectNullName()  {
        String projectBName = "lalala";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectBName);
        getDriver().findElement(By.id("name")).clear();
        getWait20();

        Assert.assertTrue(getDriver().findElement(By.id("itemname-required")).isEnabled());
    }
}
