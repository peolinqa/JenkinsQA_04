import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

public class _FreestyleTest extends BaseTest {
    private static final String RANDOM_NAME = TestUtils.getRandomStr(5);
    private static final String EDITED_RANDOM_NAME = "New " + RANDOM_NAME;
    private static final String NAME_WITH_SPECIAL_CHARACTERS = "-()+-_~-1";
    private static final String INVALID_DATA = "!@#$;%^&?*[]/:.";
    private static final String RANDOM_DESCRIPTION = TestUtils.getRandomStr(15);
    private static final String EDITED_RANDOM_DESCRIPTION = TestUtils.getRandomStr(15);

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
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject, RANDOM_NAME);
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
        ProjectUtils.deleteProject(getDriver(), RANDOM_NAME);
    }

    @Test(dataProvider = "data")
    public void testDisableEnableIconsDashboard(CheckBox project) {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject, RANDOM_NAME);
        if (project.equals(CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }
        ProjectUtils.clickSaveButton(getDriver());

        Assert.assertEquals(clickAndFindIcon().getAttribute("tooltip"),
                project.getStatusIcons());

        ProjectUtils.deleteProject(getDriver(), RANDOM_NAME);
    }

    @Test
    public void testCreateFreestyleProject() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject, RANDOM_NAME);
        ProjectUtils.clickSaveButton(getDriver());

        WebElement projectName = getDriver().findElement(By.xpath(String.format("//li/a[@href='/job/%s/']", RANDOM_NAME)));
        Assert.assertEquals(projectName.getText(), RANDOM_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testUserCanConfigureFreestyleProject() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        getDriver().findElement(By.cssSelector("[title='Configure']")).click();

        boolean projectConfig = getDriver().findElements(By.cssSelector(".config-section-activator")).size() > 0;

        TestUtils.clearAndSend(getDriver(), By.cssSelector("[name='description']"), "This is a description for a Freestyle project");
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        String alert = String.valueOf(ExpectedConditions.alertIsPresent());

        getDriver().switchTo().alert().dismiss();
        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());
        getDriver().switchTo().alert().accept();
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        String description = getDriver().findElement(By.cssSelector(".jenkins-buttons-row")).getText();

        Assert.assertTrue(projectConfig);
        Assert.assertEquals(alert, "alert to be present");
        Assert.assertEquals(description, "Add description");
    }

    @Test(dependsOnMethods = "testUserCanConfigureFreestyleProject")
    public void testUserEnableDisableProject() {
        ProjectUtils.openProject(getDriver(), RANDOM_NAME);
        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Disable Project']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//form[contains(text(), 'This project is currently disabled')]")).isDisplayed());

        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Enable']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Build Now']")).isEnabled());
    }


    @Test(dependsOnMethods = "testUserEnableDisableProject")
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
        TestUtils.actionsMove(getDriver(), By.xpath("//label[text()='Discard old builds']/../a"), 500);
        TestUtils.actionsMove(getDriver(), By.xpath("//a[@tooltip='Help for feature: Discard old builds']"), 0);

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

    @Test
    public void testNoEnterNameFreestyleItem() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        ProjectUtils.NewItemTypes.FreestyleProject.click(getDriver());
        Assert.assertEquals(
                getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testEnterSeveralSpaces() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());
        TestUtils.clearAndSend(getDriver(), By.id("name"), "    ");
        ProjectUtils.NewItemTypes.FreestyleProject.click(getDriver());
        ProjectUtils.clickOKButton(getDriver());

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/p")).getText(), "No name is specified");
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testCheckHelpButtonBuildTriggersBuildPeriodically() throws InterruptedException {
        ProjectUtils.openProject(getDriver(), EDITED_RANDOM_NAME);
        getDriver().findElement(By.linkText("Configure")).click();
        getDriver().findElement(By.cssSelector(".tab.config-section-activator.config_build_triggers")).click();
        Thread.sleep(500);
        TestUtils.actionsClick(getDriver(), By.xpath("//a[@tooltip='Help for feature: Build periodically']"));

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
    public void testNewFreestyleItemProhibitedCharCopyDeleteLater() {

        ProjectUtils.openProject(getDriver(), NAME_WITH_SPECIAL_CHARACTERS);
        renameFreestyleProject(NAME_WITH_SPECIAL_CHARACTERS, "!");

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id='main-panel']/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "‘!’ is an unsafe character");
    }

    @Test(dependsOnMethods = "testNewFreestyleItemProhibitedCharCopyDeleteLater")
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

    @Test(dependsOnMethods = "testRenameWithInvalidData")
    public void testRenameCopyDeleteLater() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject, RANDOM_NAME);

        ProjectUtils.Dashboard.Main.Dashboard.click(getDriver());

        TestUtils.actionsClick(getDriver(), By.xpath("//a[@href='job/" + RANDOM_NAME + "/']"));
        TestUtils.actionsClick(getDriver(), By.xpath("//div[@id='menuSelector']"));
        TestUtils.actionsClick(getDriver(), By.xpath("//a[@href='/job/" + RANDOM_NAME + "/confirm-rename']"));

        getDriver().findElement(By.xpath(
                "//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input")).clear();

        TestUtils.clearAndSend(getDriver(), By.xpath("//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input"), EDITED_RANDOM_NAME);

        getDriver().findElement(By.xpath(
                "//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String editedName = getDriver().findElement(By.xpath("//h1")).getText();

        ProjectUtils.deleteProject(getDriver(), EDITED_RANDOM_NAME);

        Assert.assertEquals(editedName, "Project " + EDITED_RANDOM_NAME);
    }

    @Test
    public void testCannotCreateProjectNameWithInvalidCharacter() {
        ProjectUtils.Dashboard.Main.NewItem.click(getDriver());

        String[] characterName = {"!", "@", "#", "$", ";", "%", "^", "&", "?", "*", "[", "]", "/", ":", "."};
        boolean resultButtonOkDisabled = true;

        for (String character : characterName) {
            TestUtils.clearAndSend(getDriver(), By.id("name"), character);
            ProjectUtils.NewItemTypes.FreestyleProject.click(getDriver());
            if (!getDriver().findElement(By.xpath("//button[@class]")).getAttribute("class").equals("disabled")) {
                resultButtonOkDisabled = false;
            }
            getDriver().navigate().refresh();

            Assert.assertTrue(resultButtonOkDisabled);
        }
    }

    @Test(dependsOnMethods = "testRenameWithInvalidData")
    public void testDeleteFreestyleProject() {
        ProjectUtils.deleteProject(getDriver(), NAME_WITH_SPECIAL_CHARACTERS);

        boolean checkProjectExists;
        try {
            getDriver().findElement(By.linkText(NAME_WITH_SPECIAL_CHARACTERS)).isDisplayed();
            checkProjectExists = true;
        } catch (Exception ee) {
            checkProjectExists = false;
        }
        Assert.assertFalse(checkProjectExists);
    }
}