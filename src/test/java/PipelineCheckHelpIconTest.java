import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class PipelineCheckHelpIconTest extends BaseTest {

    final String HELP_ICON = "//label[text() = 'Discard old builds']//following-sibling::a";

    private void isEmptyTable() {
        List<WebElement> tableOfProjects = getDriver().findElements((By
                .xpath("//tr[@class=' job-status-nobuilt']")));
        if (tableOfProjects.size() == 0) {
            createNewItem();
            goToHomePage();
        }
    }

    private void createNewItem() {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys("First_Project");
        getDriver().findElement(By.xpath("//li[contains(@class, 'FreeStyleProject')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void goToHomePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private void goToDropDownConfigure() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.className("jenkins-table__link"))).perform();
        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(By.xpath("//span[text() = 'Configure']")).click();
    }

    @Test
    public void testCheckHelpIcon() {
        isEmptyTable();
        goToDropDownConfigure();

        Assert.assertEquals(getDriver().findElement(By.xpath(HELP_ICON)).getText(), "?");
        Assert.assertEquals(getDriver().findElement(By.xpath(HELP_ICON)).getAttribute("title"),
                "Help for feature: Discard old builds");
    }
}
