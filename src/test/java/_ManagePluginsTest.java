import model.HomePage;
import model.ManagePluginsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import java.util.Collections;
import java.util.List;

public class _ManagePluginsTest extends BaseTest {

    @Test
    public void testManagePluginsCheckNameAndArrowUp() {
        String nameArrowDown = new HomePage(getDriver())
                .getSideMenu()
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
                .getSideMenu()
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
                .getSideMenu()
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

    @Test
    public void testFilterInUpdatesTab() {
        final String textForFilter = "github";

        List<String> textNamesOfCheckboxes = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManagePlugins()
                .searchFieldInput(textForFilter).getTextNamesOfCheckboxes();

        Assert.assertTrue(textNamesOfCheckboxes.stream()
                .map(String::toLowerCase)
                .allMatch(s -> s.contains(textForFilter)));
    }
}
