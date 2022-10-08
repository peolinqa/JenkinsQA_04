import model.home.HomePage;
import model.Views.MyViewPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.List;

public class _MyViewTest extends BaseTest {
    private static final String VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_NAME_1 = TestUtils.getRandomStr();
    private static final String VIEW_NAME_2 = TestUtils.getRandomStr();
    private static final String EDIT_VIEW_NAME = TestUtils.getRandomStr();
    private static final String VIEW_DESCRIPTION = TestUtils.getRandomStr();

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckBreadcrumbs() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(TestUtils.getRandomStr())
                .setFreestyleProjectType()
                .clickOkAndGoToConfig()
                .goHome()
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(VIEW_NAME_1)
                .selectListViewType()
                .createViewAndGoConfig()
                .setDescription(VIEW_DESCRIPTION)
                .saveConfigAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(VIEW_NAME_1));
    }

    @Test (dependsOnMethods = "testRemoveColumnsFromDashboardInOwnWatchlist")
    public void testCreateNewViewWithSelectListViewAndCheckCountJobs() {
        final int countJobs = 2;

        final List<String> listJobsInMyViewName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(String.format("‘%s1", VIEW_NAME))
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(String.format("‘%s2", VIEW_NAME))
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(String.format("‘%s3", VIEW_NAME))
                .setPipelineProjectType()
                .clickOkAndGoToConfig()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(VIEW_NAME_1)
                .selectListViewType()
                .createViewAndGoConfig()
                .chooseJobs(countJobs)
                .clickApplyAndOkAndGoToMyViewPage()
                .getListJobsName();

        Assert.assertEquals(listJobsInMyViewName.size(), countJobs);
    }

    @Test
    public void testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(VIEW_NAME_2)
                .selectMyViewType()
                .createViewAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(VIEW_NAME_2));
    }

    @Test
    public void testCreateNewViewWithSelectLabelListViewCheckTabBar() {
        final String viewName3 = TestUtils.getRandomStr();

        MyViewPage myViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView()
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
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(viewName4)
                .selectMyViewType()
                .createViewAndGoToView();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnTabBar().contains(viewName4));
    }

    @Test(dependsOnMethods = "testDeleteViewViaTabBarFrame")
    public void testCreateNewViewWithSelectListViewAddAllColumns() {
        final int countColumns = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(VIEW_NAME)
                .selectListViewType()
                .createViewAndGoConfig()
                .chooseJobs(1)
                .addAllUniqueColumns()
                .clickApplyAndOkAndGoToMyViewPage()
                .getCountOfColumns();

        Assert.assertEquals(countColumns, 11);
    }

    @Test(dependsOnMethods = "testCreateNewViewWithSelectLabelMyViewCheckBreadcrumbs")
    public void testCreateNewViewWithAnExistingName() {
        String errorText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView()
                .setViewName(VIEW_NAME_2)
                .selectMyViewType()
                .getErrorText();

        Assert.assertEquals(errorText, "A view already exists with the name " + '"' + VIEW_NAME_2 + '"');
    }

    @Test(dependsOnMethods = "testCreateNewViewWithAnExistingName")
    public void testEditViewChangeName() {
        MyViewPage myViewPage = new HomePage(getDriver())
                .selectNameOfViewOnBreadcrumbs(VIEW_NAME_2)
                .getSideMenu()
                .clickMenuEditView()
                .setName(EDIT_VIEW_NAME)
                .saveConfigAndGoToView1();

        Assert.assertTrue(myViewPage.getNamesOfViewsOnBreadcrumbs().contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = "testCreateNewViewWithSelectListViewAddAllColumns")
    public void testRemoveColumnsFromDashboardInOwnWatchlist() {
        final int countColumnsAfterDelete = new HomePage(getDriver())
                .selectNameOfViewOnBreadcrumbs(VIEW_NAME)
                .getSideMenu()
                .clickMenuEditView()
                .scrollPageDown()
                .removeColumns()
                .clickApplyAndOkAndGoToMyViewPage()
                .getCountOfColumns();

        Assert.assertEquals(countColumnsAfterDelete, 1);
    }

    @Test
    public void testAddDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView()
                .clickAddOrEditDescriptionButton()
                .sendTextareaDescription(VIEW_NAME)
                .clickButtonSave();

        Assert.assertEquals(myViewsPage.getTextFromFieldDescriptionOnThePage(), VIEW_NAME);
    }

    @Test(dependsOnMethods = "testAddDescriptionOnMyViews")
    public void testEditDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView()
                .clickAddOrEditDescriptionButton()
                .clearTextareaDescription()
                .sendTextareaDescription(VIEW_NAME)
                .clickButtonSave();

        Assert.assertEquals(myViewsPage.getTextFromFieldDescriptionOnThePage(), VIEW_NAME);
    }

    @Test(dependsOnMethods = "testEditDescriptionOnMyViews")
    public void testCheckButtonPreviewDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView()
                .clickAddOrEditDescriptionButton()
                .assertEquals(MyViewPage::isButtonPreviewDisplayed, true)
                .clickButtonPreview()
                .assertEquals(MyViewPage:: isTextareaPreviewDisplayed, true);

        Assert.assertEquals(myViewsPage.getTextFromTextareaPreview(), myViewsPage.getTextareaDescription());
    }

    @Test(dependsOnMethods = "testCheckButtonPreviewDescriptionOnMyViews")
    public void testCheckButtonHidePreviewDescriptionOnMyViews() {
        MyViewPage myViewsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView()
                .clickAddOrEditDescriptionButton()
                .assertEquals(MyViewPage::isButtonPreviewDisplayed, true)
                .clickButtonPreview()
                .assertEquals(MyViewPage:: isTextareaPreviewDisplayed, true)
                .assertEquals(MyViewPage::isButtonHidePreviewDisplayed, true)
                .clickButtonHidePreview()
                .assertEquals(MyViewPage:: isTextareaPreviewDisplayed, false);

        Assert.assertFalse(myViewsPage.isButtonHidePreviewDisplayed());
    }

    @Test(dependsOnMethods = "testEditViewChangeName")
    public void testDeleteViewViaBreadcrumbs() {
        List<String> viewsOnBreadcrumbs = new HomePage(getDriver())
                .selectNameOfViewOnBreadcrumbs(EDIT_VIEW_NAME)
                .getSideMenu()
                .clickMenuDeleteView()
                .clickSubmitDeleteViewAndGoHome()
                .getListItemNamesOnBreadcrumbs();

        Assert.assertFalse(viewsOnBreadcrumbs.contains(EDIT_VIEW_NAME));
    }

    @Test(dependsOnMethods = "testDeleteViewViaBreadcrumbs")
    public void testDeleteViewViaTabBarFrame() {
        List<String> viewOnTabBar = new HomePage(getDriver())
                .clickNameOfViewOnTabBar(VIEW_NAME_1)
                .getSideMenu()
                .clickMenuDeleteView()
                .clickSubmitDeleteViewAndGoHome()
                .clickNameOfFirstViewOnTabBar()
                .getNamesOfViewsOnTabBar();

        Assert.assertFalse(viewOnTabBar.contains(VIEW_NAME_1));
    }
}
