import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class JuliaCelarscaiaTest extends BaseTest {

    @Test
    public void greatSchoolMainPageTest() {
        getDriver().get("https://www.greatschools.org");
        WebElement searchBox = getDriver().findElement(By.xpath("//*[@class=\"full-width pam search_form_field\"]"));
        WebElement searchButton = getDriver().findElement(By.xpath("//*[@class=\"search-label\"]"));
        searchBox.sendKeys("95660");
        searchButton.click();
        Assert.assertEquals("Schools in 95660, 1-25 | GreatSchools", getDriver().getTitle());

    }


}

