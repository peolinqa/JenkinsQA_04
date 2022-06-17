import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class CreateUserTest extends BaseTest {
    private static final By XPATH_MANAGE_JENKINS = By.xpath("//span[text()='Manage Jenkins']");
    private static final By XPATH_MANAGE_USERS = By.xpath("//dt[text()='Manage Users']");
    private static final By XPATH_CREATE_USER = By.xpath("//span[text()='Create User']");
    private static final By XPATH_CREATE_USER_BUTTON = By.xpath("//button[text()='Create User']");
    private static final By XPATH_ERRORS = By.xpath("//div[@class='form-content']/div");

    @Test
    public void testCreateUserEmptyFields108011() {
        List<String> expectedErrorsText = List.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");
        goOnCreateUserPage();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_CREATE_USER_BUTTON));
        getDriver().findElement(XPATH_CREATE_USER_BUTTON).click();

        List<String> actualErrorsText = new ArrayList<>();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_ERRORS));
        var errorElements = getDriver().findElements(XPATH_ERRORS);
        for (var error : errorElements) {
            actualErrorsText.add(error.getText());
        }
        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }

    private void goOnCreateUserPage() {
        getDriver().findElement(XPATH_MANAGE_JENKINS).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_MANAGE_USERS));
        getDriver().findElement(XPATH_MANAGE_USERS).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(XPATH_CREATE_USER));
        getDriver().findElement(XPATH_CREATE_USER).click();
    }
}
