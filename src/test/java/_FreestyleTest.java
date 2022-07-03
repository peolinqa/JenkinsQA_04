import model.HomePage;
import model.ProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.ArrayList;
import java.util.List;

import static runner.ProjectUtils.ProjectType.Freestyle;

public class _FreestyleTest extends BaseTest {
    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = "New " + RANDOM_NAME;
    private static final String NAME_WITH_SPECIAL_CHARACTERS = "-()+-_~-1";
    private static final String INVALID_DATA = "!@#$;%^&?*[]/:.";
    private static final String RANDOM_DESCRIPTION = TestUtils.getRandomStr(15);
    private static final String EDITED_RANDOM_DESCRIPTION = TestUtils.getRandomStr(15);
    private static final String DESCRIPTION_TEXT = "This is a description for a Freestyle project";

    private void checkBoxDisableProject() {
        getDriver().findElement(By.xpath("//label[text()='Disable this project']")).click();
    }

    private WebElement clickAndFindIcon() {
        getDriver().findElement(By.id("jenkins-home-link")).click();

        return getDriver().findElement(By.xpath(
                String.format("//tr[@id='job_%s']/td/div/span/*[@tooltip]",
                        RANDOM_NAME)));
    }

    private void renameFreestyleProject(String currentName, String newName) {
        ProjectUtils.openProject(getDriver(), currentName);
        getDriver().findElement(By.linkText("Rename")).click();
        TestUtils.clearAndSend(getDriver(), By.name("newName"), newName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void editDescription() {
        getDriver().findElement(By.xpath("//a[text() = 'Edit description']")).click();
        TestUtils.clearAndSend(getDriver(), By.xpath("//textarea[@name = 'description']"), EDITED_RANDOM_DESCRIPTION);
        ProjectUtils.clickSaveButton(getDriver());
    }

    private void deleteProject(String name) {
        getDriver().findElement(By.xpath(String.format("//a[text()='%s']", name))).click();
        ProjectUtils.Dashboard.Project.DeleteProject.click(getDriver());
        getDriver().switchTo().alert().accept();
    }

    private enum CheckBox {
        ENABLE("Not built", "Disable Project"),
        DISABLE("Disabled", "Enable");

        private final String statusIcons;
        private final String statusFreestyleProject;

        CheckBox(String statusIcons, String statusFreestyleProject) {
            this.statusIcons = statusIcons;
            this.statusFreestyleProject = statusFreestyleProject;
        }

        public String getStatusIcons() {
            return statusIcons;
        }

        public String getStatusFreestyleProject() {
            return statusFreestyleProject;
        }
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {CheckBox.ENABLE},
                {CheckBox.DISABLE}
        };
    }

    @Test(dataProvider = "data")
    public void testDisableEnableFreestyleProject(CheckBox project) {
        ProjectUtils.createProject(getDriver(), Freestyle, RANDOM_NAME);
        if (project.equals(CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }

        ProjectUtils.clickSaveButton(getDriver());
        WebElement button = getDriver().findElement(By.cssSelector(
                "[type='submit']"));

        Assert.assertEquals(button.getText(),
                project.getStatusFreestyleProject());

        if (project.equals(CheckBox.DISABLE)) {
            WebElement actualText = getDriver().findElement(By.cssSelector(
                    "[method='post']"));

            Assert.assertTrue(actualText.getText().contains(
                    "This project is currently disabled"));
        }
        deleteProject(RANDOM_NAME);
    }

    @Test(dataProvider = "data")
    public void testDisableEnableIconsDashboard(CheckBox project) {
        ProjectUtils.createProject(getDriver(), Freestyle, RANDOM_NAME);
        if (project.equals(CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }
        ProjectUtils.clickSaveButton(getDriver());

        Assert.assertEquals(clickAndFindIcon().getAttribute("tooltip"),
                project.getStatusIcons());

        deleteProject(RANDOM_NAME);
    }

    @Test
    public void testCreateFreestyleProject() {
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_NAME)
                .setProjectType(Freestyle)
                .createAndGoToConfig()
                .saveConfigAndGoToProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testUserCanConfigureFreestyleProject() {

        String description = new HomePage(getDriver())
                .clickProjectName(RANDOM_NAME)
                .clickConfigure()
                .setDescription(DESCRIPTION_TEXT)
                .saveConfigAndGoToProject()
                .getDescriptionName();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testUserCanConfigureFreestyleProject")
    public void testDisableProject() {

        ProjectPage projectPage = new HomePage(getDriver())
                .clickProjectName(RANDOM_NAME)
                .clickDisable();

        Assert.assertEquals(projectPage.getDisableName()[0], "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {

        ProjectPage projectPage = new HomePage(getDriver())
                .clickProjectName(RANDOM_NAME)
                .clickEnable();

        Assert.assertTrue(projectPage._disableButton());
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testFreestyleProjectAddDescription() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        getDriver().findElement(By.xpath("//span[text()='Configure']")).click();
        TestUtils.clearAndSend(getDriver(), By.name("description"), RANDOM_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        String actualDescription = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        Assert.assertEquals(actualDescription, RANDOM_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testFreestyleProjectAddDescription")
    public void testFreestyleProjectEditDescription() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        editDescription();
        String actualEditedDescription = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        Assert.assertEquals(actualEditedDescription, EDITED_RANDOM_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testFreestyleProjectEditDescription")
    public void testHelpButtonGeneralTabDiscardOldBuildsPopup() {
        ProjectUtils.openProject(getDriver(),RANDOM_NAME);
        ProjectUtils.Dashboard.Project.Configure.click(getDriver());
        getActions().moveToElement(getDriver().findElement(By.xpath("//label[text()='Discard old builds']/../a"))).pause(500).build().perform();
        getActions().moveToElement(getDriver().findElement(By.xpath("//a[@tooltip='Help for feature: Discard old builds']"))).build().perform();

        Assert.assertEquals(getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'tt']"))).getText(),
                "Help for feature: Discard old builds");
    }

    @Test(dependsOnMethods = "testHelpButtonGeneralTabDiscardOldBuildsPopup")
    public void testDisabledFreestyleProject() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        ProjectUtils.clickDisableProject(getDriver());
        Assert.assertTrue(getDriver()
                .findElement(By.id("enable-project")).getText().contains("This project is currently disabled"));
    }

    @Test(dependsOnMethods = "testDisabledFreestyleProject")
    public void testDisabledEnabledFreestyleProject() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        ProjectUtils.clickEnableProject(getDriver());
        Assert.assertEquals(getDriver().findElement(By.id("yui-gen1-button")).getText(), "Disable Project");
    }

    @Test(dependsOnMethods = "testDisabledEnabledFreestyleProject")
    public void testRenameFreestyleProject() {
        renameFreestyleProject(RANDOM_NAME, EDITED_RANDOM_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project " + EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testCheckHelpButtonBuildTriggersBuildPeriodically() throws InterruptedException {
        ProjectUtils.openProject(getDriver(), EDITED_RANDOM_NAME);
        getDriver().findElement(By.linkText("Configure")).click();
        getDriver().findElement(By.cssSelector(".tab.config-section-activator.config_build_triggers")).click();
        Thread.sleep(500);
        getActions().moveToElement(getDriver().findElement(By.xpath("//a[@tooltip='Help for feature: Build periodically']"))).click().build().perform();

        String actualText = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();
        Assert.assertEquals(actualText, "Help for feature: Build periodically");
    }

    @Test(dependsOnMethods = "testCheckHelpButtonBuildTriggersBuildPeriodically")
    public void testNewFreestyleWithSpecialCharacters() {
        ProjectUtils.openProject(getDriver(), EDITED_RANDOM_NAME);
        renameFreestyleProject(EDITED_RANDOM_NAME, NAME_WITH_SPECIAL_CHARACTERS);

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project " + NAME_WITH_SPECIAL_CHARACTERS);
    }

    @Test(dependsOnMethods = "testNewFreestyleWithSpecialCharacters")
    public void testRenameWithInvalidData() {
        for (int i = 0; i < INVALID_DATA.length(); i++) {
            String newProjectName = INVALID_DATA.substring(i, (i + 1));
            renameFreestyleProject(NAME_WITH_SPECIAL_CHARACTERS, newProjectName);
            String alertMessage = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
            Assert.assertTrue(alertMessage.contains("is an unsafe character")
                    || alertMessage.contains("is not an allowed name"));
            getDriver().navigate().back();
        }
    }

    @Test
    public void testCannotCreateProjectNameWithInvalidCharacter() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        String[] characterName = {"!", "@", "#", "$", ";", "%", "^", "&", "?", "*", "[", "]", "/", ":", "."};
        boolean resultButtonOkDisabled = true;

        for (String character : characterName) {
            TestUtils.clearAndSend(getDriver(), By.id("name"), character);
            Freestyle.click(getDriver());
            if (!getDriver().findElement(By.xpath("//button[@class]")).getAttribute("class").equals("disabled")) {
                resultButtonOkDisabled = false;
            }
            getDriver().navigate().refresh();

            Assert.assertTrue(resultButtonOkDisabled);
        }
    }

    @Test (dependsOnMethods = "testRenameWithInvalidData")
    public void testDeleteFreestyleProject() {
        var driver = getDriver();
        List<String> jobsNames = TestUtils.getTextFromList(driver, By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a"));
        deleteProject(NAME_WITH_SPECIAL_CHARACTERS);
        List<String> jobsNames2 = TestUtils.getTextFromList(driver, By.xpath("//table[@id='projectstatus']/tbody/tr/td[3]/a"));

        List<String> differences = new ArrayList<>(jobsNames);
        differences.removeAll(jobsNames2);

        Assert.assertTrue(differences.contains(NAME_WITH_SPECIAL_CHARACTERS));
    }

    @Test
    public void testConfigureSaveButton() {

        String expectedLink = "/job/" + RANDOM_NAME + "/";

        ProjectUtils.createProject(getDriver(), Freestyle, RANDOM_NAME);
        ProjectUtils.clickSaveButton(getDriver());

        Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedLink));
    }

    @Test (dependsOnMethods = "testConfigureSaveButton")
    public void testConfigureApplyButton() {

        String expectedAlertMessage = "Saved";

        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        ProjectUtils.Dashboard.Project.Configure.click(getDriver());

        getDriver().findElement(By.xpath("//button[contains(text(),'Apply')]")).click();
        String alertMessage = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='notification-bar'][class='notif-alert-success notif-alert-show']")
        )).getText();

        deleteProject(RANDOM_NAME);

        Assert.assertEquals(alertMessage, expectedAlertMessage);
    }
}
