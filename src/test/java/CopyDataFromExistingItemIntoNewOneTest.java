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
    private final String TIMESTAMPS_MARK = "pseudoRemoteTrigger";
    private final String SAVE_BUTTON = "yui-gen25-button";
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
        action.moveToElement(okButton).build().perform();
        okButton.click();

        getDriver().findElement(By.name(DESCRIPTION_FIELD)).sendKeys(DESCRIPTION_INPUT);

        action.moveToElement(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div/div/div/form/div[1]/div[9]/div[1]/div/div"))
        ).build().perform();

        WebElement buildTriggerMark = getWait20().until(
                ExpectedConditions.elementToBeClickable(getDriver().findElement(By.name(TIMESTAMPS_MARK)))
        );
        buildTriggerMark.click();

        WebElement projectUrl = getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS));
        action.moveToElement(projectUrl).build().perform();
        projectUrl.sendKeys(TOKEN_NAME);

        action.moveToElement(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div/div/div/form/div[1]/div[10]/div[1]/div/div"))
        ).build().perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name(TIMESTAMPS_MARK))).click();

        WebElement saveButton = getDriver().findElement(By.id(SAVE_BUTTON));
        action.moveToElement(saveButton).build().perform();
        saveButton.click();
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
        action.moveToElement(copFromButton).build().perform();
        copFromButton.sendKeys(NAME);
        getDriver().findElement(By.id(OK_BUTTON)).click();

        SoftAssert asserts = new SoftAssert();

        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);

        //asserts.assertTrue(getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS)).isDisplayed());
        //asserts.assertEquals(getDriver().findElement(By.name(INPUT_BUILD_TRIGGERS)).getAttribute("value"), TOKEN_NAME);

        action.moveToElement(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div/div/div/form/div[1]/div[10]/div[1]/div/div"))
        ).build().perform();

//        asserts.assertTrue(
//                getDriver().findElement(
//                        By.name("hudson-plugins-timestamper-TimestamperBuildWrapper")
//                ).isSelected()
//        );
        asserts.assertAll();

        WebElement saveButton = getDriver().findElement(By.id(SAVE_BUTTON));
        action.moveToElement(saveButton).build().perform();
        saveButton.click();
    }
}
