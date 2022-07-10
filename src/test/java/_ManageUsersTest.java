import model.CreateUserPage;
import model.HomePage;
import model.ManageUsersPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
                .clickManageJenkins()
                .clickManageUsers()
                .fillUsersList(usersListBefore)
                .clickUserDelete(USER_NAME)
                .clickYesButton()
                .fillUsersList(usersListAfter);

        usersListBefore.remove(USER_NAME.concat("\n").concat(NEW_USER_FULL_NAME));

        Assert.assertEquals(usersListAfter, usersListBefore);
    }

    @DataProvider(name = "special_characters")
    public Object[][] specialCharactersMethod() {
        return new Object[][]{
                {"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"("}, {")"}, {"+"}, {";"}, {":"}, {"?"}, {"="},
                {"~"}, {"`"}, {"["}, {"{"}, {"]"}, {"}"}, {"|"}, {"/"}, {"'"}, {","}, {"."}, {"*"}, {"\""}, {"\\"}, {" "}
        };
    }

    @Test(dataProvider = "special_characters")
    public void testUsernameFieldDoesNotAcceptSpecialCharacters(String specialCharacter) {

        final String expectedResult = "User name must only contain alphanumeric characters, underscore and dash";

        String errorMessage = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME.concat(specialCharacter))
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessage();

        Assert.assertEquals(errorMessage, expectedResult);
    }

    @DataProvider(name = "css_values")
    public Object[][] cssValuesMethod() {
        return new Object[][]{
                {"color", "rgba(204, 0, 0, 1)"}, {"font-weight", "700"}, {"padding-left", "20px"},
                {"min-height", "16px"}, {"line-height", "16px"}, {"background-position", "0% 0%"},
                {"background-repeat", "no-repeat"}, {"background-size", "16px 16px"}
        };
    }

    @Test(dataProvider = "css_values")
    public void testErrorMessagesHaveValidCssValues(String cssProperty, String expectedResult) {

        String cssValue = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME.concat("*"))
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getCssValue(cssProperty);

        Assert.assertEquals(cssValue, expectedResult);
    }

    @Test
    public void testCreateUserEmptyFields() {

        Set<String> actualErrorsText = new TreeSet<>();
        final Set<String> expectedErrorsText = Set.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList(actualErrorsText);

        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }

    @Test
    public void testCheckValueInUsernameEqualValueFromFullName() {
        String fullName = new HomePage(getDriver())
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

        Set<String> actualErrorsText = new TreeSet<>();
        final Set<String> expectedErrorsText = Set.of("Password is required", "Invalid e-mail address");

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME)
                .clickCreateUserButton(new CreateUserPage(getDriver()))
                .getErrorMessagesList(actualErrorsText);

        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }
}