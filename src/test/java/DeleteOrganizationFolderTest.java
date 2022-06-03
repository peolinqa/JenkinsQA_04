import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.NoSuchElementException;

public class DeleteOrganizationFolderTest extends BaseTest {

    private final String VALID_VALUE_FOR_NAME = "New organization folder";

    private void preconditionCreateOrganizationFolder() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME);
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@id = 'yui-gen17-button']")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }

    @Test
    public void testDeleteOrganizationFolder() {
        preconditionCreateOrganizationFolder();
        getDriver().findElement(By.linkText(VALID_VALUE_FOR_NAME)).click();
        getDriver().findElement(By.linkText("Delete Organization Folder")).click();
        getDriver().findElement(By.xpath("//button[@id = 'yui-gen1-button']")).click();
        try {
            List<WebElement> tableOnDashboardAfter =
                    getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
            boolean expectedResultInTable = false;
            for (WebElement item : tableOnDashboardAfter) {
                if (item.getText().contains(VALID_VALUE_FOR_NAME)) {
                    expectedResultInTable = true;
                }
            }
            Assert.assertFalse(expectedResultInTable);
        } catch (NoSuchElementException exception) {
            Assert.assertTrue(true);
        }
    }
}
