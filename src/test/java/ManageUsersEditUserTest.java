import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class ManageUsersEditUserTest extends BaseTest {
    private static final String NAME = "Mike";
    private static final String NEW_NAME = "Michael";
    private static final By DELETE_USER = By.xpath("//a[@href='/user/mike/delete']");
    private static final By YES_BUTTON = By.id("yui-gen1-button");
    private static final By NEW_NAME_XPATH = By.xpath("//div[@id='main-panel']/h1");
    private static final String USER_ID = "Jenkins User ID: Mike";
    private static final By SETTINGS_SYMBOL = By.xpath("//a[@href='user/mike/configure']");

    private void createUser() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.partialLinkText("Manage Users")).click();
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();
        getDriver().findElement(By.id("username")).sendKeys("Mike");
        getDriver().findElement(By.name("password1")).sendKeys("7456");
        getDriver().findElement(By.name("password2")).sendKeys("7456");
        getDriver().findElement(By.name("email")).sendKeys("568@mail.ru");
        getDriver().findElement(YES_BUTTON).click();
    }

    private void editUserName() {
        getDriver().findElement(SETTINGS_SYMBOL).click();
        getDriver().findElement(By.xpath("//input[@value='".concat(NAME).concat("']"))).clear();
        getDriver().findElement(By.xpath("//input[@name='_.fullName']")).sendKeys(NEW_NAME);
        getDriver().findElement(By.id("yui-gen2-button")).click();
    }

    private void deleteUerFromTopMenu() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='/securityRealm/user/".concat(NAME.toLowerCase()).concat("/']")))).build().perform();
        action.moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(DELETE_USER));
        getDriver().findElement(DELETE_USER).click();
        getDriver().findElement(YES_BUTTON).click();
    }

    @Test
    public void testEditUserName() {

        createUser();

        getWait20().until(ExpectedConditions.visibilityOfElementLocated(SETTINGS_SYMBOL));

        editUserName();

        String actualName = getDriver().findElement(NEW_NAME_XPATH).getText();
        String actualFont = getDriver().findElement(NEW_NAME_XPATH).getCssValue("font-weight");
        String actualUserId = getDriver().findElement(By.xpath("//div[text()='".concat(USER_ID).concat("']"))).getText();

        Assert.assertEquals(actualName, NEW_NAME);
        Assert.assertEquals(actualFont, "700");
        Assert.assertEquals(actualUserId, USER_ID);

        deleteUerFromTopMenu();
    }
}
