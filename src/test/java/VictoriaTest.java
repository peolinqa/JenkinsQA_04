import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VictoriaTest extends BaseTest {
    @Test
    public void CourseraTest() {
        getDriver().get("https://www.coursera.org/");
        WebElement searchBox = getDriver().findElement(By.xpath("//div[@class='react-autosuggest__container']//input"));
        searchBox.sendKeys("Test automation");
        getDriver().findElement(By.xpath("//header/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[1]/div[1]/button[2]/div[1]/*[1]")).click();
        WebElement search = getDriver().findElement(By.xpath("//header/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
        Assert.assertEquals(search.getAttribute("value"), "Test automation");
    }
}


