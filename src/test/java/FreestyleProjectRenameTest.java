import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectRenameTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath(
            "//span[normalize-space(text())='New Item']");
    private static final By NEW_ITEM_FIELD = By.xpath(
            "//input[@id='name']");
    private static final By DASHBOARD = By.xpath("//a[normalize-space(text())='Dashboard']");
    private static final By OK_BUTTON = By.xpath("//button[@id='ok-button']");
    private static final By SAVE_BUTTON = By.xpath(
            "//div[@class='bottom-sticker-inner']/span/span/button[@type='submit']");
    private static final By FREESTYLE_PROJECT = By.xpath(
            "//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span");
    private static final By RENAME_FIELD = By.xpath(
            "//div[@id='main-panel']/form/div[1]/div[1]/div[2]/input");
    private static final By RENAME_BUTTON = By.xpath(
            "//div[@class='bottom-sticker-inner']/span/span/button");

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

    private void createProject(String projectName) {

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NEW_ITEM_FIELD).sendKeys(projectName);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
    }

    private void deleteCreatedProject(String newProjectName) {
        getDriver().findElement(DASHBOARD).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + newProjectName + "/']"))).click().build().perform();

        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        getDriver().switchTo().alert().accept();

    }

    @Test
    public void test_TC_003_001_FreestyleProjectRename() {

        String projectName = createRandomName();
        String newProjectName = createRandomName();

        createProject(projectName);

        getDriver().findElement(DASHBOARD).click();

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
        getDriver().findElement(RENAME_FIELD).clear();
        getDriver().findElement(RENAME_FIELD).sendKeys(newProjectName);
        getDriver().findElement(RENAME_BUTTON).click();

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText();
        deleteCreatedProject(newProjectName);

        Assert.assertEquals(actualResult, "Project " + newProjectName);
    }
}