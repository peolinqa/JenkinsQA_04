import model.CreateUserPage;
import model.HomePage;
import model.ManageUsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.*;

public class _ManageUsersTest extends BaseTest {

    private static final String USER_NAME_FIRST = "viktorp";
    private static final String USER_NAME_SECOND = "Balthazarrr";
    private static final String PASSWORD = "123456ABC";
    private static final String FULL_NAME = "Viktor P";
    private static final String NEW_USER_FULL_NAME = "Michael";
    private static final String EMAIL = "testemail.@gmail.com";

    private static final By ERROR_MESSAGES = By.className("error");
    private static final By FULL_NAME_XPATH = By.name("fullname");

    @Test
    public void testUserCanCreateNewUser() {

        Set<String> usersListBefore = new TreeSet<>();
        Set<String> usersListAfter = new TreeSet<>();

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .fillUsersList(usersListBefore)
                .clickCreateUser()
                .setUserName(USER_NAME_FIRST)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton(new ManageUsersPage(getDriver()))
                .fillUsersList(usersListAfter);

        usersListBefore.add(USER_NAME_FIRST.concat("\n").concat(FULL_NAME));

        Assert.assertEquals(usersListAfter, usersListBefore);
    }

    @Test(dependsOnMethods = "testUserCanCreateNewUser")
    public void testEditUserFullName() {

        String userName = new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickUserConfigure(USER_NAME_FIRST)
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
                .clickUserDelete(USER_NAME_FIRST)
                .clickYesButton()
                .fillUsersList(usersListAfter);

        usersListBefore.remove(USER_NAME_FIRST.concat("\n").concat(NEW_USER_FULL_NAME));

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
                .setUserName(USER_NAME_FIRST.concat(specialCharacter))
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
                .setUserName(USER_NAME_FIRST.concat("*"))
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
        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME_SECOND)
                .setPassword("")
                .setConfirmPassword("")
                .setFullName("")
                .setEmailAddress("")
                .clickCreateUserButton(new CreateUserPage(getDriver()));

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(FULL_NAME_XPATH));

        String actualResultFullName = getDriver().findElement(FULL_NAME_XPATH).getAttribute("value");
        Assert.assertEquals(actualResultFullName, USER_NAME_SECOND);

        final List<String> expectedErrorsText = List.of("Password is required", "Invalid e-mail address");
        List<WebElement> actualErrors = TestUtils.getList(getDriver(), ERROR_MESSAGES);
        List<String> actualErrorsText = new ArrayList<>();
        for (WebElement error : actualErrors) {
            actualErrorsText.add(error.getText());
        }

        Assert.assertEquals(actualErrorsText.size(), 2);
        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }
}