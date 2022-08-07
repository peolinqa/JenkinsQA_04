import model.HomePage;
import model.PeoplePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _PeopleTest extends BaseTest {

    private static final String NEW_USER_DESCRIPTION = TestUtils.getRandomStr();

    @Test
    public void testCheckFunctionalityIconsSML() {
        PeoplePage newPeoplePage = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople();

        Assert.assertEquals(newPeoplePage.setSizeM().getHeightLastCommitActivityFirstCell(), 40);
        Assert.assertEquals(newPeoplePage.setSizeL().getHeightLastCommitActivityFirstCell(), 50);
        Assert.assertEquals(newPeoplePage.setSizeS().getHeightLastCommitActivityFirstCell(), 34);
    }

    @Test
    public void testAddDescriptionForUser() {
        String newDescription = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuPeople()
                .goToUserStatusPage()
                .clickEditDescriptionButton()
                .clearDescriptionTextArea()
                .addUserDescription(NEW_USER_DESCRIPTION)
                .clickDescriptionSaveButton()
                .getUserDescriptionText();

        Assert.assertEquals(newDescription, NEW_USER_DESCRIPTION);
    }
}
