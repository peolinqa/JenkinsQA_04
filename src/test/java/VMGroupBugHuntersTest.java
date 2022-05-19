import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VMGroupBugHuntersTest extends BaseTest {

    @Ignore
    @Test
    public void veranikaMalazhavayaTest() {
        getDriver().get("https://coderoll.net/");

        WebElement searchBox = getDriver().findElement(By.name("story"));
        searchBox.sendKeys("java");
        getDriver().findElement(By.className("ri-search-2-line")).click();
        searchBox = getDriver().findElement(By.id("searchinput"));

        Assert.assertEquals(searchBox.getAttribute("value"), "java");

    }
}
