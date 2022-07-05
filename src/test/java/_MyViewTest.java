import model.HomePage;
import model.MyViewPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.List;

import static runner.ProjectUtils.ProjectType.Freestyle;


public class _MyViewTest extends BaseTest {
    private static final String VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_NAME_1 = "My View";
    private static final String VIEW_NAME_2 = "Fox";
    private static final String EDIT_VIEW_NAME = "Fox1";
    private static final String VIEW_DESCRIPTION = TestUtils.getRandomStr();

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setProjectType(Freestyle)
                .createAndGoHome()
                .clickNewView()
                .setViewName(VIEW_NAME_1)
                .selectListViewType()
                .createViewAndGoConfig()
                .setDescription(VIEW_DESCRIPTION)
                .saveConfigAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(VIEW_NAME_1));
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .clickNewView()
                .setViewName(VIEW_NAME_2)
                .selectMyViewType()
                .createViewAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(VIEW_NAME_2));
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckTabBar() {
        final String viewName3 = TestUtils.getRandomStr();

        MyViewPage myViewPage = new HomePage(getDriver())
                .clickNewView()
                .setViewName(viewName3)
                .selectListViewType()
                .createViewAndGoConfig()
                .setDescription(VIEW_DESCRIPTION)
                .saveConfigAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnTabBar().contains(viewName3));
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckTabBar() {
        final String viewName4 = TestUtils.getRandomStr();

        MyViewPage myViewPage = new HomePage(getDriver())
                .clickNewView()
                .setViewName(viewName4)
                .selectMyViewType()
                .createViewAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnTabBar().contains(viewName4));
    }

   @Ignore
    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs")
    public void testCreateNewViewWithAnExistingName() {
        String errorText = new HomePage(getDriver())
                .clickNewView()
                .setViewName(VIEW_NAME_2)
                .selectMyViewType()
                .getErrorText();

        Assert.assertEquals(errorText, "A view already exists with the name " + '"' + VIEW_NAME_2 + '"');
    }

    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs")
    public void testEditViewChangeName() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .clickNameOfViewOnBreadcrumbs(VIEW_NAME_2)
                .clickEditView()
                .setName(EDIT_VIEW_NAME)
                .saveConfigAndGoToView1();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(EDIT_VIEW_NAME));
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

    @Test(dependsOnMethods = "testEditViewChangeName")
    public void testDeleteViewViaBreadcrumbs() {
        List<String> viewsOnBreadcrumbs = new HomePage(getDriver())
                .clickNameOfViewOnBreadcrumbs(EDIT_VIEW_NAME)
                .clickDeleteView()
                .clickSubmitDeleteViewAndGoHome()
                .getNamesOfViewsOnBreadcrumbs();

        Assert.assertFalse(viewsOnBreadcrumbs.contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs")
    public void testDeleteViewViaTabBarFrame() {
        List<String> viewOnTabBar = new HomePage(getDriver())
                .clickNameOfViewOnTabBar(VIEW_NAME_1)
                .clickDeleteView()
                .clickSubmitDeleteViewAndGoHome()
                .clickNameOfViewOnTabBar()
                .getNamesOfViewsOnTabBar();

        Assert.assertFalse(viewOnTabBar.contains(VIEW_NAME_1));
    }
}
