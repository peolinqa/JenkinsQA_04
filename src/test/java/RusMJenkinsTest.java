import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private boolean notPresent() {
        try {
            getDriver().findElement(By.xpath("//tr[@id='job_folder 1']/td[3]/a")).isDisplayed();
            return false;
        } catch (NoSuchElementException no) {
            return true;
        }
    }

    private void clickNewItemButton() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();
    }

    private void clickOkButton() {
        getDriver().findElement(By.xpath("//button[@id='ok-button']"))
                .click();
    }

    private void clickSaveButton() {
        getDriver().findElement(By
                .xpath("//div[2]/div[2]/span[1]/span/button")).click();
    }

    private void clickTheButton() {
        getDriver().findElement(By.xpath("//button")).click();
    }

    private void clickBackToDashboard() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();
    }

    static String inputNewItemName = "//input[@id='name']";

    private void createPipeline() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("First Pipeline Project");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[2]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testFreestyleNewItemValidName() {
        clickNewItemButton();
        getDriver().findElement(By.xpath((inputNewItemName))).sendKeys("item 123");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li"))
                .click();
        clickOkButton();
        clickSaveButton();
        clickBackToDashboard();

        assertTrue(getDriver().findElement(By.xpath("//tr[@id='job_item 123']/td[3]/a"))
                .isDisplayed());
    }

    @Test
    public void testFreestyleRename() {
        Actions dropdown = new Actions(getDriver());
        dropdown.moveToElement(getDriver().findElement(By
                .xpath("//tr[@id='job_item 123']/td[3]/a"))).perform();

        getDriver().findElement(By.xpath("//div[@id='menuSelector']")).click();

        getDriver().findElement(By
                .xpath("//a[contains(@href,'/job/item%20123/confirm-rename')]")).click();

        WebElement renameProjectName = getDriver().findElement(By
                .xpath("//input[@type='text']"));
        renameProjectName.clear();
        renameProjectName.sendKeys("project987");

        clickTheButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))
                .getText(), "Project project987");

        clickBackToDashboard();

        Assert.assertEquals(getDriver().findElement(By
                        .xpath("//tr[@id='job_project987']/td[3]/a"))
                .getText(), "project987");
    }

    @Test
    public void testFolderDelete() {
        clickNewItemButton();
        getDriver().findElement(By.xpath((inputNewItemName))).sendKeys("Folder 1");
        getDriver().findElement(By
                .xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label")).click();
        clickOkButton();
        clickSaveButton();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[5]/span/a/span[2]")).click();
        clickTheButton();

        assertTrue(notPresent());
    }

    @Test
    public void testPipelineBuild() {
        createPipeline();
        getDriver().findElement(By
                .xpath("//div[@id='main-panel']//div[11]/div[2]/div/div/div/div[1]/div/label")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
        getDriver().findElement(By.xpath("//li[@id='yui-gen9']/a")).click();
        getDriver().findElement(By.name("parameter.name"))
                .sendKeys("Name of the Choice Parameter");
        getDriver().findElement(By.name("parameter.choices"))
                .sendKeys("First Choice" + '\n' + "Second Choice" + '\n' + "Third Choice");
        getDriver().findElement(By.name("parameter.description"))
                .sendKeys("Description of parameter");
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a")).click();

        SoftAssert asserts = new SoftAssert();

        asserts.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))
                .getText(), "Pipeline First Pipeline Project");

        asserts.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p"))
                .getText(), "This build requires parameters:");

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[1]"))
                .getText(), "Name of the Choice Parameter");

        List<WebElement> option = getDriver().findElements(By.xpath("//select/option"));
        List<String> actualRes = new ArrayList<>();

        for (WebElement dropDown : option) {
            actualRes.add(dropDown.getText());
        }

        List<String> expectedRes = new ArrayList<>();
        expectedRes.add("First Choice");
        expectedRes.add("Second Choice");
        expectedRes.add("Third Choice");

        asserts.assertEquals(actualRes, expectedRes);

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[4]/div[2]"))
                .getText(), "Description of parameter");

        getDriver().findElement(By.id("yui-gen1-button")).click();

        if ("expand".equals((getDriver().findElement(By.cssSelector(".collapse"))
                .getAttribute("title")))) {getDriver().findElement(By
                .xpath("//div[@id=\"buildHistory\"]/div[1]/div/a")).click();
        }

        WebElement buildOne = getWait5()
                .until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("td.build-row-cell a.display-name")));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", buildOne);

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[7]/span/a")).click();

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//div[@id=\"main-panel\"]/div/div/div[2]/div/div[1]"))
                .getText(), "Name of the Choice Parameter");

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//div[@id='main-panel']/div/div/div[2]/div/div[2]/input"))
                .getAttribute("value"), "First Choice");

        asserts.assertEquals(getDriver().findElement(By
                        .xpath("//div[@id=\"main-panel\"]/div/div/div[2]/div/div[4]/div[2]"))
                .getText(), "Description of parameter");

        asserts.assertAll();
    }
}
