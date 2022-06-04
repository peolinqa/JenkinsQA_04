import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class BuildHistoryPageTest extends BaseTest {

    private final String PROJECT_NAME = "BuildHistoryPageProject";
    private String buildName;

    private void createNewProject() {

        getDriver().findElement(By.linkText("New Item")).click();

        WebElement projectName = getDriver().findElement(By.name("name"));
        projectName.sendKeys(PROJECT_NAME);

        getDriver().findElement(
                By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[1]")
        ).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")
        ).click();
    }

    private String buildAndReturnBuildName() {

        getDriver().findElement(By.partialLinkText("Build Now")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        WebElement buildName = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[@class='build-row single-line overflow-checked']/td/div/a"))
        );

        return buildName.getText();
    }

    private void homePage() {

        getDriver().findElement(By.xpath("//a[@href='/']")).click();
    }

    private void buildHistoryPage() {

        getDriver().findElement(By.partialLinkText("Build History")).click();
    }

    private void clickProjectSpanMenu() {

        getDriver().findElement(
                By.xpath(String.format("//a[@href='/job/%s/%s/']", PROJECT_NAME, buildName))
        ).click();
    }

    private void deleteProject() {

        homePage();
        getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']", PROJECT_NAME))).click();
        getDriver().findElement(By.partialLinkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testBuildHistoryChanges() {

        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        createNewProject();

        buildName = buildAndReturnBuildName().substring(1);

        homePage();
        buildHistoryPage();
        clickProjectSpanMenu();
        getDriver().findElement(By.partialLinkText("Changes")).click();

        String expectedResult = String.format("http://localhost:8080/job/%s/%s/changes", PROJECT_NAME, buildName);
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testBuildHistoryConsole() {

        homePage();
        buildHistoryPage();
        clickProjectSpanMenu();
        getDriver().findElement(By.partialLinkText("Console Output")).click();

        String expectedResult = String.format("http://localhost:8080/job/%s/%s/console", PROJECT_NAME, buildName);
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }
}