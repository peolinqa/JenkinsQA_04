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

        WebElement searchBox = getDriver().findElement(By.xpath("//*[@class='full-width pam search_form_field']"));
        searchBox.sendKeys("95660");
        WebElement searchButton = getDriver().findElement(By.xpath("//*[@class='search_form_button']"));
        searchButton.click();

        Assert.assertEquals(getDriver().getTitle(),"Schools in 95660, 1-25 | GreatSchools");

    }

}

