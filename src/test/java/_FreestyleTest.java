import org.apache.commons.lang3.RandomStringUtils;
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
            deleteFreestyleProject();
        }
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
}
