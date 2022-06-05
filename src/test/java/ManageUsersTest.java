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

    public WebElement passwordOne() {
        return getDriver().findElement(By.name("password1"));
    }

    public WebElement passwordTwo() {
        return getDriver().findElement(By.name("password2"));
    }

    public WebElement fullName() {
        return getDriver().findElement(By.name("fullname"));
    }

    public WebElement email() {
        return getDriver().findElement(By.name("email"));
    }

    public WebElement createUserButtonSubmit() {
        return getDriver().findElement(By.id("yui-gen1-button"));
    }

    public void urlPageCreateUser() {
        getDriver().get("http://localhost:8080/securityRealm/addUser");
    }

    public void fillOutFieldsCreateUser(String userName, String password, String fullName, String email) {

        userName().sendKeys(userName);
        passwordOne().sendKeys(password);
        passwordTwo().sendKeys(password);
        fullName().sendKeys(fullName);
        email().sendKeys(email);
    }

    @Test
    public void testUserNameFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(userName().isDisplayed());
    }

    @Test
    public void testPasswordOneFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(passwordOne().isDisplayed());
    }

    @Test
    public void testPasswordTwoFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(passwordTwo().isDisplayed());
    }

    @Test
    public void testFullNameFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(fullName().isDisplayed());
    }

    @Test
    public void testEmailFieldIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(email().isDisplayed());
    }

    @Test
    public void testCreateUserButtonSubmitIsDisplayed() {
        urlPageCreateUser();
        Assert.assertTrue(createUserButtonSubmit().isDisplayed());
    }

    @Test(dependsOnMethods = {"testUserNameFieldIsDisplayed", "testPasswordOneFieldIsDisplayed",
            "testPasswordTwoFieldIsDisplayed", "testFullNameFieldIsDisplayed", "testEmailFieldIsDisplayed",
            "testCreateUserButtonSubmitIsDisplayed"})
    public void testUserCanCreateNewUser() {

        urlPageCreateUser();
        fillOutFieldsCreateUser(USER_NAME, PASSWORD, FULL_NAME, EMAIL);
        createUserButtonSubmit().click();

        List<WebElement> users = getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));
        for (WebElement user : users) {
            if (user.getText().contains(USER_NAME) && user.getText().contains(FULL_NAME)) {

                Assert.assertTrue(user.isDisplayed());
            }
        }
    }

    @Test(dependsOnMethods = {"testUserCanCreateNewUser"}, priority = 1)
    public void testUserCanDeleteUser() {

        getDriver().get("http://localhost:8080/securityRealm/");
        getDriver().findElement(By.xpath(String.format("//a[contains(@href, '%s/delete')]", USER_NAME))).click();
        getDriver().findElement(By.xpath("//button[@id='yui-gen1-button']")).click();

        List<WebElement> users = getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));

        for (WebElement user : users) {

            Assert.assertFalse(user.getText().contains(USER_NAME) && user.getText().contains(FULL_NAME));
        }
    }

    @Test(dependsOnMethods = {"testUserCanCreateNewUser"}, priority = 2)
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
                createUserButtonSubmit().click();

                Assert.assertEquals(getDriver().findElement(
                                By.xpath("//div[@id='main-panel']//div[@class='error']")).getText(),
                        "User name must only contain alphanumeric characters, underscore and dash");
            }
        }
    }
}
