package qa_old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.BaseUtils;

@Ignore
public class AnastasiaTest extends BaseTest {

    @Test
    public void AnaLaraTest()   {

        String baseUrl = "https://portal.311.nyc.gov/";
        String expectedTitle = "Home  · NYC311";
        String actualTitle;

        getDriver().get(baseUrl);

        actualTitle = getDriver().getTitle();
        System.out.println(actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle);

    }
}


