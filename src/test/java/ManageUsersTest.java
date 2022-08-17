import model.CreateUserPage;
import model.HomePage;
import model.ManageUsersPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

public class ManageUsersTest extends BaseTest {

    private static final String USER_NAME = "viktorp";
    private static final String PASSWORD = "123456ABC";
    private static final String FULL_NAME = "Viktor P";
    private static final String NEW_USER_FULL_NAME = "Michael";
    private static final String EMAIL = "testemail.@gmail.com";

    @Test
    public void testCreateNewUser() {
        Set<String> usersListBefore = new TreeSet<>();
        Set<String> usersListAfter = new TreeSet<>();

        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .addUsersToList(usersListBefore)
                .getSideMenu()
                .clickMenuCreateUser()
                .setUserName(USER_NAME)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new ManageUsersPage(getDriver()))
                .addUsersToList(usersListAfter);

        usersListBefore.add(USER_NAME.concat("\n").concat(FULL_NAME));

        Assert.assertEquals(usersListAfter, usersListBefore);
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testEditUserFullName() {
        String userName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .clickUserConfigure(USER_NAME)
                .clearFullName()
                .setFullName(NEW_USER_FULL_NAME)
                .clickSaveButton()
                .getUserName();

        Assert.assertEquals(userName, NEW_USER_FULL_NAME);
    }

    @Test(dependsOnMethods = "testEditUserFullName")
    public void testDeleteUser() {
        Set<String> usersListBefore = new TreeSet<>();
        Set<String> usersListAfter = new TreeSet<>();

        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .addUsersToList(usersListBefore)
                .clickIconDeleteUser(USER_NAME)
                .getSideMenu()
                .confirmDeleteUser()
                .addUsersToList(usersListAfter);

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
                    .clickMenuManageJenkins()
                    .clickManageUsers()
                    .getSideMenu()
                    .clickMenuCreateUser()
                    .setUserName(USER_NAME.concat(character))
                    .setPassword(PASSWORD)
                    .setConfirmPassword(PASSWORD)
                    .setFullName(FULL_NAME)
                    .setEmailAddress(EMAIL)
                    .clickCreateUserButton(new CreateUserPage(getDriver()))
                    .getErrorMessageText();

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
                .clickMenuManageJenkins()
                .clickManageUsers()
                .getSideMenu()
                .clickMenuCreateUser()
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
        final Set<String> expectedErrorMessagesText = Set.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");

        Set<String> actualErrorMessagesText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .getSideMenu()
                .clickMenuCreateUser()
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList();

        Assert.assertEquals(actualErrorMessagesText, expectedErrorMessagesText);
    }

    @Test
    public void testCheckUsernameEqualFullName() {
        String fullName = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .getSideMenu()
                .clickMenuCreateUser()
                .setUserName(USER_NAME)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getFullNameAttributeValue();

        Assert.assertEquals(fullName, USER_NAME);
    }

    @Test
    public void testFillOutOnlyUserNameField() {
        final Set<String> expectedErrorsText = Set.of("Password is required", "Invalid e-mail address");

        Set<String> actualErrorsText = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageUsers()
                .getSideMenu()
                .clickMenuCreateUser()
                .setUserName(USER_NAME)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList();

        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }
}
