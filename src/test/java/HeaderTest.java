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

public class HeaderTest extends BaseTest {

    private final String logoIconAttribute = "/images/svgs/logo.svg";
    private final String pageHeaderLocation = "(0, 0)";

    @Test
    public void testIsHeaderDisplayedOnHomePage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.isTopPageHeaderVisible());
        Assert.assertEquals(newHomePage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnNewItemPage() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        Assert.assertTrue(newItemPage.isTopPageHeaderVisible());
        Assert.assertEquals(newItemPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople();

        Assert.assertTrue(newPeoplePage.isTopPageHeaderVisible());
        Assert.assertEquals(newPeoplePage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.isTopPageHeaderVisible());
        Assert.assertEquals(newBuildHistoryPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.isTopPageHeaderVisible());
        Assert.assertEquals(newManageJenkinsPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView();

        Assert.assertTrue(newMyViewPage.isTopPageHeaderVisible());
        Assert.assertEquals(newMyViewPage.getPageHeaderLocation(), pageHeaderLocation);
    }

    @Test
    public void testIsHeaderDisplayedOnNewViewsPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView();

        Assert.assertTrue(newNewViewPage.isTopPageHeaderVisible());
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
    public void testIsHeaderLogoClickableOnAllPages(Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>> click) {
        click.apply(new HomePage(getDriver()).getSideMenu())
                .clickJenkinsName()
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
    public void testIsLogoIconPresentOnHomePage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.isHeaderIconVisible());
        Assert.assertTrue(newHomePage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnNewItemPage() {
        NewItemPage<Object> newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        Assert.assertTrue(newItemPage.isHeaderIconVisible());
        Assert.assertTrue(newItemPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople();

        Assert.assertTrue(newPeoplePage.isHeaderIconVisible());
        Assert.assertTrue(newPeoplePage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.isHeaderIconVisible());
        Assert.assertTrue(newBuildHistoryPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.isHeaderIconVisible());
        Assert.assertTrue(newManageJenkinsPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuMyView();

        Assert.assertTrue(newMyViewPage.isHeaderIconVisible());
        Assert.assertTrue(newMyViewPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsLogoIconPresentOnNewViewPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewView();

        Assert.assertTrue(newNewViewPage.isHeaderIconVisible());
        Assert.assertTrue(newNewViewPage.getLogoIconAttribute("src").contains(logoIconAttribute));
    }

    @Test
    public void testIsHeaderLogoImage() {
        HomePage headerLogoIsImage = new HomePage(getDriver());

        Assert.assertEquals(headerLogoIsImage.getLogoIconTagName(), "img");
    }

    @Test
    public void testIsHeaderLogoImageExtensionSvg() {
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
                .cleanAndSearchText("TryToFindSomething")
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
                .clickUserDropDownMenu()
                .clickMenuSelectorBuilds()
                .getUserNameText();

        Assert.assertEquals(userBuilds, "Builds for admin");
    }

    @Test
    public void testCheckExpandMenuConfigure() {
        String buttonText = new HomePage(getDriver())
                .clickUserDropDownMenu()
                .clickMenuSelectorUserConfigure()
                .getGen2ButtonText();

        Assert.assertEquals(buttonText, "Add new Token");
    }

    @Test
    public void testCheckExpandMenuMyViews() {
        String myViewsText = new HomePage(getDriver())
                .clickUserDropDownMenu()
                .clickMenuSelectorMyView()
                .getTextMyView();

        Assert.assertEquals(myViewsText, "My Views");
    }

}
