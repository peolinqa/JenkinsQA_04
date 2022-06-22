import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";

    private static final By HEADER_TEXT_XPATH = By.xpath("//span[@class='jenkins-icon-adjacent']");

    private String buildName;

    private void createNewProject() {

        if (getDriver().findElements(By.partialLinkText(PROJECT_NAME)).size() > 0) {
            deleteProject();
        }

        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        WebElement projectName = getDriver().findElement(By.name("name"));
        projectName.sendKeys(PROJECT_NAME);

        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[1]")).click();

        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();
    }

    private String buildAndReturnBuildName() {

        ProjectUtils.Dashboard.Project.BuildNow.click(getDriver());

        WebElement buildName = getWait20().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[@class='build-row single-line overflow-checked']/td/div/a"))
        );

        return buildName.getText().substring(1);
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

    public void createAndBuildFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("NewFreestyleProject");
        getDriver().findElement(By.xpath("//li[contains(@class,'hudson_model_FreeStyleProject')]")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();
        getDriver().findElement(By.xpath("//a[@title='Build Now']")).click();
        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();
        boolean success = false;
        int maxTries = 0;
        while (!success) {
            try {

                getDriver().navigate().refresh();
                getDriver().findElement(By.xpath("//*[local-name() = 'svg' and @tooltip='Success']"));
                success = true;
            } catch (Exception e) {
                if (++maxTries > 3) {
                    throw e;
                }
            }
        }
    }

    @Test
    public void testBuildHistoryChanges() {
        createNewProject();
        buildName = buildAndReturnBuildName();

        homePage();
        buildHistoryPage();
        clickProjectSpanMenu();
        getDriver().findElement(By.partialLinkText("Changes")).click();

        final String expectedChangesURL = String.format("job/%s/%s/changes", PROJECT_NAME, buildName);
        String actualChangesURL = getDriver().getCurrentUrl().substring(22);

        final String expectedChangesHeader = "Changes";
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

    @Test
    public void testVerifyChangeOnBuildStatusPage() {
        createAndBuildFreestyleProject();
        getDriver().findElement(By.xpath("//a[@href='job/NewFreestyleProject/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//a[@title='Edit Build Information']")).click();
        getDriver().findElement(By.xpath("//input[@name='displayName']")).sendKeys("New build 123");
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Build 123 description test");
        getDriver().findElement(By.xpath("//button[@type='submit']")).submit();

        String buildName = getDriver().findElement(By.xpath("//span[@class='jenkins-icon-adjacent']")).getText();
        String buildDescription = getDriver().findElement(By.id("description")).getText();

        Assert.assertTrue(buildName.contains("Build New build 123"));
        Assert.assertTrue(buildDescription.contains("Build 123 description test"));
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnBuildStatusPage"})
    public void testVerifyChangeOnProjectStatusPage() {
        getDriver().findElement(By.xpath("//a[@href='job/NewFreestyleProject/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Project']")).click();

        String buildName = getDriver().findElement(By.xpath("//a[text()='New build 123']")).getText();
        String buildDescription = getDriver().findElement(By.xpath("//div[@class='pane desc indent-multiline']")).getText();

        Assert.assertTrue(buildName.contains("New build 123"));
        Assert.assertTrue(buildDescription.contains("Build 123 description test"));
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnProjectStatusPage"})
    public void testVerifyChangeOnBuildHistoryPage() {
        getDriver().findElement(By.xpath("//a[@href='job/NewFreestyleProject/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Project']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Dashboard']")).click();
        getDriver().findElement(By.xpath("//span[text()='Build History']")).click();

        String buildName = getDriver().findElement(By.xpath("//a[@href='/job/NewFreestyleProject/1/']")).getText();

        Assert.assertTrue(buildName.contains("New build 123"));
    }

}
