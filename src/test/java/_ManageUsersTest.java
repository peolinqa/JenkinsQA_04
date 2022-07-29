import model.CreateUserPage;
import model.HomePage;
import model.ManageUsersPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

public class _ManageUsersTest extends BaseTest {

    private static final String USER_NAME = "viktorp";
    private static final String PASSWORD = "123456ABC";
    private static final String FULL_NAME = "Viktor P";
    private static final String NEW_USER_FULL_NAME = "Michael";
    private static final String EMAIL = "testemail.@gmail.com";

    @Test
    public void testUserCanCreateNewUser() {
        Set<String> usersListBefore = new TreeSet<>();
        Set<String> usersListAfter = new TreeSet<>();

        new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .fillUsersList(usersListBefore)
                .clickCreateUser()
                .setUserName(USER_NAME)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new ManageUsersPage(getDriver()))
                .fillUsersList(usersListAfter);

        usersListBefore.add(USER_NAME.concat("\n").concat(FULL_NAME));

        Assert.assertEquals(usersListAfter, usersListBefore);
    }

    @Test(dependsOnMethods = "testUserCanCreateNewUser")
    public void testEditUserFullName() {
        String userName = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickUserConfigure(USER_NAME)
                .clearFullName()
                .setFullName(NEW_USER_FULL_NAME)
                .clickSaveButton()
                .getUserName();

        Assert.assertEquals(userName, NEW_USER_FULL_NAME);
    }

    @Test(dependsOnMethods = "testEditUserFullName")
    public void testUserCanDeleteUser() {
        Set<String> usersListBefore = new TreeSet<>();
        Set<String> usersListAfter = new TreeSet<>();

        new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .fillUsersList(usersListBefore)
                .clickUserDelete(USER_NAME)
                .clickYesButton()
                .fillUsersList(usersListAfter);

        usersListBefore.remove(USER_NAME.concat("\n").concat(NEW_USER_FULL_NAME));

        Assert.assertEquals(usersListAfter, usersListBefore);
    }

    @Test
    public void testUsernameFieldDoesNotAcceptSpecialCharacters() {
        final String expectedResult = "User name must only contain alphanumeric characters, underscore and dash";
        List<String> specialCharacters = List.of(
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "+", ";", ":", "?", "=",
                "~", "`", "[", "]", "{", "}", "|", "/", "'", ",", ".", "*", "\"", "\\", " ");

        for (String character : specialCharacters) {
            String errorMessage = new HomePage(getDriver())
                    .getSideMenu()
                    .clickManageJenkins()
                    .clickManageUsers()
                    .clickCreateUser()
                    .setUserName(USER_NAME.concat(character))
                    .setPassword(PASSWORD)
                    .setConfirmPassword(PASSWORD)
                    .setFullName(FULL_NAME)
                    .setEmailAddress(EMAIL)
                    .clickCreateUserButton(new CreateUserPage(getDriver()))
                    .getErrorMessage();

            Assert.assertEquals(errorMessage, expectedResult);
        }
    }

    @Test
    public void testErrorMessagesHaveValidCssValues() {
        final List<String> expectedResult = List.of(
                "rgba(204, 0, 0, 1)", "700", "20px", "16px", "16px", "0% 0%",
                "no-repeat", "16px 16px");

        List<String> actualResult = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME.concat("*"))
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getCssValuesList();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateUserEmptyFields() {
        final Set<String> expectedErrorsText = Set.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");

        Set<String> actualErrorsText = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList();

        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }

    @Test
    public void testCheckValueInUsernameEqualValueFromFullName() {
        String fullName = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getAttributeFullName();

        Assert.assertEquals(fullName, USER_NAME);
    }

    @Test
    public void testCheckErrorMessagesIfFillOutOnlyUserNameField() {
        final Set<String> expectedErrorsText = Set.of("Password is required", "Invalid e-mail address");

        Set<String> actualErrorsText = new HomePage(getDriver())
                .getSideMenu()
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList();

        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }
}