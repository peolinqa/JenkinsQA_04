import model.CreateUserPage;
import model.HomePage;
import model.UserConfigurePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
    private static final String USER_NAME_XPATH = "username"; //+

    private static final By BUTTON_SUBMIT_TYPE = By.id("yui-gen1-button");
    private static final By ERROR_MESSAGES = By.className("error");
    private static final By ALL_USERS = By.xpath("//table[@id='people']/tbody/tr");
    private static final By FULL_NAME_XPATH = By.name("fullname");

    private boolean displayedWebElement(String webElement) {
        return getDriver().getPageSource().contains(webElement);
    }

    @Test
    public void testUserCanCreateNewUser() {

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME_FIRST)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton();

        for (WebElement user : TestUtils.getList(getDriver(), ALL_USERS)) {
            if (user.getText().contains(USER_NAME_FIRST) && user.getText().contains(FULL_NAME)) {

                Assert.assertTrue(user.isDisplayed());
            }
        }
    }

    @Test(dependsOnMethods = "testUserCanCreateNewUser")
    public void testEditUserFullName() {

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers();
        getDriver().findElement(By.xpath("//a[@href='user/".concat(USER_NAME_FIRST.toLowerCase()).concat("/configure']")))
                .click();

        String userName = new UserConfigurePage(getDriver())
                .clearFullName()
                .setFullName(NEW_USER_FULL_NAME)
                .clickSaveButton()
                .getUserName();

        Assert.assertEquals(userName, NEW_USER_FULL_NAME);
    }

    @Test(dependsOnMethods = "testEditUserFullName")
    public void testUserCanDeleteUser() {

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers();
        getDriver().findElement(By.xpath(String.format("//a[contains(@href, '%s/delete')]", USER_NAME_FIRST))).click();
        getDriver().findElement(BUTTON_SUBMIT_TYPE).click();

        for (WebElement user : TestUtils.getList(getDriver(), ALL_USERS)) {

            Assert.assertFalse(user.getText().contains(USER_NAME_FIRST));
        }
    }

    @Test(dependsOnMethods = "testUserCanCreateNewUser")
    public void testUsernameFieldDoesNotAcceptSpecialCharacters() {

        SoftAssert asserts = new SoftAssert();

        final String expectedResult = "User name must only contain alphanumeric characters, underscore and dash";

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName("")
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL);

        List<String> specialCharacters = new ArrayList<>(Arrays.asList(
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "+", ";", ":", "?", "=",
                "~", "`", "[", "{", "]", "}", "|", "/", "'", ",", ".", "*", "\"", "\\", " "));

        for (String specialCharacter : specialCharacters) {
            List<String> namesWithSpecialCharacter = new ArrayList<>(Arrays.asList(
                    specialCharacter + USER_NAME_FIRST, USER_NAME_FIRST + specialCharacter,
                    "vik".concat(specialCharacter).concat("torp")));

            for (String nameWithSpecialCharacter : namesWithSpecialCharacter) {
                if (!displayedWebElement(USER_NAME_XPATH)) {
                    getDriver().navigate().back();
                    getDriver().navigate().refresh();
                    new CreateUserPage(getDriver())
                            .clearUserName()
                            .clearPassword()
                            .clearConfirmPassword()
                            .clearFullName()
                            .clearEmailAddress()
                            .setUserName("")
                            .setPassword(PASSWORD)
                            .setConfirmPassword(PASSWORD)
                            .setFullName(FULL_NAME)
                            .setEmailAddress(EMAIL);
                }

                new CreateUserPage(getDriver())
                        .clearUserName()
                        .setUserName(nameWithSpecialCharacter)
                        .clickCreateUserButton();

                if (displayedWebElement(USER_NAME_XPATH)) {
                    for (WebElement errorMessage : TestUtils.getList(getDriver(), ERROR_MESSAGES)) {

                        asserts.assertEquals(errorMessage.getText(), expectedResult);
                    }
                } else {
                    for (WebElement user : TestUtils.getList(getDriver(), ALL_USERS)) {
                        if (user.getText().contains(nameWithSpecialCharacter)) {

                            asserts.assertEquals(nameWithSpecialCharacter, expectedResult);

                            break;
                        }
                    }
                }
            }
        }
        asserts.assertAll();
    }

    @Test(dependsOnMethods = "testUsernameFieldDoesNotAcceptSpecialCharacters")
    public void testErrorMessagesHaveValidCssValues() {

        SoftAssert asserts = new SoftAssert();

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser()
                .setUserName(USER_NAME_FIRST)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setEmailAddress(EMAIL)
                .clickCreateUserButton();

        List<String> cssValues = new ArrayList<>(Arrays.asList(
                "color", "font-weight", "padding-left", "min-height", "line-height",
                "background-image",
                "background-position", "background-repeat", "background-size"));

        List<String> expectedResults = new ArrayList<>(Arrays.asList(
                "rgba(204, 0, 0, 1)", "700", "20px", "16px", "16px",
                "error.svg",
                "0% 0%", "no-repeat", "16px 16px"));

        for (WebElement errorMessageElement : TestUtils.getList(getDriver(), ERROR_MESSAGES)) {
            int index = 0;

            for (String expectedResult : expectedResults) {
                String actualResult = errorMessageElement.getCssValue(cssValues.get(index));
                if (actualResult.contains("url")) {

                    asserts.assertTrue(actualResult.contains(expectedResults.get(index)));
                } else {

                    asserts.assertEquals(actualResult, expectedResult);
                }
                index++;
            }
        }
        asserts.assertAll();
    }

    @Test
    public void testCreateUserEmptyFields() {
        final List<String> expectedErrorsText = List.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");

        new HomePage(getDriver())
                .clickManageJenkins()
                .clickManageUsers()
                .clickCreateUser();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_SUBMIT_TYPE)).click();

        List<WebElement> actualErrors = TestUtils.getList(getDriver(), ERROR_MESSAGES);
        List<String> actualErrorsText = new ArrayList<>();
        for (WebElement error : actualErrors) {
            actualErrorsText.add(error.getText());
        }

        Assert.assertEquals(actualErrorsText.size(), 4);
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
                .clickCreateUserButton();

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