package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class FabrianaTest extends BaseTest {

    private static final String URL_SITE = "http://uitestingplayground.com/";


    @Test
    public void testGoToAnotherPageOnTheSite() {
        getDriver().get(URL_SITE);
        String titleSite = getDriver().getTitle();
        Assert.assertEquals(titleSite, "UI Test Automation Playground");

        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        getDriver().findElement(By.xpath("//a[contains(@href,'/textinput')]")).click();
        Assert.assertEquals("Text Input", "Text Input");

        getDriver().findElement(By.xpath("//button[contains (@class, 'btn-primary')]")).isDisplayed();
        String nextUrl = getDriver().getCurrentUrl();
        String nextPageTitle = getDriver().getTitle();

        Assert.assertEquals(nextUrl, "http://uitestingplayground.com/textinput");
        Assert.assertEquals(nextPageTitle, "Text Input");
    }

}