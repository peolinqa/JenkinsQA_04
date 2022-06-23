import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import static runner.TestUtils.getRandomStr;

public class CreateFreestyleProjectNoSavingChangesTest extends BaseTest {

    private static final String projectName = "TC_001_039_".concat(getRandomStr(5));

    protected void findItemOnJobsTableAndClick(String itemName) {
        itemName = itemName.replaceAll(" ", "%20");
        getDriver().findElement(By.xpath("//a[@href=\"job/".concat(itemName).concat("/\"]"))).click();
    }

    @Test
    public void createNewFPWithoutSavingChanges() {

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("description")).sendKeys("Physically I'm fine, emotionally I'm bruised");
        getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]")).click();
        getDriver().switchTo().alert().accept();
        findItemOnJobsTableAndClick(projectName);

        Assert.assertEquals(getDriver().findElement(By.id("description")).getText(), "Add description");
    }
}
