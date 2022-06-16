import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DisableEnableFreestyleProjectTest extends BaseTest {
    private static String NAME;

    @BeforeMethod
    public void setUp() {
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

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {CheckBox.ENABLE},
                {CheckBox.DISABLE}
        };
    }

    private void saveButton() {
        getDriver().findElement(By.cssSelector("[type='submit']")).click();
    }

    private void clickIconHome() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
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

    @Test(dataProvider = "data", description = "TC_004.003")
    public void testDisableEnableFreestyleProject(CheckBox project) {
        if (project.equals(CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }

        saveButton();
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
    }

    @Test(dataProvider = "data", description = "TC_004.003")
    public void testDisableEnableIconsDashboard(CheckBox project) {
        if (project.equals(CheckBox.DISABLE)) {
            checkBoxDisableProject();
        }
        saveButton();

        Assert.assertEquals(clickAndFindIcon().getAttribute("tooltip"),
                project.getStatusIcons());
    }

    @Test
    public void testDeleteAllProject() {
        String scriptDeleteAllProjects = "for(j in jenkins.model.Jenkins." +
                "theInstance.getAllItems()) {j.delete()}";

        clickIconHome();
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='script']")).click();

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver()
                        .findElement(By.cssSelector("[class$='default']")))
                .click()
                .sendKeys(scriptDeleteAllProjects)
                .moveToElement(getDriver().findElement(By.cssSelector(
                        "[type='submit']")))
                .click()
                .build()
                .perform();
        clickIconHome();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),
                "Welcome to Jenkins!");
    }
}


