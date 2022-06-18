import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class RusMJenkinsTest extends BaseTest {

    private static final String NEW_ITEM_OR_BACK_TO_DASHBOARD_BUTTONS = "//div[@id='tasks']/div[1]/span/a";
    private static final String DESCRIPTION_OF_PARAMETER = "//div[contains(text(),'Description of parameter')]";

    private boolean notPresent() {
        try {
            getDriver().findElement(By.xpath("//tr[@id='job_folder 1']/td[3]/a")).isDisplayed();
            return false;
        } catch (NoSuchElementException no) {
            return true;
        }
    }

    private void clickNewItemOrBackToDashboardButtons() {
        getDriver().findElement(By.xpath(NEW_ITEM_OR_BACK_TO_DASHBOARD_BUTTONS)).click();
    }

    private void inputNewItemName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);
    }

    private void chooseFreestyleProject() {
        getDriver().findElement(By
                .xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label")).click();
    }

    private void clickOkButton() {
        getDriver().findElement(By.id("ok-button"))
                .click();
    }

    private void clickSaveButton() {
        getDriver().findElement(By
                .xpath("//div[2]/div[2]/span[1]/span/button")).click();
    }

    private void clickTheButton() {
        getDriver().findElement(By.xpath("//button")).click();
    }

    private void createPipeline() {
        clickNewItemOrBackToDashboardButtons();
        inputNewItemName("First Pipeline Project");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[2]"))
                .click();
        clickOkButton();
    }

    private void clickAddParameterOrBuildButton() {
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    private void clickOnParameters() {
        getDriver().findElement(By.xpath("//span[contains(text(),'Parameters')]")).click();
    }

    @Test
    public void testFreestyleNewItemValidName() {
        clickNewItemOrBackToDashboardButtons();
        inputNewItemName("item 123");
        chooseFreestyleProject();
        clickOkButton();
        clickSaveButton();
        clickNewItemOrBackToDashboardButtons();

        assertTrue(getDriver().findElement(By.xpath("//tr[@id='job_item 123']/td[3]/a"))
                .isDisplayed());
    }

    @Test
    public void testFreestyleRename() {
        clickNewItemOrBackToDashboardButtons();
        inputNewItemName("item 234");
        chooseFreestyleProject();
        clickOkButton();
        clickSaveButton();
        clickNewItemOrBackToDashboardButtons();

        Actions dropdown = new Actions(getDriver());
        dropdown.moveToElement(getDriver().findElement(By
                .xpath("//tr[@id='job_item 234']/td[3]/a"))).perform();

        getDriver().findElement(By.xpath("//div[@id='menuSelector']")).click();

        getDriver().findElement(By
                .xpath("//a[contains(@href,'/job/item%20234/confirm-rename')]")).click();

        WebElement renameProjectName = getDriver().findElement(By
                .xpath("//input[@type='text']"));
        renameProjectName.clear();
        renameProjectName.sendKeys("project987");

        clickTheButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))
                .getText(), "Project project987");

        clickNewItemOrBackToDashboardButtons();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//tr[@id='job_project987']/td[3]/a")).getText(), "project987");
    }

    @Test
    public void testFolderDelete() {
        clickNewItemOrBackToDashboardButtons();
        inputNewItemName("Folder 1");
        getDriver().findElement(By
                .xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label")).click();
        clickOkButton();
        clickSaveButton();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[5]/span/a/span[2]")).click();
        clickTheButton();

        assertTrue(notPresent());
    }
}