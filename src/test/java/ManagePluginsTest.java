import model.HomePage;
import model.ManagePluginsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.Collections;
import java.util.List;

public class ManagePluginsTest extends BaseTest {

    @Test
    public void testManagePluginsCheckNameAndArrowUp() {
        String nameArrowDown = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManagePlugins()
                .getBtnSortArrowText();

        String nameArrowUp = new ManagePluginsPage(getDriver())
                .clickBtnSortArrow()
                .getBtnSortArrowText();

        SoftAssert asserts = new SoftAssert();
        asserts.assertTrue(nameArrowDown.contains("  ↓"));
        asserts.assertTrue(nameArrowUp.contains("  ↑"));
        asserts.assertAll();
    }

    @Test
    public void testManagePluginsCheckNameFilter() {
        List<String> listInAlphabeticalOrder = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManagePlugins()
                .sortAlphabeticallyFromAtoZ()
                .getAllPluginNamesList();

        List<String> listReverseAlphabeticalOrder = new ManagePluginsPage(getDriver())
                .changeSortAlphabeticallyFromZtoA()
                .getAllPluginNamesList();

        Collections.sort(listInAlphabeticalOrder);
        Collections.sort(listReverseAlphabeticalOrder);

        Assert.assertEquals(listInAlphabeticalOrder, listReverseAlphabeticalOrder);
    }

    @Test
    public void testManageJenkinsPluginsValidateAllTabs() {
        int numberPluginsUpdates = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManagePlugins()
                .clickTabUpdates()
                .countPlugins();

        int numberPluginsAvailable = new ManagePluginsPage(getDriver())
                .clickTabAvailable()
                .countPlugins();

        int numberPluginsInstalled = new ManagePluginsPage(getDriver())
                .clickTabAvailable()
                .countPlugins();

        SoftAssert asserts = new SoftAssert();
        asserts.assertNotEquals(numberPluginsUpdates, 0);
        asserts.assertNotEquals(numberPluginsAvailable, 0);
        asserts.assertNotEquals(numberPluginsInstalled, 0);
        asserts.assertAll();
    }

    @Test
    public void testFilterInUpdatesTab() {
        final String textForFilter = "github";

        List<String> textNamesOfCheckboxes = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManagePlugins()
                .setInputFilterBox(textForFilter).getNamesOfCheckboxesText();

        Assert.assertTrue(textNamesOfCheckboxes.stream()
                .map(String::toLowerCase)
                .allMatch(s -> s.contains(textForFilter)));
    }
}
