import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _NewItemTest extends BaseTest {

    private static final String DESCRIPTION_INPUT = "New Project created by TA";
    private static final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    @Test
    public void testCopyDataFromExistingItemNegative() {
        String ErrorNoSuchJob = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName("NJ3")
                .setProjectTypeFreestyle()
                .setCopyFromName("NJ4")
                .createAndGoToErrorPage()
                .getErrorHeader();

        Assert.assertEquals(ErrorNoSuchJob, "Error");
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        FreestyleConfigPage copyDataFromExistingItemToNew = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName("NJ")
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .setDescription(DESCRIPTION_INPUT)
                .clickGithubProjectCheckbox()
                .setGithubUrl(URL_INPUT)
                .saveConfigAndGoToFreestyleProject()
                .clickDashboardButton()
                .getSideMenu()
                .clickNewItem()
                .setProjectName("NJ2")
                .setProjectTypeFreestyle()
                .setCopyFromName("NJ")
                .clickOkAndGoToConfig();

        Assert.assertEquals(copyDataFromExistingItemToNew.getDescription(), DESCRIPTION_INPUT);
        Assert.assertEquals(copyDataFromExistingItemToNew.getGithubUrl(), URL_INPUT);
    }

    @Test
    public void testCheckItemLabelStyle() {
        NewItemPage<Object> itemLabelStyle = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        List<String> listFontWeigh = itemLabelStyle.getFontWeightForEachProjectLabel();
        Assert.assertTrue(listFontWeigh.stream().allMatch(value -> value.equals("700")));

        List<String> listFontSize = itemLabelStyle.getFontSizeForEachProjectLabel();
        Assert.assertTrue(listFontSize.stream().allMatch(value -> value.equals("16px")));

        List<String> listColor = itemLabelStyle.getColorForEachProjectLabel();
        Assert.assertTrue(listColor.stream().allMatch(value -> value.equals("rgba(51, 51, 51, 1)")));
    }

    @Test
    public void testCheckDescriptionStyle() {
        NewItemPage<Object> descriptionStyle = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        List<String> listFontWeigh = descriptionStyle.getFontWeightForEachDescription();
        Assert.assertTrue(listFontWeigh.stream().allMatch(value -> value.equals("400")));

        List<String> listFontSize = descriptionStyle.getFontSizeForEachDescription();
        Assert.assertTrue(listFontSize.stream().allMatch(value -> value.equals("14px")));

        List<String> listColor = descriptionStyle.getColorForEachDescription();
        Assert.assertTrue(listColor.stream().allMatch(value -> value.equals("rgba(51, 51, 51, 1)")));
    }

    @Test
    public void testCheckIconAvailabilityAndDisplaying() {
        NewItemPage<Object> iconAvailability = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        List<Boolean> listImageIsDisplayed = iconAvailability.projectTypeImageIsDisplayed();
        Assert.assertTrue(listImageIsDisplayed.stream().allMatch(value -> value.equals(true)));

        List<Boolean> listImageIsEnabled = iconAvailability.projectTypeImageIsEnabled();
        Assert.assertTrue(listImageIsEnabled.stream().allMatch(value -> value.equals(true)));
    }

    @Test
    public void testCheckTextLabelForItem() {
        final List<String> expectedItemLabelText = List.of("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        List<String> actualItemLabelText = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem().getTextForEachProjectLabel();

        Assert.assertEquals(actualItemLabelText, expectedItemLabelText);
    }

    @Test
    public void testErrorMessageNameRequiredDisplaying() {
        String NameRequiredErrorMessage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .clickCreateButton()
                .getErrorNameRequiredText();

        Assert.assertEquals(NameRequiredErrorMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCheckBreadcrumbs() {
        NewItemPage<Object> checkBreadcrumbs = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem();

        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbs(0), "Dashboard");
        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbs(2), "All");
    }

    @Test
    public void testEnterSeveralSpaces() {
        ErrorPage errorPage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName("     ")
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .getErrorPageIfPresent();

        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test
    public void testCheckIncorrectCharacters() {
        char[] characterName = {'!', '@', '#', '$', '%', '^', '&', '*', '[', ']', '\\', '|', ';', ':', '/', '?', '<', '>'};
        for (char ch : characterName) {

            String alertMessage = new HomePage(getDriver())
                    .getSideMenu()
                    .clickNewItem()
                    .setProjectName(Character.toString(ch))
                    .getNameErrorText();

            String expectedResult = String.format("» ‘%s’ is an unsafe character", ch);

            Assert.assertEquals(alertMessage, expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test
    public void testInputDot() {
        String alertMessage = new HomePage(getDriver())
                .getSideMenu()
                .clickNewItem()
                .setProjectName(".")
                .getNameErrorText();

        Assert.assertEquals(alertMessage, "» “.” is not an allowed name");
    }
}
