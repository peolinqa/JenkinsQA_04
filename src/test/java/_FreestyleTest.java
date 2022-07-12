import model.FreestyleConfigPage;
import model.FreestylePage;
import model.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
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

    private void deleteProject(String name) {
        getDriver().findElement(By.xpath(String.format("//a[text()='%s']", name))).click();
        ProjectUtils.Dashboard.Project.DeleteProject.click(getDriver());
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testCreateFreestyleProject() {

        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(RANDOM_NAME)
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToFreestyleProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testConfigureSaveButton() {

        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .saveConfigAndGoToFreestyleProject()
                .getProjectName();

        Assert.assertEquals(projectName, RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testConfigureSaveButton")
    public void testConfigureApplyButton() {

        boolean alertIsDisplayed = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .clickApplyAndGetAlert();

        Assert.assertTrue(alertIsDisplayed);
    }

    @Test(dependsOnMethods = "testConfigureApplyButton")
    public void testAddDescription() {

        String description = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .setDescription(DESCRIPTION_TEXT)
                .saveConfigAndGoToFreestyleProject()
                .getDescriptionName();

        Assert.assertEquals(description, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testEditDescription() {

        String editDescription = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickEditDescription()
                .editDescription(EDITED_RANDOM_DESCRIPTION)
                .clickMultiButton()
                .getDescriptionName();

        Assert.assertEquals(editDescription, EDITED_RANDOM_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testDisableProject() {

        FreestylePage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickMultiButton();

        Assert.assertEquals(freestylePage.getDisableName()[0], "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {

        FreestylePage freestylePage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickMultiButton();

        Assert.assertEquals(freestylePage._disableButton(), "Disable Project");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testHelpButtonPopupGeneral() {

        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesGeneral(), "Help for feature: Discard old builds");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupGeneral")
    public void testHelpButtonPopupBuildPeriodically() {

        FreestyleConfigPage freestyleConfigPage = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickFreestyleConfigure()
                .clickBuildTriggers();

        Assert.assertEquals(freestyleConfigPage.getHelpNamesBuildTriggers(), "Help for feature: Build periodically");
    }

    @Test(dependsOnMethods = "testHelpButtonPopupBuildPeriodically")
    public void testRenameFreestyleProject() {

        String projectName = new HomePage(getDriver())
                .clickFreestyleName(RANDOM_NAME)
                .clickAdnGoToRenamePage()
                .setNewProjectName(EDITED_RANDOM_NAME)
                .clickRenameAndGoToFreestyle()
                .getProjectName();

        Assert.assertEquals(projectName, EDITED_RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testNewFreestyleWithSpecialCharacters() {

        String projectName = new HomePage(getDriver())
                .clickFreestyleName(EDITED_RANDOM_NAME)
                .clickAdnGoToRenamePage()
                .setNewProjectName(NAME_WITH_SPECIAL_CHARACTERS)
                .clickRenameAndGoToFreestyle()
                .getProjectName();

        Assert.assertEquals(projectName, NAME_WITH_SPECIAL_CHARACTERS);
    }

    private void renameFreestyleProject(String currentName, String newName) {
        ProjectUtils.openProject(getDriver(), currentName);
        getDriver().findElement(By.linkText("Rename")).click();
        TestUtils.clearAndSend(getDriver(), By.name("newName"), newName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
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
}
