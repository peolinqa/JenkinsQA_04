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
    private static String descriptionRandom = RandomStringUtils.randomAlphabetic(5);
    private static String editDescriptionRandom = RandomStringUtils.randomAlphabetic(5);

    public void createFreestyleProjectRandomName() {
        NAME = RandomStringUtils.randomAlphanumeric(3, 9);
        getDriver().findElement(By.cssSelector("[title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME);
        getDriver().findElement(By.xpath(
                "//li[contains(@class,'FreeStyleProject')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private String createRandomName() {
        String projectNameSubstrate = "0123456789qwertyuiopasdfghjklzxcvbnm";
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            builder.append(
                    projectNameSubstrate.charAt((int) (Math.random() * projectNameSubstrate.length())));
        }
        String projectName = builder.toString();
        return projectName;
    }

    private void deleteCreatedProject(String newProjectName) {
        dashboardClick();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + newProjectName + "/']"))).click().build().perform();

        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        getDriver().switchTo().alert().accept();

    }

    private String checkErrorMessage() {

        return getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText();
    }

    private void clearSendClick(String newProjectName) {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(
                By.xpath("//input[@checkdependson='newName']")).sendKeys(newProjectName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

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

    private void deleteProject1() {
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                        By.xpath("//div/table/tbody/tr[@id=\"job_freestyle-project-()+-_~-1\"]/td[3]/a")))
                .click().build().perform();

        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        getDriver().switchTo().alert().accept();
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

    private void clickNewItem() {
        getDriver().findElement(By.className("task-link-text")).click();
    }

    private void deleteItem() {
        getDriver().findElement(By.linkText(nameRandom)).click();
        getDriver().findElement(By.linkText("Delete Project")).click();

        getWait5().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    private void createNEWFreeStyleProject() {
        clickNewItem();
        getDriver().findElement(By.id("name")).sendKeys(nameRandom);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    private void createDisabledFreeStyleProject() {
        createNEWFreeStyleProject();

        getDriver().findElement(By.name("disable")).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void addDescriptionToFreestyleProject() {
        getDriver().findElement(By.xpath("//textarea[@name = 'description']"))
                .sendKeys(descriptionRandom);
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();

    }

    private void editDescription() {
        getDriver().findElement(By.xpath("//a[text() = \"Edit description\"]")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//textarea[@name = 'description']"))).click();

        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).sendKeys(editDescriptionRandom);
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
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

        Assert.assertEquals(actualText, "Disable Project");
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

        Assert.assertEquals(actualErrorMessage, expectedText);
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

        Assert.assertEquals(actualErrorMessage, expectedText);
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

        Assert.assertEquals(actualText, "Help for feature: Build periodically");
    }

    private static final String ITEM_NAME = "freestyle-project-()+-_~-1";

    private void createProject(String name) {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[1]/span/a/span[2]")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testNewFreestyleItem() {

        createProject(ITEM_NAME);

        getDriver().findElement(By.xpath("//div[@class='bottom-sticker-inner']/span/span/button")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[1]/a")).click();

        WebElement jobItem = getDriver().findElement(
                By.xpath("//tr[@class=' job-status-nobuilt']//a[@href='job/"
                        + ITEM_NAME.replace("~", "%7E") + "/']"));
        Assert.assertTrue(jobItem.isDisplayed());
        Assert.assertEquals(jobItem.getText().replace("\n", ""), ITEM_NAME);

        deleteProject1();
    }

    @Test
    public void testNewFreestyleItem_negative() {

        createProject("freestyle-project-2!");

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id='main-panel']/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "‘!’ is an unsafe character");
    }

    @Test
    public void testRename() {

        String projectName = createRandomName();
        String newProjectName = createRandomName();

        createProject(projectName);

        dashboardClick();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                        By.xpath("//a[@href='job/" + projectName + "/']")))
                .build().perform();

        action.moveToElement(getDriver().findElement(By.xpath(
                        "//div[@id='menuSelector']")))
                .click().build().perform();

        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + projectName + "/confirm-rename']")))
                .click().build().perform();
        getDriver().findElement(By.xpath(
                "//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input")).clear();

        getDriver().findElement(By.xpath(
                        "//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input"))
                .sendKeys(newProjectName);

        getDriver().findElement(By.xpath(
                "//div[@class='bottom-sticker-inner']/span/span/button")).click();

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();

        deleteCreatedProject(newProjectName);

        Assert.assertEquals(actualResult, "Project " + newProjectName);
    }

    @Test
    public void testRenameWithInvalidData() {

        String projectName = createRandomName();
        String newProjectName = " ";
        String invalidData = "!@#$%^*/|\\;:?";

        createProject(projectName);

        getDriver().findElement(By.xpath("//a[(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + projectName + "/']")))
                .build().perform();
        action.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + projectName + "/confirm-rename']")))
                .click().build().perform();

        for (int i = 0; i < invalidData.length(); i++) {
            newProjectName = invalidData.substring(i, (i + 1));

            clearSendClick(newProjectName);

            String expectedResult = "‘" + newProjectName + "’ is an unsafe character";
            System.out.println("expectedResult = " + expectedResult);
            Assert.assertEquals(checkErrorMessage(), expectedResult);
            getDriver().navigate().back();
        }

        deleteCreatedProject(projectName);
    }

    @Test
    public void testFreestyleProjectEditDescription() {
        createNEWFreeStyleProject();

        addDescriptionToFreestyleProject();

        editDescription();

        String actualEditedDescription = getDriver().findElement(
                By.xpath("//div[@id='description']/div[1]")).getText();

        deleteItem();

        Assert.assertEquals(actualEditedDescription,editDescriptionRandom);
    }

    @Test
    public void testCreateProjectNameWithCharacterSymbols() {
        getDriver().findElement(By.linkText("New Item")).click();

        String[] characterName = {"!", "@", "#", "$", ";", "%", "^", "&", "?", "*", "[", "]", "/", ":", "."};
        boolean resultButtonOkDisabled = true;

        for (int i = 0; i < characterName.length; i++) {
            getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(characterName[i]);
            getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
            if (!getDriver().findElement(By.xpath("//button[@class]")).getAttribute("class").equals("disabled")) {
                resultButtonOkDisabled = false;
            }
            getDriver().navigate().refresh();

            Assert.assertTrue(resultButtonOkDisabled);
        }
    }
}