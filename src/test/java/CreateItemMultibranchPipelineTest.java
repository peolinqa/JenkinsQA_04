import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.UUID;

public class CreateItemMultibranchPipelineTest extends BaseTest {

    private static final String PROJECT_NAME = UUID.randomUUID().toString();

    private void createNewJob() {
        WebElement newItemButton = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='task-link-text' and contains (text(), 'New Item')]")));
        newItemButton.click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and contains(text(), 'Multibranch Pipeline')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen8-button")).click();
    }

    private void goToDashboard() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(text(), 'Dashboard')]")).click();
    }

    @Test
    public void testCreateNewItemMultibranch() {
        createNewJob();
        goToDashboard();
        String itemListLocator = String.format("//tr[@id='job_%s']", PROJECT_NAME);
        int itemList = getDriver().findElements(By.xpath(itemListLocator)).size();

        Assert.assertEquals(itemList, 1);
    }
}