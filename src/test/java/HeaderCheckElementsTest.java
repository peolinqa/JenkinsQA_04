import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HeaderCheckElementsTest extends BaseTest {

    private void menuSelector() {

        Actions actions = new Actions(getDriver());
        WebElement hover = getDriver().findElement(By.cssSelector("a[class='model-link inside inverse']"));
        actions.moveToElement(hover).perform();

        getDriver().findElement(By.cssSelector("div[id='menuSelector'][class='inverse']")).click();
    }

    @Test
    public void testCheckMainPage() {

        String expectedUrl = "http://localhost:8080/";

        getDriver().findElement(By.id("jenkins-home-link")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl);
    }

    @Test
    public void testCheckSearchPanel() {

        String expectedResult = "Search for 'TryToFindSomething'";

        getDriver().findElement(By.id("search-box")).sendKeys("TryToFindSomething", Keys.ENTER);
        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='page-body' and @class='clear']/div[@id='main-panel']/h1")
        ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckAdminPage() {

        String expectedURL = "http://localhost:8080/user/admin/";

        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
    }

    @Test
    public void testCheckExpandMenuBuilds() {

        String expectedURL = "http://localhost:8080/user/admin/builds";

        menuSelector();
        getDriver().findElement(By.partialLinkText("Builds")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
    }

    @Test
    public void testCheckExpandMenuConfigure() {

        String expectedURL = "http://localhost:8080/user/admin/configure";

        menuSelector();
        getDriver().findElement(By.partialLinkText("Configure")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
    }

    @Test
    public void testCheckExpandMenuMyViews() {

        String expectedURL = "http://localhost:8080/user/admin/my-views/view/all/";

        menuSelector();
        getDriver().findElement(By.partialLinkText("My Views")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);
    }
}
