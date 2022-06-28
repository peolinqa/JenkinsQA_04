import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

public class _MyViewTest extends BaseTest {
    private static final String VIEW_NAME = TestUtils.getRandomStr();
    private static final String EDIT_VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_DESCRIPTION = TestUtils.getRandomStr();

    private final By VIEW_NAMES_ON_TABBAR = By.cssSelector("div .tab a");
    private final By VIEW_NAMES_ON_BREADCRUMBS = By.xpath("//ul[@id='breadcrumbs']/li[@class='item']");

    private WebElement textareaDescription() {
        return getDriver().findElement(By.xpath("//textarea[contains(@name, 'description')]"));
    }

    private WebElement fieldDescriptionOnThePage() {
        return getDriver().findElement(By.xpath("//div[@id='description']/div[not(@class)]"));
    }

    private WebElement buttonPreview() {
        return getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']"));
    }

    private WebElement textareaPreview() {
        return getDriver().findElement(By.xpath("//div[@class='textarea-preview']"));
    }

    private WebElement buttonHidePreview() {
        return getDriver().findElement(By.xpath("//a[@class='textarea-hide-preview']"));
    }

    private WebElement buttonSubmitDelete() {
        return getDriver().findElement(By.id("yui-gen1-button"));
    }

    private WebElement buttonSubmitEdit() {
        return getDriver().findElement(By.id("yui-gen2-button"));
    }

    private WebElement buttonSubmitDescription() {
        return getDriver().findElement(By.id("yui-gen13-button"));
    }

    public static void clickAddOrEditDescriptionButton(WebDriver driver) {
        driver.findElement(By.xpath("//a[contains(@href, 'editDescription')]")).click();
    }

    private void clickNameOfViewOnBreadcrumbs() {
        getDriver().findElement(VIEW_NAMES_ON_BREADCRUMBS).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + VIEW_NAME + "')]")).click();
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());

        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'List View']")).click();

        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonSubmitDescription().click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Ignore
    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'My View']")).click();

        getDriver().findElement(By.id("ok")).click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME));
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckTabBar() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.NewItemTypes.FreestyleProject);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());

        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'List View']")).click();

        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonSubmitDescription().click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Ignore
    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs"})
    public void testCreateNewViewWithAnExistingName() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());
        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'My View']")).click();

        Assert.assertEquals(getDriver().findElement(By.className("error")).getText(), "A view already exists with the name " + '"' + VIEW_NAME + '"');
    }
    @Ignore
    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs"})
    public void testEditViewChangeName() {
        clickNameOfViewOnBreadcrumbs();

        ProjectUtils.Dashboard.View.EditView.click(getDriver());
        TestUtils.clearAndSend(getDriver(), By.name("name"), EDIT_VIEW_NAME);
        buttonSubmitEdit().click();

        Assert.assertEquals(EDIT_VIEW_NAME, getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).getText());
    }

    @Test
    public void testAddDescriptionOnMyViews() {
        ProjectUtils.Dashboard.Main.MyViews.click(getDriver());

        clickAddOrEditDescriptionButton(getDriver());

        textareaDescription().sendKeys(VIEW_NAME);
        ProjectUtils.clickSaveButton(getDriver());

        Assert.assertEquals(fieldDescriptionOnThePage().getText(), VIEW_NAME);
    }

    @Test(dependsOnMethods = {"testAddDescriptionOnMyViews"})
    public void testEditDescriptionOnMyViews() {
        ProjectUtils.Dashboard.Main.MyViews.click(getDriver());

        clickAddOrEditDescriptionButton(getDriver());

        textareaDescription().clear();
        textareaDescription().sendKeys(VIEW_NAME);
        ProjectUtils.clickSaveButton(getDriver());

        Assert.assertEquals(fieldDescriptionOnThePage().getText(), VIEW_NAME);
    }

    @Test(dependsOnMethods = {"testEditDescriptionOnMyViews"})
    public void testCheckButtonPreviewDescriptionOnMyViews() {
        ProjectUtils.Dashboard.Main.MyViews.click(getDriver());

        clickAddOrEditDescriptionButton(getDriver());

        buttonPreview().isDisplayed();
        buttonPreview().click();

        textareaPreview().isDisplayed();
        Assert.assertEquals(textareaPreview().getText(), textareaDescription().getText());
    }

    @Test(dependsOnMethods = {"testCheckButtonPreviewDescriptionOnMyViews"})
    public void testCheckButtonHidePreviewDescriptionOnMyViews() {
        ProjectUtils.Dashboard.Main.MyViews.click(getDriver());

        clickAddOrEditDescriptionButton(getDriver());

        buttonPreview().click();
        textareaPreview().isDisplayed();
        Assert.assertEquals(textareaPreview().getText(), textareaDescription().getText());
        Assert.assertTrue(buttonHidePreview().isDisplayed());

        buttonHidePreview().click();
        Assert.assertFalse(textareaPreview().isDisplayed());
        Assert.assertFalse(buttonHidePreview().isDisplayed());
    }

    @Ignore
    @Test(dependsOnMethods = {"testEditViewChangeName"})
    public void testDeleteViewViaBreadcrumbs() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).click();

        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonSubmitDelete().click();

        Assert.assertFalse(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs"})
    public void testDeleteViewViaTabBarFrame() {
        getDriver().findElement(By.xpath("//div/a[contains(text(),'" + VIEW_NAME + "')]")).click();
        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonSubmitDelete().click();

        Assert.assertFalse(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
    }
}
