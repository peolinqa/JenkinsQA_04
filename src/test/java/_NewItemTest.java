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
                .clickMenuNewItem()
                .setProjectName("NJ3")
                .setFreestyleProjectType()
                .setCopyFromName("NJ4")
                .clickBtnOkAndGoToErrorPage()
                .getErrorHeaderText();

        Assert.assertEquals(ErrorNoSuchJob, "Error");
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        FreestyleConfigPage copyDataFromExistingItemToNew = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName("NJ")
                .setFreestyleProjectType()
                .clickOkAndGoToConfig()
                .setDescription(DESCRIPTION_INPUT)
                .clickGithubProjectCheckbox()
                .setGithubUrl(URL_INPUT)
                .saveProjectConfiguration()
                .clickLinkDashboard()
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName("NJ2")
                .setFreestyleProjectType()
                .setCopyFromName("NJ")
                .clickOkAndGoToConfig();

        Assert.assertEquals(copyDataFromExistingItemToNew.getDescription(), DESCRIPTION_INPUT);
        Assert.assertEquals(copyDataFromExistingItemToNew.getGithubUrl(), URL_INPUT);
    }

    @Test
    public void testCheckItemLabelStyle() {
        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .assertTrue((object) -> object.getFontWeightForEachProjectLabel().stream().allMatch(value -> value.equals("700")))
                .assertTrue((object) -> object.getFontSizeForEachProjectLabel().stream().allMatch(value -> value.equals("16px")))
                .assertTrue((object) -> object.getColorForEachProjectLabel().stream().allMatch(value -> value.equals("rgba(51, 51, 51, 1)")));
    }

    @Test
    public void testCheckDescriptionStyle() {
        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .assertTrue((object) -> object.getFontWeightForEachDescription().stream().allMatch(value -> value.equals("400")))
                .assertTrue((object) -> object.getFontSizeForEachDescription().stream().allMatch(value -> value.equals("14px")))
                .assertTrue((object) -> object.getColorForEachDescription().stream().allMatch(value -> value.equals("rgba(51, 51, 51, 1)")));
    }

    @Test
    public void testCheckIconAvailabilityAndDisplaying() {
        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .assertTrue((object) -> object.isTypeProjectImageDisplayed().stream().allMatch(value -> value.equals(true)))
                .assertTrue((object) -> object.isTypeProjectImageEnabled().stream().allMatch(value -> value.equals(true)));
    }

    @Test
    public void testCheckTextLabelForItem() {
        final List<String> expectedItemLabelText = List.of("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        List<String> actualItemLabelText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem().getTextForEachProjectLabel();

        Assert.assertEquals(actualItemLabelText, expectedItemLabelText);
    }

    @Test
    public void testErrorMessageNameRequiredDisplaying() {
        String NameRequiredErrorMessage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .clickOkButton()
                .getErrorMsgNameRequiredText();

        Assert.assertEquals(NameRequiredErrorMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCheckBreadcrumbs() {
        NewItemPage<Object> checkBreadcrumbs = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem();

        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbsItemText(0), "Dashboard");
        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbsItemText(2), "All");
    }

    @Test
    public void testEnterSeveralSpaces() {
        ErrorPage errorPage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName("     ")
                .setFreestyleProjectType()
                .clickOkAndGoToConfig()
                .getErrorPageIfPresent();

        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessageText(), "No name is specified");
    }

    @Test
    public void testCheckIncorrectCharacters() {
        char[] characterName = {'!', '@', '#', '$', '%', '^', '&', '*', '[', ']', '\\', '|', ';', ':', '/', '?', '<', '>'};
        for (char ch : characterName) {

            String alertMessage = new HomePage(getDriver())
                    .getSideMenu()
                    .clickMenuNewItem()
                    .setProjectName(Character.toString(ch))
                    .getErrorMsgNameInvalidText();

            String expectedResult = String.format("» ‘%s’ is an unsafe character", ch);

            Assert.assertEquals(alertMessage, expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test
    public void testInputDot() {
        String alertMessage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuNewItem()
                .setProjectName(".")
                .getErrorMsgNameInvalidText();

        Assert.assertEquals(alertMessage, "» “.” is not an allowed name");
    }
}
