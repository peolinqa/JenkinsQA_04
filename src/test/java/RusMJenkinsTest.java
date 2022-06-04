import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.*;

public class RusMJenkinsTest extends BaseTest {

    public boolean notPresent() {
        try {
            getDriver().findElement(By.xpath("//tr[@id='job_folder 1']/td[3]/a")).isDisplayed();
            return false;
        } catch (NoSuchElementException no) {
            return true;
        }
    }

    public void clickNewItemButton() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();
    }

    public void clickOkButton() {
        getDriver().findElement(By.xpath("//button[@id='ok-button']"))
                .click();
    }

    public void clickSaveButton() {
        getDriver().findElement(By
                .xpath("//div[2]/div[2]/span[1]/span/button")).click();
    }

    public void clickTheButton() {
        getDriver().findElement(By.xpath("//button")).click();
    }

    public void clickBackToDashboard() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a")).click();
    }

    public void setInputRenameProjectName() {
        WebElement renameProjectName = getDriver().findElement(By
                .xpath("//div/div[2]/input"));
        renameProjectName.clear();
        renameProjectName.sendKeys("project987");
    }

    static String inputNewItemName = "//input[@id='name']";

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
        dropdown.moveToElement(getDriver().findElement(By
                        .xpath("//ul/li[6]/a/span")))
                            .click().perform();

        setInputRenameProjectName();
        clickTheButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))
                .getText(), "Project project987");

        clickBackToDashboard();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr[@id='job_project987']/td[3]/a"))
                .getText(), "project987");
    }

    @Test
    public void testFolderDelete() {
        clickNewItemButton();
        getDriver().findElement(By.xpath((inputNewItemName))).sendKeys("Folder 1");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]/label")).click();
        clickOkButton();
        clickSaveButton();
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[5]/span/a/span[2]")).click();
        clickTheButton();

        assertTrue(notPresent());
    }
}
