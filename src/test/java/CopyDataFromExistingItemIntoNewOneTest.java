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

    private final String NEW_ITEM_BUTTON = "//a[@title='New Item']";
    private final String NAME_FIELD = "name";
    private final String FREESTYLE_PROJECT = "//li[@tabindex='0']";
    private final String OK_BUTTON = "ok-button";
    private final String DESCRIPTION_FIELD = "description";
    private final String INPUT_BUILD_TRIGGERS = "authToken";
    private final String TOKEN_NAME = "token=TOKEN_1";
    private final String TIMESTAMPS_MARK = "cb20";
    private final String SAVE_BUTTON = "yui-gen13-button";
    private final String NAME = "NJ";
    private final String NAME2 = "NJ2";
    private final String DESCRIPTION_INPUT = "New Project created by TA";
    private final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    public void createBaseFreestyleProject() {
        getDriver().findElement(By.xpath(NEW_ITEM_BUTTON)).click();
        getDriver().findElement(By.id(NAME_FIELD)).sendKeys(NAME);
        getDriver().findElement(By.xpath(FREESTYLE_PROJECT)).click();

        Actions action = new Actions(getDriver());

        WebElement okButton = getDriver().findElement(By.id(OK_BUTTON));
        action.moveToElement(okButton).perform();
        okButton.click();

        getDriver().findElement(By.name(DESCRIPTION_FIELD)).sendKeys(DESCRIPTION_INPUT);

        WebElement buildTriggerMark = getDriver().findElement(By.name("pseudoRemoteTrigger"));
        action.moveToElement(buildTriggerMark).click().build().perform();

        WebElement projectUrl = getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS));
        action.moveToElement(projectUrl).click().build().perform();
        projectUrl.sendKeys(TOKEN_NAME);

//        action.moveToElement(getDriver().findElement(By.id("yui-gen7-button"))).build().perform();
//        getWait5().until(ExpectedConditions.elementToBeClickable(By.id(TIMESTAMPS_MARK))).click();
        action.moveToElement(getDriver().findElement(By.id(SAVE_BUTTON))).click().build().perform();
    }


    @Test
    public void testCopyDataFromExistingItemPositive() {

        createBaseFreestyleProject();

        getDriver().findElement(By.xpath("//a[@title='Back to Dashboard']")).click();
        getDriver().findElement(By.xpath(NEW_ITEM_BUTTON)).click();
        getDriver().findElement(By.id(NAME_FIELD)).sendKeys(NAME2);
        getDriver().findElement(By.xpath(FREESTYLE_PROJECT)).click();

        Actions action = new Actions(getDriver());
        WebElement copFromButton = getDriver().findElement(By.id("from"));
        action.moveToElement(copFromButton).perform();
        copFromButton.sendKeys(NAME);
        getDriver().findElement(By.id(OK_BUTTON)).click();

        SoftAssert asserts = new SoftAssert();

        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);

        asserts.assertTrue(getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS)).isDisplayed());
        asserts.assertEquals(getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS)).getAttribute("value"), TOKEN_NAME);

//        action.moveToElement(getDriver().findElement(By.id("yui-gen7-button"))).build().perform();
//        asserts.assertTrue(getDriver().findElement(By.id(TIMESTAMPS_MARK)).isSelected());
        asserts.assertAll();

        getDriver().findElement(By.id(SAVE_BUTTON)).click();
    }
}
