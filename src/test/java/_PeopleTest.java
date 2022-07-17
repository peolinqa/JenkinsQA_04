import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _PeopleTest extends BaseTest {

    private static final String NEW_USER_DESCRIPTION = TestUtils.getRandomStr();

    @Test
    public void checkFunctionalityIconsSMLTest() {

        int sizeM = new HomePage(getDriver())
                .clickPeople()
                .setSizeM()
                .heightLastCommitActivityFirstCell();

        int sizeL = new HomePage(getDriver())
                .clickPeople()
                .setSizeL()
                .heightLastCommitActivityFirstCell();

        int sizeS = new HomePage(getDriver())
                .clickPeople()
                .setSizeS()
                .heightLastCommitActivityFirstCell();

        Assert.assertEquals(sizeM, 40);
        Assert.assertEquals(sizeL, 50);
        Assert.assertEquals(sizeS, 34);
    }

    @Ignore
    @Test
    public void addDescriptionForUserTest() {

        String newDescription = new HomePage(getDriver())
                .clickPeople()
                .goToUserStatusPage()
                .clearUserDescription()
                .addUserDescription(NEW_USER_DESCRIPTION)
                .getUserDescriptionText();

        Assert.assertEquals(newDescription, NEW_USER_DESCRIPTION);
    }
}
