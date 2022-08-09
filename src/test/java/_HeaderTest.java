import model.*;
import model.base.BaseHeaderFooterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class _HeaderTest extends BaseTest {

    private final String logoIconAttribute = "/images/svgs/logo.svg";
    private final String pageHeaderLocation = "(0, 0)";

    @Test
    public void testIsHeaderDisplayedOnTopOnMainPage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.topPageHeaderIsVisible());
        Assert.assertEquals(newHomePage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnNewItemPage() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        Assert.assertTrue(newItemPage.topPageHeaderIsVisible());
        Assert.assertEquals(newItemPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople();

        Assert.assertTrue(newPeoplePage.topPageHeaderIsVisible());
        Assert.assertEquals(newPeoplePage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.topPageHeaderIsVisible());
        Assert.assertEquals(newBuildHistoryPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.topPageHeaderIsVisible());
        Assert.assertEquals(newManageJenkinsPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView();

        Assert.assertTrue(newMyViewPage.topPageHeaderIsVisible());
        Assert.assertEquals(newMyViewPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnNewViewsPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView();

        Assert.assertTrue(newNewViewPage.topPageHeaderIsVisible());
        Assert.assertEquals(newNewViewPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @DataProvider(name = "clickSideMenu")
    public Object[][] clickSideMenu() {
        return Stream.<Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>>>of(
                HomePageSideMenuFrame::clickMenuNewItem,
                HomePageSideMenuFrame::clickMenuPeople,
                HomePageSideMenuFrame::clickMenuBuildHistory,
                HomePageSideMenuFrame::clickMenuManageJenkins,
                HomePageSideMenuFrame::clickMenuMyView,
                HomePageSideMenuFrame::clickMenuNewView
        ).map(item -> new Object[]{item}).toArray(Object[][]::new);
    }

    @Test(dataProvider = "clickSideMenu")
    public void testHeaderLogoIsClickableOnAllPagesToHomepage(Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>> click) {
        click.apply(new HomePage(getDriver()).getSideMenu())
                .clickJenkins()
                .assertEquals(HomePage::isTitleDashboardJenkins, true);
    }

    @Test(dataProvider = "clickSideMenu")
    public void testHeaderPositionOfElementsUI(Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>> click) {
        click.apply(new HomePage(getDriver()).getSideMenu())
                .assertEquals(BaseHeaderFooterPage::isRightPositionOfHeaderElementsUI, true);
    }

    @Test
    public void testVerifyImageOrderOnAllPages() {
        HomePageSideMenuFrame homePageMenu = new HomePageSideMenuFrame(getDriver());

        List<Supplier<? extends BaseHeaderFooterPage>> menuCallList = List.of(
                homePageMenu::clickMenuNewItem,
                homePageMenu::clickMenuPeople,
                homePageMenu::clickMenuBuildHistory,
                homePageMenu::clickMenuManageJenkins,
                homePageMenu::clickMenuMyView,
                homePageMenu::clickMenuNewView);

        for (Supplier<? extends BaseHeaderFooterPage> method : menuCallList) {
            BaseHeaderFooterPage newPage = method.get();

            Assert.assertTrue(newPage.isRightPositionOfJenkinsHeadIcon());

            newPage.goHome();
        }
    }

    @Test
    public void testLogoIconIsViewedOnMainPage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.headerIconIsVisible());
        Assert.assertTrue(newHomePage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnNewItemPage() {
        NewItemPage<Object> newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        Assert.assertTrue(newItemPage.headerIconIsVisible());
        Assert.assertTrue(newItemPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople();

        Assert.assertTrue(newPeoplePage.headerIconIsVisible());
        Assert.assertTrue(newPeoplePage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.headerIconIsVisible());
        Assert.assertTrue(newBuildHistoryPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.headerIconIsVisible());
        Assert.assertTrue(newManageJenkinsPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView();

        Assert.assertTrue(newMyViewPage.headerIconIsVisible());
        Assert.assertTrue(newMyViewPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testLogoIconIsViewedOnNewViewPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView();

        Assert.assertTrue(newNewViewPage.headerIconIsVisible());
        Assert.assertTrue(newNewViewPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testHeaderLogoIsImage() {
        HomePage headerLogoIsImage = new HomePage(getDriver());

        Assert.assertEquals(headerLogoIsImage.getLogoIconTagName(), "img");
    }

    @Test
    public void testHeaderLogoImageExtensionIsSvg() {
        HomePage headerLogoIsSvg = new HomePage(getDriver());

        Assert.assertTrue(headerLogoIsSvg.getLogoIconAttribute("src").contains(".svg"));
    }

    @Test
    public void testHeaderDesignUI() {
        HomePage pageHeader = new HomePage(getDriver());

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(pageHeader.getPageHeaderCssValueBackgroundColor(), "rgba(0, 0, 0, 1)");
        asserts.assertEquals(pageHeader.getPageHeaderCssValueDisplay(), "flex");
        asserts.assertEquals(pageHeader.getPageHeaderCssValueHeight(), "56px");
        asserts.assertEquals(pageHeader.getPageHeaderCssValueAlignItems(), "center");

        asserts.assertAll();
    }

    @Test
    public void testCheckSearchPanel() {
        String actualResult = new HomePage(getDriver())
                .sendTextSearchPanel("TryToFindSomething")
                .getSearchMainPanelText();

        Assert.assertEquals(actualResult, "Search for 'TryToFindSomething'");
    }

    @Test
    public void testCheckAdminPage() {
        String userIDLine = new HomePage(getDriver())
                .clickUserAndGoToUserPage()
                .getJenkinsUserIDLine();

        Assert.assertTrue(userIDLine.contains("Jenkins User ID:"));
    }

    @Test
    public void testCheckExpandMenuBuilds() {
        String userBuilds = new HomePage(getDriver())
                .clickDropDownMenu()
                .selectMenuBuildsAndGoToBuildsPage()
                .getTextName();

        Assert.assertEquals(userBuilds, "Builds for admin");
    }

    @Test
    public void testCheckExpandMenuConfigure() {
        String buttonText = new HomePage(getDriver())
                .clickDropDownMenu()
                .selectMenuConfigureAndGoToConfigurePage()
                .getGen2ButtonText();

        Assert.assertEquals(buttonText, "Add new Token");
    }

    @Test
    public void testCheckExpandMenuMyViews() {
        String myViewsText = new HomePage(getDriver())
                .clickDropDownMenu()
                .selectMenuMyViewAndGoToMyViewPage()
                .getTextMyView();

        Assert.assertEquals(myViewsText, "My Views");
    }
}