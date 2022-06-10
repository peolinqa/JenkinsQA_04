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

    private final String NEW_ITEM_BUTTON = "//a[@class='task-link ']";
    private final String NAME_FIELD = "name";
    private final String FREESTYLE_PROJECT = "//li[@tabindex='0']";
    private final String OK_BUTTON = "ok-button";
    private final String DESCRIPTION_FIELD = "description";
    private final String GITHUB_PROJECT_MARK = "cb5";
    private final String PROJECT_URL_FIELD = "_.projectUrlStr";
    private final String TIMESTAMPS_MARK = "cb24";
    private final String SAVE_BUTTON = "yui-gen25-button";
    private final String NAME = "NJ";
    private final String NAME2 = "NJ2";
    private final String DESCRIPTION_INPUT = "New Project created by TA";
    private final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    private String startPage;
    protected void saveStartPage() {
        super.beforeMethod();
        startPage = getDriver().getCurrentUrl();
    }

    protected void goToStartPage() {
        getDriver().get(startPage);
    }

    public void createBaseFreestyleProject() {
        getDriver().findElement(By.xpath(NEW_ITEM_BUTTON)).click();
        getDriver().findElement(By.id(NAME_FIELD)).sendKeys(NAME);
        getDriver().findElement(By.xpath(FREESTYLE_PROJECT)).click();

        Actions action = new Actions(getDriver());

        WebElement okButton = getDriver().findElement(By.id(OK_BUTTON));
        action.moveToElement(okButton).perform();
        okButton.click();

        getDriver().findElement(By.name(DESCRIPTION_FIELD)).sendKeys(DESCRIPTION_INPUT);

        WebElement githubMark = getDriver().findElement(By.id(GITHUB_PROJECT_MARK));
        action.moveToElement(githubMark).click().build().perform();

        WebElement projectUrl = getDriver().findElement(By.name(PROJECT_URL_FIELD));
        action.moveToElement(projectUrl).click().build().perform();
        projectUrl.sendKeys(URL_INPUT);

        WebElement timeStampMark = getDriver().findElement(By.id(TIMESTAMPS_MARK));
        action.moveToElement(timeStampMark).click().build().perform();

        WebElement saveButton = getDriver().findElement(By.id(SAVE_BUTTON));
        action.moveToElement(saveButton).click().build().perform();

        goToStartPage();
    }

//    private void deleteProject(String projectName) {
//        String currentUrl = getDriver().getCurrentUrl();
//        getDriver().get(currentUrl + "job/" + projectName);
//
//        if (!getDriver().findElement(By.xpath("/html/body/h2")).isDisplayed()) {
//            Actions action = new Actions(getDriver());
//            WebElement delete = getDriver().findElement(By.xpath("//div[@id='tasks']/div[7]/span/a"));
//            action.moveToElement(delete).perform();
//            delete.click();
//            getDriver().switchTo().alert().accept();
//        }
//    }


    @Test
    public void testCopyDataFromExistingItemPositive() {
//        saveStartPage();
//        deleteProject("NJ");
//        goToStartPage();
//        deleteProject("NJ2");
//
//        goToStartPage();
        createBaseFreestyleProject();

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
        asserts.assertTrue(getDriver().findElement(By.name(PROJECT_URL_FIELD)).isDisplayed());
//        asserts.assertEquals(getDriver().findElement(By.name(PROJECT_URL_FIELD)).getText(), URL_INPUT);
//        WebElement mark = getDriver().findElement(By.xpath("//div[@nameref='cb24']"));
//        asserts.assertEquals(mark.getCssValue("class"), "rowvg-start tr");
        asserts.assertAll();

        getDriver().findElement(By.id(SAVE_BUTTON)).click();

//        goToStartPage();
//        asserts.assertTrue(getDriver().findElement(By.xpath("//a[@href ='job/\" + NAME2 + \"/']")).isDisplayed());


    }
}
