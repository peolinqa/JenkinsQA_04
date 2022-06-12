import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.assertTrue;

@Ignore
public class RenamePipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = RandomStringUtils.randomAlphanumeric(3, 5);
    private static final String NEW_PIPELINE_NAME = RandomStringUtils.randomAlphanumeric(6, 10);

    private static final By DASHBOARD_ITEM = By.xpath("//tr[@id='job_" + PIPELINE_NAME + "']");

    private void getDashboard() {
        getDriver().findElement(
                By.className("breadcrumbBarAnchor")).click();
    }

    private void createPipeline(String pipelineName) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[contains(text(),'Pipeline')]")).click();
        clickButtonByName("OK");
        clickButtonByName("Save");
    }

    private WebElement getPipelineOnTheDashboard(String pipelineName) {
        return getDriver().findElement(
                By.xpath("//tr[@id='job_" + pipelineName + "']//a[contains(@class,'jenkins-table__link')]"));
    }

    private boolean isPipelinePresentOnTheDashboard(By xpath) {
        try {
            getDriver().findElement(xpath);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private boolean isPipelineNameOnThePipelinePage(String pipelineName) {
        return getDriver().findElement(
                By.xpath("//h1[text()='Pipeline " + pipelineName + "']")).isDisplayed();
    }

    private void clickMenuItemByName(String menuItemName) {
        getDriver().findElement(By.xpath("//span[text()='" + menuItemName + "']/../../a")).click();
    }

    private void clickButtonByName(String buttonName) {
        getDriver().findElement(By.xpath("//button[text()='" + buttonName + "']")).click();
    }

    private void openSubMenu(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        WebElement menuSelector = getDriver().findElement(By.id("menuSelector"));
        actions.moveToElement(menuSelector);
        actions.click().build().perform();
    }

    @Ignore
    @Test(priority = 1)
    public void testRenamePipelineWithValidName() {
        getDashboard();

        if (!isPipelinePresentOnTheDashboard(DASHBOARD_ITEM)) {
            createPipeline(PIPELINE_NAME);
        }

        assertTrue(isPipelineNameOnThePipelinePage(PIPELINE_NAME));

        getDashboard();

        assertTrue(getPipelineOnTheDashboard(PIPELINE_NAME).isDisplayed());

        openSubMenu(getPipelineOnTheDashboard(PIPELINE_NAME));
        clickMenuItemByName("Rename");

        getDriver().findElement(
                By.xpath("//div[@class='setting-main']/input[@name='newName']")).clear();

        getDriver().findElement(
                By.xpath("//div[@class='setting-main']/input[@name='newName']"))
                .sendKeys(NEW_PIPELINE_NAME);

        clickButtonByName("Rename");

        assertTrue(isPipelineNameOnThePipelinePage(NEW_PIPELINE_NAME));

        clickMenuItemByName("Back to Dashboard");

        assertTrue(getPipelineOnTheDashboard(NEW_PIPELINE_NAME).isDisplayed());
    }

    @Ignore
    @Test(priority = 2)
    public void testRenamePipelineWithTheSameName() {
        getDashboard();

        if (!isPipelinePresentOnTheDashboard(DASHBOARD_ITEM)) {
            createPipeline(PIPELINE_NAME);
        }

        clickMenuItemByName("Rename");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='warning']")).getText(),
                "The new name is the same as the current name.");

        clickButtonByName("Rename");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Error");
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                "The new name is the same as the current name.");
    }

    @Ignore
    @Test(priority = 3)
    public void testRenamePipelineWithInvalidName() {
        String[] invalidCharacters = {"!", "@", "#", "$", "%", "^", "*", ":", ";", "\\", "|", "?"};

        getDashboard();

        if (!isPipelinePresentOnTheDashboard(DASHBOARD_ITEM)) {
            createPipeline(PIPELINE_NAME);
        }
        openSubMenu(getPipelineOnTheDashboard(PIPELINE_NAME));
        clickMenuItemByName("Rename");

        for (String str : invalidCharacters) {
            getDriver().findElement(By.xpath("//div[@class='setting-main']/input[@name='newName']")).sendKeys(str);
            clickButtonByName("Rename");

            Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                    "Error");
            Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                    String.format("‘%s’ is an unsafe character", str));

            getDriver().navigate().back();
        }

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//a[contains(@href, '/job') and contains(@class, 'breadcrumbBarAnchor')]")).getText(),
                PIPELINE_NAME);
    }
}
