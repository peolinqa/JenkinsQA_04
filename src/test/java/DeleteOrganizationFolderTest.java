import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class DeleteOrganizationFolderTest extends BaseTest {

    final String VALID_VALUE_FOR_NAME = "New organization folder";

    private void preconditionCreateOrganizationFolder(){
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME);
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen17-button")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }
    @Test
    public void testDeleteOrganizationFolder()  {
        preconditionCreateOrganizationFolder();
        List<WebElement> tableOnDashboard =
                getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
        for (WebElement item : tableOnDashboard){
            if (item.getText().contains(VALID_VALUE_FOR_NAME)) {
                item.click();
            }
        }
        getDriver().findElement(By.linkText("Delete Organization Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> tableOnDashboardAfter =
                getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
        boolean expectedResult = false;
        for (WebElement item : tableOnDashboardAfter){
            if (item.getText().contains(VALID_VALUE_FOR_NAME)) {
                expectedResult = true;
            }
        }
        Assert.assertFalse(expectedResult);
    }
}
