import model.HomePage;
import model.ItemConfigPage;
import model.NewItemPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import static runner.ProjectUtils.ProjectType.Freestyle;

public class _NewItemTest extends BaseTest {

    private static final String DESCRIPTION_INPUT = "New Project created by TA";
    private static final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";

    @Test
    public void testCopyDataFromExistingItemNegative() {
        String ErrorNoSuchJob = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName("NJ3")
                .setProjectType(Freestyle)
                .setCopyFromName("NJ4")
                .createAndGoToErrorPage()
                .getErrorHeader();

        Assert.assertEquals(ErrorNoSuchJob, "Error");
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        ItemConfigPage copyDataFromExistingItemToNew = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName("NJ")
                .setProjectType(Freestyle)
                .createAndGoToConfig()
                .setDescription(DESCRIPTION_INPUT)
                .clickGithubProjectCheckbox()
                .setGithubUrl(URL_INPUT)
                .saveConfigAndGoToProject()
                .clickDashboardButton()
                .clickNewItem()
                .setProjectName("NJ2")
                .setProjectType(Freestyle)
                .setCopyFromName("NJ")
                .createAndGoToConfig();

        Assert.assertEquals(copyDataFromExistingItemToNew.getDescription(), DESCRIPTION_INPUT);
        Assert.assertEquals(copyDataFromExistingItemToNew.getGithubUrl(), URL_INPUT);
    }

    @Test
    public void testCheckItemLabelStyle() {
        NewItemPage itemLabelStyle = new HomePage(getDriver()).clickNewItem();

        for (WebElement value : itemLabelStyle.getProjectTypeLabels()) {
            Assert.assertEquals(value.getCssValue("font-weight"), "700");
            Assert.assertEquals(value.getCssValue("font-size"), "16px");
            Assert.assertEquals(value.getCssValue("color"), "rgba(51, 51, 51, 1)");
        }
    }

    @Test
    public void testCheckDescriptionStyle() {
        NewItemPage descriptionStyle = new HomePage(getDriver()).clickNewItem();

        for (WebElement value : descriptionStyle.getDescriptionStyle()) {
            Assert.assertEquals(value.getCssValue("font-weight"), "400");
            Assert.assertEquals(value.getCssValue("font-size"), "14px");
            Assert.assertEquals(value.getCssValue("color"), "rgba(51, 51, 51, 1)");
        }
    }

    @Test
    public void testCheckIconAvailabilityAndDisplaying() {
        NewItemPage iconAvailability = new HomePage(getDriver()).clickNewItem();

        for (WebElement icon : iconAvailability.getProjectTypeImage()) {
            Assert.assertTrue(icon.isDisplayed());
            Assert.assertTrue(icon.isEnabled());
        }
    }

    @Test
    public void testCheckLabelDisplayingOnNewItemPage() {
        String[] expectedItemLabelText = {
                "Freestyle project",
                "Pipeline",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder",
                };

        NewItemPage itemLabelText = new HomePage(getDriver()).clickNewItem();

        for(int i = 0; i < expectedItemLabelText.length; i++){
            Assert.assertEquals(itemLabelText.getProjectTypeLabels().get(i).getText(), expectedItemLabelText[i]);
        }
    }

    @Test
    public void testErrorMessageNameRequiredDisplaying() {
        String NameRequiredErrorMessage = new HomePage(getDriver())
                .clickNewItem()
                .clickCreateButton()
                .getErrorNameRequiredText();

        Assert.assertEquals(NameRequiredErrorMessage, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCheckBreadcrumbs() {
        NewItemPage checkBreadcrumbs = new HomePage(getDriver()).clickNewItem();

        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbs(0), "Dashboard");
        Assert.assertEquals(checkBreadcrumbs.getBreadCrumbs(2), "All");
    }

    @Test
    public void testEnterSeveralSpaces() {
        String NoNameSpecified = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName("     ")
                .setProjectType(Freestyle)
                .createAndGoToErrorPage()
                .getErrorMessage();

        Assert.assertEquals(NoNameSpecified, "No name is specified");
    }

}