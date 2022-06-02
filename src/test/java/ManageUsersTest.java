import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ManageUsersTest extends BaseTest {

    private static final String USER_NAME = "viktorp";
    private static final String FULL_NAME = "Viktor P";

    @Test
    public void testUserCanCreateNewUser() {

        getDriver().get("http://localhost:8080/securityRealm/addUser");

        getDriver().findElement(By.id("username")).sendKeys(USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys("123456ABC");
        getDriver().findElement(By.name("password2")).sendKeys("123456ABC");
        getDriver().findElement(By.name("fullname")).sendKeys(FULL_NAME);
        getDriver().findElement(By.name("email")).sendKeys("testemail.@gmail.com");

        getDriver().findElement(By.id("yui-gen1-button")).click();

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
}
