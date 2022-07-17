import model.HomePage;
import model.ManagePluginsPage;
import model.PluginManagerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.Collections;
import java.util.List;

public class _ManagePluginsTest extends BaseTest {
    private static final String[] SEARCH_LIST = {"GitHub"};

    @Test
    public void testManagePluginsCheckNameAndArrowUp() {
        String nameArrowDown = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManagePlugins()
                .getTextButtonArrow();

        String nameArrowUp = new ManagePluginsPage(getDriver())
                .clickButtonArrow()
                .getTextButtonArrow();

        SoftAssert asserts = new SoftAssert();
        asserts.assertTrue(nameArrowDown.contains("  ↓"));
        asserts.assertTrue(nameArrowUp.contains("  ↑"));
        asserts.assertAll();
    }

    @Test
    public void testManagePluginsCheckNameFilter() {
        List<String> listInAlphabeticalOrder = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManagePlugins()
                .sortAlphabeticallyFromAtoZ()
                .getAllPluginNamesInTabUpdates();

        List<String> listReverseAlphabeticalOrder = new ManagePluginsPage(getDriver())
                .changeSortAlphabeticallyFromZtoA()
                .getAllPluginNamesInTabUpdates();

        Collections.sort(listInAlphabeticalOrder);
        Collections.sort(listReverseAlphabeticalOrder);

        Assert.assertEquals(listInAlphabeticalOrder, listReverseAlphabeticalOrder);
    }

    @Test
    public void testManageJenkinsPluginsValidateAllTabs() {
        int numberPluginsUpdates = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManagePlugins()
                .clickButtonUpdates()
                .countPlugins();

        int numberPluginsAvailable = new ManagePluginsPage(getDriver())
                .clickButtonAvailable()
                .countPlugins();

        int numberPluginsInstalled = new ManagePluginsPage(getDriver())
                .clickButtonAvailable()
                .countPlugins();

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotEquals(numberPluginsUpdates, 0);
        asserts.assertNotEquals(numberPluginsAvailable, 0);
        asserts.assertNotEquals(numberPluginsInstalled, 0);
        asserts.assertAll();
    }

    @Ignore
    @Test
    public void testFilterInUpdatesTab(){
        WebDriver driver = getDriver();
        ProjectUtils.Dashboard.Main.ManageJenkins.click(driver);
        ProjectUtils.ManageJenkins.ManagePlugins.click(driver);
        PluginManagerPage PluginManager = new PluginManagerPage(driver);
        WebElement searchField = PluginManager.getSearchField();
        SoftAssert asserts = new SoftAssert();
        for (String s: SEARCH_LIST) {
            PluginManager.searchFieldInput(s);
            List<WebElement> filtredPluginsList = PluginManager.getaList();
            asserts.assertTrue(filtredPluginsList.size() > 0, "empty filter result, search string:"+s);
            for (int i = 0; i < filtredPluginsList.size(); i++) {
                asserts.assertTrue(PluginManagerPage.checkWordsInLine(s, filtredPluginsList.get(i).getText()), "Expected but not found " + s);
            }
        }
        asserts.assertAll();
    }

}
