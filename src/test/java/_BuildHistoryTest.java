import model.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static runner.ProjectUtils.ProjectType.Freestyle;

public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";

    private String buildName;

    @Test
    public void testBuildIsOnProjectPage() {

        WebElement build = new HomePage(getDriver())
                .clickNewItem()
                .setProjectType(Freestyle)
                .setProjectName(PROJECT_NAME)
                .createAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButton()
                .getBuild();

        buildName = build.getText().substring("#".length());

        Assert.assertTrue(build.isDisplayed());
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        boolean result = new HomePage(getDriver())
                .clickBuildHistory()
                .checkProjectOnBoard(PROJECT_NAME);

        Assert.assertTrue(result);
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

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {

        String changesHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildName)
                .clickChangesAndGoToBuildPage()
                .getChangesPageHeader();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test (dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsole() {

        String consoleHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildName)
                .clickConsoleAndGoToBuildPage()
                .getConsolePageHeader();

        Assert.assertEquals(consoleHeader, "Console Output");
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