import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
@Ignore
public class OrganizationFolderChangeAppearanceTest extends BaseTest {

    private static final By MENU_DASHBOARD = By.xpath("//li[@class='item']");

    private void deleteFolder() {
        final String NAME_MY_ITEM = "Servachak Organization Folder";

        getDriver().findElement(MENU_DASHBOARD).click();

        List<WebElement> itemNamesInTable = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']/tbody//a"));

        Actions action = new Actions(getDriver());

        for (WebElement a : itemNamesInTable) {
            if (a.getText().contains(NAME_MY_ITEM)) {
                action.moveToElement(a).perform();
            }
        }
        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(
                By.xpath("//span[contains(text(),'Delete Organization Folder')]"))
                .click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/logout']")));
    }
    @Ignore
    @Test
    public void testOrganizationFolderChangeAppearance() {
        final String FOLDER_NAME = "Servachak Organization Folder TC_039.002";
        By buttonSave = By.id("yui-gen17-button");

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(
                        By.xpath("//li[@class='jenkins_branch_OrganizationFolder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(buttonSave).click();

        String iconFirst = getDriver().findElement(By.xpath("//h1/img")).
                getAttribute("title");

        getDriver().findElement(MENU_DASHBOARD).click();
        getDriver().findElement(
                        By.xpath("//a[@href='job/Servachak%20Organization%20Folder%20TC_039.002/']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./configure']")).click();
        getDriver().findElement(
                        By.xpath("//option[contains(text(),'Default Icon')]"))
                .click();
        getDriver().findElement(buttonSave).click();

        String iconSecond = getDriver().findElement(By.xpath("//h1/img")).
                getAttribute("title");

        Assert.assertNotEquals(iconSecond, iconFirst);

        deleteFolder();
    }
}