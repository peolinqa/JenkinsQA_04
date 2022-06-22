import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class _ConfigureSystemTest extends BaseTest {

    private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");
    private static final By SYSTEM_MESSAGE_FORM = By.name("system_message");
    private static final By MAIN_PAGE_SYSTEM_MESSAGE = By.id("systemmessage");
    private static final By DASHBOARD = By.id("jenkins-home-link");

    private void goToConfigureSystemPage() {
        getDriver().findElement(By.xpath("//span[contains(text(),'Manage Jenkins')]")).click();
        getDriver().findElement(By.xpath("//dt[contains(text(),'Configure System')]")).click();
    }

    @Test
    public void testConfigureSystemMessageTestPositive() {

        final String randomSystemMessage = TestUtils.getRandomStr();

        goToConfigureSystemPage();

        TestUtils.clearAndSend(getDriver(), SYSTEM_MESSAGE_FORM, randomSystemMessage);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        getWait20().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        getDriver().findElement(DASHBOARD).click();

        Assert.assertEquals(getDriver().findElement(MAIN_PAGE_SYSTEM_MESSAGE).getText(), randomSystemMessage);
    }
}
