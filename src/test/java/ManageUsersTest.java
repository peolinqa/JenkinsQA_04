import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.*;

public class ManageUsersTest extends BaseTest {

    private static final String USER_NAME = "viktorp";
    private static final String PASSWORD = "123456ABC";
    private static final String FULL_NAME = "Viktor P";
    private static final String EMAIL = "testemail.@gmail.com";

    public WebElement userName() {
        return getDriver().findElement(By.id("username"));
    }

    public WebElement password() {
        return getDriver().findElement(By.name("password1"));
    }

    public WebElement passwordConfirm() {
        return getDriver().findElement(By.name("password2"));
    }

    public WebElement fullName() {
        return getDriver().findElement(By.name("fullname"));
    }

    public WebElement emailAddress() {
        return getDriver().findElement(By.name("email"));
    }

    public WebElement buttonCreateUser() {
        return getDriver().findElement(By.id("yui-gen1-button"));
    }

    public void urlPageCreateUser() {
        getDriver().get("http://localhost:8080/securityRealm/addUser");
    }

    public void fillOutFieldsCreateUser(String userName, String password, String fullName, String email) {

        userName().sendKeys(userName);
        password().sendKeys(password);
        passwordConfirm().sendKeys(password);
        fullName().sendKeys(fullName);
        emailAddress().sendKeys(email);
    }

    public List<WebElement> createListWithErrorMessages() {

        return getDriver().findElements(By.xpath("//div[@class='form-content']/div"));
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testUserNameFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(userName().isDisplayed());
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testPasswordFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(password().isDisplayed());
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testPasswordConfirmFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(passwordConfirm().isDisplayed());
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testFullNameFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(fullName().isDisplayed());
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testEmailAddressFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(emailAddress().isDisplayed());
    }

    @Test(groups = {"Elements_are_displayed"})
    public void testButtonCreateUserIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(buttonCreateUser().isDisplayed());
    }

    @Test(dependsOnGroups = {"Elements_are_displayed"})
    public void testUserCanCreateNewUser() {

        urlPageCreateUser();
        fillOutFieldsCreateUser(USER_NAME, PASSWORD, FULL_NAME, EMAIL);
        buttonCreateUser().click();

        List<WebElement> users = getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));
        for (WebElement user : users) {
            if (user.getText().contains(USER_NAME) && user.getText().contains(FULL_NAME)) {

                Assert.assertTrue(user.isDisplayed());
            }
        }
    }

    @Test(dependsOnMethods = {"testUserCanCreateNewUser"})
    public void testUserCanDeleteUser() {

        getDriver().get("http://localhost:8080/securityRealm/");
        getDriver().findElement(By.xpath(String.format("//a[contains(@href, '%s/delete')]", USER_NAME))).click();
        getDriver().findElement(By.xpath("//button[@id='yui-gen1-button']")).click();

        List<WebElement> users = getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));
        for (WebElement user : users) {

            Assert.assertFalse(user.getText().contains(USER_NAME) && user.getText().contains(FULL_NAME));
        }
    }

    @Test(dependsOnGroups = {"Elements_are_displayed"})
    public void testUsernameFieldDoesNotAcceptSpecialCharacters() {

        urlPageCreateUser();
        fillOutFieldsCreateUser(USER_NAME, PASSWORD, FULL_NAME, EMAIL);

        List<String> specialCharacters = new ArrayList<>(Arrays.asList(
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "+", ";", ":", "?", "=",
                "~", "`", "[", "{", "]", "}", "|", "/", "'", ",", ".", "*", "\"", "\\", " "));

        for (String specialCharacter : specialCharacters) {
            List<String> namesWithSpecialCharacter = new ArrayList<>(Arrays.asList(
                    specialCharacter + USER_NAME, USER_NAME + specialCharacter,
                    "vik".concat(specialCharacter).concat("torp")));

            for (String nameWithSpecialCharacter : namesWithSpecialCharacter) {
                userName().clear();
                userName().sendKeys(nameWithSpecialCharacter);
                buttonCreateUser().click();

                for (WebElement errorMessage : createListWithErrorMessages()) {
                    Assert.assertEquals(errorMessage.getText(),
                            "User name must only contain alphanumeric characters, underscore and dash");
                }
            }
        }
    }

    @Test(dependsOnGroups = {"Elements_are_displayed"},
            dependsOnMethods = {"testUsernameFieldDoesNotAcceptSpecialCharacters"})
    public void testErrorMessagesHaveValidCssValues() {

        urlPageCreateUser();
        fillOutFieldsCreateUser(USER_NAME.concat("*"), PASSWORD, FULL_NAME, EMAIL);
        buttonCreateUser().click();

        List<String> cssValues = new ArrayList<>(Arrays.asList(
                "color", "font-weight", "padding-left", "min-height", "line-height",
                "background-image",
                "background-position", "background-repeat", "background-size"));

        List<String> expectedResults = new ArrayList<>(Arrays.asList(
                "rgba(204, 0, 0, 1)", "700", "20px", "16px", "16px",
                "error.svg",
                "0% 0%", "no-repeat", "16px 16px"));


        for (WebElement errorMessageElement : createListWithErrorMessages()) {
            int index = 0;

            for (String expectedResult : expectedResults) {
                String actualResult = errorMessageElement.getCssValue(cssValues.get(index));
                if (actualResult.contains("http://localhost:8080")) {

                    Assert.assertTrue(actualResult.contains(expectedResults.get(index)));
                } else {

                    Assert.assertEquals(actualResult, expectedResult);
                }
                index++;
            }
        }
    }
}