import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";

    private static final By HEADER_TEXT_XPATH = By.xpath("//span[@class='jenkins-icon-adjacent']");

    private String buildName;

    private void createNewProjectAndBuild() {

        if (getDriver().findElements(By.partialLinkText(PROJECT_NAME)).size() == 0) {
            ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.Freestyle, PROJECT_NAME);
            ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        }

        getDriver().findElement(By.linkText(PROJECT_NAME)).click();

        if (getDriver().findElement(By.id("no-builds")).isDisplayed()) {
            ProjectUtils.Dashboard.Project.BuildNow.click(getDriver());
        }

        WebElement build = getWait20().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[@class='build-row single-line overflow-checked']/td/div/a"))
        );

        buildName = build.getText().substring(1);
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
        createNewProjectAndBuild();

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.Dashboard.Main.BuildHistory.click(getDriver());
        getDriver().findElement(By.xpath(String.format("//a[@href='/job/%s/%s/']", PROJECT_NAME, buildName))).click();
        ProjectUtils.Dashboard.Build.Changes.click(getDriver());

        String actualChangesURL = getDriver().getCurrentUrl().substring(22);
        String actualChangesHeader = getDriver().findElement(HEADER_TEXT_XPATH).getText();

        Assert.assertEquals(actualChangesURL, String.format("job/%s/%s/changes", PROJECT_NAME, buildName));
        Assert.assertEquals(actualChangesHeader, "Changes");
    }

    @Test (dependsOnMethods = {"testBuildHistoryChanges"})
    public void testBuildHistoryConsole() {

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.Dashboard.Main.BuildHistory.click(getDriver());
        getDriver().findElement(By.xpath(String.format("//a[@href='/job/%s/%s/']", PROJECT_NAME, buildName))).click();
        ProjectUtils.Dashboard.Build.ConsoleOutput.click(getDriver());

        String actualConsoleURL = getDriver().getCurrentUrl().substring(22);
        String actualConsoleHeader = getDriver().findElement(HEADER_TEXT_XPATH).getText();

        Assert.assertEquals(actualConsoleURL, String.format("job/%s/%s/console", PROJECT_NAME, buildName));
        Assert.assertEquals(actualConsoleHeader, "Console Output");
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
