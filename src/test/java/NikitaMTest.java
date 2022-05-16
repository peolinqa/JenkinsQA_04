import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NikitaMTest extends BaseTest {

    @Test
    public void nikitaMTestW3schools() throws InterruptedException{

        getDriver().get("https://www.w3schools.com/");

        Assert.assertEquals(getDriver().getTitle(), "W3Schools Online Web Tutorials");

        WebElement searchBox = getDriver().findElement(By.id("search2"));
        WebElement searchButton = getDriver().findElement(By.id("learntocode_searchbtn"));

        searchBox.sendKeys("Java Tutorial");

        Thread.sleep(1000);

        searchButton.click();

        Assert.assertEquals(getDriver().getTitle(), "Java Tutorial");
    }
}