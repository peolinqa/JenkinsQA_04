import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ChangeAppearanceToDefaultIconOrganizationFolderTest extends BaseTest {

    private final String VALID_VALUE_FOR_NAME = "New organization folder";

    private void preconditionCreateOrganizationFolder(){
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(VALID_VALUE_FOR_NAME);
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen17-button")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }
    private void postconditionDeleteFolderAfterTest(){
        getDriver().findElement(By.linkText("" + VALID_VALUE_FOR_NAME + "")).click();
        getDriver().findElement(By.linkText("Delete Organization Folder")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }
    @Test
    public void testChangeAppearanceToDefaultIconOrganizationFolder() {
        preconditionCreateOrganizationFolder();
        List<WebElement> tableOnDashboard =
                getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a"));
        for (WebElement item : tableOnDashboard){
            if (item.getText().contains(VALID_VALUE_FOR_NAME)) {
                item.click();
            }
        }
        getDriver().findElement(By.linkText("Configure")).click();
        getDriver().findElement(By.xpath("//div[@class = 'tab config-section-activator config_appearance']")).click();
        getDriver().findElement(By.xpath(
                "//div[@class = 'jenkins-form-item has-help config_appearance active']" +
                        "//div/select[@class = 'setting-input dropdownList']")).click();
        getDriver().findElement(By.xpath(
                "//div[@class='jenkins-form-item has-help config_appearance active']//div/select/option[@value = '0']"))
                .click();
        getDriver().findElement(By.id("yui-gen17-button")).click();
        WebElement defaultIcon = getDriver().findElement(By.xpath("//img[@title = 'Folder']"));
        Assert.assertTrue(defaultIcon.isDisplayed());
        postconditionDeleteFolderAfterTest();
    }
}
