import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMultiConfigurationProjectXbrookxTest extends BaseTest {
    private final String PROJECT_NAME = "Neeew Multi configuration project";
    private final By PROJECT_ON_DAHBOARD = By.xpath("//table[@id='projectstatus']//a[normalize-space(.)='" + PROJECT_NAME + "']");

    private void createMultiConfigurationProject(String projectName) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    private void returnHomePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private List<String> getListProjects() {

        return getDriver().findElements(
                        By.xpath("//tbody/tr/td/a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void deleteMultiConfigurationProject() {
        getDriver().findElement(PROJECT_ON_DAHBOARD).click();
        getDriver().findElement(By.xpath("//a[contains(@class, 'confirmation-link')] ")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void TC_041_003_testCreateMultiConfigurationProject() {
        createMultiConfigurationProject(PROJECT_NAME);
        returnHomePage();

        Assert.assertTrue(getListProjects().contains(PROJECT_NAME));
    }

    @Test
    public void TC_041_005_testCheckSubMenuConfigureAfterCreatingProject() {
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

        WebDriverWait wait = new WebDriverWait(getDriver(), 9);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-bar")));
    }

    @Test
    public void TC_043_006_testMultiConfigurationProjectRenameUsingInvalidName() {
        String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

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
    }

    @Test(dependsOnMethods = "TC_043_006_testMultiConfigurationProjectRenameUsingInvalidName")
    public void TC_041_004_testDeleteMultiConfigurationProject() {
        returnHomePage();
        Assert.assertTrue(getListProjects().contains(PROJECT_NAME));
        deleteMultiConfigurationProject();
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();

        Assert.assertFalse(getListProjects().contains(PROJECT_NAME));
    }
}
