import model.HomePage;
import model.ProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";

    private String buildNumber;

    @Test
    public void testBuildIsOnProjectPage() {

        ProjectPage projectPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeFreestyle()
                .setProjectName(PROJECT_NAME)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButton();

        buildNumber = projectPage.getBuildNumber();

        Assert.assertTrue(projectPage.buildNumberIsDisplayed());
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        boolean result = new HomePage(getDriver())
                .clickBuildHistory()
                .checkProjectIsOnBoard(PROJECT_NAME);

        Assert.assertTrue(result);
    }

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {

        String changesHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickChangesAndGoToChangesPage()
                .getPageHeader();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test (dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsole() {

        String consoleHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickConsoleAndGoToConsolePage()
                .getPageHeader();

        Assert.assertEquals(consoleHeader, "Console Output");
    }

    @Test
    public void testVerifyChangeOnBuildStatusPage() {

        new HomePage(getDriver())
                .clickNewItem()
                .setProjectName("NewFreestyleProject")
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButton()
                .waitForBuildToComplete()
                .clickDashboardButton();

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