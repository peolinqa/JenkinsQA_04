import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.BaseUtils;
import runner.ProjectUtils;

public class CopyDataFromExistingItemIntoNewOneTest extends BaseTest {

    private final String DESCRIPTION_FIELD = "description";
    private final String GITHUB_URL = "_.projectUrlStr";
    private final String NAME = "NJ";
    private final String NAME2 = "NJ2";
    private final String DESCRIPTION_INPUT = "New Project created by TA";
    private final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";
    private Actions action;

    private Actions getAction() {
        if (action == null) {
            action = new Actions(getDriver());
        }
        return action;
    }

    public void startFreestyleProject(String name) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
    }

    public void okButton() {
        getAction().moveToElement(getDriver().findElement(By.id("ok-button"))).click().perform();
    }

    public void saveButton() {
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']//button[@type='submit']")).click();
    }

    public void createBaseFreestyleProject() {
        startFreestyleProject(NAME);

        okButton();

        getDriver().findElement(By.name(DESCRIPTION_FIELD)).sendKeys(DESCRIPTION_INPUT);
        getDriver().findElement(By.name("githubProject")).click();
        getDriver().findElement(By.name(GITHUB_URL)).sendKeys(URL_INPUT);
        saveButton();

        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {

        createBaseFreestyleProject();

        startFreestyleProject(NAME2);

        WebElement copFromButton = getDriver().findElement(By.id("from"));
        getAction().moveToElement(copFromButton).perform();
        copFromButton.sendKeys(NAME);
        okButton();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);
        asserts.assertEquals(getDriver().findElement(By.name(GITHUB_URL)).getAttribute("value"),URL_INPUT);
        asserts.assertAll();

        saveButton();
    }
}
