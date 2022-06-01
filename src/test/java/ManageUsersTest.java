import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ManageUsersTest extends BaseTest {

    @Test
    public void testUserCanCreateNewUser() {

        getDriver().get("http://localhost:8080/securityRealm/addUser");

        String username = "viktorp";
        String fullname = "Viktor P";

        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.name("password1")).sendKeys("123456ABC");
        getDriver().findElement(By.name("password2")).sendKeys("123456ABC");
        getDriver().findElement(By.name("fullname")).sendKeys(fullname);
        getDriver().findElement(By.name("email")).sendKeys("testemail.@gmail.com");

        getDriver().findElement(By.id("yui-gen1-button")).click();

        List<WebElement> users = getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));

        for (WebElement user : users) {
            if (user.getText().contains(username) && user.getText().contains(fullname)) {

                Assert.assertTrue(user.isDisplayed());
            }
        }
    }
}
