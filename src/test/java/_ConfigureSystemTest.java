import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _ConfigureSystemTest extends BaseTest {

    private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SYSTEM_MESSAGE_FORM = By.name("system_message");
    private static final By MAIN_PAGE_SYSTEM_MESSAGE = By.id("systemmessage");
    private static final By DASHBOARD = By.id("jenkins-home-link");

    private String createRandomText() {
        String nameSubstrate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        return RandomStringUtils.random(20, nameSubstrate);
    }

    private void goToConfigureSystemPage() {
        getDriver().findElement(By.xpath("//span[contains(text(),'Manage Jenkins')]")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Configure System')]")).click();
    }

    @Test
    public void testConfigureSystemMessageTestPositive() {

        String randomSystemMessage = createRandomText();

        goToConfigureSystemPage();

        getDriver().findElement(SYSTEM_MESSAGE_FORM).clear();
        getDriver().findElement(SYSTEM_MESSAGE_FORM).sendKeys(randomSystemMessage);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        getWait20().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        getDriver().findElement(DASHBOARD).click();

        Assert.assertEquals(getDriver().findElement(MAIN_PAGE_SYSTEM_MESSAGE).getText(), randomSystemMessage);
    }
}
