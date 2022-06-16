import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class _OrganizationFolderTest extends BaseTest {

    private final String VALID_VALUE_FOR_NAME1 = "Organization Test";
    private final String VALID_VALUE_FOR_NAME2 = "Test";

    public void getDashboardPage(){ getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click(); }
    public void getButtonOk(){ getDriver().findElement(By.id("ok-button")).click(); }
    public void getButtonSave(){ getDriver().findElement(By.id("yui-gen17-button")).click(); }
    public void getButtonYes(){ getDriver().findElement(By.id("yui-gen1-button")).click(); }

    @Test(priority = 1)
    public void createOrganizationFolder (){
        getDriver().findElement(By.xpath("//span[@class ='task-link-text'][text() = 'New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME1);

        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("window.scrollBy(0,350)");

        getDriver().findElement(By.xpath("//span[@class ='label'][text()='Organization Folder']")).click();
        getButtonOk();
        getButtonSave();
        getDashboardPage();

        Assert.assertEquals
                (getDriver().findElement(By.xpath("//a[text()='Organization Test']"))
                                .getText(),VALID_VALUE_FOR_NAME1);
    }


    @Test(priority = 2)
    public void renameOrganizationFolder() {
        String expectedResult = VALID_VALUE_FOR_NAME2;
        getDriver().findElement(By.xpath("//a[text()='Organization Test']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Rename')]")).click();
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(VALID_VALUE_FOR_NAME2);
        getButtonYes();
        String actualResult = getDriver().findElement(By.xpath("//a[text()='Test']")).getText();

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Ignore
    @Test(priority = 3)
    public void deleteOrganizationFolder() {
        getDashboardPage();
        List<WebElement> tableOnDashboard =
                getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
        for (WebElement item : tableOnDashboard){
            if (item.getText().contains(VALID_VALUE_FOR_NAME2)) {
                getDriver().findElement(By.xpath("//a[text()='Test']")).click();
                getDriver().findElement(By.linkText("Delete Organization Folder")).click();
                getButtonYes();
                break;
            }
        }
        Assert.assertEquals(getDriver().findElements(By.xpath("//a[text()='Test']")).size(),0);
    }
}
