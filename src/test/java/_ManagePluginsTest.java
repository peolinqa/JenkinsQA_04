import model.HomePage;
import model.ManagePluginsPage;
import model.PluginManagerPage;
import org.openqa.selenium.By;
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
    private final By NAME_FILTER = By.xpath("//thead/tr/th/a[text()='Name']");
    private final By NAME_ARROW = By.xpath("//thead/tr/th/a[text()='Name']/span");
    private int getListSize() {
        return getDriver().findElements(By.xpath("//table[@id='plugins']//tbody//tr")).size();
    }
    private static final String[] SEARCH_LIST = {"GitHub"};

    @Test
    public void testManagePluginsCheckNameAndArrowUp() {
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ManagePlugins.click(getDriver());

        SoftAssert asserts = new SoftAssert();
        asserts.assertTrue(getDriver().findElement(NAME_FILTER).isDisplayed());
        asserts.assertEquals(getDriver().findElement(NAME_ARROW).getText(), "  ↓");

        getDriver().findElement(NAME_FILTER).click();
        asserts.assertEquals(getDriver().findElement(NAME_ARROW).getText(),
                "  ↑");
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
        ProjectUtils.Dashboard.Main.ManageJenkins.click(getDriver());
        ProjectUtils.ManageJenkins.ManagePlugins.click(getDriver());

        SoftAssert asserts = new SoftAssert();

        getDriver().findElement(By.xpath("//a[contains(text(),'Updates')]")).click();
        asserts.assertNotEquals(getListSize(), 0);
        getDriver().findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Available')]")).click();
        asserts.assertNotEquals(getListSize(), 0);
        getDriver().findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Installed')]")).click();
        asserts.assertNotEquals(getListSize(), 0);
        getDriver().findElement(By.xpath("//div[@class='tab']//a[contains(text(),'Advanced')]")).click();
        asserts.assertNotEquals(getDriver().findElement(By.className("jenkins-section__title")).getSize(),0);
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
