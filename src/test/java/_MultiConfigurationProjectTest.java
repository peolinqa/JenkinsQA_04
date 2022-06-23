import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class _MultiConfigurationProjectTest extends BaseTest {

    private final String NAME_FOLDER = "TestMultiConfigurationProject";
    private final String PROJECT_NAME = "Mcproject";
    private final By PROJECT_ON_DAHBOARD = By.xpath("//table[@id='projectstatus']//a[normalize-space(.)='" + PROJECT_NAME + "']");

    private void createMultiConfigFolder(String name) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    private void deleteFolder(String name) {

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + name + "/']"))).click().build().perform();
        getDriver().findElement(By.xpath("//span[text()='Delete Multi-configuration project']")).click();
        getDriver().switchTo().alert().accept();
    }

    private void returnToMainPage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private boolean isElementPresent(String name) {
        boolean isPresent = false;

        List<WebElement> projectsOnDashboard = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']//tbody//td[3]"));
        for (WebElement jobs : projectsOnDashboard) {
            if (jobs.getText().contains(name)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    private void runBuildNow(String name) {
        getDriver().findElement(By.id("jenkins-home-link")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'" + name + "')]")).click();
        getDriver().findElement(By.linkText("Build Now")).click();
    }

    private void selectTopBuildInHistory() {
        getDriver().findElement(By.className("build-status-link")).click();
    }

    private boolean isSuccesedBuildIsDipslayed() {
        return getDriver().findElement(
                By.xpath("//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")).isDisplayed();
    }

    private void openProjectJob(String name) {
        WebElement nameOnDashboard = getDriver().findElement(By.xpath("//a[@href='job/" + name + "/']"));
        nameOnDashboard.click();
    }

    private String getFolderNameOnDashboard(String name) {
        WebElement nameOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + NAME_FOLDER + "']//td[3]"));
        return nameOnDashboard.getText();
    }

    private List<String> getListProjects() {

        return getDriver().findElements(
                        By.xpath("//tbody/tr/td/a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Test
    public void testCreateMultiConfigFolder() {

        createMultiConfigFolder(NAME_FOLDER);
        returnToMainPage();

        Assert.assertEquals(getFolderNameOnDashboard(NAME_FOLDER), NAME_FOLDER);
    }

    @Test(dependsOnMethods = {"testCreateMultiConfigFolder"})
    public void testBuildNow() {

        runBuildNow(NAME_FOLDER);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("build-status-link")));
        selectTopBuildInHistory();

        Assert.assertTrue(isSuccesedBuildIsDipslayed());
    }

    @Test
    public void testBuildNowInDisabledProject() {
        String nameTestedFolder = "disabledFolder";
        boolean isBuildNowDisplayed = false;

        createMultiConfigFolder(nameTestedFolder);
        returnToMainPage();
        openProjectJob(nameTestedFolder);
        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> jobMenu = getDriver().findElements(By.xpath("//div[@id='tasks']//span[2]"));
        for (WebElement menu : jobMenu) {
            if (menu.getText().contains("Build Now")) {
                isBuildNowDisplayed = true;
            }
        }

        Assert.assertFalse(isBuildNowDisplayed);
    }

    @Test
    public void testAddDescription() {
        createMultiConfigFolder(PROJECT_NAME);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div/textarea[@name='description']")).sendKeys("test");
        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), "test");

        returnToMainPage();
    }

    @Test(dependsOnMethods = {"testAddDescription"})
    public void testRenameMCProject() {
        String expectedResult = "Project McprojectRename";

        openProjectJob(PROJECT_NAME);
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"))
                .sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "McprojectRename");
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        returnToMainPage();
        deleteFolder("McprojectRename");
    }

    public void testRenameMCProjectError() {
        String expectedResult = "Error\nThe new name is the same as the current name.";

        createMultiConfigFolder(PROJECT_NAME);
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.xpath("//div[@id='main-panel']/form/div/div/div[2]/input"));
        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        returnToMainPage();
        deleteFolder(PROJECT_NAME);
    }

    @Test(dependsOnMethods = {"testBuildNow"})
    public void testDeleteMultiConfigFolder() {
        String nameTestedFolder = "testToDelete";

        createMultiConfigFolder(nameTestedFolder);
        returnToMainPage();
        deleteFolder(nameTestedFolder);

        Assert.assertFalse(isElementPresent(nameTestedFolder));
    }

    @Test
    public void testCheckSubMenuConfigureAfterCreatingProject() {
        String expectedResultDiscardOldBuilds = "Help for feature: Discard old builds";
        String expectedResult1 = "This determines when, if ever, build records for this project should be discarded. " +
                "Build records include the console output, archived artifacts, and any other metadata related " +
                "to a particular build.\n" +
                "Keeping fewer builds means less disk space will be used in the Build Record Root Directory," +
                " which is specified on the Configure System screen.\n" +
                "Jenkins offers two options for determining when builds should be discarded:\n" +
                "Build age: discard builds when they reach a certain age; for example, seven days old.\n" +
                "Build count: discard the oldest build when a certain number of builds already exist.\n" +
                "These two options can be active at the same time, so you can keep builds for 14 days, " +
                "but only up to a limit of 50 builds, for example. If either limit is exceeded, then any " +
                "builds beyond that limit will be discarded.\n" +
                "You can also ensure that important builds are kept forever, regardless of the " +
                "setting here — click the Keep this build forever button on the build page.\n" +
                "The last stable and last successful build are also excluded from these rules.\n" +
                "In the Advanced section, the same options can be specified, but specifically for build " +
                "artifacts. If enabled, build artifacts will be discarded for any builds which exceed the " +
                "defined limits. The builds themselves will still be kept; only the associated artifacts, " +
                "if any, will be deleted.\n" +
                "For example, if a project builds some software and produces a large installer, which is " +
                "archived, you may wish to always keep the console log and information about which source " +
                "control commit was built, while for disk space reasons, you may want to keep only " +
                "the last three installers that were built.\n" +
                "This can make sense for projects where you can easily recreate the same artifacts later by building " +
                "the same source control commit again.\n" +
                "Note that Jenkins does not discard items immediately when this configuration is updated, " +
                "or as soon as any of the configured values are exceeded; these rules are evaluated " +
                "each time a build of this project completes.";
        String expectedResultMessage = "Saved";

        createMultiConfigFolder(PROJECT_NAME);
        returnToMainPage();

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(PROJECT_ON_DAHBOARD)).perform();
        WebElement subMenuButton = getDriver().findElement(By.id("menuSelector"));
        actions.moveToElement(subMenuButton).click().build().perform();
        actions.moveToElement(getDriver().findElement(
                By.xpath("//a[@class='yuimenuitemlabel']//span[text()='Configure']"))).click().build().perform();

        WebElement helpTitle = getDriver().findElement(By.xpath("//a[contains(@tooltip, 'Discard old builds')]"));

        Assert.assertEquals(helpTitle.getAttribute("title"), expectedResultDiscardOldBuilds);

        helpTitle.click();

        String textHelp = getDriver().findElement(By.xpath("//div[@class='help']/div")).getText();

        Assert.assertEquals(textHelp, expectedResult1);

        WebElement checkBoxDiscardOldBuilds = getDriver().findElement(
                By.xpath("//label[text()='Discard old builds']/preceding-sibling::input"));

        if (checkBoxDiscardOldBuilds.isSelected()) {
        } else {
            checkBoxDiscardOldBuilds.click();
        }

        checkBoxDiscardOldBuilds = getDriver().findElement(
                By.xpath("//label[text()='Discard old builds']/preceding-sibling::input"));

        Assert.assertTrue(checkBoxDiscardOldBuilds.isSelected());

        actions.moveToElement(getDriver().findElement(By.xpath("//span[@name='Apply']"))).click().build().perform();

        WebElement applyMessage = getDriver().findElement(By.xpath("//div[@id='notification-bar']/span"));

        Assert.assertEquals(applyMessage.getText(), expectedResultMessage);

        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-bar")));

        returnToMainPage();
        deleteFolder(PROJECT_NAME);
    }

    @Test
    public void testMultiConfigurationProjectRenameUsingInvalidName() {
        String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        createMultiConfigFolder(PROJECT_NAME);
        returnToMainPage();

        getDriver().findElement(PROJECT_ON_DAHBOARD).click();
        getDriver().findElement(By.linkText("Rename")).click();

        for (String unsafeChar : invalidName) {
            getDriver().findElement(By.name("newName")).clear();
            getDriver().findElement(By.name("newName")).sendKeys(unsafeChar);
            getDriver().findElement(By.id("yui-gen1-button")).click();
            String expectedResult = "‘" + unsafeChar + "’ is an unsafe character";
            if ("&" == unsafeChar) {
                expectedResult = "‘&amp;’ is an unsafe character";
            } else if (unsafeChar == "<") {
                expectedResult = "‘&lt;’ is an unsafe character";
            } else if (unsafeChar == ">") {
                expectedResult = "‘&gt;’ is an unsafe character";
            } else if (unsafeChar == "" || unsafeChar == " ") {
                expectedResult = "No name is specified";
            }

            String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

            Assert.assertEquals(actualResult, expectedResult);

            getDriver().navigate().back();
        }

        returnToMainPage();
        deleteFolder(PROJECT_NAME);
    }

    @Test
    public void testDeleteMultiConfigurationProject() {
        createMultiConfigFolder(PROJECT_NAME);
        returnToMainPage();
        Assert.assertTrue(getListProjects().contains(PROJECT_NAME));
        deleteFolder(PROJECT_NAME);
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();

        Assert.assertFalse(getListProjects().contains(PROJECT_NAME));
    }
}
