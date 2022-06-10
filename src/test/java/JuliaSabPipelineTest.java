import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

public class JuliaSabPipelineTest extends BaseTest {
    private static final By XPATH_NEW_ITEM = By.xpath("//span[contains(text(), 'New Item')]");
    private static final By XPATH_ENTER_AN_ITEM_NAME = By.xpath("//input[@id='name']");
    private static final By XPATH_PIPELINE_LABEL = By.xpath("//span[text()='Pipeline']");
    private static final By XPATH_OK_BUTTON = By.xpath("//button[@id='ok-button']");
    private static final By XPATH_TEXT_AREA_DESCRIPTION = By.xpath("//textarea[@name='description']");
    private static final By XPATH_APPLY_BUTTON = By.xpath("//button[contains(text(), 'Apply')]");
    private static final By XPATH_SAVE_BUTTON = By.xpath("//button[contains(text(), 'Save')]");
    private static final By XPATH_BACK_TO_DASHBOARD = By.xpath("//span[text()='Back to Dashboard']");
    private static final By XPATH_JOB_DESCRIPTION = By.xpath("//div[@id='description']/div[1]");
    private static final By XPATH_DISAPPEARING_BUTTON = By.xpath("//div[@id='menuSelector']");
    private static final By XPATH_PREVIEW_BUTTON = By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']");
    private static final By XPATH_HIDE_PREVIEW_BUTTON = By.xpath("//a[text()='Hide preview']");
    private static final By XPATH_TEXT_AREA_PREVIEW = By.xpath("//a[text()='Hide preview']//following-sibling::div");
    private static final By XPATH_PIPELINE_SYNTAX = By.xpath("//span[text()='Pipeline Syntax']");
    private static final By XPATH_SAMPLE_STEP_DROP_DOWN = By.xpath("//select");
    private static final By XPATH_ERROR_OPTION = By.xpath("//option[text()='error: Error signal']");
    private static final By XPATH_ICON_TOOLTIP = By.xpath("//div/a[@tooltip='Help for feature: error']");
    private static final By XPATH_HIDDEN_TEXT_OPT_ERROR = By.xpath("//div[contains(text(), 'Signals an error')]");
    private static final By XPATH_GENERATE_SCRIPT_BUTTON = By.xpath("//button[@id='yui-gen1-button']");

    @Test
    public void testCheckNameAndDescriptionForPipeline025001() {
        WebElement pipeline = getDriver()
                .findElement(By.xpath("//td[3]/a[text()='First Pipeline']"));
        String actualNameResult = pipeline.getText();
        Assert.assertEquals(actualNameResult, "First Pipeline");

        pipeline.click();
        String actualDescResult = (getDriver().findElement(XPATH_JOB_DESCRIPTION).getText());
        Assert.assertEquals(actualDescResult, "First test");
    }

    @Test
    public void testEditNewDescriptionPipeline025001() {
        selectItemFromDropDownMenu("First Pipeline", "Configure");
        getDriver().findElement(XPATH_TEXT_AREA_DESCRIPTION).clear();
        getDriver().findElement(XPATH_TEXT_AREA_DESCRIPTION).sendKeys("new description");
        getDriver().findElement(XPATH_APPLY_BUTTON).click();
        getDriver().navigate().refresh();
        String actualResult = getDriver().findElement(XPATH_TEXT_AREA_DESCRIPTION).getText();

        Assert.assertEquals(actualResult, "new description");
    }

    @Test
    public void testCheckPreviewAndHideDescription025001() {
        selectItemFromDropDownMenu("First Pipeline", "Configure");
        if (!getDriver().findElement(XPATH_HIDE_PREVIEW_BUTTON).isDisplayed()) {
            Assert.assertFalse(getDriver().findElement(XPATH_HIDE_PREVIEW_BUTTON).isDisplayed());

            getDriver().findElement(XPATH_PREVIEW_BUTTON).click();
            Assert.assertTrue(getDriver().findElement(XPATH_HIDE_PREVIEW_BUTTON).isDisplayed());
        } else {
            Assert.assertTrue(getDriver().findElement(XPATH_HIDE_PREVIEW_BUTTON).isDisplayed());
        }

        String actualResult = getDriver().findElement(XPATH_TEXT_AREA_PREVIEW).getText();
        Assert.assertEquals(actualResult, "First test");
    }

