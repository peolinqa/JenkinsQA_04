import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewFreestyleProjectNoSavingChanges extends BaseTest {

    private final String NEW_JOB_FREESTYLE_CLASS = "hudson_model_FreeStyleProject";

    protected void goToDashBoard() {
        getDriver().get("http://localhost:8080/");
    }

    protected void findItemOnJobsTableAndClick(String itemName) {
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

    @Test (description = "TC_001.039 New item > Create Freestyle project > Leave Page Without Saving Changes  ")
    public void createNewFPWithoutSavingChanges() {

        String projectName = "TC_001_039_".concat(RandomStringUtils.randomAlphabetic(5));
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        choseNewItemType(NEW_JOB_FREESTYLE_CLASS);
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("description")).sendKeys("Physically I'm fine, emotionally I'm bruised");
        goToDashBoard();
        getDriver().switchTo().alert().accept();
        getWait20();
        findItemOnJobsTableAndClick(projectName);

        Assert.assertEquals(getDriver().findElement(By.id("description")).getText(), "Add description");

        deleteFreestyleProject(projectName);
    }
}
