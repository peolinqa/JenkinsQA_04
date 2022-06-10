import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

/**
 * log in Jenkins
 * - click "New Item"
 * - create new folder  "Financial Project"
 * - select "Folder"
 * - click "Ok"
 * - add description "The Company created new financial projec witn return investments"
 * - click "Save"
 * - make sure the description is displayed
 */

public class CreateDescriptionOfFolderTest extends BaseTest {

    @Test
    public void testTC_011_004_NewFolderDescription(){

        final String descriptionText = "The Company created new financial projec witn return investments";

        WebDriver driver = getDriver();

        getDriver().findElement(By.xpath("//div[@id='tasks']/div/span/a/span[2]")).click();
        getDriver().findElement(By.name("name")).sendKeys("Financial Project");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']//li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("_.description"))
                .sendKeys("The Company created new financial projec witn return investments");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        Assert.assertEquals(descriptionText, getDriver().findElement(By.id("view-message")).getText());

    }
}
