import model.HomePage;
import model.MyViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;


public class _MyViewTest extends BaseTest {
    private static final String VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_NAME_1 = TestUtils.getRandomStr();
    private static final String VIEW_NAME_2 = TestUtils.getRandomStr();
    private static final String EDIT_VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_DESCRIPTION = TestUtils.getRandomStr();

    private final By VIEW_NAMES_ON_TABBAR = By.cssSelector("div .tab a");
    private final By VIEW_NAMES_ON_BREADCRUMBS = By.xpath("//ul[@id='breadcrumbs']/li[@class='item']");

    private WebElement fieldDescriptionOnThePage() {
        return getDriver().findElement(By.xpath("//div[@id='description']/div[not(@class)]"));
    }

    private WebElement buttonSubmitDeleteView() {
        return getDriver().findElement(By.id("yui-gen1-button"));
    }

    private WebElement buttonSubmitEditListViewName() {
        return getDriver().findElement(By.id("yui-gen13-button"));
    }

    private void clickNameOfViewOnBreadcrumbs() {
        getDriver().findElement(VIEW_NAMES_ON_BREADCRUMBS).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + VIEW_NAME + "')]")).click();
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.Freestyle);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());

        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'List View']")).click();

        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonSubmitEditListViewName().click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME_1);
        getDriver().findElement(By.xpath("//label[text() = 'My View']")).click();

        getDriver().findElement(By.id("ok")).click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(VIEW_NAME_1));
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckTabBar() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[text() = 'List View']")).click();

        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.name("description")).sendKeys(VIEW_DESCRIPTION);
        buttonSubmitEditListViewName().click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
        Assert.assertEquals(VIEW_DESCRIPTION, fieldDescriptionOnThePage().getText());
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckTabBar() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME_2);
        getDriver().findElement(By.xpath("//label[text() = 'My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertTrue(TestUtils.getTextFromList(getDriver(),VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME_2));
    }


    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs")
    public void testCreateNewViewWithAnExistingName() {
        ProjectUtils.Dashboard.View.NewView.click(getDriver());

        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME_1);
        getDriver().findElement(By.xpath("//label[text() = 'My View']")).click();

        Assert.assertEquals(getDriver().findElement(By.className("error")).getText(), "A view already exists with the name " + '"' + VIEW_NAME_1 + '"');
    }

    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs")
    public void testEditViewChangeName() {
        clickNameOfViewOnBreadcrumbs();

        ProjectUtils.Dashboard.View.EditView.click(getDriver());
        TestUtils.clearAndSend(getDriver(), By.name("name"), EDIT_VIEW_NAME);
        buttonSubmitEditListViewName().click();

        Assert.assertEquals(EDIT_VIEW_NAME, getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).getText());
    }

    @Test
    public void testAddDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .clickMyView()
                .clickAddOrEditDescriptionButton()
                .sendTextareaDescription(VIEW_NAME)
                .clickButtonSave();

        Assert.assertEquals(myViewsPage.getTextFromFieldDescriptionOnThePage(), VIEW_NAME);
    }

    @Test(dependsOnMethods = "testAddDescriptionOnMyViews")
    public void testEditDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .clickMyView()
                .clickAddOrEditDescriptionButton()
                .clearTextareaDescription()
                .sendTextareaDescription(VIEW_NAME)
                .clickButtonSave();

        Assert.assertEquals(myViewsPage.getTextFromFieldDescriptionOnThePage(), VIEW_NAME);
    }

    @Test(dependsOnMethods = "testEditDescriptionOnMyViews")
    public void testCheckButtonPreviewDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .clickMyView()
                .clickAddOrEditDescriptionButton()
                .clickButtonPreview();

        Assert.assertTrue(myViewsPage.getButtonPreview().isDisplayed());
        Assert.assertTrue(myViewsPage.getTextareaPreview().isDisplayed());
        Assert.assertEquals(myViewsPage.getTextFromTextareaPreview(), myViewsPage.getTextareaDescription());
    }

    @Test(dependsOnMethods = "testCheckButtonPreviewDescriptionOnMyViews")
    public void testCheckButtonHidePreviewDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .clickMyView()
                .clickAddOrEditDescriptionButton()
                .clickButtonPreview();

        Assert.assertTrue(myViewsPage.getTextareaPreview().isDisplayed());
        Assert.assertEquals(myViewsPage.getTextFromTextareaPreview(),
                myViewsPage.getTextareaDescription());
        Assert.assertTrue(myViewsPage.getButtonHidePreview().isDisplayed());

        myViewsPage.clickButtonHidePreview();

        Assert.assertFalse(myViewsPage.getTextareaPreview().isDisplayed());
        Assert.assertFalse(myViewsPage.getButtonHidePreview().isDisplayed());
    }

    @Test(dependsOnMethods = {"testEditViewChangeName"})
    public void testDeleteViewViaBreadcrumbs() {
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li[@class='children']")).click();
        getDriver().findElement(By.xpath("//li/a[contains(@href, '" + EDIT_VIEW_NAME + "')]")).click();

        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonSubmitDeleteView().click();

        Assert.assertFalse(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_BREADCRUMBS).contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = {"testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs"})
    public void testDeleteViewViaTabBarFrame() {
        getDriver().findElement(By.xpath("//div/a[contains(text(),'" + VIEW_NAME + "')]")).click();
        ProjectUtils.Dashboard.View.DeleteView.click(getDriver());
        buttonSubmitDeleteView().click();

        Assert.assertFalse(TestUtils.getTextFromList(getDriver(), VIEW_NAMES_ON_TABBAR).contains(VIEW_NAME));
    }
}
