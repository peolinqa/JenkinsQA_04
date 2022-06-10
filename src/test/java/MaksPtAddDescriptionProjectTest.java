import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaksPtAddDescriptionProjectTest extends BaseTest {

    private final By NEW_ITEM = By.linkText("New Item");
    private final By INPUT = By.xpath("//input[@name='name']");
    private final By FREESTYLE_PROJECT = By.xpath("//li[@class='hudson_model_FreeStyleProject']");
    private final By OK_BUTTON = By.id("ok-button");
    private final By DASHBOARD = By.id("jenkins-home-link");

    private void stepsCreateNewProject(String str) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT).sendKeys(str);
        getDriver().findElement(FREESTYLE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(DASHBOARD).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.xpath("//a[@data-message]")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testAddDescriptionProject() {
        stepsCreateNewProject("freestyle-project");

        getDriver().findElement(By.xpath("//td/a[contains(@href,'freestyle-project')]")).click();
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("QA Automation_04");
        getDriver().findElement(By.xpath("//button[text()='Save']")).click();

        String actualDescription = getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
        Assert.assertEquals(actualDescription, "QA Automation_04");

        deleteProject();
    }
}