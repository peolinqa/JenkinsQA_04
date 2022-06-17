import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class _NewItemTest extends BaseTest {

    private final String DESCRIPTION_FIELD = "description";
    private final String GITHUB_URL = "_.projectUrlStr";
    private final String NAME = "NJ";
    private final String DESCRIPTION_INPUT = "New Project created by TA";
    private final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    public void homePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    public void startFreestyleProject(String name) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
    }

    public void okButton() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.id("ok-button"))).click().perform();
    }

    public void saveButton() {
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']//button[@type='submit']")).click();
    }

    public void copyFrom(String name) {
        WebElement copFromButton = getDriver().findElement(By.id("from"));
        Actions action = new Actions(getDriver());
        action.moveToElement(copFromButton).perform();
        copFromButton.sendKeys(name);
        okButton();
    }

    public void createBaseFreestyleProject() {
        startFreestyleProject(NAME);

        okButton();

        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.name(DESCRIPTION_FIELD))).sendKeys(DESCRIPTION_INPUT);
        getDriver().findElement(By.name("githubProject")).click();
        getDriver().findElement(By.name(GITHUB_URL)).sendKeys(URL_INPUT);
        saveButton();

        homePage();
    }

    @Test
    public void testCopyDataFromExistingItemNegative() {
        startFreestyleProject("A");
        okButton();
        saveButton();
        homePage();

        startFreestyleProject("NJ3");
        copyFrom("NJ4");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
        getDriver().findElement(By.id("jenkins-home-link")).click();
        homePage();
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        createBaseFreestyleProject();

        startFreestyleProject("NJ2");
        copyFrom(NAME);

        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.name(DESCRIPTION_FIELD))).isDisplayed();

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);
        asserts.assertEquals(getDriver().findElement(By.name(GITHUB_URL)).getAttribute("value"), URL_INPUT);
        asserts.assertAll();
    }
}
