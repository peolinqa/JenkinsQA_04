import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TatianaP_Jenkins_Test extends BaseTest {
    private void deleteNewFolder() {

        getDriver().findElement(By.linkText("Delete Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testCreateNewItem() {
        String expectedResult = "Hello Java";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Hello Java");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen6-button")).click();
        String actualResult = getDriver().findElement(By.xpath("//h1['     Hello Java   ']")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        deleteNewFolder();
    }
}
