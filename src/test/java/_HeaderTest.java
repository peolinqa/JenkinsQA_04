import model.*;
import model.base.BasePage;
import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private static List<WebElement> getMenuItems(WebDriver driver) {
        return driver.findElements(By.xpath("//div[@class='task ']//a"));
    }

    public void verifyPositionOfElements(By locator, String attribute, String... expectedResult) {
        List<WebElement> elementList = getDriver().findElements(locator);

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotNull(elementList);
        asserts.assertEquals(elementList.size(), expectedResult.length);

        for (int i = 0; i < expectedResult.length; i++) {
            asserts.assertEquals(elementList.get(i).getAttribute(attribute), expectedResult[i]);
        }
        asserts.assertAll();
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnMainPage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.topPageHeaderIsVisible());
        Assert.assertEquals(newHomePage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnNewItemPage() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        Assert.assertTrue(newItemPage.topPageHeaderIsVisible());
        Assert.assertEquals(newItemPage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickPeople();

        Assert.assertTrue(newPeoplePage.topPageHeaderIsVisible());
        Assert.assertEquals(newPeoplePage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.topPageHeaderIsVisible());
        Assert.assertEquals(newBuildHistoryPage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.topPageHeaderIsVisible());
        Assert.assertEquals(newManageJenkinsPage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMyView();

        Assert.assertTrue(newMyViewPage.topPageHeaderIsVisible());
        Assert.assertEquals(newMyViewPage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testIsHeaderDisplayedOnTopOnNewViewsPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewView();

        Assert.assertTrue(newNewViewPage.topPageHeaderIsVisible());
        Assert.assertEquals(newNewViewPage.getPageHeaderLocation(), "(0, 0)");
    }

    @Test
    public void testVerifyImageOrderOnAllPages() {
        for (int i = 1; i <= getMenuItems(getDriver()).size(); i++) {
            getDriver().findElement(
                    By.xpath("//div[@class='task '][" + i + "]//a")).click();
            verifyPositionOfElements(By.xpath("//div[@class='logo']/a/img"), "id",
                    "jenkins-head-icon", "jenkins-name-icon");
            getDriver().navigate().back();
        }
    }

    @DataProvider(name = "clickSideMenu")
    public Object[][] clickSideMenu() {
        return Stream.<Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>>>of(
                HomePageSideMenuFrame::clickNewItem,
                HomePageSideMenuFrame::clickPeople,
                HomePageSideMenuFrame::clickBuildHistory,
                HomePageSideMenuFrame::clickManageJenkins,
                HomePageSideMenuFrame::clickMyView,
                HomePageSideMenuFrame::clickNewView
        ).map(item -> new Object[]{item}).toArray(Object[][]::new);
    }

    @Test(dataProvider = "clickSideMenu")
    public void testHeaderLogoIsClickableOnAllPagesToHomepage(Function<HomePageSideMenuFrame, BaseHeaderFooterPage<?>> click) {
        click.apply(new HomePage(getDriver()).getSideMenu())
                .clickJenkins()
                .assertEquals(HomePage::isTitleDashboardJenkins, true);
    }

    @Test
    public void testLogoIconIsViewedOnMainPage() {
        HomePage newHomePage = new HomePage(getDriver());

        Assert.assertTrue(newHomePage.headerIconIsVisible());
        Assert.assertTrue(newHomePage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnNewItemPage() {
        NewItemPage<Object> newItemPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        Assert.assertTrue(newItemPage.headerIconIsVisible());
        Assert.assertTrue(newItemPage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnPeoplePage() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickPeople();

        Assert.assertTrue(newPeoplePage.headerIconIsVisible());
        Assert.assertTrue(newPeoplePage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnBuildHistoryPage() {
        BuildHistoryPage newBuildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertTrue(newBuildHistoryPage.headerIconIsVisible());
        Assert.assertTrue(newBuildHistoryPage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnManageJenkinsPage() {
        ManageJenkinsPage newManageJenkinsPage = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins();

        Assert.assertTrue(newManageJenkinsPage.headerIconIsVisible());
        Assert.assertTrue(newManageJenkinsPage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnMyViewPage() {
        MyViewPage newMyViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMyView();

        Assert.assertTrue(newMyViewPage.headerIconIsVisible());
        Assert.assertTrue(newMyViewPage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
    }

    @Test
    public void testLogoIconIsViewedOnNewViewPage() {
        NewViewPage newNewViewPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewView();

        Assert.assertTrue(newNewViewPage.headerIconIsVisible());
        Assert.assertTrue(newNewViewPage.getLogoIconAttribute("src").contains("/images/svgs/logo.svg"));
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
        asserts.assertEquals(pageHeader.getPageHeaderCssValue("background-color"), "rgba(0, 0, 0, 1)");
        asserts.assertEquals(pageHeader.getPageHeaderCssValue("display"), "flex");
        asserts.assertEquals(pageHeader.getPageHeaderCssValue("height"), "56px");
        asserts.assertEquals(pageHeader.getPageHeaderCssValue("align-items"), "center");

        asserts.assertAll();
    }

    @Test
    public void testHeaderPositionOfElementsUI() {
        HomePageSideMenuFrame homePageSideMenuFrame = new HomePage(getDriver())
                .getSideMenu();

        List<Supplier<? extends BasePage>> slidePanelMenu = List.of(
                homePageSideMenuFrame::clickNewItem,
                homePageSideMenuFrame::clickPeople,
                homePageSideMenuFrame::clickBuildHistory,
                homePageSideMenuFrame::clickPeople,
                homePageSideMenuFrame::clickMyView,
                homePageSideMenuFrame::clickNewView
        );

        NewItemPage headerElement = new NewItemPage(getDriver());

        List<WebElement> headerElementsArray = List.of(
                headerElement.getPageHeaderBrand(),
                headerElement.getSearchboxHidden(),
                headerElement.getHeaderIcon(),
                headerElement.getNameIcon(),
                headerElement.getVisibleAmInsertion(),
                headerElement.getVisibleSecAmInsertion(),
                headerElement.getLogOut());

        for (Supplier<? extends BasePage> method : slidePanelMenu) {
            BasePage basePage = method.get();

            for (WebElement element : headerElementsArray) {
                Assert.assertTrue(element.isDisplayed(), String.format("There is now %s", element.getText()));
            }

            basePage.goHome();
        }
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
                .navigateAndClickDropDownUserMenu()
                .clickBuildsAndGoToBuildsPage()
                .getTextName();

        Assert.assertEquals(userBuilds, "Builds for admin");
    }

    @Test
    public void testCheckExpandMenuConfigure() {
        String buttonText = new HomePage(getDriver())
                .navigateAndClickDropDownUserMenu()
                .clickConfigureAndGoToConfigurePage()
                .getGen2ButtonText();

        Assert.assertEquals(buttonText, "Add new Token");
    }

    @Test
    public void testCheckExpandMenuMyViews() {
        String myViewsText = new HomePage(getDriver())
                .navigateAndClickDropDownUserMenu()
                .clickMyViewAndGoToMyViewPage()
                .getTextMyView();

        Assert.assertEquals(myViewsText, "My Views");
    }
}