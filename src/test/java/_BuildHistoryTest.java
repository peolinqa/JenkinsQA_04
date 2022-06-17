import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _BuildHistoryTest extends BaseTest {

    private final String PROJECT_NAME = "BuildHistoryPageProject";
    private String buildName;

    private final By HEADER_TEXT_XPATH = By.xpath("//span[@class='jenkins-icon-adjacent']");

    private void createNewProject() {

        if (getDriver().findElements(By.partialLinkText(PROJECT_NAME)).size() > 0) {
            deleteProject();
        }

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

        WebElement buildName = getWait20().until(ExpectedConditions.presenceOfElementLocated(
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

        createNewProject();

        buildName = buildAndReturnBuildName().substring(1);

        homePage();
        buildHistoryPage();
        clickProjectSpanMenu();
        getDriver().findElement(By.partialLinkText("Changes")).click();

        String expectedChangesURL = String.format("job/%s/%s/changes", PROJECT_NAME, buildName);
        String actualChangesURL = getDriver().getCurrentUrl().substring(22);

        String expectedChangesHeader = "Changes";
        String actualChangesHeader = getDriver().findElement(HEADER_TEXT_XPATH).getText();

        Assert.assertEquals(actualChangesURL, expectedChangesURL);
        Assert.assertEquals(actualChangesHeader, expectedChangesHeader);
    }

    @Test (dependsOnMethods = {"testBuildHistoryChanges"})
    public void testBuildHistoryConsole() {

        homePage();
        buildHistoryPage();
        clickProjectSpanMenu();
        getDriver().findElement(By.partialLinkText("Console Output")).click();

        String expectedConsoleURL = String.format("job/%s/%s/console", PROJECT_NAME, buildName);
        String actualConsoleURL = getDriver().getCurrentUrl().substring(22);

        String expectedConsoleHeader = "Console Output";
        String actualConsoleHeader = getDriver().findElement(HEADER_TEXT_XPATH).getText();

        Assert.assertEquals(actualConsoleURL, expectedConsoleURL);
        Assert.assertEquals(actualConsoleHeader, expectedConsoleHeader);

        deleteProject();
    }
}
