import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class _ConfigureGlobalSecurityTest extends BaseTest {
    private final By GLOBAL_SECURITY_XPATH = By.xpath("//a[@href='configureSecurity']");
    private final By SECURITY_CHAPTERS_CLASS_NAME = By.className("jenkins-section__header");

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
}
