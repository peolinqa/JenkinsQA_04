import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class GShCheckButtonSavePageStatusTest extends BaseTest {
    private final String FOLDER_NAME = "genashepel";
    private final String NEW_ITEM = "//span[@class='task-link-wrapper ']/a[@href='/view/all/newJob']";

    private void deleteFolderName(String name) {
        if (name != null && !name.equals("")) {
            getDriver().findElement(By.xpath("//li[@class='item']/a")).click();
            getDriver().findElement(By.xpath("//td/a[@href='job/".concat(FOLDER_NAME).concat("/']"))).click();
            getDriver()
                    .findElement(By.xpath("//span[@class='task-link-wrapper ']/a[@title='Delete Folder']"))
                    .click();
            getDriver().findElement(By.id("yui-gen1-button")).click();
        }
    }

    private boolean checkNameforNewPipeline(String name) {

        getDriver().findElement(By.xpath("//li[@class='item']/a")).click();

        List<WebElement> getElementsDashboard = getDriver()
                .findElements(By.xpath("//table[@id='projectstatus']/tbody"));

        if (getElementsDashboard != null && getElementsDashboard.size() != 0) {
            for (WebElement element : getElementsDashboard) {

                if (element.getText().contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testTC_005a_013_CheckButtonSave() {

        String expectedResult = "genashepel_folder";

        if (checkNameforNewPipeline(FOLDER_NAME)) {
            deleteFolderName(FOLDER_NAME);
        }

        getDriver().findElement(By.xpath(NEW_ITEM)).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver()
                .findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver()
                .findElement(By.xpath("//div[@class='setting-main'] /input"))
                .sendKeys("genashepel_folder");
        getDriver().findElement(By.id("yui-gen6-button")).click();

        String actualResult = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTC_005a_014_CheckButtonApply(){

        String expectedResult = "notif-alert-show";
        if (checkNameforNewPipeline(FOLDER_NAME)) {
            deleteFolderName(FOLDER_NAME);
        }

        getDriver().findElement(By.xpath(NEW_ITEM)).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver()
                .findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen4-button")).click();

        String allert = getDriver().findElement(By.id("notification-bar")).getAttribute("class");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notif-alert-clear")));

        Assert.assertTrue(allert.contains(expectedResult));
    }
}
