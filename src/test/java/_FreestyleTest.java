import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FreestyleTest extends BaseTest {
    private static String NAME;
    private static String nameRandom = RandomStringUtils.randomAlphabetic(5);

    public void createFreestyleProjectRandomName() {
        NAME = RandomStringUtils.randomAlphanumeric(3, 9);
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME);
        getDriver().findElement(By.xpath(
                "//li[contains(@class,'FreeStyleProject')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
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

    private static final String PROJECT_NAME = "New Freestyle project";

    private void dashboardClick() {
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
    }

    private void createNewProject() {
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    private void deleteFreestyleProject() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[text()='%s']", NAME)))).build().perform();
        getDriver().findElement(By.id("menuSelector")).click();
        deleteProject();
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {_FreestyleTest.CheckBox.ENABLE},
                {_FreestyleTest.CheckBox.DISABLE}
        };
    }

    private void saveButton() {
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
    }

    private void checkBoxDisableProject() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//label[text()='Disable this project']"))).click();
    }

    private WebElement clickAndFindIcon() {
        getDriver().findElement(By.id("jenkins-home-link")).click();

        return getDriver().findElement(By.xpath(
                String.format("//tr[@id='job_%s']/td/div/span/*[@tooltip]",
                        NAME)));
    }

    private void clickNewItem(){
        getDriver().findElement(By.className("task-link-text")).click();
    }

    private void deleteItem() {
        getDriver().findElement(By.linkText(nameRandom)).click();
        getDriver().findElement(By.linkText("Delete Project")).click();

        getWait5().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    private void createNEWFreeStyleProject(){
        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys(nameRandom);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void createDisabledFreeStyleProject(){
        createNEWFreeStyleProject();

        getDriver().findElement(By.name("disable")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void openConfigurePage() {

        getDriver().findElement(By.partialLinkText("Configure")).click();
    }

    @Test(dataProvider = "data")
    public void testDisableEnableFreestyleProject(_FreestyleTest.CheckBox project) {
        createFreestyleProjectRandomName();
        if (project.equals(_FreestyleTest.CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }

        saveButton();
        WebElement button = getDriver().findElement(By.cssSelector(
                "[type='submit']"));

        Assert.assertEquals(button.getText(),
                project.getStatusFreestyleProject());

        if (project.equals(_FreestyleTest.CheckBox.DISABLE)) {
            WebElement actualText = getDriver().findElement(By.cssSelector(
                    "[method='post']"));

            Assert.assertTrue(actualText.getText().contains(
                    "This project is currently disabled"));
        }
        deleteFreestyleProject();
    }

    @Test(dataProvider = "data")
    public void testDisableEnableIconsDashboard(_FreestyleTest.CheckBox project) {
        createFreestyleProjectRandomName();
        if (project.equals(_FreestyleTest.CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }
        saveButton();

        Assert.assertEquals(clickAndFindIcon().getAttribute("tooltip"),
                project.getStatusIcons());

        deleteFreestyleProject();
    }

    @Test
    public void testUserCanDeleteFreestyleProject() {

        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text() = 'New Item']")).click();
        By.xpath("//input[@id='name']").findElement(getDriver()).sendKeys("project-freestyle");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("New project");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Dashboard']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href='job/project-freestyle/']")).isDisplayed());
        getDriver().findElement(By.xpath("//a[@href='job/project-freestyle/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();
        getDriver().switchTo().alert().accept();

        boolean checkProjectExists;
        try {
            getDriver().findElement(By.linkText("project-freestyle")).isDisplayed();
            checkProjectExists = true;
        } catch (Exception ee) {
            checkProjectExists = false;
        }
        Assert.assertFalse(checkProjectExists);
    }

    @Test
    public void testCreateFreestyleProject() {
        String expectedResult = "FirstProject";
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProject");

        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//html/body/div[5]/div/div/div/div/form/div[1]/div[12]/div/div[2]/div[2]/span[1]/span/button")
        ).click();

        String actualResult = getDriver().findElement(By.xpath("//ul/li/a[@href='/job/FirstProject/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }

    @Test
    public void testUserCanConfigureNewProject() {
        createNewProject();
        boolean projectConfig = getDriver().findElements(By.cssSelector(".config-section-activator")).size() > 0;

        getDriver().findElement(By.cssSelector("[name='description']"))
                .sendKeys("This is a description for a Freestyle project");
        dashboardClick();
        String alert = String.valueOf(ExpectedConditions.alertIsPresent());

        getDriver().switchTo().alert().dismiss();
        dashboardClick();
        getDriver().switchTo().alert().accept();
        getDriver().findElement(By.xpath(String.format("//a[text()='%s']", PROJECT_NAME))).click();
        String description = getDriver().findElement(By.cssSelector(".jenkins-buttons-row")).getText();

        Assert.assertTrue(projectConfig);
        Assert.assertEquals(alert, "alert to be present");
        Assert.assertEquals(description, "Add description");

        deleteProject();
    }

    @Test
    public void testUserEnableDisableProject() {
        createNewProject();
        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Save']")).click();

        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Disable Project']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//form[contains(text(), 'This project is currently disabled')]")).isDisplayed());

        getDriver().findElement(By.xpath("//div//button[@type='submit'][text()='Enable']")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//span[text()='Build Now']")).isEnabled());

        deleteProject();
    }

    @Test
    public void testFreestyleProjectAddDescription() {
        createNEWFreeStyleProject();

        getDriver().findElement(By.name("description")).sendKeys("Test description");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        String actualDescription = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        deleteItem();

        Assert.assertEquals(actualDescription, "Test description");
    }
    @Test
    public void testDisabledFreestyleProject() {
        createDisabledFreeStyleProject();

        String actualText = getDriver()
                .findElement(By.id("enable-project"))
                .getText();

        deleteItem();

        Assert.assertTrue(actualText.contains("This project is currently disabled"));
    }

    @Test
    public void testDisabledEnabledFreestyleProject() {
        createDisabledFreeStyleProject();

        getDriver().findElement(By.id("yui-gen1-button")).click();

        String actualText = getDriver()
                .findElement(By.id("yui-gen1-button"))
                .getText();

        deleteItem();

        Assert.assertEquals(actualText,"Disable Project");
    }

    @Test
    public void testRenameFreestyleProject() {
        clickNewItem();

        getDriver().findElement(By.id("name")).sendKeys("First name");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(nameRandom);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        String actualText = getDriver()
                .findElement(By.id("main-panel"))
                .findElement(By.tagName("h1"))
                .getText();

        deleteItem();

        Assert.assertEquals(actualText,"Project " + nameRandom);
    }

    @Test
    public void testNoEnterNameFreestyleItem() {
        String expectedText = "» This field cannot be empty, please enter a valid name";

        clickNewItem();
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();

        String actualErrorMessage = getDriver().findElement(By.id("itemname-required")).getText();

        Assert.assertEquals(actualErrorMessage,expectedText);
    }

    @Test
    public void testEnterSeveralSpaces() {
        String expectedText = "No name is specified";

        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys("    ");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        String actualErrorMessage = getDriver().findElement(
                By.xpath("//div[@id='main-panel']/p")).getText();

        Assert.assertEquals(actualErrorMessage,expectedText);
    }

    @Test
    public void testCheckHelpButton() {
        createNEWFreeStyleProject();

        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.linkText("Configure")).click();
        getDriver().findElement(By.cssSelector(".tab.config-section-activator.config_build_triggers")).click();

        new Actions(getDriver())
                .pause(500)
                .moveToElement(getDriver().findElement(
                                By.xpath("//a[@tooltip='Help for feature: Build periodically']")))
                .perform();

        String actualText = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();

        deleteItem();

        Assert.assertEquals(actualText,"Help for feature: Build periodically");
    }

    @Test
    public void testConfigureApplyButton() {

        String expectedAlertMessage = "Saved";

        createNEWFreeStyleProject();
        saveButton();
        openConfigurePage();

        getDriver().findElement(By.xpath("//button[contains(text(),'Apply')]")).click();
        String alertMessage = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[id='notification-bar'][class='notif-alert-success notif-alert-show']")
        )).getText();

        Assert.assertEquals(alertMessage, expectedAlertMessage);

        deleteItem();
    }

    @Test
    public void testConfigureSaveButton() {

        String expectedLink = "/job/" + nameRandom + "/";

        createNEWFreeStyleProject();
        saveButton();
        openConfigurePage();

        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")
        ).click();

        Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedLink));

        deleteItem();
    }
}
