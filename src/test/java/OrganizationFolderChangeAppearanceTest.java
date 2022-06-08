import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class OrganizationFolderChangeAppearanceTest extends BaseTest {

    private static final By MENU_DASHBOARD = By.xpath("//li[@class='item']");

    private void deleteFolder() {
        final String NAME_MY_ITEM = "Organization Folder";
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
                By.xpath("//span[contains(text(),'Delete')]")).click();
        getDriver().findElement(By.xpath("//span[@name='Submit']")).click();
    }

    @Test
    public void testOrganizationFolderChangeAppearance() {
        final String FOLDER_NAME = "Organization Folder TC_039.002";
        final String APPEARANCE_FIRST = "Metadata Folder Icon";
        By menuAppearance = By.xpath(
                "//div[@class='tabBar config-section-activators']//div[text()='Appearance']");
        By buttonSave = By.id("yui-gen17-button");

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(
                        By.xpath("//li[@class='jenkins_branch_OrganizationFolder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(menuAppearance).click();

        String actualResultAppearanceFirst = getDriver()
                .findElement(
                        By.xpath("//div[@class='jenkins-form-item has-help" +
                                " config_appearance active']//option[@value='1']"))
                .getText();

        getDriver().findElement(buttonSave).click();

        String iconFirst = getDriver().findElement(By.xpath("//h1/img")).
                getAttribute("title");

        getDriver().findElement(MENU_DASHBOARD).click();
        getDriver().findElement(
                        By.xpath("//a[@href='job/Organization%20Folder%20TC_039.002/']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./configure']")).click();
        getDriver().findElement(menuAppearance).click();
        getDriver().findElement(
                        By.xpath("//div[@class='jenkins-form-item has-help " +
                                "config_appearance active']//option[@value='0']"))
                .click();
        getDriver().findElement(buttonSave).click();

        String iconSecond = getDriver().findElement(By.xpath("//h1/img")).
                getAttribute("title");

        Assert.assertEquals(actualResultAppearanceFirst, APPEARANCE_FIRST);
        Assert.assertNotEquals(iconSecond, iconFirst);

        deleteFolder();
    }
}