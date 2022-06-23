import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class _ConfigureGlobalSecurityTest extends BaseTest {
    private final By GLOBAL_SECURITY_XPATH = By.xpath("//a[@href='configureSecurity']");
    private final By SECURITY_CHAPTERS_CLASS_NAME = By.className("jenkins-section__header");

    private static final List<String> expectedChapters = List.of(
            "Authentication",
            "Security Realm",
            "Authorization",
            "Markup Formatter",
            "Agents",
            "CSRF Protection",
            "Hidden security warnings",
            "API Token",
            "SSH Server");

    private void enterConfigureGlobalSecurity() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getDriver().findElement(By.xpath("//dt[text()='Configure Global Security']")).click();
    }

    @Test
    public void test11ChaptersDisplayedOnGlobalSecurityPage() {
        getDriver().findElement(By.xpath("//*[@title='Manage Jenkins']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(GLOBAL_SECURITY_XPATH)).perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(GLOBAL_SECURITY_XPATH)).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(SECURITY_CHAPTERS_CLASS_NAME)).isDisplayed();

//        action.moveToElement(getDriver().findElement(SECURITY_CHAPTERS_CLASS_NAME)).perform();
        List<WebElement> chapters = getDriver().findElements(SECURITY_CHAPTERS_CLASS_NAME);

        Assert.assertEquals(chapters.size(), 9);
    }

    @Test
    public void testCheckChapters() {
        enterConfigureGlobalSecurity();

        List<WebElement> chapters = getDriver()
                .findElements(By.xpath("//div[@class='jenkins-section__header']"));

        List<String> actualChapters = new ArrayList<>(chapters.size());
        for (WebElement e : chapters) {
            actualChapters.add(e.getText());
        }

        Assert.assertEquals(actualChapters, expectedChapters);
    }

    @Test
    public void testCheckHelpButtonSSHServer() {
        enterConfigureGlobalSecurity();

        WebElement locator = getDriver().findElement(
                By.xpath("//a[@title='Help for feature: SSHD Port']"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", locator);

        new Actions(getDriver())
                .pause(500)
                .moveToElement(locator)
                .perform();

        String actualText = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("tt"))).getText();

        Assert.assertEquals(actualText, "Help for feature: SSHD Port");
    }

}