    @Test
    public void testCheckIconWithTip023002() {
        findAndChooseErrorOptInSampleStep();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_ICON_TOOLTIP));
        String actualResultTip = getDriver().findElement(XPATH_ICON_TOOLTIP).getAttribute("tooltip");
        getDriver().findElement(XPATH_ICON_TOOLTIP).click();

        Assert.assertEquals(actualResultTip, "Help for feature: error");
    }

    @Test
    public void testCheckClickIconWithTipCheckElementIsDisplaying023002() {
        findAndChooseErrorOptInSampleStep();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_ICON_TOOLTIP));
        getDriver().findElement(XPATH_ICON_TOOLTIP).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_HIDDEN_TEXT_OPT_ERROR));
        Assert.assertTrue(getDriver()
                .findElement(XPATH_HIDDEN_TEXT_OPT_ERROR)
                .isDisplayed());

        getDriver().findElement(XPATH_ICON_TOOLTIP).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(XPATH_HIDDEN_TEXT_OPT_ERROR));
        Assert.assertFalse(getDriver()
                .findElement(XPATH_HIDDEN_TEXT_OPT_ERROR)
                .isDisplayed());
    }

    @Test
    public void testCheckHiddenTextBelowIconWithTipAfterClick023002() {
        findAndChooseErrorOptInSampleStep();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_ICON_TOOLTIP));
        getDriver().findElement(XPATH_ICON_TOOLTIP).click();

        String expectedResult = "Signals an error. Useful if you want to conditionally abort some part of your program. " +
                "You can also just throw new Exception(), but this step will avoid printing a stack trace.";
        String actualResult = getDriver().findElement(XPATH_HIDDEN_TEXT_OPT_ERROR).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckGenerateScript023001() {
        findAndChooseErrorOptInSampleStep();
        getDriver().findElement(By.xpath("//input[@name='_.message']"))
                .sendKeys("an error has been detected");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_GENERATE_SCRIPT_BUTTON));
        getDriver().findElement(XPATH_GENERATE_SCRIPT_BUTTON).click();

        String actualResult = getDriver()
                .findElement(By.xpath("//script[contains(text(), 'function')]")).getAttribute("innerHTML");
        Assert.assertTrue(actualResult.contains("responseText"));
    }

    @BeforeMethod
    protected void setUp() {
        super.beforeMethod();
        createNewPipeline("First Pipeline", "First test");
    }

    @AfterMethod
    protected void setDown() {
        deletePipeline("First Pipeline", "Delete Pipeline");
    }

    private void findAndChooseErrorOptInSampleStep() {
        getDriver().findElement(By.xpath("//td[3]/a[text()='First Pipeline']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_PIPELINE_SYNTAX));
        getDriver().findElement(XPATH_PIPELINE_SYNTAX).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_SAMPLE_STEP_DROP_DOWN));
        getDriver().findElement(XPATH_SAMPLE_STEP_DROP_DOWN).click();
        getDriver().findElement(XPATH_ERROR_OPTION).click();
    }

    private void createNewPipeline(String name, String description) {
        getDriver().findElement(XPATH_NEW_ITEM).click();
        getDriver().findElement(XPATH_PIPELINE_LABEL).click();
        getDriver().findElement(XPATH_ENTER_AN_ITEM_NAME).sendKeys(name);
        getDriver().findElement(XPATH_OK_BUTTON).click();
        getDriver().findElement(XPATH_TEXT_AREA_DESCRIPTION).sendKeys(description);
        getDriver().findElement(XPATH_SAVE_BUTTON).click();
        getDriver().findElement(XPATH_BACK_TO_DASHBOARD).click();
    }

    private void selectItemFromDropDownMenu(String nameJob, String item) {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver()
                .findElement(By.xpath("//a[text()='" + nameJob + "']"))).build().perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_DISAPPEARING_BUTTON));

        action.moveToElement(getDriver()
                .findElement(XPATH_DISAPPEARING_BUTTON)).click().build().perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + item + "']")));
        getDriver().findElement(By.xpath("//span[text()='" + item + "']")).click();
    }

    private void deletePipeline(String nameJob, String item) {
        selectItemFromDropDownMenu(nameJob, item);
        getDriver().switchTo().alert().accept();
    }
}
