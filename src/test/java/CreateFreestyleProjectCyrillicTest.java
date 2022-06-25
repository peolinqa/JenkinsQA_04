import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import static runner.TestUtils.getRandomStrCyrillic;

public class CreateFreestyleProjectCyrillicTest extends BaseTest {

    private static String projectNameCyrillic = getRandomStrCyrillic();

    @Test
    public void CreateFreestyleWithCyrillic() {

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(projectNameCyrillic);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[3]")).getText(), projectNameCyrillic);

    }
}