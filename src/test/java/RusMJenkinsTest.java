import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.*;

public class RusMJenkinsTest extends BaseTest {

    public void clickNewItemButton() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();
    }

    public void clickOkButton() {
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    public void clickSaveButton() {
        getDriver().findElement(By.xpath("//div[2]/div[2]/span[1]/span/button")).click();
    }

    public void clickTheButton() {
        getDriver().findElement(By.xpath("//button")).click();
    }

    public void clickBackToDashboard() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();
    }

    public static String inputNewItemName = "//input[@id='name']";
    public static String inputRenameProjectName = "//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input";
    public static String projectName = "//tr/td[3]/a";

    @Test
    public void testFreestyleNewItemValidName() {
        clickNewItemButton();
        getDriver().findElement(By.xpath(inputNewItemName)).sendKeys("item 123");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li"))
                .click();
        clickOkButton();
        clickSaveButton();
        clickBackToDashboard();

        assertTrue(getDriver().findElement(By.xpath(projectName))
                .isDisplayed());
    }

    @Test
    public void testFreestyleRename() {
        Actions dropdown = new Actions(getDriver());
        dropdown.moveToElement(getDriver().findElement(By
                .xpath(projectName))).perform();

        getDriver().findElement(By.xpath("//div[@id='menuSelector']")).click();
        dropdown.moveToElement(getDriver().findElement(By
                        .xpath("//ul/li[6]/a/span")))
                            .click().perform();

        getDriver().findElement(By.xpath(inputRenameProjectName)).clear();
        getDriver().findElement(By.xpath(inputRenameProjectName))
                .sendKeys("project987");
        clickTheButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id=\"main-panel\"]/h1"))
                .getText(), "Project project987");

        clickBackToDashboard();

        Assert.assertEquals(getDriver().findElement(By.xpath(projectName))
                .getText(), "project987");
    }

    @Test
    public void testFolderDelete() {
        clickNewItemButton();
        getDriver().findElement(By.xpath(inputNewItemName)).sendKeys("Folder 1");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label")).click();
        clickOkButton();
        clickSaveButton();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[5]/span/a/span[2]")).click();
        clickTheButton();

        assertEquals(getDriver().findElements(By.xpath(projectName)).isEmpty(), true);
    }
}
